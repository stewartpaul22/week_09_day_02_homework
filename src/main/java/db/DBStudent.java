package db;

import models.Student;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DBStudent {

    private static Transaction transaction;
    private static Session session;

    public static void save(Student student) {

        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static List<Student> getStudents()
    {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Student> students = null;
        try {
            transaction = session.beginTransaction();
            String hql = "from Student";
            Query query = session.createQuery(hql);
            students = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return students;
    }

    public static void deleteAll(){
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            String hql = "delete from Student";
            Query query = session.createQuery(hql);
            query.executeUpdate();
        } catch (HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void update(Student student){
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(student);
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
        Student student = null;
        try {
            transaction = session.beginTransaction();
            String hql = "from Student where id = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id", id);
            student = (Student) query.uniqueResult();
            session.delete(student);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static Student findStudentById(int id)
    {
        session = HibernateUtil.getSessionFactory().openSession();
        Student student = null;
        try {
            transaction = session.beginTransaction();
            String hql = "from Student where id = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id", id);
            student = (Student) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return student;
    }

}
