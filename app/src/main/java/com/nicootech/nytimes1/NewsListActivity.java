package com.nicootech.nytimes1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.nicootech.nytimes1.model.ArticleResponse;
import com.nicootech.nytimes1.model.Docs;
import com.nicootech.nytimes1.request.NYTApi;
import com.nicootech.nytimes1.request.ServiceGenerator;
import com.nicootech.nytimes1.request.responses.ArticleSearchResponse;
import com.nicootech.nytimes1.util.Constants;
import com.nicootech.nytimes1.util.Testing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewsListActivity extends AppCompatActivity {

    private static final String TAG = "NewsListActivity";
    private NewsListViewModel mNewsListViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        mNewsListViewModel = new ViewModelProvider(this).get(NewsListViewModel.class);

        subscribeObservers();
        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testRetrofitRequest();
            }
        });
    }

    private void subscribeObservers(){
        mNewsListViewModel.getDocs().observe(this, new Observer<List<Docs>>() {
            @Override
            public void onChanged(List<Docs> docs) {
                if(docs != null){
//                    for(Docs doc : docs ){
//                        Log.d(TAG, "onChanged: "+ doc.getSnippet());
//                    }
                    Testing.printRecipes(docs,"docs test");
                }

            }
        });
    }
    private void searchNYTApi(String query, int pageNumber){

        mNewsListViewModel.searchNYTApi(query,pageNumber);

    }
    private void testRetrofitRequest(){
        searchNYTApi("afghanistan",1);
    }
}