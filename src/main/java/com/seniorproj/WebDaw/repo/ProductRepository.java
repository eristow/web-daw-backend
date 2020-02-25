package com.seniorproj.WebDaw.repo;

import com.seniorproj.WebDaw.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, String> {
    @Override
    void delete(Product deleted);
}
