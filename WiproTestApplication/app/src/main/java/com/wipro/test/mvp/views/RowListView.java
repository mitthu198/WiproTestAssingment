package com.wipro.test.mvp.views;

import com.wipro.test.mvp.model.Rows;

import java.util.List;

/**
 * This interface is supplied to {@link com.wipro.test.mvp.presenters.RowsPresenter}
 * to update the view and is implemented by {@link com.wipro.test.MainActivity}
 */
public interface RowListView  {
    /**
     * show loading view
     */
    void showLoading();

    /**
     * hide loading view when finish load or exception
     */
    void hideLoading();

    /**
     * show error message
     * @param msg
     */
    void showError(String msg);

    /**
     * show list item
     * @param list
     */
    void showResult(List<Rows> list);

    /**
     * update action bar title
     * @param title
     */
    void showTitle(String title);

}
