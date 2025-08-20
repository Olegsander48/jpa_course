package ru.job4j.relationships.onetoone;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import ru.job4j.relationships.onetoone.entity.Passport;

public class OneToOneBiMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
/*            Student student = new Student("Serena", "Abu", 9.7);
            Passport passport = new Passport("serena@gmail.com", 156, "black");
            passport.setStudent(student);
            student.setPassport(passport);
            entityManager.persist(passport);*/

            Passport passport = entityManager.find(Passport.class, 1);
            System.out.println(passport);
            System.out.println(passport.getStudent());

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
