package br.com.gava.api.domain.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "combos")
public class Combos {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  @Column(name = "name", length = 45, nullable = false)
  String name;

  @Column(name = "description", length = 500, nullable = false)
  String description;

  @Column(name = "price", nullable = false)
  int price;

  @ManyToMany
  @JoinTable(
    name = "combos_products",
    joinColumns = { @JoinColumn(name = "id_products") },
    inverseJoinColumns = { @JoinColumn(name = "id_combos") }
  )
  private Set<Products> products = new HashSet<Products>();
}
