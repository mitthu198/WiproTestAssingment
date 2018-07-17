package com.wipro.test.mvp.model;

import retrofit2.http.GET;
import rx.Observable;

/**
 * This interface is passed to retrofit to create rest adapter
 */
public interface RowsRestService {
    final String SERVICE_ENDPOINT = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json/";
    /**
     * Use Retrofit to get JSON from URL.
     * @return Observable
     */
    @GET(".")
    Observable<ServerResponse> getCountry();


}
