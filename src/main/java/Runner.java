import db.DBCourse;
import db.DBInstructor;
import db.DBLesson;
import db.DBStudent;
import models.Course;
import models.Instructor;
import models.Lesson;
import models.Student;

import java.util.Date;
import java.util.GregorianCalendar;

public class Runner {

    public static void main(String[] args) {
        Course course1 = new Course("Electrical Engineering", "BEng", new GregorianCalendar(2018,2,12), new GregorianCalendar(19,5,2018));
        DBCourse.saveCourse(course1);

        Student student1 = new Student("Bart Simpson", 12, 000001, course1);
        DBStudent.save(student1);

        Student student2 = new Student("Lisa Simpson", 11, 00002, course1);
        DBStudent.save(student2);

        Instructor instructor1 = new Instructor("Principle Skinner");
        DBInstructor.save(instructor1);
        Instructor instructor2 = new Instructor("Mrs Krabappel");
        DBInstructor.save(instructor2);

        Lesson lesson1 = new Lesson("Intro to CAD", "EE101", course1, instructor1);
        DBLesson.saveLesson(lesson1);
        Lesson lesson2 = new Lesson("Programming with C++", "EE210", course1, instructor2);
        DBLesson.saveLesson(lesson2);

        addStudentToLesson(student1, lesson1);
        addStudentToLesson(student2, lesson1);
        addStudentToLesson(student1, lesson2);

        addInstructorToCourse(course1, instructor1);
        addInstructorToCourse(course1, instructor2);
        addInstructorToCourse(course1, instructor2);

        Course myCourse = DBCourse.findCourseById(course1.getId());
        System.out.println();

    }

    public static void addStudentToLesson(Student student, Lesson lesson){
        student.addLesson(lesson);
        lesson.addStudent(student);
        DBLesson.update(lesson);
        DBStudent.update(student);
    }

    public static void addInstructorToCourse(Course course, Instructor instructor){
        course.addInstructor(instructor);
        instructor.addCourse(course);
        DBInstructor.update(instructor);
        DBCourse.update(course);
    }
}
