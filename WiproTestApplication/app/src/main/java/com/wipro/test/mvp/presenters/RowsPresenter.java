package com.wipro.test.mvp.presenters;


import com.wipro.test.mvp.model.IRowsDataListener;
import com.wipro.test.mvp.model.Rows;
import com.wipro.test.mvp.model.RowsRetrofitClient;
import com.wipro.test.mvp.model.ServerResponse;
import com.wipro.test.mvp.views.RowListView;

import java.util.ArrayList;
import java.util.List;

/**
 * This is presentation class of MainActivity.
 * This class is responsible for presenting the data to view layer by calling {@link RowListView} mehtods
 * This class also interact with the model layer to download the data from server by calling {@link RowsRetrofitClient}
 * The model layer return the data back by calling {@link IRowsDataListener} method(onError and onRowsResultReceived)
 */
public class RowsPresenter implements IRowsDataListener {
    private final static String TAG = "RowsPresenter";

    private RowListView view;
    private List<Rows> rows;
    private RowsRetrofitClient rowsRetrofitClient;

    public RowsPresenter(RowListView view) {
        this.view = view;
        rows = new ArrayList<Rows>();
        rowsRetrofitClient = new RowsRetrofitClient();
    }

    /**
     * asynchronous function to start load Rows list
     *
     */
    public void startLoadRows() {
        if (view == null) {
            return;
        }
        view.showLoading();

        rowsRetrofitClient.callRowsData(this);
    }


    @Override
    public void onRowsResultReceived(ServerResponse serverResponse) {
        List<Rows> list = serverResponse.getRows();
        view.showResult(list);
        view.showTitle(serverResponse.getTitle());
        view.hideLoading();
    }

    @Override
    public void onError(String s) {
        view.hideLoading();
        view.showError(s);
    }
}
