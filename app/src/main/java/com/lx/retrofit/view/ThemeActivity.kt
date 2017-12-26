package com.lx.retrofit.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lx.retrofit.Common.Constance
import com.lx.retrofit.R
import com.lx.retrofit.Utils.LogUtils
import com.lx.retrofit.model.api.ThemeApi
import com.lx.retrofit.model.data.ThemeData
import kotlinx.android.synthetic.main.activity_theme.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession

/**
 * Created by liuxi on 2017/12/23.
 */
class ThemeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theme)
        swipeRefresh.setOnRefreshListener { loadData() }
        loadData()
    }

    private fun loadData() {
        LogUtils.d("loadData")
        var intercepor = HttpLoggingInterceptor()
        intercepor.level = HttpLoggingInterceptor.Level.BODY
        var client = OkHttpClient.Builder().addNetworkInterceptor(intercepor).hostnameVerifier(hostName()).build()

        var retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(Constance.ZhihuHeader).build()

        var themeApi = retrofit.create(ThemeApi::class.java)
        themeApi.getTheme().enqueue(object : Callback<ThemeData>{
            override fun onResponse(call: Call<ThemeData>?, response: Response<ThemeData>?) {
                LogUtils.d(response!!.body().toString())
                LogUtils.d("---------------------------")
            }

            override fun onFailure(call: Call<ThemeData>?, t: Throwable?) {
                LogUtils.d("---------------------------error")
            }

        })
    }


    class hostName:HostnameVerifier{
        override fun verify(p0: String?, p1: SSLSession?): Boolean {
            return true
        }

    }
}
