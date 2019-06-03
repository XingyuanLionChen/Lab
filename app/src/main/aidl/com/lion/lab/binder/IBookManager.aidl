// IBookManager.aidl
package com.lion.lab.binder;

// Declare any non-default types here with import statements
import com.lion.lab.binder.Book;
interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
}