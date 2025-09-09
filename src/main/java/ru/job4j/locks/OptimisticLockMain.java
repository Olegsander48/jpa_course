package ru.job4j.locks;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import ru.job4j.locks.entity.Student;

public class OptimisticLockMain {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager entityManager = factory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Student student = new Student("Aleks", "Peskov", 5.5, 15);
            entityManager.persist(student);
            System.out.println(student);
            student.setGrade(4534.345345);
            System.out.println(student);
            transaction.commit(); //version = 1

            transaction = entityManager.getTransaction();
            transaction.begin();
            Student student2 = entityManager.find(Student.class, student.getId());
            student2.setGrade(0);
            System.out.println(student2);
            transaction.commit(); //version = 2

        } catch (Exception exception) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }
    }
}
