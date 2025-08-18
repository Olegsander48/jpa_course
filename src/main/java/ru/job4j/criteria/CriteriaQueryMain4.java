package ru.job4j.criteria;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import ru.job4j.criteria.entity.Student;
import ru.job4j.criteria.entity.University;

import java.util.List;

public class CriteriaQueryMain4 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager em = emf.createEntityManager();
        try {
            //SQL: select * from University JOIN Students

            //Step 1: create criteria builder
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

            //Step 2: create criteria query - this is expected value
            CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

            //Step 3: create root
            Root<University> universityRoot = criteriaQuery.from(University.class); //from Unviresity

            //Step 3.1: join
            Join<University, Student> join = universityRoot.join("students"); //field from University table

            //Step 4: add root to criteria query
            criteriaQuery.multiselect(universityRoot, join); //select * from University JOIN Students

            //Step 5: query creation
            TypedQuery<Object[]> query = em.createQuery(criteriaQuery);

            //Step 6: get result of query
            List<Object[]> info = query.getResultList();
            info.forEach(object -> System.out.println(object[0] + " ----> " + object[1]));
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
