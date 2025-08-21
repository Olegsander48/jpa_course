package ru.job4j.relationships.onetomany;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import ru.job4j.relationships.onetomany.entity.Student;
import ru.job4j.relationships.onetomany.entity.University;

import java.sql.Date;

public class OneToManyUniMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            University university = new University("Harvard", Date.valueOf("1636-10-28"));
            Student student1 = new Student("Royman", "Koll", 8.5);
            Student student2 = new Student("David", "Goggins", 9.7);
            university.addStudent(student1);
            university.addStudent(student2);

            entityManager.persist(university);

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
