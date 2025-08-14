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

            teacher1.setSubject("Physic"); //all in 1 query-update
            teacher1.setProfessor(true);

            transaction.commit();
            transaction.begin();
            //empty transaction load all differences to DB
            transaction.commit();

            System.out.println(entityManager.find(Teacher.class, teacher1.getId()));
            System.out.println(entityManager.find(Teacher.class, teacher1.getId()));
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
