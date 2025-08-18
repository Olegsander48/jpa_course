package ru.job4j.criteria;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import ru.job4j.criteria.entity.Student;

import java.util.List;

public class CriteriaQueryMain1 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager em = emf.createEntityManager();
        try {
            //SQL: select name from Student

            //Step 1: create criteria builder
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

            //Step 2: create criteria query - this is expected value
            CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);

            //Step 3: create root
            Root<Student> studentRoot = criteriaQuery.from(Student.class);

            //Step 4: add root to criteria query
            criteriaQuery.select(studentRoot.get("name")); //select name from Student

            //Step 5: query creation
            TypedQuery<String> query = em.createQuery(criteriaQuery);

            //Step 6: get result of query
            List<String> students = query.getResultList();
            students.forEach(System.out::println);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
