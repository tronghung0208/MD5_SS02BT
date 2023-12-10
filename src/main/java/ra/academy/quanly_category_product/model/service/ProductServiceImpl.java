package ra.academy.quanly_category_product.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.academy.quanly_category_product.model.entity.Category;
import ra.academy.quanly_category_product.model.entity.Product;
import ra.academy.quanly_category_product.model.repository.ProductRepository;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product saveOrUpdate(Product product) {
        return productRepository.saveOrUpdate(product);
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
productRepository.delete(id);
    }
}
