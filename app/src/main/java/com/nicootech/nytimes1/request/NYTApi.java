package com.nicootech.nytimes1.request;

import com.nicootech.nytimes1.request.responses.ArticleSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NYTApi {

    //SEARCH
    @GET("svc/search/v2/articlesearch.json")
    Call<ArticleSearchResponse> searchArticle(
            @Query("api-key")String key,
            @Query("q")String query,
            @Query("page")String page
    );
}
