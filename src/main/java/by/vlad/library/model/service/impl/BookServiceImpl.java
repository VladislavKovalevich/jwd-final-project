package by.vlad.library.model.service.impl;

import by.vlad.library.entity.*;
import by.vlad.library.exception.DaoException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.dao.BookDao;
import by.vlad.library.model.dao.impl.BookDaoImpl;
import by.vlad.library.model.service.BookService;
import by.vlad.library.util.ConverterToCSVString;
import by.vlad.library.validator.BookValidator;
import by.vlad.library.validator.impl.BookValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;

/**
 * {@code BookServiceImpl} class implements functional of {@link BookService}
 * @see Book
 * @see BookService
 */
public class BookServiceImpl implements BookService {
    private static final Logger logger = LogManager.getLogger();
    private static final String BOOK_COMPONENTS_DELIMITER = "\\|";
    private static final String PREV_PAGE = "prev";
    private static final String NEXT_PAGE = "next";

    private static BookServiceImpl instance;

    private BookServiceImpl() {
    }

    public static BookServiceImpl getInstance() {
        if (instance == null) {
            instance = new BookServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Book> getAllBooks(String direction, Map<String, Long> paginationData, Map<String, Long[]> filterMap) throws ServiceException {
        List<Book> books;

        try {

            if (direction == null || paginationData.isEmpty()) {
                direction = NEXT_PAGE;
                initPaginationDataMap(paginationData, filterMap);
            }

            BookDao bookDAO = BookDaoImpl.getInstance();
            long tempPage = paginationData.get(CURRENT_PAGE_NUM);

            if (direction.equals(NEXT_PAGE)){
                tempPage++;
            }else{
                tempPage--;
            }

            books = bookDAO.getBooks(tempPage, filterMap);

            paginationData.put(CURRENT_PAGE_NUM, tempPage);
        } catch (DaoException e) {
            logger.error("Method getAllBooks from book service was failed" + e);
            throw new ServiceException("Method getAllBooks from book service was failed", e);
        }

        return books;
    }

    private void initPaginationDataMap(Map<String, Long> paginationMap, Map<String, Long[]> filterMap) throws DaoException {
        long pagesNumber = BookDaoImpl.getInstance().findNumberOfPage(filterMap);
        paginationMap.put(PAGES_NUMBER, pagesNumber);
        paginationMap.put(CURRENT_PAGE_NUM, 0L);
    }

    @Override
    public Optional<Book> getBookById(long id) throws ServiceException {
        BookDao bookDAO = BookDaoImpl.getInstance();
        Optional<Book> optionalBook;

        try {
            optionalBook = bookDAO.getBookById(id);
        } catch (DaoException e) {
            logger.error("Method getBookById from book service was failed" + e);
            throw new ServiceException("Method getBookById from book service was failed", e);
        }

        return optionalBook;
    }

    @Override
    public Optional<Book> addBook(Map<String, String> bookData) throws ServiceException {
        Optional<Book> optionalBook = Optional.empty();

        BookValidator bookValidator = BookValidatorImpl.getInstance();

        if (!bookValidator.validateBookData(bookData)){
            return optionalBook;
        }

        String title = bookData.get(TITLE_FORM);
        long author_id = Long.parseLong(bookData.get(AUTHOR_FORM));
        long publisher_id = Long.parseLong(bookData.get(PUBLISHER_FORM));
        long genre_id = Long.parseLong(bookData.get(GENRE_FORM));
        String description = bookData.get(DESCRIPTION_FORM);
        int pages_count = Integer.parseInt(bookData.get(PAGES_COUNT_FORM));
        int copies_number = Integer.parseInt(bookData.get(COPIES_NUMBER_FORM));
        int year = Integer.parseInt(bookData.get(RELEASE_YEAR_FORM));

        BookDao bookDao = BookDaoImpl.getInstance();

        try {

            if (!bookDao.findBooksByTitle(title)) {
                Book book = Book.getBuilder()
                        .withTitle(title)
                        .withGenre(new Genre(genre_id))
                        .withAuthor(new Author(author_id))
                        .withPublisher(new Publisher(publisher_id))
                        .withCopiesNumber(copies_number)
                        .withDescription(description)
                        .withNumberOfPages(pages_count)
                        .withReleaseYear(Year.of(year))
                        .withImage(new Image())
                        .buildBook();

                optionalBook = bookDao.addNewBook(book);
            }else {
                bookData.put(WRONG_TITLE_EXISTS_FORM, BookValidator.WRONG_FORMAT_MARKER);
            }

        } catch (DaoException e) {
            logger.error("Method addBook from book service was failed" + e);
            throw new ServiceException("Method addBook from book service was failed", e);
        }

        return optionalBook;
    }

    @Override
    public Optional<Book> updateBook(Map<String, String> bookData) throws ServiceException {
        Optional<Book> optionalBook = Optional.empty();

        BookValidator bookValidator = BookValidatorImpl.getInstance();

        if (!bookValidator.validateBookData(bookData)){
            return optionalBook;
        }

        String[] authorParams = bookData.get(AUTHOR_FORM).split(BOOK_COMPONENTS_DELIMITER);
        String[] genreParams = bookData.get(GENRE_FORM).split(BOOK_COMPONENTS_DELIMITER);
        String[] publisherParams = bookData.get(PUBLISHER_FORM).split(BOOK_COMPONENTS_DELIMITER);

        long bookId = Long.parseLong(bookData.get(BOOK_ID));
        long imageId = Long.parseLong(bookData.get(IMAGE_ID));
        String title = bookData.get(TITLE_FORM);
        String description = bookData.get(DESCRIPTION_FORM);
        int pages_count = Integer.parseInt(bookData.get(PAGES_COUNT_FORM));
        int copies_number = Integer.parseInt(bookData.get(COPIES_NUMBER_FORM));
        int year = Integer.parseInt(bookData.get(RELEASE_YEAR_FORM));

        BookDao bookDao = BookDaoImpl.getInstance();

        try {
            if (!bookDao.findBooksByTitle(title)) {
                Book book = Book.getBuilder()
                        .withId(bookId)
                        .withTitle(title)
                        .withGenre(new Genre(Long.parseLong(genreParams[0]), genreParams[1]))
                        .withAuthor(new Author(Long.parseLong(authorParams[0]), authorParams[1], authorParams[2]))
                        .withPublisher(new Publisher(Long.parseLong(publisherParams[0]), publisherParams[1]))
                        .withCopiesNumber(copies_number)
                        .withDescription(description)
                        .withNumberOfPages(pages_count)
                        .withReleaseYear(Year.of(year))
                        .withImage(new Image(imageId))
                        .buildBook();

                optionalBook = bookDao.updateBook(book);
            }else{
                bookData.put(WRONG_TITLE_EXISTS_FORM, BookValidator.WRONG_FORMAT_MARKER);
            }
        } catch (DaoException e) {
            logger.error("Method updateBook from book service was failed" + e);
            throw new ServiceException("Method updateBook from book service was failed", e);
        }

        return optionalBook;
    }

    @Override
    public List<Book> getBooksByOrderId(long orderId) throws ServiceException {
        List<Book> books;
        BookDao bookDao = BookDaoImpl.getInstance();

        try {
            books = bookDao.getBooksByOrderId(orderId);
        } catch (DaoException e) {
            logger.error("Method getBooksByOrderId from book service was failed" + e);
            throw new ServiceException("Method getBooksByOrderId from book service was failed", e);
        }

        return books;
    }

    @Override
    public boolean isBooksCopiesExists(List<Book> books) throws ServiceException {
        boolean isExists;
        BookDao bookDao = BookDaoImpl.getInstance();
        ConverterToCSVString converter = ConverterToCSVString.getInstance();

        String CSVBookIds = converter.convertEntityList(books);

        try {
            List<Integer> copiesList = bookDao.findBooksCopiesNumber(CSVBookIds);

            isExists = !copiesList.contains(0);
        } catch (DaoException e) {
            logger.error("Method isBooksCopiesExists from book service was failed" + e);
            throw new ServiceException("Method isBooksCopiesExists from book service was failed", e);
        }

        return isExists;
    }
}
