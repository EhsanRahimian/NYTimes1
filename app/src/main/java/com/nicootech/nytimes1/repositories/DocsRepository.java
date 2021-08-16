package com.nicootech.nytimes1.repositories;

import com.nicootech.nytimes1.model.Docs;
import com.nicootech.nytimes1.request.NYTApiClient;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class DocsRepository {
    private static DocsRepository instance;
    private NYTApiClient mNYTApiClient;

    public static DocsRepository getInstance(){
        if(instance == null){
            instance = new DocsRepository();
        }

        return instance;
    }

    private DocsRepository(){
        mNYTApiClient = NYTApiClient.getInstance();

    }
    public LiveData<List<Docs>> getDocs(){
        return mNYTApiClient.getDocs();
    }

    public void searchNYTApi(String query, int pageNumber){
//        if(pageNumber == 0){
//            pageNumber = 1;
//        }
        mNYTApiClient.searchNYTApi(query,pageNumber);
    }


}
