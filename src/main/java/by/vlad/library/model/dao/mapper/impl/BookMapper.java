package by.vlad.library.model.dao.mapper.impl;

import by.vlad.library.entity.Author;
import by.vlad.library.entity.Book;
import by.vlad.library.entity.Genre;
import by.vlad.library.entity.Publisher;
import by.vlad.library.model.dao.mapper.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static by.vlad.library.model.dao.ColumnName.*;

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
                    resultSet.getLong(AUTHOR_ID_COL),
                    resultSet.getString(AUTHOR_NAME_COL),
                    resultSet.getString(AUTHOR_SURNAME_COL)
            );

            Publisher publisher = new Publisher(
                    resultSet.getLong(PUBLISHER_ID_COL),
                    resultSet.getString(PUBLISHER_NAME_COL)
            );

            Genre genre = new Genre(
                    resultSet.getLong(GENRE_ID_COL),
                    resultSet.getString(GENRE_NAME_COL)
            );

            Book book = Book.getBuilder()
                    .withId(resultSet.getLong(BOOK_ID_COL))
                    .withTitle(resultSet.getString(BOOK_TITLE_COL))
                    .withReleaseYear(Year.of(resultSet.getInt(BOOK_PUBLISH_YEAR_COL)))
                    .withNumberOfPages(resultSet.getInt(BOOK_NUMBER_OF_PAGES_COL))
                    .withDescription(resultSet.getString(BOOK_DESCRIPTION_COL))
                    .withCopiesNumber(resultSet.getInt(BOOK_COPIES_NUMBER_COL))
                    .withAuthor(author)
                    .withPublisher(publisher)
                    .withGenre(genre)
                    .buildBook();

            books.add(book);
        }

        return books;
    }
}
