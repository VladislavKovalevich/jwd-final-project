package by.vlad.JavaWebProject.model.service.impl;

import by.vlad.JavaWebProject.entity.Book;
import by.vlad.JavaWebProject.exception.DaoException;
import by.vlad.JavaWebProject.exception.ServiceException;
import by.vlad.JavaWebProject.model.dao.BookDAO;
import by.vlad.JavaWebProject.model.dao.impl.BookDAOImpl;
import by.vlad.JavaWebProject.model.service.BookService;

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
        BookDAO bookDAO = BookDAOImpl.getInstance();

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
        BookDAO bookDAO = BookDAOImpl.getInstance();
        Optional<Book> optionalBook;

        try {
            optionalBook = bookDAO.getBookById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return optionalBook;
    }
}
