package ru.job4j.advancedmapping;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import ru.job4j.advancedmapping.entity.Employee;
import ru.job4j.advancedmapping.entity.Friend;
import java.util.List;

public class ListMappingMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            Friend friend1 = new Friend("Vlad", "Mishustin", 25);
            Friend friend2 = new Friend("Andrey", "Voitov", 31);
            Employee employee = new Employee("Aleks", 1000, 5.5, List.of(friend1, friend2));
            em.persist(employee);
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
