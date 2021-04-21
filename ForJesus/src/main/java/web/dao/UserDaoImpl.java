package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository

public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> showAllUsers() {
        TypedQuery<User> query = entityManager.createQuery("select u from User u", User.class);
        return query.getResultList();


    }


    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }


    @Override
    public void deleteUser(long id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public User getUserById(long id) {
        return entityManager.createQuery("select u from User u  " + " where u.id =?1", User.class)
                .setParameter(1, id)
                .getSingleResult();

    }

    @Override
    public User getUserByName(String name) {
        return entityManager.createQuery("select u from User u  " + " where u.name =?1", User.class)
                .setParameter(1, name)
                .getSingleResult();
    }
}