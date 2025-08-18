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

public class CriteriaQueryMain3 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager em = emf.createEntityManager();
        try {
            //SQL: select name, avgGrade from Student

            //Step 1: create criteria builder
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

            //Step 2: create criteria query - this is expected value
            CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

            //Step 3: create root
            Root<Student> studentRoot = criteriaQuery.from(Student.class); //from Student

            //Step 4: add root to criteria query
            criteriaQuery.multiselect(studentRoot.get("name"), studentRoot.get("avgGrade")); //select name, avgGrade from Student

            //Step 5: query creation
            TypedQuery<Object[]> query = em.createQuery(criteriaQuery);

            //Step 6: get result of query
            List<Object[]> info = query.getResultList();
            info.forEach(object -> System.out.println(object[0] + " " + object[1]));
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
