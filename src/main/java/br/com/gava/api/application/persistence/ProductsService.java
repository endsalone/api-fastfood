package br.com.gava.api.application.persistence;

import br.com.gava.api.common.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.gava.api.domain.entities.Products;
import br.com.gava.api.domain.products.IProductsRepository;

@Service
public class ProductsService implements IProductsRepository {

  private final IProductsJpaRepository repository;

  ProductsService(IProductsJpaRepository repository) {
    this.repository = repository;
  }

  @Override
  public Page<Products> findAll(Pagination pagination) {
    PageRequest pageRequest = pagination.pageRequest();
    Page<Products> productsPage = this.repository.findAll(pageRequest);

    return productsPage;
  }

  @Override
  public Page<Products> findByNameOrDescription(Pagination pagination) {
    PageRequest pageRequest = pagination.pageRequest();
    Page<Products> productsPage = this.repository.findByNameContainsOrDescriptionContains(pagination.getSearch(), pagination.getSearch(), pageRequest);

    return productsPage;
  }

  @Override
  public Products findById(long id) {
    return this.repository.findById(id);
  }

}
