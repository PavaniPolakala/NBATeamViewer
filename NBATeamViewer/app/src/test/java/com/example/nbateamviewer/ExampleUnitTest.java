package com.example.nbateamviewer;

import com.example.nbateamviewer.networkservices.nbarepository.NBATeamRepository;
import com.example.nbateamviewer.viewmodel.TeamListViewModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private TeamListViewModel viewModel;

    @Mock
    private NBATeamRepository repository;
    //@Mock
    //private SearchViewContract viewContract;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);// required for the "@Mock" annotations

        //viewModel = Mockito.spy(new TeamListViewModel());
    }

}