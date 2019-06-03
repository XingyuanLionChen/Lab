package com.lion.lab.binder;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.lion.lab.R;
import com.lion.lab.base.BaseActivity;

import java.util.List;

public class BinderActivity extends BaseActivity {

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            try {
                IBookManager bookManager = IBookManager.Stub.asInterface(service);
                List<Book> list = bookManager.getBookList();
                Log.d(TAG, "onServiceConnected: query book list, list type: " + list.getClass().getCanonicalName());
                Log.d(TAG, "onServiceConnected: query book list: " + list.toString());
                Book newBook = new Book(2, "iOS");
                Log.d(TAG, "onServiceConnected: add book: " + newBook);
                bookManager.addBook(newBook);
                List<Book> newList = bookManager.getBookList();
                Log.d(TAG, "onServiceConnected: query book list: " + newList.toString());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder);

        Intent intent = new Intent(this, BookManagerService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }
}
