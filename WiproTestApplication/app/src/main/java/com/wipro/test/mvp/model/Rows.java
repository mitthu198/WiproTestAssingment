package com.wipro.test.mvp.model;

/**
 * A pojo class for list row being used by retrofit to parse the json response.
 */
public class Rows {

    private String title;
    private String description;
    private String imageHref;

    public String getDescription() {
        return description;
    }

    public String getImageHref() {
        return imageHref;
    }

    public String getTitle() {
        return title;
    }
}
