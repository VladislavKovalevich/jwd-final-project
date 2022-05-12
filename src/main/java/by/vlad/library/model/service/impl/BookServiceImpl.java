package by.vlad.library.model.service.impl;

import by.vlad.library.entity.Author;
import by.vlad.library.entity.Book;
import by.vlad.library.entity.Genre;
import by.vlad.library.entity.Publisher;
import by.vlad.library.exception.DaoException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.dao.BookDao;
import by.vlad.library.model.dao.impl.BookDaoImpl;
import by.vlad.library.model.service.BookService;
import by.vlad.library.validator.BookValidator;
import by.vlad.library.validator.impl.BookValidatorImpl;

import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;

public class BookServiceImpl implements BookService {
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
    public List<Book> getAllBooks(String direction, Map<String, Long> paginationData) throws ServiceException {
        List<Book> books;

        try {

            if (direction == null || paginationData.isEmpty()) {
                direction = NEXT_PAGE;
                initPaginationDataMap(paginationData);
            }

            BookDao bookDAO = BookDaoImpl.getInstance();

            if (direction.equals(NEXT_PAGE)){
                long lastId = paginationData.get(LAST_ID);
                books = bookDAO.findNextBooks(lastId);
            }else{
                long firstId = paginationData.get(FIRST_ID);
                books = bookDAO.findPrevBooks(firstId);
            }

            if (!books.isEmpty()){
                long newFirstId = books.get(0).getId();
                long newLastId = books.get(books.size() - 1).getId();

                paginationData.put(FIRST_ID, newFirstId);
                paginationData.put(LAST_ID, newLastId);

                long currPageNumber = paginationData.get(CURRENT_PAGE_NUM);
                currPageNumber = currPageNumber + (direction.equals(NEXT_PAGE) ? 1 : -1);

                paginationData.put(CURRENT_PAGE_NUM, currPageNumber);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return books;
    }

    private void initPaginationDataMap(Map<String, Long> paginationData) throws DaoException {
        long pagesNumber = BookDaoImpl.getInstance().findNumberOfBooks();
        paginationData.put(PAGES_NUMBER, pagesNumber);
        paginationData.put(CURRENT_PAGE_NUM, 0L);
        paginationData.put(FIRST_ID, 0L);
        paginationData.put(LAST_ID, 0L);
    }

    @Override
    public Optional<Book> getBookById(long id) throws ServiceException {
        BookDao bookDAO = BookDaoImpl.getInstance();
        Optional<Book> optionalBook;

        try {
            optionalBook = bookDAO.getBookById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return optionalBook;
    }

    @Override
    public boolean addBook(Map<String, String> bookData) throws ServiceException {
        boolean isAdded = false;

        BookValidator bookValidator = BookValidatorImpl.getInstance();

        if (!bookValidator.validateBookData(bookData)){
            return isAdded;
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

            Book book = Book.getBuilder()
                    .withTitle(title)
                    .withGenre(new Genre(genre_id))
                    .withAuthor(new Author(author_id))
                    .withPublisher(new Publisher(publisher_id))
                    .withCopiesNumber(copies_number)
                    .withDescription(description)
                    .withNumberOfPages(pages_count)
                    .withReleaseYear(Year.of(year))
                    .buildBook();

            isAdded = bookDao.addNewBook(book);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return isAdded;
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
        String title = bookData.get(TITLE_FORM);
        String description = bookData.get(DESCRIPTION_FORM);
        int pages_count = Integer.parseInt(bookData.get(PAGES_COUNT_FORM));
        int copies_number = Integer.parseInt(bookData.get(COPIES_NUMBER_FORM));
        int year = Integer.parseInt(bookData.get(RELEASE_YEAR_FORM));

        BookDao bookDao = BookDaoImpl.getInstance();

        try {

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
                    .buildBook();

            optionalBook = bookDao.updateBook(book);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return optionalBook;
    }
}
