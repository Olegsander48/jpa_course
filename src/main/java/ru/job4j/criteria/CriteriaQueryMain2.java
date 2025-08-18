package ru.job4j.criteria;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import ru.job4j.criteria.entity.Student;

import java.util.List;

public class CriteriaQueryMain2 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager em = emf.createEntityManager();
        try {
            //SQL: select * from Student where avgGrade>=7.5

            //Step 1: create criteria builder
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

            //Step 2: create criteria query - this is expected value
            CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);

            //Step 3: create root
            Root<Student> studentRoot = criteriaQuery.from(Student.class);

            //Step 3.1: condition creation
            Predicate avgGrade = criteriaBuilder.greaterThanOrEqualTo(studentRoot.get("avgGrade"), 7.5);

            //Step 3.2: adding condition to criteria query
            criteriaQuery.where(avgGrade);

            //Step 4: add root to criteria query
            criteriaQuery.select(studentRoot); //select name from Student

            //Step 5: query creation
            TypedQuery<Student> query = em.createQuery(criteriaQuery);

            //Step 6: get result of query
            List<Student> students = query.getResultList();
            students.forEach(System.out::println);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
