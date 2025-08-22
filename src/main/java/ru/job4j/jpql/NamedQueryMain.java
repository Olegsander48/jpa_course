package ru.job4j.jpql;

import jakarta.persistence.*;
import ru.job4j.jpql.entity.Student;
import ru.job4j.jpql.entity.University;

import java.util.List;

public class NamedQueryMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager em = emf.createEntityManager();

        try {
            //first named query
            TypedQuery<University> query = em.createNamedQuery("University.allUniversitiesLessOrEqualToTwo", University.class);
            List<University> universities = query.getResultList();
            universities.forEach(System.out::println);

            //find students in range
            TypedQuery<Student> query1 = em.createNamedQuery("University.studentsWithAvgGradeBetween", Student.class);
            query1.setParameter("from", 6);
            query1.setParameter("to", 8);
            List<Student> students = query1.getResultList();
            students.forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
