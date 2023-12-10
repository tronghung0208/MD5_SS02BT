package ra.academy.quanly_category_product.model.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.academy.quanly_category_product.model.entity.Category;
import ra.academy.quanly_category_product.model.entity.Product;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();
        Session session = sessionFactory.openSession();
        try {
            productList = session.createQuery("from Product ").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return productList;
    }

    @Override
    public Product saveOrUpdate(Product product) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                if (product.getId() != null) {
                    session.merge(product);
                } else {
                    session.save(product);
                }
                transaction.commit();
                return product;
            } catch (HibernateException e) {
                e.printStackTrace();
                transaction.rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product findById(Integer id) {
        List<Product> productList = findAll();
        for (Product product : productList
        ) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Product productDelete = findById(id);
                if (productDelete != null) {
                    session.delete(productDelete);
                    transaction.commit();
                }
            } catch (HibernateException e) {
                e.printStackTrace();
                transaction.rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
