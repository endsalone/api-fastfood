package br.com.gava.api.domain.products;

import br.com.gava.api.domain.entities.IEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Relation(collectionRelation = "products")
public class ProductsDto extends RepresentationModel<ProductsDto> implements Serializable, IEntity {

  private long id;

  @NotBlank(message = "Nome não pode ser vazio")
  private String name;

  @NotBlank(message = "Descrição não pode ser vazio")
  private String description;

  @NotNull(message = "Preço não pode ser nulo")
  private int price;

  private String image;

}
