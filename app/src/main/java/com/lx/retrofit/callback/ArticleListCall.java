package com.lx.retrofit.callback;

import com.lx.retrofit.bean.ArticleList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created on 17-8-2 下午2:59
 */

public interface ArticleListCall {
    @GET("api/data/Android/10/1/")
    Call<ArticleList> getCall();
}
