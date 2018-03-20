package db;

import models.Course;
import models.Lesson;
import models.Student;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DBCourse {
    private static Transaction transaction;
    private static Session session;

    public static void saveCourse(Course course) {

        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(course);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static List<Course> getCourses() {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Course> courses = null;
        try {
            transaction = session.beginTransaction();
            String hql = "from Course";
            Query query = session.createQuery(hql);
            courses = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return courses;
    }

    public static void deleteAll() {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            String hql = "delete from Course";
            Query query = session.createQuery(hql);
            query.executeUpdate();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static List<Student> getStudents(int id) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Student> students = null;
        try {
            transaction = session.beginTransaction();
            String hql = String.format("from Student WHERE course_id = %d", id);
            students = session.createQuery(hql).list();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return students;
    }

    public static List<Lesson> getLessons(int id) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Lesson> lessons = null;
        try {
            transaction = session.beginTransaction();
            String hql = String.format("from Lesson WHERE course_id = %d", id);
            lessons = session.createQuery(hql).list();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return lessons;
    }

    public static void update(Course course) {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(course);
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
        Course course = null;
        try {
            transaction = session.beginTransaction();
            String hql = "from Course where id = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id", id);
            course = (Course) query.uniqueResult();
            session.delete(course);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static Course findCourseById(int id) {
        session = HibernateUtil.getSessionFactory().openSession();
        Course course = null;
        try {
            transaction = session.beginTransaction();
            String hql = "from Course where id = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id", id);
            course = (Course) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return course;
    }
}
