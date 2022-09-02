package com.example.bmicalculatormvc;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ModelRESTfulAPI {
    String BASE_URL = "http://webstrar99.fulton.asu.edu";

    @GET("/page3/Service1.svc/calculateBMI")
    Call<WebPageResult> getBodyMassIndex(@Query("height") double height, @Query("weight") double weight);

}
