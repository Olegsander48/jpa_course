package ru.job4j.nplus1problem;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import ru.job4j.nplus1problem.model.University;

public class JoinFetchMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
/*            Student student1 = new Student("Alex", 8.5);
            Student student2 = new Student("Bob", 7.6);
            Student student3 = new Student("John", 6.3);
            University university = new University("Cambridge");
            university.setStudents(List.of(student1, student2, student3));
            student1.setUniversity(university);
            student2.setUniversity(university);
            student3.setUniversity(university);
            entityManager.persist(university);
            entityManager.persist(student1);
            entityManager.persist(student2);
            entityManager.persist(student3);*/

/*            Student student1 = new Student("Jean", 8.5);
            Student student2 = new Student("Dean", 7.6);
            Student student3 = new Student("Volf", 6.3);
            University university = new University("Oxford University");
            university.setStudents(List.of(student1, student2, student3));
            student1.setUniversity(university);
            student2.setUniversity(university);
            student3.setUniversity(university);
            entityManager.persist(university);
            entityManager.persist(student1);
            entityManager.persist(student2);
            entityManager.persist(student3);*/

            entityManager.createQuery("from University u JOIN fetch u.students", University.class).getResultList().forEach(System.out::println);

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }
    }
}
