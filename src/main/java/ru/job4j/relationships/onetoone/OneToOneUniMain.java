package ru.job4j.relationships.onetoone;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import ru.job4j.relationships.onetoone.entity.Passport;
import ru.job4j.relationships.onetoone.entity.Student;

public class OneToOneUniMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Student student = new Student("Aleksey", "Vaskov", 5.5);
            Passport passport = new Passport("aleksey@gmail.com", 179, "blue");
            student.setPassport(passport);
            entityManager.persist(passport);
            entityManager.persist(student);

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
