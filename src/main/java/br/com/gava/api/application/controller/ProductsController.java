package br.com.gava.api.application.controller;

import br.com.gava.api.application.persistence.ProductsService;
import br.com.gava.api.common.HalList;
import br.com.gava.api.common.Pagination;
import br.com.gava.api.domain.entities.Products;
import br.com.gava.api.domain.error.out.ErrorResponse;
import br.com.gava.api.domain.products.IProductsRepository;
import br.com.gava.api.domain.products.ProductsDto;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

@RestController
@RequestMapping(value = "/products", produces = "application/hal+json")
public class ProductsController extends HalList<ProductsDto> {

  private final IProductsRepository service;

  ProductsController(ProductsService service) {
    this.service = service;
  }

  @Override
  public ResponseEntity<?> getAll(Pagination pagination) {

    Page<Products> productsPage = pagination.getSearch() == null
      ? this.service.findAll(pagination)
      : this.service.findByNameOrDescription(pagination);

    this.setPageItems(productsPage);
    this.setDto(ProductsDto.class);
    CollectionModel<EntityModel<ProductsDto>> response = this.listLinks(pagination);

    return ResponseEntity.status(200).body(response);
  }

  @Override
  public ResponseEntity<?> getItem(@PathVariable long id) {
    Products product = this.service.findById(id);

    if(product == null) {
      ErrorResponse errorResponse = new ErrorResponse();
      errorResponse.setCode(404);
      errorResponse.setErrors(new ArrayList<String>());
      return ResponseEntity.status(404).body(errorResponse);
    }

    this.setDto(ProductsDto.class);
    EntityModel<ProductsDto> entityModel = this.itemLink(product);

    return ResponseEntity.status(200).body(entityModel);
  }

  @PostMapping
  public ResponseEntity<?> getItem(@Valid ProductsDto product) {
    return ResponseEntity.status(200).body(null);
  }
}
