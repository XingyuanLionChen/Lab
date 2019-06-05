package com.lion.lab.binder;

import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.lion.lab.base.BaseService;

import java.util.ArrayList;
import java.util.List;

public class AuthorManagerService extends BaseService {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new Binder();
    }

    private class Binder extends IAuthorManager.Stub {
        private List<Author> authors;

        Binder() {
            authors = new ArrayList<>();
        }

        @Override
        public List<Author> getAuthors() {
            return authors;
        }

        @Override
        public void addAuthor(Author author) {
            authors.add(author);
        }
    }
}
