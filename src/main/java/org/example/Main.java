package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;


public class Main {

    public static void main(String[] args) {
        SessionFactory sessionFactory = AuthorSessionFactory.getAuthorSessionFactory();
        Author author =  new Author();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(author);
        transaction.commit();
        session.close();
        LibraryDAO libraryDAO = new LibraryDAO();
        List<Book> result1 = libraryDAO.getBooksOfAuthor(author.getName());
        System.out.println(result1);
    }

}