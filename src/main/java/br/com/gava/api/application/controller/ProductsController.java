package br.com.gava.api.application.controller;

import br.com.gava.api.application.persistence.ProductsService;
import br.com.gava.api.common.HalList;
import br.com.gava.api.common.Pagination;
import br.com.gava.api.domain.entities.Products;
import br.com.gava.api.domain.products.IProductsRepository;
import br.com.gava.api.domain.products.ProductsDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/products", produces = "application/hal+json")
public class ProductsController extends HalList {

  private final IProductsRepository service;
  private final ModelMapper modelMapper = new ModelMapper();

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

    List<ProductsDto> productsDto = productsPage
      .getContent()
      .stream()
      .map(product -> modelMapper.map(product, ProductsDto.class))
      .collect(Collectors.toList());

    List<EntityModel<ProductsDto>> entityProducts = productsDto
      .stream()
      .map(product -> EntityModel
        .of(product)
        .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getProduct(product.getId())).withSelfRel())
      )
      .collect(Collectors.toList());

    Link selfUrl = WebMvcLinkBuilder
      .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).listProducts(pagination, search))
      .slash(pagination.toString())
      .withSelfRel();


    CollectionModel<EntityModel<ProductsDto>> response = CollectionModel
      .of(entityProducts)
      .add((selfUrl));
    return ResponseEntity.status(200).body(response);
  }

  @Override
  public ResponseEntity<?> getItem(@PathVariable long id) {
    return ResponseEntity.status(200).body(id);
  }

}
