package by.vlad.library.model.service;

import by.vlad.library.entity.Book;
import by.vlad.library.entity.Order;
import by.vlad.library.entity.OrderStatus;
import by.vlad.library.entity.OrderType;
import by.vlad.library.exception.ServiceException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * {@code OrderService} represent a functional of business logic to work with {@link Order} class
 */
public interface OrderService {
    /**
     * Get orders by user identifier
     * @param userId user identifier
     * @return order list or empty list
     * @throws ServiceException if dao method throw {@link by.vlad.library.exception.DaoException}
     */
    List<Order> getOrdersByUserId(long userId) throws ServiceException;

    /**
     * Get order information by order identifier
     * @param orderId order identifier
     * @return object order boxed in {@link Optional} class or empty Optional object
     * @throws ServiceException if dao method throw {@link by.vlad.library.exception.DaoException}
     */
    Optional<Order> getOrderInfoById(long orderId) throws ServiceException;

    /**
     * Create order
     * @param userId user identifier
     * @param bookId book identifier
     * @param orderId order identifier
     * @return true if order was created, false - if was not
     * @throws ServiceException if dao method throw {@link by.vlad.library.exception.DaoException}
     */
    boolean createOrder(long userId, long bookId, long orderId, Map<String, Boolean> orderMap) throws ServiceException;

    /**
     * Get orders by status
     * @param orderStatuses status array
     * @return order list or empty list
     * @throws ServiceException if dao method throw {@link by.vlad.library.exception.DaoException}
     */
    List<Order> getOrdersByStatus(OrderStatus[] orderStatuses) throws ServiceException;

    /**
     * Change books copies and order status
     * @param orderId order identifier
     * @param orderType type of order
     * @param books book list
     * @param statusId status identifier (RETURNED or ORDERED), depending on the status of the order, the books will be returned or picked up
     * @param date operation date
     * @return true if operation finished successfully, false - if didn't
     * @throws ServiceException if dao method throw {@link by.vlad.library.exception.DaoException}
     */
    boolean changeBooksOrderStatus(long orderId, OrderType orderType, List<Book> books, long statusId, LocalDate date) throws ServiceException;

    /**
     * Change order status
     * @param orderId order identifier
     * @param statusId status identifier
     * @return true if status changed, false - if wasn't
     * @throws ServiceException if dao method throw {@link by.vlad.library.exception.DaoException}
     */
    boolean changeOrderStatus(long orderId, long statusId) throws ServiceException;

    /**
     * Add book to order
     * @param orderId order identifier
     * @param bookId book identifier
     * @return true if book added, false - if wasn't
     * @throws ServiceException if dao method throw {@link by.vlad.library.exception.DaoException}
     */
    boolean addBookToOrder(long orderId, long bookId, Map<String, Boolean> bookMap) throws ServiceException;

    /**
     * Delete order
     * @param orderId order identifier
     * @return true if order was deleted, false - if wasn't
     * @throws ServiceException if dao method throw {@link by.vlad.library.exception.DaoException}
     */
    boolean deleteOrder(long orderId) throws ServiceException;

    /**
     * Remove book from order
     * @param orderId order identifier
     * @param bookId book identifier
     * @return true if book was removed, false - if wasn't
     * @throws ServiceException if dao method throw {@link by.vlad.library.exception.DaoException}
     */
    boolean removeBookFromOrder(long orderId, long bookId) throws ServiceException;
}
