package com.swiggy.swiggyapplication.network;

import com.swiggy.swiggyapplication.responsedto.VariantsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Amardeep on 7/2/16.
 */
public interface ApiService {

    @GET("/bins/3b0u2")
    Call<VariantsResponse> requestCategories();
}
