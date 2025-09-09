package ru.job4j.locks;

import jakarta.persistence.*;
import ru.job4j.locks.entity.Student;

public class PessimisticLockMain {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager entityManager = factory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Student student = entityManager.find(Student.class, 1, LockModeType.PESSIMISTIC_READ);
            entityManager.lock(student, LockModeType.PESSIMISTIC_WRITE);
            entityManager.lock(student, LockModeType.PESSIMISTIC_FORCE_INCREMENT);

            System.out.println(student);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }
    }
}
