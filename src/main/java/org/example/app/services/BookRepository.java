package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository implements ProjectRepository<Book>, ApplicationContextAware {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    private final List<Book> repo = new ArrayList<>();
    private ApplicationContext contex;

    @Override
    public List<Book> retreiveAll() {
        return new ArrayList<>(repo);
    }

    @Override
    public void store(Book book) {
        book.setId(contex.getBean(IdProvider.class).provideId(book));
        logger.info("store new book: " + book);
        repo.add(book);
    }

    @Override
    public boolean removeItemById(String bookIdToRemove) {
        for (Book book : retreiveAll()) {
            if (book.getId().equals(bookIdToRemove)) {
                logger.info("remove book completed: " + book);
                return repo.remove(book);
            }
        }
        return false;
    }

    @Override
    public boolean removeItemByAuthor(String bookAuthorToRemove) {
        boolean isContains = false;
        for (Book book : retreiveAll()) {
            if (book.getAuthor().equalsIgnoreCase(bookAuthorToRemove)) {
                logger.info("remove book completed: " + book);
                isContains = repo.remove(book);
            }
        }
        return isContains;
    }

    @Override
    public boolean removeItemByTitle(String bookTitleToRemove) {
        boolean isContains = false;
        for (Book book : retreiveAll()) {
            if (book.getTitle().equalsIgnoreCase(bookTitleToRemove)) {
                logger.info("remove book completed: " + book);
                isContains = repo.remove(book);
            }
        }
        return isContains;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.contex = applicationContext;
    }
}
