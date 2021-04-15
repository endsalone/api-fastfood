package br.com.gava.api.domain.products;

import br.com.gava.api.domain.products.ProductsDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("unit")
public class ProductsDtoTest {

  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  void mustInstantiateProductsDto() {
    //given
    Long id = Long.parseLong("2");
    String name = "Coca-cola - 600 ml";
    String description = "Refrigerante Coca-cola 600 ml";
    String image = "http://...";
    int price = 600;


    //when
    ProductsDto product = new ProductsDto(id, name, description, price, image);
    Set<ConstraintViolation<ProductsDto>> violations = validator.validate(product);

    //then
    assertThat(product.getName()).isEqualTo(name);
    assertThat(product.getDescription()).isEqualTo(description);
    assertThat(product.getPrice()).isEqualTo(price);
    assertThat(product.getImage()).isEqualTo(image);
    assertTrue(violations.isEmpty());
  }

  @Test
  void mustCheckErrorsWhenValidateTheProductsDto() {

    // Given
    Long id = Long.parseLong("2");
    String name = "";
    String description = "";
    String image = "http://...";
    int price = 600;


    //when
    ProductsDto product = new ProductsDto(id, name, description, price, image);
    Set<ConstraintViolation<ProductsDto>> violations = validator.validate(product);

    //then
    assertFalse(violations.isEmpty());
    assertThat(violations.size()).isEqualTo(2);
  }
}
