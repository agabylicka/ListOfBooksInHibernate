package org.example;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class LibraryDAO {

    private final SessionFactory sessionFactory = AuthorSessionFactory.getAuthorSessionFactory();

    public List<Book> getBooksOfAuthor(String authorName) {
        // check that author is in base --> read
        SessionFactory sessionFactory = AuthorSessionFactory.getAuthorSessionFactory();
        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Author> authorQuery = cb.createQuery(Author.class);
        Root<Author> root = authorQuery.from(Author.class);
        authorQuery.select(root).where(cb.equal(root.get("name"), authorName));
        Author foundAuthor = session.createQuery(authorQuery).getSingleResult();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Book> bookQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> root1 = bookQuery.from(Book.class);
        bookQuery.select(root1).where(criteriaBuilder.equal(root.get("author"), foundAuthor));
        List<Book> results = session.createQuery(bookQuery).list();
        session.close();
        return results;
    }


    public void addBookToAuthor(String authorName, Book book) {
        //add book to author
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.merge(book);
            transaction.commit();
            session.close();
    }

    public void getAllAuthors() {
    }

    // lista wszystkich autorów --> read
    public void getAllBooks() {

    }

    //lista wszystkich książek --> read
    public void getAllBooksAndAuthors() {

    }

    //pokaż listę wszystkich autorów i książek --> read
    public void addAuthor(org.example.Author author) {

    }


   /* private void saveBooks(List<Book> books) {
        SessionFactory sessionFactory = AuthorSessionFactory.getAuthorSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        books.forEach(session::merge);
        transaction.commit();
        session.close(); */
    }

    public Author findBookByAuthorName(String book) {
        SessionFactory sessionFactory = AuthorSessionFactory.getAuthorSessionFactory();
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Author> userQuery = cb.createQuery(Author.class);
        Root<Author> root = userQuery.from(Author.class);
        userQuery.select(root).where(cb.equal(root.get("book"), book));

        return session.createQuery(userQuery).getSingleResult();
    }

    public Author findBookByTitle(String title) {
        SessionFactory sessionFactory = AuthorSessionFactory.getAuthorSessionFactory();
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Book> book = cb.createQuery(Book.class);
        Root<Book> root = book.from(Book.class);
        book.select(root).where(cb.equal(root.get("title"), title));

        return null;
    }

    /*public void changeUserAge(String email, int age) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User u = findUserByEmail(email);
        u.setAge(age);
        session.merge(u);
        transaction.commit();
        session.close();
    } */


    public void deleteBook(String title) {
        SessionFactory sessionFactory = AuthorSessionFactory.getAuthorSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Book b = findBookByTitle(title);
        session.remove(title);
        transaction.commit();
        session.close();
    }

    public void deleteAuthor(String authorName) {
        SessionFactory sessionFactory = AuthorSessionFactory.getAuthorSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Author a = findBookByAuthorName(authorName);
        session.remove(a);
        transaction.commit();
        session.close();
    }
}


