package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class UserDaoHibernateImpl implements UserDao {
    private String sql;
    private Transaction transaction = null;

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

        sql = "CREATE TABLE IF NOT EXISTS users (`id` INT NOT NULL AUTO_INCREMENT," +
                "`name` VARCHAR(45) NULL, " +
                "`lastname` VARCHAR(100) NULL, `age` INT NULL, PRIMARY KEY (`id`))";


        try (Session session = Util.getSessionFactory().openSession()) {

            // start the transaction
            transaction = session.beginTransaction();
            session.createSQLQuery(sql).addEntity(User.class).executeUpdate();

            // commit transction
            transaction.commit();
            //query.executeUpdate();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {

        sql = "DROP TABLE IF EXISTS users";

        try (Session session = Util.getSessionFactory().openSession()) {

            // start the transaction
            transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            // commit transction
            transaction.commit();
            // query.executeUpdate();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try (Session session = Util.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            session.save(new User(name, lastName, age));
            // commit transction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();
            session.delete(session.get(User.class, id));
            // commit transction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        sql = "SELECT * FROM users";
        List<User> userList = new ArrayList<>();
        try (Session session = Util.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();
            userList = session.createSQLQuery(sql).addEntity(User.class).list();
            // commit transction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        sql = "TRUNCATE  users";
        try (Session session = Util.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            // commit transction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }
}
