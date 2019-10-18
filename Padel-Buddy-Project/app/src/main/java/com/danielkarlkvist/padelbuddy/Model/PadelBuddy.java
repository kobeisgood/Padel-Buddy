package com.danielkarlkvist.padelbuddy.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PadelBuddy implements ICreate {

    private List<IGame> games = new ArrayList<>();
    private Player player;

    public PadelBuddy() {

        this.player = new Player("Daniel", "Karlkvist", "danielkarlkvist@gmail.com", "0701234567", "Bla bla bla jflkhadfbjkldasjkbfbabfabdfjsdaf", 20, SkillLevel.Nybörjare);

    }


    public List<IGame> getGames() {
        return games;
    }

    public Player getPlayer() {
        return player;
    }

    // TODO Command query?
    public void createAd(String location, Date date, String length) {
        Game game = new Game(player, location, date, length);
        game.getPlayers()[0] = player;
        games.add(game);
    }

    public void removeAd(IGame game) {
        if (games.contains(game)) {
            games.remove(game);
        }

        // TODO Error message? FancyToast Library?? Finns i slack
    }

    public List<Game> getAvailableGames() {
        List<Game> availableGames = new ArrayList<>();
        int arrayLength = games.get(0).getPlayers().length;

        for (Game game : games){
            for (int i=0; i<arrayLength; i++){
                if (game.getPlayers()[i] == player){
                    break;
                }
                availableGames.add(game);
            }
        }
        //HadeCoded game where Daniel is not a player. should be removed when we create games without daniel in Service.
        availableGames.add(new Game(new Player("Calle","balle","lingon","skalle","hejsan",12,SkillLevel.Medel), "PDL Trollhättan", new Date(), "60 min"));
        return availableGames;
    }

    public List<Game> getUpcomingGames() {
        List<Game> upcomingGames = new ArrayList<>();
        for (Game game : games) {
            if (!game.isFinishedGame()) {
                upcomingGames.add(game);
            }
        }

        return upcomingGames;
    }


    public List<IGame> getPlayedGames() {
        List<IGame> playedGames = new ArrayList<>();
        for (IGame game : games) {
            if (game.isFinishedGame()) {
                playedGames.add(game);
            }
        }

        return playedGames;
    }
}
