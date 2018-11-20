package com.example.algorithm.reactive;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class DelegateSubGeneral<T> implements Subscriber<T> {

  Subscriber sub;

  public DelegateSubGeneral(Subscriber<? super T> sub) {
    this.sub = sub;
  }

  @Override
  public void onSubscribe(Subscription s) {
    sub.onSubscribe(s);
  }

  @Override
  public void onNext(T i) {
    sub.onNext(i);
  }

  @Override
  public void onError(Throwable throwable) {
    sub.onError(throwable);
  }

  @Override
  public void onComplete() {
    sub.onComplete();
  }
}
