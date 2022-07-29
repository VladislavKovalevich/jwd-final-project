package by.vlad.library.model.dao;

import by.vlad.library.entity.Book;
import by.vlad.library.exception.DaoException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * {@code BookDao} extends {@link BasicDao} interface and
 *  represent dao functional of the {@link Book} class
 * @see BasicDao
 * @see Book
 */
public interface BookDao extends BasicDao<Book> {
    /**
     * Find books by the filter map from database
     * @param limit count of books in one page
     * @param filterMap filter map, type {@link Map}
     * @return books list or empty list
     * @throws DaoException if request from database was failed
     */
    List<Book> getBooks(long limit, Map<String, Long[]> filterMap) throws DaoException;

    /**
     * Find book by unique identifier from database
     * @param id book identifier
     * @return book object boxed in {@link Optional} class
     * @throws DaoException if request from database was failed
     */
    Optional<Book> getBookById(long id) throws DaoException;

    /**
     * Counting the number of pages by filter map from database
     * @param filterMap filter map, type {@link Map}
     * @return number of pages
     * @throws DaoException if request from database was failed
     */
    int findNumberOfPage(Map<String, Long[]> filterMap) throws DaoException;

    /**
     * Insert new book in database
     * @param book book entity
     * @return book object boxed in {@link Optional} class
     * @throws DaoException if request from database was failed
     */
    Optional<Book> addNewBook(Book book) throws DaoException;

    /**
     * Update book entity in database
     * @param book book entity
     * @return book object boxed in {@link Optional} class
     * @throws DaoException if request from database was failed
     */
    Optional<Book> updateBook(Book book) throws DaoException;

    /**
     * Find book list by order identifier from database
     * @param orderId - unique order identifier
     * @return book list or empty list
     * @throws DaoException if request from database was failed
     */
    List<Book> getBooksByOrderId(long orderId) throws DaoException;

    /**
     * Find books copies number by string id
     * @param bookIdString string that contains books id
     * @return List of books copies
     * @throws DaoException if request from database was failed
     */
    List<Integer> findBooksCopiesNumber(String bookIdString) throws DaoException;

    /**
     * Find books by title
     * @param title book title
     * @return true if book with this title is already exists, false - if isn't
     * @throws DaoException if request from database was failed
     */
    boolean findBooksByTitle(String title) throws DaoException;
}