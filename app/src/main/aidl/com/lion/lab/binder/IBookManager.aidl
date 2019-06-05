// IBookManager.aidl
package com.lion.lab.binder;

// Declare any non-default types here with import statements
import com.lion.lab.binder.Book;
interface IBookManager {
    List<Book> getBooks();
    void addBook(in Book book);
}