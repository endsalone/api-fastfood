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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;


public abstract class HalList<T extends IEntity>  {

  @Setter
  private Page<?> pageItems;
  @Setter
  private Class<T> dto;

  private final ModelMapper modelMapper = new ModelMapper();

  @GetMapping
  public abstract ResponseEntity<?> getAll(
    Pagination pagination,
    @RequestParam(required = false) String search
  );

  @GetMapping("/{id}")
  public abstract ResponseEntity<?> getItem(@PathVariable long id);

  protected CollectionModel<EntityModel<T>> makeLinks(Pagination pagination, String search) {
    List<T> listDto = this.pageItems
      .getContent()
      .stream()
      .map(item -> modelMapper.map(item, this.dto))
      .collect(Collectors.toList());

    List<EntityModel<T>> entityModel = listDto
      .stream()
      .map(item -> EntityModel
        .of(item)
        .add()
        .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getItem(item.getId())).withSelfRel())
      )
      .collect(Collectors.toList());

    Link selfUrl = WebMvcLinkBuilder
      .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAll(pagination, search))
      .slash(pagination.toString())
      .withSelfRel();

    return CollectionModel
      .of(entityModel)
      .add((selfUrl));
  }
}
