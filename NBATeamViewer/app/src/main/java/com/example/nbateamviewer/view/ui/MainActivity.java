package com.example.nbateamviewer.view.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.nbateamviewer.R;
import com.example.nbateamviewer.networkservices.nbamodel.TeamModel;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add team list fragment
        if (savedInstanceState == null) {
            Log.d(TAG,"Add team list fragment");
            TeamListFragment fragment = new TeamListFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, TeamListFragment.TAG).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        TeamListFragment fragment = (TeamListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        switch (item.getItemId()) {
            case R.id.action_name:
                Log.d(TAG,"sort by name clicked");
                fragment.sortDataByName();
                return true;
            case R.id.action_wins:
                Log.d(TAG,"sort by wins clicked");
                fragment.sortDataByWins();
                return true;
            case R.id.action_losses:
                Log.d(TAG,"sort by losses clicked");
                fragment.sortDataByLosses();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        invalidateOptionsMenu();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Fragment frag = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (frag instanceof PlayerListFragment) {
            menu.findItem(R.id.action_name).setVisible(false);
            menu.findItem(R.id.action_losses).setVisible(false);
            menu.findItem(R.id.action_wins).setVisible(false);
        }else {
            menu.findItem(R.id.action_name).setVisible(true);
            menu.findItem(R.id.action_losses).setVisible(true);
            menu.findItem(R.id.action_wins).setVisible(true);
        }
        return true;
    }

    /** Shows the project detail fragment */
    public void show(TeamModel teamModel) {
        PlayerListFragment playerListFragment = PlayerListFragment.getPlayerListFragment(teamModel);

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("playerDetails")
                .replace(R.id.fragment_container,
                        playerListFragment, null).commit();
        Log.d(TAG,"show player list fragment");
    }
}