package ru.job4j.relationships.onetoone;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import ru.job4j.relationships.onetoone.entity.EyeColor;
import ru.job4j.relationships.onetoone.entity.Passport;
import ru.job4j.relationships.onetoone.entity.Student;

public class EnumMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Student student = new Student("Erik", "Scott", 9.7);
            Passport passport = new Passport("erik@gmail.com", 177, EyeColor.GREEN);
            student.setPassport(passport);
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
