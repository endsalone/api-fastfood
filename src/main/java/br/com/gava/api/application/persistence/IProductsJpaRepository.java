package br.com.gava.api.application.persistence;

import br.com.gava.api.domain.entities.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IProductsJpaRepository extends JpaRepository<Products, Long>, PagingAndSortingRepository<Products, Long> {
  Page<Products> findByNameContainsOrDescriptionContains(String name, String description, Pageable pageable);
  Products findById(long id);
}
