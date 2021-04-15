package br.com.gava.api.domain.products;

import br.com.gava.api.domain.entities.Products;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IProductsResponse extends PagingAndSortingRepository<Products, Long> {
}
