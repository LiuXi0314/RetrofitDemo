package com.lx.retrofit.model.api

import com.lx.retrofit.model.data.ThemeData
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by liuxi on 2017/12/23.
 */
interface ThemeApi {
    @GET
    fun getTheme(): Call<MutableList<ThemeData>>
}