package com.wipro.test.mvp.model;

import android.text.TextUtils;

import java.util.Iterator;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * This class is used by {@link com.wipro.test.mvp.presenters.RowsPresenter}
 * to request the data from network.
 */
public class RowsRetrofitClient {

    /**
     *
     * @param iRowsDataListener: used for interacting with the presenter to send the data/error_message
     *                         back to presenter, so that prasenter can update the view.
     */
    public void callRowsData(final IRowsDataListener iRowsDataListener) {
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(RowsRestService.SERVICE_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
         restAdapter.create(RowsRestService.class).getCountry()
                 .map(new Func1<ServerResponse, ServerResponse>() {
                     @Override
                     public ServerResponse call(ServerResponse serverResponse) {
                         /**
                          * Use Object stream of RxJava to filter null list item
                          */
                         List<Rows> list = serverResponse.getRows();
                         if (list != null) {
                             Iterator<Rows> iterator = list.iterator();
                             while (iterator.hasNext()) {
                                 Rows fact = iterator.next();
                                 if (TextUtils.isEmpty(fact.getTitle())) {
                                     iterator.remove();
                                 }
                             }
                         }
                         return serverResponse;
                     }
                 })
                 .subscribeOn(Schedulers.newThread())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Subscriber<ServerResponse>() {
                     @Override
                     public void onCompleted() {
                         // finish all work

                     }

                     @Override
                     public void onError(Throwable e) {
                         // has some exceptions
                         if (iRowsDataListener != null ) {
                             iRowsDataListener.onError(e.getMessage());
                         }

                     }

                     @Override
                     public void onNext(ServerResponse serverResponse) {
                         // get the parse result
                         if (iRowsDataListener != null ) {
                             iRowsDataListener.onRowsResultReceived(serverResponse);
                         }

                     }
                 });;
    }
}
