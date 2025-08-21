package ru.job4j.relationships.onetomany;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import ru.job4j.relationships.onetomany.entity.University;

import java.sql.Date;

public class OneToManyBiMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
/*            University university = new University("MIT", Date.valueOf("1861-04-10"));
            Student student1 = new Student("Ivan", "Ignatenko", 4.6);
            Student student2 = new Student("Vladimir", "Krizhov", 7.6);
            Student student3 = new Student("Nikola", "Kristenko", 5.6);
            Student student4 = new Student("Yan", "Polnikov", 9.6);
            university.addStudent(student1);
            university.addStudent(student2);
            university.addStudent(student3);
            university.addStudent(student4);

            entityManager.persist(university);*/

            University university = entityManager.find(University.class, 2);
            university.getStudents().forEach(System.out::println);

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
