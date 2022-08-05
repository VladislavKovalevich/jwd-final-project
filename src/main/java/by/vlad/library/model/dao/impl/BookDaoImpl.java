package by.vlad.library.model.dao.impl;

import by.vlad.library.entity.*;
import by.vlad.library.exception.DaoException;
import by.vlad.library.model.dao.BookDao;
import by.vlad.library.model.dao.mapper.Mapper;
import by.vlad.library.model.dao.mapper.impl.BookMapper;
import by.vlad.library.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.*;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.model.dao.ColumnName.*;

/**
 * {@code BookDaoImpl} class implements functional of {@link BookDao}
 * @see Book
 * @see BookDao
 * @see by.vlad.library.model.dao.BasicDao
 */
public class BookDaoImpl implements BookDao {
    private static final Logger logger = LogManager.getLogger();

    private static final String SQL_SELECT_BOOK_BY_ID =
            "SELECT book_id, book_title, book_copies_number, book_publish_year, " +
                    "book_number_of_pages, book_description, author_id, author_name, " +
                    "author_surname, genre_id, genre_name, publisher_id, publisher_name, " +
                    "image_id, image_content " +
            "FROM books " +
            "JOIN authors ON books.authors_id = author_id " +
            "JOIN publishers ON publishers_id = publisher_id " +
            "JOIN genres ON genres_id = genre_id " +
            "LEFT JOIN images ON images_id = image_id " +
            "WHERE book_id = ?";

    private static final String SQL_SELECT_NUMBER_OF_BOOKS =
            "SELECT COUNT(*) as count_col " +
                    "FROM books";

    private static final String SQL_INSERT_NEW_BOOK =
            "INSERT INTO books (`book_title`, `book_copies_number`, `book_publish_year`, " +
                    "`book_number_of_pages`,`book_description`, `authors_id`, `publishers_id`, " +
                    "`genres_id`) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String SQL_UPDATE_BOOK =
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

    private static final String SQL_SELECT_BOOKS_PAGE =
            "SELECT book_id, book_title, book_copies_number, book_publish_year, book_number_of_pages, " +
                    "book_description, author_id, author_name, author_surname, genre_id, genre_name, " +
                    "publisher_id, publisher_name, image_id, image_content " +
                    "FROM books " +
                    "JOIN authors ON authors_id = author_id " +
                    "JOIN publishers ON publishers_id = publisher_id " +
                    "JOIN genres ON genres_id = genre_id " +
                    "LEFT JOIN images ON images_id = image_id ";

    private static final String SQL_SELECT_COPIES_NUMBER_BY_BOOKS_ID = "" +
            "SELECT book_copies_number " +
                "FROM library.books " +
                "WHERE book_id ";

    private static final String SQL_SELECT_BOOKS_BY_ORDER_ID =
            "SELECT book_id, book_title, book_copies_number, book_publish_year, book_number_of_pages, " +
                    "book_description, author_id, author_name, author_surname, genre_id, genre_name, " +
                    "publisher_id, publisher_name, image_id, image_content " +
                    "FROM books " +
                    "JOIN authors ON books.authors_id = author_id " +
                    "JOIN publishers ON publishers_id = publisher_id " +
                    "JOIN genres ON genres_id = genre_id " +
                    "LEFT JOIN images ON images_id = image_id " +
                    "JOIN library.books_orders ON books_id = book_id " +
                    "WHERE orders_id = ?";

