package com.hibtest.hib_jpa_test.dao;

import com.hibtest.hib_jpa_test.HibernateUtil;
import com.hibtest.hib_jpa_test.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDao {
    public void saveUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception ex) {
            if(transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public User getUserById(long id) {
        User user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            user = session.get(User.class, id);
        }
        return user;
    }

    public int updateUser(User user) {
        Transaction transaction = null;
        int status = 0;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            status = 1;
        } catch (Exception ex) {
            status = -1;
            if(transaction != null)
                transaction.rollback();
            ex.printStackTrace();
        }
        return status;
    }

    public void deleteUser(long id) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            User user = session.get(User.class, id);
            transaction = session.beginTransaction();
            session.remove(user);
            transaction.commit();
        } catch (Exception ex) {
            if(transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    //HQL is similar to SQL but operates on Hibernate's managed objects

    public List<User> getAllUsers() {
        List<User> users = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("FROM User", User.class);
            users = query.list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return users;
    }

    public List<User> getUsersByName(String name, int pageNo, int pageSize) {
        List<User> users = null;
        String hql = "from User where name like :name";
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("name", "%" + name + "%");
            query.setFirstResult(pageNo);
            query.setMaxResults(pageSize);
            users = query.list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return users;
    }

    public List<User> getUsersByNamedQuery(String name) {
        List<User> users = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.getNamedQuery("findByName");
            query.setParameter("name", "%"+name+"%");
            users = query.list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return users;
    }

    public List<User> getUsersByNameUsingSQL(String name) {
        List<User> users = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createNativeQuery("select * from users where username like :uname", User.class);
            query.setParameter("uname", "%"+name+"%");
            users = query.list();
        }
        return users;
    }
}
