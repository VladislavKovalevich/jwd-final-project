package by.vlad.library.model.dao;

import by.vlad.library.entity.Order;
import by.vlad.library.entity.OrderType;
import by.vlad.library.exception.DaoException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * {@code BookDao} extends {@link BasicDao} interface and
 *  represent dao functional of the {@link Order} class
 * @see BasicDao
 * @see Order
 */
public interface OrderDao extends BasicDao<Order> {
    /**
     * Find orders by user identifier
     * @param userId user identifier
     * @return order list or empty list
     * @throws DaoException if request from database was failed
     */
    List<Order> findOrdersByUserId(long userId) throws DaoException;

    /**
     * Find orders by status
     * @param orderStatusesStr status list as CSV string
     * @return order list or empty list
     * @throws DaoException if request from database was failed
     */
    List<Order> findOrdersByStatus(String orderStatusesStr) throws DaoException;

    /**
     * Find order by unique identifier
     * @param orderId order identifier
     * @return order object boxed in {@link Optional} class
     * @throws DaoException if request from database was failed
     */
    Optional<Order> findOrderById (long orderId) throws DaoException;

    /**
     * Insert order in database
     * @param userId user identifier
     * @param createdDate date, when order was created
     * @param orderTypeId order type identifier
     * @return order identifier boxed in {@link Optional} class
     * @throws DaoException if request from database was failed
     */
    Optional<Long> insertOrder(long userId, LocalDate createdDate, long orderTypeId) throws DaoException;

    /**
     * Add book to order in database (insert row to table library.books_orders)
     * @param orderId order identifier
     * @param bookId book identifier
     * @return true if book was added, false - if was not
     * @throws DaoException if request from database was failed
     */
    boolean insertBookToOrder(long orderId, long bookId) throws DaoException;

    /**
     * Remove book from order in database(delete row from table library.books_orders)
     * @param orderId order identifier
     * @param bookId book identifier
     * @return true if book was removed, false - if was not
     * @throws DaoException if request from database was failed
     */
    boolean deleteBookFromOrder(long orderId, long bookId) throws DaoException;

    /**
     * Delete order from database by order identifier
     * @param orderId order identifier
     * @return true if order was deleted, false - if was not
     * @throws DaoException if request from database was failed
     */
    boolean deleteOrder(long orderId) throws DaoException;

    /**
     * Update order status in database
     * @param orderId order identifier
     * @param reserveDate date when order was reserved
     * @param statusId order status identifier
     * @return true if status was changed, false - if was not
     * @throws DaoException if request from database was failed
     */
    boolean updateOrderStatus(long orderId, LocalDate reserveDate, long statusId) throws DaoException;

    /**
     * Update order status and book copies number
     * @param orderId order identifier
     * @param orderType type of order
     * @param booksIdString books id list as CSV string
     * @param booksCount number of books in order
     * @param date operation date
     * @param statusId order status identifier
     * @return true if order status and book copies number were changed, false - if were not
     * @throws DaoException if request from database was failed
     */
    boolean updateStatusAndCopiesNumber(long orderId, OrderType orderType, String booksIdString, int booksCount, LocalDate date, long statusId) throws DaoException;

    /**
     * Find book in some order
     * @param orderId order identifier
     * @param bookId book identifier
     * @return true - if order contains this book, false - if isn't
     * @throws DaoException if request from database was failed
     */
    boolean bookIsAlreadyInOrder(long orderId, long bookId) throws DaoException;

    /**
     *
     * @param orderId
     * @return
     * @throws DaoException
     */
    boolean isLimitOfBooks(long orderId) throws DaoException;

    /**
     *
     * @param userId
     * @return
     * @throws DaoException
     */
    boolean isOrderCountLimit(long userId) throws DaoException;
}
