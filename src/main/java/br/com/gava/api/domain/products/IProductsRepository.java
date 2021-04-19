package br.com.gava.api.domain.products;

import br.com.gava.api.common.Pagination;
import br.com.gava.api.domain.entities.Products;
import org.springframework.data.domain.Page;

public interface IProductsRepository {
  Page<Products> findAll(Pagination pagination);
  Page<Products> findByNameOrDescription(Pagination pagination);
  Products findById(long id);
}
