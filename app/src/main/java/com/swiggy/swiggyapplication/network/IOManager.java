package com.swiggy.swiggyapplication.network;


import com.swiggy.swiggyapplication.responsedto.VariantsResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Amardeep on 13/2/16.
 */
public class IOManager {

    private static ApiService apiService;
    private static Retrofit getRetroFit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        return  new Retrofit.Builder()
                .baseUrl(Config.UrlConstants.REQUEST_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static ApiService getApiService() {
        if (apiService == null) {
            apiService = getRetroFit().create(ApiService.class);
        }
        return apiService;
    }

    public static void requestCategories(Callback<VariantsResponse> callback) {
        ApiService service = getApiService();
        Call<VariantsResponse> model = service.requestCategories();
        model.enqueue(callback);
    }
}
