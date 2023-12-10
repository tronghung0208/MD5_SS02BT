package ra.academy.quanly_category_product.model.repository;

import ra.academy.quanly_category_product.model.entity.Category;

import java.util.List;

public interface IgennericRepository<T, ID> {
    List<T> findAll();

    T saveOrUpdate(T t);

T findById(ID id);

    void delete(ID id);
}
