package ru.job4j.relationships.onetomany;

import jakarta.persistence.*;
import ru.job4j.relationships.onetomany.entity.Student;
import ru.job4j.relationships.onetomany.entity.University;

import java.sql.Date;

public class NPlusOneProblemMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
/*            University university1 = new University("Harvard", Date.valueOf("1636-10-28"));
            University university2 = new University("MIT", Date.valueOf("1861-04-10"));
            University university3 = new University("Standford", Date.valueOf("1891-10-01"));

            Student student1 = new Student("Chanel", "King", 9.2);
            Student student2 = new Student("Leo", "Farrel", 8.4);
            Student student3 = new Student("Yulia", "Din", 7.1);

            university1.addStudent(student1);
            university2.addStudent(student2);
            university3.addStudent(student3);

            entityManager.persist(university1);
            entityManager.persist(university2);
            entityManager.persist(university3);*/

            Query query = entityManager.createQuery("FROM University");
            query.getResultList().forEach(System.out::println);

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
