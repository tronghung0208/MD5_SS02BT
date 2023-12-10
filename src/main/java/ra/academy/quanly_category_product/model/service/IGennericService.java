package ra.academy.quanly_category_product.model.service;

import ra.academy.quanly_category_product.model.entity.Category;

import java.util.List;

public interface IGennericService <T,ID>{
    List<T> findAll();

    T saveOrUpdate(T t);
T findById(ID id);
    void delete(ID id);
}
