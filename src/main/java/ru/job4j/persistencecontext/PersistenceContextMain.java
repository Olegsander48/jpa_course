package ru.job4j.persistencecontext;

import jakarta.persistence.*;
import ru.job4j.persistencecontext.entity.Teacher;

public class PersistenceContextMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Teacher teacher1 = entityManager.find(Teacher.class, 1);
            transaction.commit();
            entityManager.close();

            teacher1.setName("VASILIY");

            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();

            Teacher mergedTeacher = entityManager.merge(teacher1);
            System.out.println(mergedTeacher);

            transaction.commit();
        } catch (Exception ex) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
