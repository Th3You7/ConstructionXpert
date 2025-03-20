package app.com.constructionxpert.dao;

import app.com.constructionxpert.config.HibernateConfig;
import app.com.constructionxpert.dtos.UserDTO;
import app.com.constructionxpert.entity.User;
import app.com.constructionxpert.exception.DuplicateEmailException;
import app.com.constructionxpert.exception.UserEmailNoExistException;
import app.com.constructionxpert.mapper.UserMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Set;
import java.util.stream.Collectors;

public class UserDAO {

    public UserDTO getUser(String email, String password) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()){
            if(!isEmailExist(session, email)){
                throw new UserEmailNoExistException(email + " is not exist");
            }
            User user = session.createQuery("FROM User WHERE email = :email AND password = :password", User.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .uniqueResult();
            return UserMapper.INSTANCE.toDTO(user);
        }
    }

    public UserDTO getUserByEmail(String email) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()){
            User user = session.createQuery("From User WHERE email = :email", User.class)
                    .setParameter("email", email)
                    .uniqueResult();

            return UserMapper.INSTANCE.toDTO(user);
        }
    }

    public boolean isEmailExist(Session session, String email) {
        return session.createQuery("select count(*) from User u where u.email = :email", Long.class)
                .setParameter("email", email).uniqueResult() > 0;
    }

    public User getUserById(long id) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()){
            return session.find(User.class, id);
        }
    }

    public void saveUser(User user) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()){
            Transaction tx = session.beginTransaction();
            if(isEmailExist(session, user.getEmail())) {
                tx.rollback();
                throw new DuplicateEmailException("Email " + user.getEmail() + " already exists");
            }
            session.persist(user);
            tx.commit();
        }
    }

    public void updateUser(User user, boolean isTheSameEmail) {
        try(Session session = HibernateConfig.getSessionFactory().openSession()){
            Transaction tx = session.beginTransaction();
            if(isEmailExist(session, user.getEmail()) && !isTheSameEmail) {
                tx.rollback();
                throw new DuplicateEmailException("Email " + user.getEmail() + " already exists");
            }
            session.merge(user);
            tx.commit();
        }
    }

    public void deleteUser(User user) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(user);
            transaction.commit();
        }
    }

    public Set<UserDTO> getAllUsersByName(String name) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("FROM User u where u.firstName like ':name%' OR u.lastName like ':name%'", User.class)
                    .setParameter("name", name)
                    .getResultList()
                    .stream()
                    .map(UserMapper.INSTANCE::toDTO)
                    .collect(Collectors.toSet());
        }
    }

    public Set<UserDTO> getUsersByRole(Class<? extends User> role) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("FROM User u WHERE type(u) = :role ", User.class)
                    .setParameter("role", role)
                    .getResultList()
                    .stream()
                    .map(UserMapper.INSTANCE::toDTO)
                    .collect(Collectors.toSet());

        }
    }

    public long getUserCountByRole(Class<? extends User> role) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("select count(u) from User u where type(u) = :role", Long.class)
                    .setParameter("role", role)
                    .uniqueResult();
        }
    }


}

