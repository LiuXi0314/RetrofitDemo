package com.lx.retrofit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lx.retrofit.R;
import com.lx.retrofit.Utils.LogUtils;
import com.lx.retrofit.bean.ArticleList;
import com.lx.retrofit.bean.Results;
import com.lx.retrofit.bean.Translation;
import com.lx.retrofit.callback.ArticleListCall;
import com.lx.retrofit.callback.TranslationCall;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testGankApi();
    }

    private void testGankApi() {
        Retrofit mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://gank.io/")
                .build();
        ArticleListCall articleListCall = mRetrofit.create(ArticleListCall.class);
        Call<ArticleList> call = articleListCall.getCall();
        call.enqueue(new Callback<ArticleList>() {
            @Override
            public void onResponse(Call<ArticleList> call, Response<ArticleList> response) {
                LogUtils.d("is successful===========" + response.isSuccessful());
                if (response.body() != null) {
                    LogUtils.d("response.body().isError() = " + response.body().isError());
                    if (response.body().getArticleList() == null) {
                        LogUtils.d("response.body().getArticleList()  is null");
                    } else {
                        LogUtils.d("response.body().getArticleList().size()" + response.body().getArticleList().size());
                        Results results = response.body().getArticleList().get(0);
                        if (results == null) {
                            LogUtils.d("results = null ");
                        } else {
                            LogUtils.d("results = " + results.toString());
                        }
                    }


                }


            }

            @Override
            public void onFailure(Call<ArticleList> call, Throwable t) {

            }
        });
    }

    private void testYouDaoApi() {
        Retrofit youDaoRetrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TranslationCall request = youDaoRetrofit.create(TranslationCall.class);
        Call<Translation> call = request.getCall("I hate you");
        call.enqueue(new Callback<Translation>() {
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                LogUtils.d("翻译是：" + response.body().getTranslateResult().get(0).get(0).getTgt());
            }

            @Override
            public void onFailure(Call<Translation> call, Throwable t) {
                LogUtils.d("请求失败");
            }
        });

    }


}
