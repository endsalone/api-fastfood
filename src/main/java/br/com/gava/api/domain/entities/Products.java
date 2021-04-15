package br.com.gava.api.domain.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "products")
public class Products {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "name", length = 45, nullable = false)
  String name;

  @Column(name = "description", length = 512, nullable = true)
  String description;

  @Column(name = "image", length = 1200, nullable = true)
  String image;

  @Column(name = "price", nullable = false)
  int price;

}
