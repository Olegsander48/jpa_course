package ru.job4j.advancedmapping;

import jakarta.persistence.*;
import ru.job4j.advancedmapping.entity.Address;
import ru.job4j.advancedmapping.entity.Employee;

public class AdvancedMappingMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            Address address = new Address("USA", "New-York", "Second-street", 45);
            Employee employee = new Employee("John", 75000, 5.6, address);
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
