package db;

import models.Instructor;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DBInstructor {

    private static Transaction transaction;
    private static Session session;

    public static void save(Instructor instructor) {

        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(instructor);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static List<Instructor> getInstructors()
    {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Instructor> instructors = null;
        try {
            transaction = session.beginTransaction();
            String hql = "from Instructor";
            Query query = session.createQuery(hql);
            instructors = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return instructors;
    }

    public static void deleteAll(){
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            String hql = "delete from Instructor";
            Query query = session.createQuery(hql);
            query.executeUpdate();
        } catch (HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void update(Instructor instructor){
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(instructor);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void delete(int id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Instructor instructor = null;
        try {
            transaction = session.beginTransaction();
            String hql = "from Instructor where id = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id", id);
            instructor = (Instructor) query.uniqueResult();
            session.delete(instructor);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static Instructor findInstructorById(int id)
    {
        session = HibernateUtil.getSessionFactory().openSession();
        Instructor instructor = null;
        try {
            transaction = session.beginTransaction();
            String hql = "from Instructor where id = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id", id);
            instructor = (Instructor) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return instructor;
    }
}
