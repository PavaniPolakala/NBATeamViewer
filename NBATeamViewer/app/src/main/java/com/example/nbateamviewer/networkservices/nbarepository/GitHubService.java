package com.example.nbateamviewer.networkservices.nbarepository;

/**
 * GitHubService is an interface that exposes APIs available
 */

import com.example.nbateamviewer.networkservices.nbamodel.TeamModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

interface GitHubService {
    String HTTPS_API_GITHUB_URL = "https://raw.githubusercontent.com/scoremedia/nba-team-viewer/master/";

    @GET("input.json")
    Call<List<TeamModel>> getNBATeamsList();
}
