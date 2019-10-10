package com.danielkarlkvist.padelbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.danielkarlkvist.padelbuddy.Controller.CreateAdFragmentController;
import com.danielkarlkvist.padelbuddy.Controller.GamesFragmentController;
import com.danielkarlkvist.padelbuddy.Controller.HomeFragmentController;
import com.danielkarlkvist.padelbuddy.Controller.ProfileFragmentController;
import com.danielkarlkvist.padelbuddy.Model.Game;
import com.danielkarlkvist.padelbuddy.Model.PadelBuddy;
import com.danielkarlkvist.padelbuddy.Model.Player;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// TODO fix better javadoc for mainactivity

/**
 * The MainActivity class is the base of the project.
 *
 * @author Robin Repo Wecklauf, Marcus Axelsson, Daniel Karlkvist
 * Carl-Johan Björnson och Fredrik Lilliecreutz
 * @version 1.0
 * @since   2019-09-05
 */

public class MainActivity extends AppCompatActivity {

    // Has the tab controllers as instance variables so the information always gets saved
    private HomeFragmentController homeFragmentController;
    private CreateAdFragmentController createAdFragmentController;
    private GamesFragmentController gamesFragmentController;
    private ProfileFragmentController profileFragmentController;
    private Fragment selectedFragmentController = null;

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavigationViewListener =
            // region bottomNavigationViewListener
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    switch (menuItem.getItemId()) {
                        case R.id.nav_home:
                            if(selectedFragmentController == homeFragmentController) {
                                homeFragmentController.scrollToTop();
                                break;
                            } else {
                                selectedFragmentController = homeFragmentController;
                                break;
                            }
                        case R.id.nav_create:
                            selectedFragmentController = createAdFragmentController;
                            break;
                        case R.id.nav_games:
                            if(selectedFragmentController == gamesFragmentController) {
                                gamesFragmentController.scrollToTop();
                                break;
                            } else {
                                selectedFragmentController = gamesFragmentController;
                                break;
                            }
                        case R.id.nav_profile:
                            selectedFragmentController = profileFragmentController;
                            break;
                        default:
                            Log.println(1, "tag", "Selected fragment that doesn't exist.");
                            selectedFragmentController = new HomeFragmentController();
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragmentController).commit();

                    return true;
                }
            };
    // endregion bottomNavigationViewListener

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);  // Always portrait mode

        createRandomGames();

        initializeBottomNavigationViewControllers();
        initializeBottomNavigationView();
    }

    private void createRandomGames() {
        PadelBuddy.getInstance().createAd("Padel center gbg", new Date(2019,0,10,15, 30));
        PadelBuddy.getInstance().createAd("Padel center gbg", new Date(2018, 2, 2,8,00));
        PadelBuddy.getInstance().createAd("Padel center gbg", new Date(2019, 1, 4,15,15));
        PadelBuddy.getInstance().createAd("Padel center gbg", new Date(2015, 7, 7,10,20));
        PadelBuddy.getInstance().createAd("Padel center gbg", new Date(2018, 9, 3,9,30));
        PadelBuddy.getInstance().createAd("Padel center gbg", new Date(2018, 12, 25,23,50));
        PadelBuddy.getInstance().createAd("Padel center gbg", new Date());

        PadelBuddy padelBuddy = PadelBuddy.getInstance();
        ArrayList<Game> testGames = padelBuddy.getGames();
        List<Player> testPlayers = PadelBuddy.testPlayers;
        testGames.get(0).addPlayer(testPlayers.get(0));
        testGames.get(0).addPlayer(testPlayers.get(1));
        testGames.get(0).addPlayer(testPlayers.get(2));
        testGames.get(0).addPlayer(testPlayers.get(3));


    }

    private void initializeBottomNavigationViewControllers() {
        homeFragmentController = new HomeFragmentController();
        createAdFragmentController = new CreateAdFragmentController();
        gamesFragmentController = new GamesFragmentController();
        profileFragmentController = new ProfileFragmentController();
    }

    private void initializeBottomNavigationView() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavigationViewListener);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);  // Sets the current selected tab as Home when the app opens
    }
}
