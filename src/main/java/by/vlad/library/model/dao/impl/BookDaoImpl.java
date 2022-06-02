package by.vlad.library.model.dao.impl;

import by.vlad.library.entity.*;
import by.vlad.library.exception.DaoException;
import by.vlad.library.model.dao.BookDao;
import by.vlad.library.model.dao.mapper.Mapper;
import by.vlad.library.model.dao.mapper.impl.BookMapper;
import by.vlad.library.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.model.dao.ColumnName.*;

public class BookDaoImpl implements BookDao {

    private static final String SELECT_BOOK_BY_ID =
            "SELECT book_id, book_title, book_copies_number, book_publish_year, book_number_of_pages, book_description, " +
                    "author_id, author_name, author_surname, genre_id, genre_name, publisher_id, publisher_name " +
            "FROM books " +
            "JOIN authors ON books.authors_id = author_id " +
            "JOIN publishers ON publishers_id = publisher_id " +
            "JOIN genres ON genres_id = genre_id " +
            "WHERE book_id = ?";

    private static final String SELECT_NUMBER_OF_BOOKS =
            "SELECT COUNT(*) as count_col FROM books";

    private static final String INSERT_NEW_BOOK =
            "INSERT INTO books (`book_title`, `book_copies_number`, `book_publish_year`, `book_number_of_pages`, " +
                    "`book_description`, `authors_id`, `publishers_id`, `genres_id`) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String UPDATE_BOOK =
            "UPDATE books " +
                    "SET book_title = ?," +
                    "    book_copies_number = ?, " +
                    "    book_publish_year = ?, " +
                    "    book_number_of_pages = ?, " +
                    "    book_description = ?, " +
                    "    authors_id = ? ," +
                    "    publishers_id = ? ," +
                    "    genres_id = ? " +
                    "WHERE book_id = ?;  ";

    private static final String SELECT_BOOKS_PAGE =
            "SELECT book_id, book_title, book_copies_number, book_publish_year, book_number_of_pages, book_description, " +
                    "author_id, author_name, author_surname, genre_id, genre_name, publisher_id, publisher_name " +
                    "FROM books " +
                    "JOIN authors ON authors_id = author_id " +
                    "JOIN publishers ON publishers_id = publisher_id " +
                    "JOIN genres ON genres_id = genre_id ";

    private static final String SQL_LIMIT = "LIMIT ?, ?";

    private static final String SQL_WHERE = " WHERE ";
    private static final String SQL_IN_START = " IN(";
    private static final String SQL_IN_END = ") ";
    private static final String SQL_AND = " AND ";

    private static final long DEFAULT_ITEM_COUNT_PER_PAGE = 4;

    private static BookDaoImpl instance;

    private BookDaoImpl(){
    }

    public static BookDaoImpl getInstance() {
        if (instance == null){
            instance = new BookDaoImpl();
        }
        return instance;
    }

    @Override
    public boolean insert(Book book) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Book book) throws DaoException {
        return false;
    }

    @Override
    public Book update(Book book) throws DaoException {
        return null;
    }

    @Override
    public List<Book> findAll() throws DaoException {
        return null;
    }

    @Override
    public List<Book> getBooks(long pageNumber, Map<String, String> filterMap) throws DaoException {
        List<Book> books;
        Mapper<Book> mapper = BookMapper.getInstance();

        StringBuilder resultSQL = new StringBuilder(SELECT_BOOKS_PAGE);

        if (!filterMap.isEmpty()){
            resultSQL.append(buildSQLFilterString(filterMap));
        }

        resultSQL.append(SQL_LIMIT);

        try(Connection connection  = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(resultSQL.toString())) {

            preparedStatement.setLong(1, (pageNumber - 1) * DEFAULT_ITEM_COUNT_PER_PAGE);
            preparedStatement.setLong(2, DEFAULT_ITEM_COUNT_PER_PAGE);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                books = mapper.map(resultSet);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return books;
    }

    @Override
    public Optional<Book> getBookById(long id) throws DaoException {
        Optional<Book> bookOptional = Optional.empty();
        Mapper<Book> bookMapper = BookMapper.getInstance();

        try(Connection connection  = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOK_BY_ID)){

            preparedStatement.setLong(1, id);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                List<Book> books = bookMapper.map(resultSet);
                if (!books.isEmpty()) {
                    bookOptional = Optional.of(books.get(0));
                }
            }

        }catch (SQLException e){
            throw  new DaoException(e);
        }

        return bookOptional;
    }

    @Override
    public int findNumberOfPage(Map<String, String> filterMap) throws DaoException {
        int result;
        int booksNumber = 0;

        StringBuilder resultSQL = new StringBuilder(SELECT_NUMBER_OF_BOOKS);

        if (!filterMap.isEmpty()){
            resultSQL.append(buildSQLFilterString(filterMap));
        }

        try(Connection connection  = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(resultSQL.toString())){

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                booksNumber = resultSet.getInt(COUNT_COL);
            }

        }catch (SQLException e){
            throw new DaoException(e);
        }

        result = (int) (booksNumber / DEFAULT_ITEM_COUNT_PER_PAGE);

        if (booksNumber % DEFAULT_ITEM_COUNT_PER_PAGE != 0){
            result++;
        }

        return result;
    }

    @Override
    public boolean addNewBook(Book book) throws DaoException {
        int rows;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_NEW_BOOK)){

            prepareInsertUpdateDbRequest(statement, book, false);

            rows = statement.executeUpdate();

        }catch (SQLException e){
            throw new DaoException(e);
        }

        return rows == 1;
    }

    @Override
    public Optional<Book> updateBook(Book book) throws DaoException {
        Optional<Book> bookOptional;
        int rows;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_BOOK)) {

            prepareInsertUpdateDbRequest(statement, book, true);

            rows = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        if (rows == 1){
            bookOptional = Optional.ofNullable(book);
        }else{
            bookOptional = Optional.empty();
        }

        return bookOptional;
    }

    private void prepareInsertUpdateDbRequest(PreparedStatement statement, Book book, boolean isUpdate) throws SQLException {
        try {
            if (isUpdate){
                statement.setLong(9, book.getId());
            }

            statement.setString(1, book.getTitle());
            statement.setInt(2, book.getCopiesNumber());
            statement.setInt(3, book.getReleaseYear().getValue());
            statement.setInt(4, book.getNumberOfPages());
            statement.setString(5, book.getDescription());
            statement.setLong(6, book.getAuthor().getId());
            statement.setLong(7, book.getPublisher().getId());
            statement.setLong(8, book.getGenre().getId());
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    private String buildSQLFilterString(Map<String, String> filterMap){
        StringBuilder sqlFilter = new StringBuilder(SQL_WHERE);

        for (Map.Entry<String ,String> e: filterMap.entrySet()) {
            switch (e.getKey()){
                case GENRE -> {
                    sqlFilter.append(GENRES_ID_COL)
                            .append(SQL_IN_START)
                            .append(e.getValue())
                            .append(SQL_IN_END);
                }

                case PUBLISHER -> {
                    sqlFilter.append(PUBLISHERS_ID_COL)
                            .append(SQL_IN_START)
                            .append(e.getValue())
                            .append(SQL_IN_END);
                }

                case AUTHOR -> {
                    sqlFilter.append(AUTHORS_ID_COL)
                            .append(SQL_IN_START)
                            .append(e.getValue())
                            .append(SQL_IN_END);
                }
            }

            sqlFilter.append(SQL_AND);
        }

        sqlFilter.delete(sqlFilter.length() - SQL_AND.length(), sqlFilter.length() - 1);

        return sqlFilter.toString();
    }
}
