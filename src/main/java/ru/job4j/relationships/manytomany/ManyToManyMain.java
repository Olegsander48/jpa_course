package ru.job4j.relationships.manytomany;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import ru.job4j.relationships.manytomany.entity.Teacher;
import ru.job4j.relationships.manytomany.entity.University;

import java.sql.Date;

public class ManyToManyMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            //persist
/*            University university = new University("Harvard", Date.valueOf("1636-10-28"));
            Teacher teacher1 = new Teacher("Aleks", "Lozano", "CS", true);
            Teacher teacher2 = new Teacher("Bob", "Berger", "AI", false);
            Teacher teacher3 = new Teacher("David", "Hopkins", "Chemistry", true);
            university.addTeacher(teacher1);
            university.addTeacher(teacher2);
            university.addTeacher(teacher3);

            entityManager.persist(university);*/

            //find
/*            University university = entityManager.find(University.class, 1);
            System.out.println(university);
            System.out.println(university.getTeachers());*/

            //remove
            University university = entityManager.find(University.class, 1);
            entityManager.remove(university);

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
