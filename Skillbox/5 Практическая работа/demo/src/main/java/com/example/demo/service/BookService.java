package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.entity.Category;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    @Cacheable(value = "books", key = "#title + #author")
    public Optional<Book> findBook(String title, String author) {
        return bookRepository.findByTitleAndAuthor(title, author);
    }

    @Cacheable(value = "categories", key = "#categoryName")
    public List<Book> findBooksByCategory(String categoryName) {
        return bookRepository.findByCategory_Name(categoryName);
    }

    @CacheEvict(value = { "books", "categories" }, allEntries = true)
    public Book createBook(String title, String author, String categoryName) {
        Category category = categoryRepository.findByName(categoryName)
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setName(categoryName);
                    return categoryRepository.save(newCategory);
                });

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setCategory(category);

        return bookRepository.save(book);
    }

    @CacheEvict(value = { "books", "categories" }, allEntries = true)
    public Book updateBook(Long id, String title, String author, String categoryName) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Category category = categoryRepository.findByName(categoryName)
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setName(categoryName);
                    return categoryRepository.save(newCategory);
                });

        book.setTitle(title);
        book.setAuthor(author);
        book.setCategory(category);

        return bookRepository.save(book);
    }

    @CacheEvict(value = { "books", "categories" }, allEntries = true)
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
