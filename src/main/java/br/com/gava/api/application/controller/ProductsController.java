package br.com.gava.api.application.controller;

import br.com.gava.api.application.persistence.ProductsService;
import br.com.gava.api.common.HalList;
import br.com.gava.api.common.Pagination;
import br.com.gava.api.domain.entities.Products;
import br.com.gava.api.domain.products.IProductsRepository;
import br.com.gava.api.domain.products.ProductsDto;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/products", produces = "application/hal+json")
public class ProductsController extends HalList<ProductsDto> {

  private final IProductsRepository service;

  ProductsController(ProductsService service) {
    this.service = service;
  }

  @Override
  public ResponseEntity<?> getAll(
    Pagination pagination,
    @RequestParam(required = false) String search
  ) {

    Page<Products> productsPage = search == null
      ? this.service.findAll(pagination)
      : this.service.findByNameOrDescription(search, pagination);

    this.setPageItems(productsPage);
    this.setDto(ProductsDto.class);
    CollectionModel<EntityModel<ProductsDto>> response = this.makeLinks(pagination, search);

    return ResponseEntity.status(200).body(response);
  }

  @Override
  public ResponseEntity<?> getItem(@PathVariable long id) {
    return ResponseEntity.status(200).body("id");
  }

}
