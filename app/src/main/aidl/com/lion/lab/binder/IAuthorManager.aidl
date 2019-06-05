package com.lion.lab.binder;

// Declare any non-default types here with import statements
import com.lion.lab.binder.Author;
interface IAuthorManager {
    List<Author> getAuthors();
    void addAuthor(in Author author);
}