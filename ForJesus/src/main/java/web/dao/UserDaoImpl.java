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
        Query querydel = entityManager.createQuery("delete  from User  " + " where id =:userid");
        querydel.setParameter("userid", id);



    }
}