package com.actvn.shopapp.api.service;


import com.actvn.shopapp.api.model.Data;
import com.actvn.shopapp.api.model.Login;
import com.actvn.shopapp.api.model.Order;
import com.actvn.shopapp.api.model.Products;
import com.actvn.shopapp.api.model.Register;
import com.actvn.shopapp.api.model.ResultLogin;
import com.actvn.shopapp.api.model.ResultRegister;
import com.actvn.shopapp.api.model.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {

    @POST("api/auth/login")
    Call<ResultLogin> login(@Body Login login);

    @POST("api/auth/create")
    Call<ResultRegister> create(@Body Register register);



    @GET("api/auth/user")
    Call<User> getUser(@Header("Authorization") String access_token);

    @GET("api/auth/orders")
    Call<Order> getOrder(@Header("Authorization") String token);

    @GET("api/products")
    Call<Products> getProducts();

    @GET("api/products")
    Call<Products> getSearchProducts(
            @Query("query") String keyword

    );


}