    private static final String SELECT_NUMBER_OF_BOOKS_BY_TITLE = "" +
            "SELECT COUNT(book_title) as count_col " +
                "FROM library.books " +
                "WHERE book_title = ?";

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
        logger.error("Unavailable operation to entity Book");
        throw new UnsupportedOperationException("Unavailable operation to entity Book");
    }

    @Override
    public boolean delete(Book book) throws DaoException {
        logger.error("Unavailable operation to entity Book");
        throw new UnsupportedOperationException("Unavailable operation to entity Book");
    }

    @Override
    public Book update(Book book) throws DaoException {
        logger.error("Unavailable operation to entity Book");
        throw new UnsupportedOperationException("Unavailable operation to entity Book");
    }

    @Override
    public List<Book> findAll() throws DaoException {
        logger.error("Unavailable operation to entity Book");
        throw new UnsupportedOperationException("Unavailable operation to entity Book");
    }

    @Override
    public List<Book> getBooks(long pageNumber, Map<String, Long[]> filterMap) throws DaoException {
        List<Book> books;
        Mapper<Book> mapper = BookMapper.getInstance();

        StringBuilder resultSQL = new StringBuilder(SQL_SELECT_BOOKS_PAGE);

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
            logger.error("SQL request getBooks for table library.books was failed" + e);
            throw new DaoException("SQL request getBooks for table library.books was failed", e);
        }

        return books;
    }

    @Override
    public Optional<Book> getBookById(long id) throws DaoException {
        Optional<Book> bookOptional = Optional.empty();
        Mapper<Book> bookMapper = BookMapper.getInstance();

        try(Connection connection  = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BOOK_BY_ID)){

            preparedStatement.setLong(1, id);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                List<Book> books = bookMapper.map(resultSet);
                if (!books.isEmpty()) {
                    bookOptional = Optional.of(books.get(0));
                }
            }

        }catch (SQLException e){
            logger.error("SQL request getBookById for table library.books was failed" + e);
            throw new DaoException("SQL request getBookById for table library.books was failed", e);
        }

        return bookOptional;
    }

    @Override
    public int findNumberOfPage(Map<String, Long[]> filterMap) throws DaoException {
        int result;
        int booksNumber = 0;

        StringBuilder resultSQL = new StringBuilder(SQL_SELECT_NUMBER_OF_BOOKS);

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
            logger.error("SQL request findNumberOfPage for table library.books was failed" + e);
            throw new DaoException("SQL request findNumberOfPage for table library.books was failed", e);
        }

        result = (int) (booksNumber / DEFAULT_ITEM_COUNT_PER_PAGE);

        if (booksNumber % DEFAULT_ITEM_COUNT_PER_PAGE != 0){
            result++;
        }

        return result;
    }

    @Override
    public Optional<Book> addNewBook(Book book) throws DaoException {
        Optional<Book> optionalBook = Optional.empty();

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_NEW_BOOK, Statement.RETURN_GENERATED_KEYS)){

            prepareInsertUpdateDbRequest(statement, book, false);

            statement.executeUpdate();

            try(ResultSet resultSet = statement.getGeneratedKeys()){
                if (resultSet.next()) {
                    long temp = resultSet.getLong(1);
                    book.setId(temp);
                    optionalBook = Optional.of(book);
                }
            }

        }catch (SQLException e){
            logger.error("SQL request addNewBook for table library.books was failed" + e);
            throw new DaoException("SQL request addNewBook for table library.books was failed", e);
        }

        return optionalBook;
    }

    @Override
    public Optional<Book> updateBook(Book book) throws DaoException {
        Optional<Book> bookOptional;
        int rows;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_BOOK)) {

            prepareInsertUpdateDbRequest(statement, book, true);

            rows = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQL request updateBook for table library.books was failed" + e);
            throw new DaoException("SQL request updateBook for table library.books was failed", e);
        }

        if (rows == 1){
            bookOptional = Optional.ofNullable(book);
        }else{
            bookOptional = Optional.empty();
        }

        return bookOptional;
    }

    @Override
    public List<Book> getBooksByOrderId(long orderId) throws DaoException {
        List<Book> books;
        Mapper<Book> bookMapper = BookMapper.getInstance();

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BOOKS_BY_ORDER_ID)){

            statement.setLong(1, orderId);

            try(ResultSet resultSet = statement.executeQuery()) {
                books = bookMapper.map(resultSet);
            }
        }catch (SQLException e){
            logger.error("SQL request getBooksByOrderId for table library.books was failed" + e);
            throw new DaoException("SQL request getBooksByOrderId for table library.books was failed", e);
        }

        return books;
    }

    @Override
    public List<Integer> findBooksCopiesNumber(String bookIdString) throws DaoException {
        List<Integer> copiesList = new ArrayList<>();

        String sqlTemp = new StringBuilder(SQL_SELECT_COPIES_NUMBER_BY_BOOKS_ID)
                .append(SQL_IN_START).append(bookIdString)
                .append(SQL_IN_END).toString();

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlTemp)){

            try(ResultSet resultSet = statement.executeQuery()) {
                 while (resultSet.next()){
                     copiesList.add(resultSet.getInt(BOOK_COPIES_NUMBER_COL));
                 }
            }
        }catch (SQLException e){
            logger.error("SQL request findBooksCopiesNumber for table library.books was failed" + e);
            throw new DaoException("SQL request findBooksCopiesNumber for table library.books was failed", e);
        }

        return copiesList;
    }

    @Override
    public boolean findBooksByTitle(String title) throws DaoException {
        int rows = 0;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_NUMBER_OF_BOOKS_BY_TITLE)){

            statement.setString(1, title);

            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()){
                    rows = resultSet.getInt(COUNT_COL);
                }
            }
        }catch (SQLException e){
            logger.error("SQL request findBooksByTitle for table library.books was failed" + e);
            throw new DaoException("SQL request findBooksByTitle for table library.books was failed", e);
        }

        return rows > 1;
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
            logger.error("Try to prepare SQL request was failed" + e);
            throw new SQLException("Try to prepare SQL request was failed", e);
        }
    }

    private String buildSQLFilterString(Map<String, Long[]> filterMap){
        StringBuilder sqlFilter = new StringBuilder(SQL_WHERE);

        for (Map.Entry<String ,Long[]> e: filterMap.entrySet()) {
            String s = Arrays.toString(e.getValue());

            switch (e.getKey()){
                case GENRE -> {
                    sqlFilter.append(GENRES_ID_COL)
                            .append(SQL_IN_START)
                            .append(s, 1, s.length() - 1)
                            .append(SQL_IN_END);
                }

                case PUBLISHER -> {
                    sqlFilter.append(PUBLISHERS_ID_COL)
                            .append(SQL_IN_START)
                            .append(s, 1, s.length() - 1)
                            .append(SQL_IN_END);
                }

                case AUTHOR -> {
                    sqlFilter.append(AUTHORS_ID_COL)
                            .append(SQL_IN_START)
                            .append(s, 1, s.length() - 1)
                            .append(SQL_IN_END);
                }

                case IS_EXISTS -> {
                    sqlFilter.append(BOOK_COPIES_NUMBER_COL)
                            .append("> 0");
                }
            }

            sqlFilter.append(SQL_AND);
        }

        sqlFilter.delete(sqlFilter.length() - SQL_AND.length(), sqlFilter.length() - 1);

        return sqlFilter.toString();
    }
}
