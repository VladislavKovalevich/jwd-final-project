package by.vlad.library.model.service.impl;

import by.vlad.library.entity.Book;
import by.vlad.library.exception.DaoException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.dao.BookDao;
import by.vlad.library.model.dao.impl.BookDaoImpl;
import by.vlad.library.model.service.BookService;

import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {

    private static BookServiceImpl instance;

    private BookServiceImpl(){}

    public static BookServiceImpl getInstance() {
        if (instance == null){
            instance = new BookServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Book> getBooks() throws ServiceException {
        BookDao bookDAO = BookDaoImpl.getInstance();

        List<Book> books;

        try {
            books = bookDAO.getBooks();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return books;
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
}
