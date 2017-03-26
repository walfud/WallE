package com.walfud.walle.network;

import java.util.Map;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;


/**
 * Created by walfud on 2016/7/28.
 * <a href="http://www.tuluu.com/platform/ymir/wikis/home">doc</a>
 */
public interface INetwork {
    @GET
    Single<Response<ResponseBody>> get(@Url String url, @QueryMap Map<String, Object> param);

    @GET
    @Streaming
    Single<Response<ResponseBody>> getLarge(@Url String url);

    @POST
    Single<Response<ResponseBody>> post(@Url String url, @Body Map<String, Object> param);
}
