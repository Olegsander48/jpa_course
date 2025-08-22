package ru.job4j.jpql;

import jakarta.persistence.*;
import ru.job4j.jpql.entity.Student;
import ru.job4j.jpql.entity.University;

import java.util.List;

public class NativeQueryMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager em = emf.createEntityManager();

        try {
            //just native sql
            Query query = em.createNativeQuery("SELECT * from students", Student.class);
            List<Student> students = query.getResultList();
            students.forEach(System.out::println);

            // native sql with condition
            Query query1 = em.createNativeQuery("SELECT * from students WHERE avg_grade > 8 AND length(name) = 5", Student.class);
            List<Student> students1 = query1.getResultList();
            students1.forEach(System.out::println);

            // native sql with parameters
            Query query2 = em.createNativeQuery("SELECT * from students WHERE avg_grade > :grade AND length(name) = :length", Student.class);
            query2.setParameter("grade", 7);
            query2.setParameter("length", 6);
            List<Student> students2 = query2.getResultList();
            students2.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
