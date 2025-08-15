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

/*            Teacher teacher1 = new Teacher("Natalia", "Balunova", "Math", false);
            entityManager.persist(teacher1);*/

            Teacher teacher1 = entityManager.find(Teacher.class, 1);

            System.out.println(entityManager.contains(teacher1));
            entityManager.detach(teacher1);
            System.out.println(entityManager.contains(teacher1));

            teacher1.setName("Jerry"); //nothing changed in DB

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
