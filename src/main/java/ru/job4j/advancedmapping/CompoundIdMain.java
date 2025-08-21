package ru.job4j.advancedmapping;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import ru.job4j.advancedmapping.entity.Book;
import ru.job4j.advancedmapping.id.BookId;

public class CompoundIdMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

/*            Book book1 = new Book("Dostoevskiy", "Presuplenoye i nakazaniye", 1866, 4.8);
            Book book2 = new Book("Dostoevskiy", "Bratya karamazovi", 1880, 4.6);
            Book book3 = new Book("Tolstoy", "War and peace", 1867, 4.7);

            em.persist(book1);
            em.persist(book2);
            em.persist(book3);*/
            Book book = em.find(Book.class, new BookId("Dostoevskiy", "Bratya karamazovi"));
            System.out.println(book);

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
