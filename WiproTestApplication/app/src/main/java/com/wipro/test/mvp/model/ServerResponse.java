package com.wipro.test.mvp.model;

import java.util.List;

/**
 * This class being used by retrofit to parse the response
 */
public class ServerResponse {
    /**
     * These members will be used by Retrofit, so we must use the same name with json
     */
    private String title;
    private List<Rows> rows;

    public List<Rows> getRows() {
        return rows;
    }

    public String getTitle() {
        return title;
    }
}
