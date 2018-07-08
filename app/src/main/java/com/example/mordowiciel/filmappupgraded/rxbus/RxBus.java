package com.example.mordowiciel.filmappupgraded.rxbus;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

public class RxBus {

    private PublishSubject<Object> eventBus = PublishSubject.create();

    public void send(Object o) {
        eventBus.onNext(o);
    }

    public Disposable register(final Class eventClass, Consumer<? super Object> consumer) {
        return eventBus
                .filter(o -> o.getClass().equals(eventClass))
                .subscribe(consumer);
    }
}
