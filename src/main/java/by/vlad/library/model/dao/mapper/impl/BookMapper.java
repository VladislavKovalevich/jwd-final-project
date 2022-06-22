package by.vlad.library.model.dao.mapper.impl;

import by.vlad.library.entity.*;
import by.vlad.library.model.dao.mapper.Mapper;
import by.vlad.library.util.ImageEncoder;

import java.sql.Blob;
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

            Image image = new Image();
            long imageId = resultSet.getLong(IMAGE_ID_COL);

            if (imageId > 0) {
                Blob blob = resultSet.getBlob(IMAGE_CONTENT_COL);
                if (blob != null) {
                    byte[] imageData = blob.getBytes(1, (int) blob.length());
                    image.setId(imageId);
                    image.setContent(imageData);
                    ImageEncoder imageEncoder = ImageEncoder.getInstance();
                    image.setEncodeImage(imageEncoder.encodeImage(imageData));
                }
            }

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

            Book.BookBuilder bookBuilder = Book.getBuilder()
                    .withId(resultSet.getLong(BOOK_ID_COL))
                    .withTitle(resultSet.getString(BOOK_TITLE_COL))
                    .withReleaseYear(Year.of(resultSet.getInt(BOOK_PUBLISH_YEAR_COL)))
                    .withNumberOfPages(resultSet.getInt(BOOK_NUMBER_OF_PAGES_COL))
                    .withDescription(resultSet.getString(BOOK_DESCRIPTION_COL))
                    .withCopiesNumber(resultSet.getInt(BOOK_COPIES_NUMBER_COL))
                    .withAuthor(author)
                    .withPublisher(publisher)
                    .withImage(image)
                    .withGenre(genre);

            books.add(bookBuilder.buildBook());
        }

        return books;
    }
}
