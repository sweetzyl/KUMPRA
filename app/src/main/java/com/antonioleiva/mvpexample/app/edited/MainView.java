package com.antonioleiva.mvpexample.app.edited;

import java.util.List;

/**
 * Created by Sweetzyl on 7/3/2015.
 */
public interface MainView {
    public void showProgress();

    public void hideProgress();

    public void setItems(List<String> items);

    public void showMessage(String message);



}
