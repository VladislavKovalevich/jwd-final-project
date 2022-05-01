package by.vlad.library.model.service.impl;

import by.vlad.library.entity.Book;
import by.vlad.library.exception.DaoException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.dao.BookDao;
import by.vlad.library.model.dao.impl.BookDaoImpl;
import by.vlad.library.model.service.AuthorService;
import by.vlad.library.model.service.BookService;
import by.vlad.library.model.service.PublisherService;
import by.vlad.library.validator.BookValidator;
import by.vlad.library.validator.impl.BookValidatorImpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;

public class BookServiceImpl implements BookService {
    private static final String CREATE_NEW_ENTITY_MARKER = "on";
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

        String isNewAuthorCheck = bookData.get(NEW_AUTHOR_CHECK_FORM);
        String isNewPublisherCheck = bookData.get(NEW_PUBLISHER_CHECK_FORM);

        if (isNewAuthorCheck.equals(CREATE_NEW_ENTITY_MARKER)){
            AuthorService authorService = AuthorServiceImpl.getInstance();

            if (!authorService.createNewAuthor(bookData)){
                return isAdded;
            }
        }

        if (isNewPublisherCheck.equals(CREATE_NEW_ENTITY_MARKER)){
            PublisherService publisherService = PublisherServiceImpl.getInstance();

            if (!publisherService.addNewPublisher(bookData)){
                return isAdded;
            }
        }

        BookValidator bookValidator = BookValidatorImpl.getInstance();

        if (!bookValidator.validateCreatedBook(bookData)){
            return isAdded;
        }

        Book book = new Book();
        BookDao bookDao = BookDaoImpl.getInstance();

        try {
            isAdded = bookDao.addNewBook(book);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return isAdded;
    }
}
