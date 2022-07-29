package by.vlad.library.model.service;

import by.vlad.library.entity.Book;
import by.vlad.library.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * {@code BookService} represent a functional of business logic to work with {@link Book} class
 */
public interface BookService {
    /**
     * Get book list
     * @param direction page direction value ("NEXT" when user wants to see next page, "PREV" - previous page)
     * @param paginationData pagination map that contains count of pages, current page
     * @param filterMap map with book search parameters (in stock, list of authors, genres, publishers)
     * @return book list or empty list
     * @throws ServiceException if dao method throw {@link by.vlad.library.exception.DaoException}
     */
    List<Book> getAllBooks(String direction, Map<String, Long> paginationData, Map<String, Long[]> filterMap) throws ServiceException;

    /**
     * Get book by identifier
     * @param id book identifier
     * @return object book boxed in {@link Optional} class or empty Optional object
     * @throws ServiceException if dao method throw {@link by.vlad.library.exception.DaoException}
     */
    Optional<Book> getBookById(long id) throws ServiceException;

    /**
     * Add new book to library
     * @param bookData map with book data
     * as key use {@link by.vlad.library.controller.command.AttributeAndParamsNames}
     * @return object book boxed in {@link Optional} class or empty Optional object
     * @throws ServiceException if dao method throw {@link by.vlad.library.exception.DaoException}
     */
    Optional<Book> addBook(Map<String, String> bookData) throws ServiceException;

    /**
     *
     * @param bookData map with book data
     * as key use {@link by.vlad.library.controller.command.AttributeAndParamsNames}
     * @return object book boxed in {@link Optional} class or empty Optional object
     * @throws ServiceException if dao method throw {@link by.vlad.library.exception.DaoException}
     */
    Optional<Book> updateBook(Map<String, String> bookData) throws ServiceException;

    /**
     * Get books by order identifier
     * @param orderId order identifier
     * @return book list or empty list
     * @throws ServiceException if dao method throw {@link by.vlad.library.exception.DaoException}
     */
    List<Book> getBooksByOrderId(long orderId) throws ServiceException;

    /**
     * Check is books have more than 0 copies
     * @param books book list
     * @return true if all books have 1 or more copies, false - if have not
     * @throws ServiceException if dao method throw {@link by.vlad.library.exception.DaoException}
     */
    boolean isBooksCopiesExists(List<Book> books) throws ServiceException;
}
