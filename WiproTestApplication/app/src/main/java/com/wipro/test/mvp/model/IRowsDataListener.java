package com.wipro.test.mvp.model;

public interface IRowsDataListener {
    void onRowsResultReceived(ServerResponse serverResponse);
    void onError(String s);
}
