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
            Teacher teacher2 = new Teacher("Elena", "Temnikova", "English", false);
            Teacher teacher3 = new Teacher("Vladimir", "Eroshin", "CS", true);

            entityManager.persist(teacher1);
            entityManager.persist(teacher2);
            entityManager.persist(teacher3);*/

            Teacher teacher1 = entityManager.find(Teacher.class, 1);
            Teacher teacher2 = entityManager.find(Teacher.class, 2);
            Teacher teacher3 = entityManager.find(Teacher.class, 3);

            teacher1.setProfessor(true);
            entityManager.remove(teacher2);
            entityManager.flush();

            Teacher teacher4 = entityManager.find(Teacher.class, 1000);
            teacher4.getName(); //after rollback we will see no changes

            teacher3.setSubject("AI");

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
