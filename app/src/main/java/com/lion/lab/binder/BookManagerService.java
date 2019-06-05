package com.lion.lab.binder;

import android.content.Intent;
import android.os.IBinder;

import com.lion.lab.base.BaseService;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BookManagerService extends BaseService {
    private Binder binder;

    @Override
    public void onCreate() {
        super.onCreate();
        binder = new Binder();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return binder;
    }

    private class Binder extends IBookManager.Stub {
        private CopyOnWriteArrayList<Book> books;

        Binder() {
            books = new CopyOnWriteArrayList<>();
            books.add(new Book(1, "Android"));
        }

        @Override
        public List<Book> getBooks() {
            return books;
        }

        @Override
        public void addBook(Book book) {
            books.add(book);
        }
    }
}
