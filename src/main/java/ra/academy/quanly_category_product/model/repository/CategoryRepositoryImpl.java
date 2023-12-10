package ra.academy.quanly_category_product.model.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.academy.quanly_category_product.model.entity.Category;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Category> findAll() {
        List<Category> categoryList = new ArrayList<>();
        Session session = sessionFactory.openSession();
        try {
            categoryList = session.createQuery("from Category ").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return categoryList;
    }

    @Override
    public Category saveOrUpdate(Category category) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            try{
                if (category.getId()!=null){
                    session.merge(category);
                }else {
                    session.save(category);
                }
                transaction.commit();
                return category;
            }catch (HibernateException e){
                e.printStackTrace();
                transaction.rollback();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Category findById(Integer id) {
        List<Category> list=findAll();
        if (!list.isEmpty()){
            for (Category category:list
                 ) {
                if (category.getId().equals(id)){

                    return category;
                }
            }
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Category categoryToDelete = findById(id);
                if (categoryToDelete != null) {
                    session.delete(categoryToDelete);
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
