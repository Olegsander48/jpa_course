package ru.job4j.inheritancemapping;

import jakarta.persistence.*;
import ru.job4j.inheritancemapping.entity.Driver;
import ru.job4j.inheritancemapping.entity.Teacher;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            Teacher teacher = new Teacher("Aleks", 2800, 4, "OOP", true);
            Driver driver = new Driver("Peter", 2000, 5, 'B', "Volkswagen");
            em.persist(teacher);
            em.persist(driver);

            Query query2 = em.createQuery("select driver from Driver driver");
            List<Driver> drivers = query2.getResultList();
            System.out.println(drivers);

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
