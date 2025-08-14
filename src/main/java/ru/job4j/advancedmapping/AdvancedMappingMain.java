package ru.job4j.advancedmapping;

import jakarta.persistence.*;
import ru.job4j.advancedmapping.entity.Employee;
import ru.job4j.advancedmapping.entity.Friend;

import java.util.ArrayList;
import java.util.List;

public class AdvancedMappingMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            List<Friend> friends = new ArrayList<>();
            friends.add(new Friend("Joe", "Swanson", 30));
            friends.add(new Friend("Leo", "Dee", 34));
            friends.add(new Friend("Antony", "Hopcins", 35));

            Employee employee = new Employee("John", 75000, 5.6, friends);
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
