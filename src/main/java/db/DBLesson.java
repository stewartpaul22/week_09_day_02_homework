package db;

import models.Lesson;
import models.Student;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DBLesson {
    private static Transaction transaction;
    private static Session session;

    public static void saveLesson(Lesson lesson) {

        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(lesson);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static List<Lesson> getLessons() {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Lesson> lessons = null;
        try {
            transaction = session.beginTransaction();
            String hql = "from Lesson";
            Query query = session.createQuery(hql);
            lessons = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lessons;
    }

    public static void deleteAll() {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            String hql = "delete from Lesson";
            Query query = session.createQuery(hql);
            query.executeUpdate();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }



    public static void update(Lesson lesson) {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(lesson);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Lesson lesson = null;
        try {
            transaction = session.beginTransaction();
            String hql = "from Lesson where id = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id", id);
            lesson = (Lesson) query.uniqueResult();
            session.delete(lesson);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static Lesson findLessonById(int id) {
        session = HibernateUtil.getSessionFactory().openSession();
        Lesson lesson = null;
        try {
            transaction = session.beginTransaction();
            String hql = "from Lesson where id = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id", id);
            lesson = (Lesson) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lesson;
    }
}
