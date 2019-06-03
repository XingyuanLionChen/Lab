package com.lion.lab.binder;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.lion.lab.base.BaseService;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BookManagerService extends BaseService {

    // CopyOnWriteArrayList支持并发读/写，自动进行线程同步
    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();

    private Binder mBinder = new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() {
            return mBookList;
        }

        @Override
        public void addBook(Book book) {
            mBookList.add(book);

        }
    };

    public BookManagerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBookList.add(new Book(1, "Android"));
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
    }
}
