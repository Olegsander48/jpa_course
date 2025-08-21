package ru.job4j.relationships.onetomany;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import ru.job4j.relationships.onetomany.entity.University;

public class LoadingTypesMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            University university = entityManager.find(University.class, 2);
            university.getStudents().size(); //load list of students
            entityManager.close();

            System.out.println(university);
            System.out.println(university.getStudents());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManagerFactory.close();
        }
    }
}
