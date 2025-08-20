package ru.job4j.relationships.onetoone;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import ru.job4j.relationships.onetoone.entity.Student;

public class OneToOneUniMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

/*            Student student = new Student("Serena", "Abu", 9.7);
            Passport passport = new Passport("serena@gmail.com", 156, "black");
            student.setPassport(passport);
            entityManager.persist(student);*/

            Student student = entityManager.find(Student.class, 1);
            System.out.println(student);
            entityManager.remove(student); //also delete it's passport

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
