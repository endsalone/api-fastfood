package br.com.gava.api.common;

import br.com.gava.api.domain.entities.IEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;


public abstract class HalList<T>  {

  private Page<?> pageItems;
  private T dto;

  private final ModelMapper modelMapper = new ModelMapper();

  @GetMapping
  public abstract ResponseEntity<?> getAll(
    Pagination pagination,
    @RequestParam(required = false) String search
  );

  @GetMapping("/{id}")
  public abstract ResponseEntity<?> getItem(@PathVariable long id);

  protected void setPageItems(Page<?> pageItems) {
    this.pageItems = pageItems;
  }

  protected void setDto(T dto) {
    this.dto = dto;
  }

  private void makeLinks(Class dto) {
    List<?> listDto = this.pageItems
      .getContent()
      .stream()
      .map(item -> modelMapper.map(item, this.dto.getClass()))
      .collect(Collectors.toList());

//    List<EntityModel<?>> entityModel = listDto
//      .stream()
//      .map(item -> EntityModel
//        .of(item)
//        .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getItem(item.getId())).withSelfRel())
//      )
//      .collect(Collectors.toList());
  }
}
