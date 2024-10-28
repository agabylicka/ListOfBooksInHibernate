package org.example;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "Author_Table")
public class Author {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private Integer age;
    private String favouriteGenre;
    @OneToMany(mappedBy = "author")
    private List<Book> books;
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getFavouriteGenre() {
        return favouriteGenre;
    }

    public void setFavouriteGenre(String favouriteGenre) {
        this.favouriteGenre = favouriteGenre;
    }

    public List<Book> getBooks() {
        return this.books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "org.example.Author{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", favouriteGenre='" + favouriteGenre + '\'' +
               // ", books=" + books +
                '}';
    }
}
