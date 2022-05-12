package by.vlad.library.model.dao.mapper.impl;

import by.vlad.library.entity.Author;
import by.vlad.library.entity.Book;
import by.vlad.library.entity.Genre;
import by.vlad.library.entity.Publisher;
import by.vlad.library.model.dao.mapper.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookMapper implements Mapper<Book> {
    private static BookMapper instance;

    public static BookMapper getInstance(){
        if (instance == null){
            instance = new BookMapper();
        }

        return instance;
    }

    private BookMapper(){}

    @Override
    public List<Book> map(ResultSet resultSet) throws SQLException {
        List<Book> books = new ArrayList<>();

        while (resultSet.next()) {
            Author author = new Author(
                    resultSet.getLong(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );

            Publisher publisher = new Publisher(
                    resultSet.getLong(6),
                    resultSet.getString(7)
            );

            Genre genre = new Genre(
                    resultSet.getString(8)
            );

            Book book = Book.getBuilder()
                    .withId(resultSet.getLong(1))
                    .withTitle(resultSet.getString(2))
                    .withAuthor(author)
                    .withPublisher(publisher)
                    .withGenre(genre)
                    .buildBook();

            books.add(book);
        }

        return books;
    }
}
