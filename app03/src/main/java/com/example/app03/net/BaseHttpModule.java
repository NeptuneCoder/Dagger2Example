package com.example.app03.net;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class BaseHttpModule {

    //TODO 在module提供实例
    @BaseHttpScope
    @Provides
    String providerUrl() {
        return "http://www.baidu.com";
    }

    @BaseHttpScope
    @Provides
    GsonConverterFactory providerGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @BaseHttpScope
    @Provides
    RxJava2CallAdapterFactory providerRxjava2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    /**
     * 1. 这里可以通过作用域限制实例使用的范围,这里的作用域必须和自己的Component使用的一样。
     * 2. 一个Component只能有一个作用域修饰符。
     *
     * @return
     */
    @BaseHttpScope
    @Provides
    OkHttpClient providerOkHttpClient() {
        return new OkHttpClient.Builder()
                //TODO  这里可以添加各种拦截器
                .build();
    }

    @BaseHttpScope
    @Provides
    Retrofit providerRetrofit(String url,
                              OkHttpClient okHttpClient,
                              GsonConverterFactory gsonConverterFactory,
                              RxJava2CallAdapterFactory rxJava2CallAdapterFactory) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .build();
    }


}