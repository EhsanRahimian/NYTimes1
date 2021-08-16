package com.nicootech.nytimes1.request;

import android.util.Log;

import com.nicootech.nytimes1.AppExecutors;
import com.nicootech.nytimes1.model.Docs;
import com.nicootech.nytimes1.request.responses.ArticleSearchResponse;
import com.nicootech.nytimes1.util.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Response;

import static com.nicootech.nytimes1.util.Constants.NETWORK_TIMEOUT;

public class NYTApiClient {
    private static final String TAG = "NYTApiClient";
    private static NYTApiClient instance;

    private MutableLiveData<List<Docs>> mDocs;
    private RetrieveDocsRunnable mRetrieveDocsRunnable;

    public static NYTApiClient getInstance() {
        if (instance == null) {
            instance = new NYTApiClient();
        }
        return instance;
    }

    public NYTApiClient() {
        mDocs = new MutableLiveData<>();
    }

    public LiveData<List<Docs>> getDocs() {
        return mDocs;
    }

    public void searchNYTApi(String query, int pageNumber) {

        mRetrieveDocsRunnable = new RetrieveDocsRunnable(query, pageNumber);
        final Future handler = AppExecutors.getInstance().networkIO().submit(mRetrieveDocsRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // let the user know its timed out
                handler.cancel(true);
            }
        }, NETWORK_TIMEOUT, TimeUnit.MICROSECONDS);
    }

    private class RetrieveDocsRunnable implements Runnable {

        private String query;
        private int pageNumber;
        boolean cancelRequest;

        public RetrieveDocsRunnable(String query, int pageNumber) {

            this.query = query;
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }

        @Override
        public void run() {
            try {
                Response response = getArticles(query, pageNumber).execute();

                if (cancelRequest == true) {
                    return;
                }
                if (response.code() == 200) {
                    List<Docs> list = new ArrayList<>(((ArticleSearchResponse) response.body()).getResponse().getDocs());
                    if (pageNumber == 1) {
                        mDocs.postValue(list);
                    } else {
                        List<Docs> currentDocs = mDocs.getValue();
                        currentDocs.addAll(list);
                        mDocs.postValue(currentDocs);

                    }
                } else {
                    String error = response.errorBody().string();
                    Log.e(TAG, "run: " + error);
                    mDocs.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mDocs.postValue(null);
            }

        }

        private Call<ArticleSearchResponse> getArticles(String query, int pageNumber) {

            return ServiceGenerator.getNytApi().searchArticle(
                    Constants.API_KEY,
                    query,
                    String.valueOf(pageNumber)
            );
        }

        private void cancelRequest() {
            Log.d(TAG, "cancelRequest: canceling the request.");

            cancelRequest = true;
        }
    }
}
