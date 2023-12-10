package ra.academy.quanly_category_product.model.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.academy.quanly_category_product.model.entity.Category;
import ra.academy.quanly_category_product.model.repository.CategoryRepository;

import java.util.List;
@Service
public class CategoryServiceIpml implements CategoryService{
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category saveOrUpdate(Category category) {
        return categoryRepository.saveOrUpdate(category);
    }

    @Override
    public Category findById(Integer id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        categoryRepository.delete(id);
    }
}
