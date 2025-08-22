package ru.job4j.jpql;

import jakarta.persistence.*;
import ru.job4j.jpql.entity.University;

import java.util.List;

public class JpqlExampleMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            //select universities without students
            TypedQuery<University> query = em.createQuery("select u from University u where u.students is empty", University.class);
            List<University> universities = query.getResultList();
            universities.forEach(System.out::println);

            //select universities with 1 student
            TypedQuery<University> query1 = em.createQuery("select u from University u where size(u.students) = 1", University.class);
            query1.getResultList().forEach(System.out::println);

            //sort universities by count of students desc
            TypedQuery<University> query2 = em.createQuery("select u from University u order by size(u.students) DESC", University.class);
            query2.getResultList().forEach(System.out::println);

            //cross join
            TypedQuery<Object[]> query3 = em.createQuery("select u, s from University u, Student s", Object[].class);
            query3.getResultList().forEach(object -> System.out.println(object[0] + " -> " + object[1]));

            //inner join
            TypedQuery<Object[]> query4 = em.createQuery("select u, s from University u JOIN u.students s", Object[].class);
            query4.getResultList().forEach(object -> System.out.println(object[0] + " -> " + object[1]));

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
