package by.vlad.library.model.dao;

import by.vlad.library.entity.Order;
import by.vlad.library.exception.DaoException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
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
     * @param order order entity
     * @return true if status was changed, false - if was not
     * @throws DaoException if request from database was failed
     */
    Optional<Order> updateOrderStatus(Order order) throws DaoException;

    /**
     * Update order status and book copies number
     * @param orderType type of order
     * @param booksIdString books id list as CSV string
     * @param booksCount number of books in order
     * @return true if order status and book copies number were changed, false - if were not
     * @throws DaoException if request from database was failed
     */
    Optional<Order> updateStatusAndCopiesNumber(Order orderType, String booksIdString, int booksCount) throws DaoException;

    /**
     * Find book in some order
     * @param orderId order identifier
     * @param bookId book identifier
     * @return true - if order contains this book, false - if isn't
     * @throws DaoException if request from database was failed
     */
    boolean bookIsAlreadyInOrder(long orderId, long bookId) throws DaoException;

    /**
     * Check book limit in one order
     * @param orderId order identifier
     * @return true if it's limit, false - if isn't
     * @throws DaoException if request from database was failed
     */
    boolean isLimitOfBooks(long orderId) throws DaoException;

    /**
     * Check order count limit
     * @param userId user identifier
     * @return true if it's a limit, false - if isn't
     * @throws DaoException if request from database was failed
     */
    boolean isOrderCountLimit(long userId) throws DaoException;

    /**
     * Find number of overdue orders by date (counting by last month)
     * @param userId user identifier
     * @param startDate begin date
     * @param endDate end date
     * @return count of overdue orders
     * @throws DaoException if request from database was failed
     */
    int findOverdueOrders(long userId, LocalDate startDate, LocalDate endDate) throws DaoException;

    /**
     * Return list of user orders by filter and pagination maps
     * @param userId user identifier
     * @param tempPage current page
     * @param filterData filter map (type, status)
     * @return order list
     * @throws DaoException
     */
    List<Order> getUserOrders(long userId, long tempPage, Map<String, Long[]> filterData) throws DaoException;

    /**
     * Find number of pages
     * @param userId user identifier
     * @param filterMap map with filter parameters
     * @return count of pages
     * @throws DaoException if request from database was failed
     */
    long findNumberOfUserOrdersPage(long userId, Map<String, Long[]> filterMap) throws DaoException;
}
