package com.example.sim.network;

import com.example.sim.dto.account.LoginDTO;
import com.example.sim.dto.account.LoginResponse;
import com.example.sim.dto.account.RegisterDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AccountApi {

    @POST("/api/account/register")
    public Call<Void> register(@Body RegisterDTO registerDTO);

    @POST("/api/account/login")
    public Call<LoginResponse> login(@Body LoginDTO loginDTO);
}
