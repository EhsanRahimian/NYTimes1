package com.nicootech.nytimes1;

import com.nicootech.nytimes1.model.Docs;
import com.nicootech.nytimes1.repositories.DocsRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewsListViewModel extends ViewModel {

    private DocsRepository mDocsRepository;

    public NewsListViewModel() {
        mDocsRepository = DocsRepository.getInstance();
    }
    public LiveData<List<Docs>> getDocs(){
        return mDocsRepository.getDocs();
    }

    public void searchNYTApi(String query, int pageNumber){

        mDocsRepository.searchNYTApi(query,pageNumber);
    }

}
