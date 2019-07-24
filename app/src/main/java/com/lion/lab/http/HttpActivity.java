package com.lion.lab.http;

import android.os.Bundle;
import android.util.Log;

import com.lion.lab.R;
import com.lion.lab.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class HttpActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);

        String user = "ReactiveX";
//        String user = "xingyuanlionchen";

        Observable<List<Repository>> observable = HttpClient.getService().getRepositories(user);
        observable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .flatMap((Function<List<Repository>, ObservableSource<Repository>>) Observable::fromIterable)
                .flatMap((Function<Repository, ObservableSource<List<Label>>>) repository -> {
                    List<Observable<List<Label>>> observables = new ArrayList<>();
                    observables.add(HttpClient.getService().getLabels(user, repository.getName()));
                    return Observable.merge(observables);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CustomObserver<List<Label>>() {
                    @Override
                    public void onNext(List<Label> value) {
                        Log.d(TAG, "onNext:" + value);
                    }
                });
    }

    abstract class CustomObserver<T> implements Observer<T> {
        @Override
        public void onSubscribe(Disposable d) {
            Log.d(TAG, "onSubscribe:" + Thread.currentThread().getName());
        }

        @Override
        public void onError(Throwable e) {
            Log.d(TAG, "onError:" + e.toString());
        }

        @Override
        public void onComplete() {
            Log.d(TAG, "onComplete");
        }
    }
}
