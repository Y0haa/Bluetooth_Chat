package com.example.admin.bluetooth_chat;

/**
 * Created by Admin on 11/21/2017.
 */

public interface BasePresenter <V extends  BaseView> {
    void attachView(V view);
    void detachView();
}
