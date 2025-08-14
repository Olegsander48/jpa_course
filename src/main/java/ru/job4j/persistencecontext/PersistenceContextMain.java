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

            Teacher teacher1 = new Teacher("Natalia", "Balunova", "Math", false);
            Teacher teacher2 = new Teacher("Elena", "Temnikova", "English", false);

            entityManager.persist(teacher1);
            entityManager.persist(teacher2);

            entityManager.find(Teacher.class, 10);
            entityManager.find(Teacher.class, 10);

            transaction.commit();
        } catch (Exception ex) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}
