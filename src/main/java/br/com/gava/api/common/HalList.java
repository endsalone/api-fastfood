package br.com.gava.api.common;

import br.com.gava.api.domain.entities.IEntity;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;


public abstract class HalList<T extends IEntity>  {

  @Setter
  private Page<?> pageItems;
  @Setter
  private Class<T> dto;

  private final ModelMapper modelMapper = new ModelMapper();

  @GetMapping
  public abstract ResponseEntity<?> getAll(Pagination pagination);

  @GetMapping("/{id}")
  public abstract ResponseEntity<?> getItem(@PathVariable long id);

  protected CollectionModel<EntityModel<T>> listLinks(Pagination pagination) {

    List<EntityModel<T>> entityModel = this.pageItems
      .getContent()
      .stream()
      .map(itemToMapper -> modelMapper.map(itemToMapper, this.dto))
      .map(itemEntityModel -> EntityModel
        .of(itemEntityModel)
        .add(
          WebMvcLinkBuilder.linkTo(getItemMethod(itemEntityModel)).withSelfRel()
        )
      )
      .collect(Collectors.toList());

    Link selfUrl = WebMvcLinkBuilder
      .linkTo(getAllMethod(pagination, pagination.getSearch()))
      .slash(pagination.toString())
      .withSelfRel();

    return CollectionModel
      .of(entityModel)
      .add(selfUrl);
  }

  private ResponseEntity getAllMethod(Pagination pagination, String search) {
    return WebMvcLinkBuilder.methodOn(this.getClass()).getAll(pagination);
  }

  private ResponseEntity getItemMethod(T itemEntityModel) {
    return WebMvcLinkBuilder.methodOn(this.getClass()).getItem(itemEntityModel.getId());
  }

  protected <U> EntityModel<T> itemLink(U item) {
    T itemEntityModel = modelMapper.map(item, this.dto);
    return EntityModel
      .of(itemEntityModel)
      .add(
        WebMvcLinkBuilder.linkTo(getItemMethod(itemEntityModel)).withSelfRel()
      );
  }
}
