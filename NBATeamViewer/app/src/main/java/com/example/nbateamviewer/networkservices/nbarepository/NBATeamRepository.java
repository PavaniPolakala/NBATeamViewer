package com.example.nbateamviewer.networkservices.nbarepository;

/**
 * NBATeamRepository is a singleton class that
 * requests API and sends response back to corresponding classes
 *
 * It uses Retrofit for webservices.
 */
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.example.nbateamviewer.networkservices.nbamodel.TeamModel;

import java.util.List;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NBATeamRepository {
    private GitHubService gitHubService;
    private static NBATeamRepository nbaTeamRepository;
    private final String LOG_TAG ="NBATeamRepository";
    private Context mContext;
    private NBATeamRepository(Context mContext) {
        this.mContext = mContext;

        // Network cache implementation
        int cacheSize = 10 * 1024 * 1024; // 10 MB
        Cache cache = new Cache(mContext.getCacheDir(), cacheSize);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GitHubService.HTTPS_API_GITHUB_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        gitHubService = retrofit.create(GitHubService.class);
    }

    public synchronized static NBATeamRepository getInstance(Context mContext) {
        if (nbaTeamRepository == null) {
            nbaTeamRepository = new NBATeamRepository(mContext);
        }
        return nbaTeamRepository;
    }

    /**
     * Requests NBATeamsList data by calling the API.
     * Sends null value if the api request has failed.
     * @return List<TeamModel>
     */
    public MutableLiveData<List<TeamModel>> getNBATeamsList() {
        final MutableLiveData<List<TeamModel>> data = new MutableLiveData<>();

        gitHubService.getNBATeamsList().enqueue(new Callback<List<TeamModel>>() {
            @Override
            public void onResponse(Call<List<TeamModel>> call, Response<List<TeamModel>> response) {
                data.setValue(response.body());
                Log.d(LOG_TAG,"Response recieved successfully");
            }

            @Override
            public void onFailure(Call<List<TeamModel>> call, Throwable t) {
                Log.d(LOG_TAG,"Failed to receive the response");
                data.setValue(null);
            }
        });

        return data;
    }
}
