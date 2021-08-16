package com.nicootech.nytimes1.request.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nicootech.nytimes1.model.ResponseBean;


public class ArticleSearchResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("copyright")
    @Expose
    private String copyright;

    @SerializedName("response")
    @Expose()
    private ResponseBean response;

    public String getStatus() {
        return status;
    }

    public String getCopyright() {
        return copyright;
    }

    public ResponseBean getResponse() {
        return response;
    }
//    @SerializedName("docs")
//    @Expose()
//    private List<Docs> docs;
//
//    public List<Docs> getDocs() {
//        return docs;
//    }
}
