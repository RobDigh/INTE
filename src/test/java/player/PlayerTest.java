package player;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class PlayerTest {

    List<Player> playerList = new ArrayList();
    Player player;

    public Player createPlayer(String name) {
        this.player = new Player(name);
        return player;
    }

    public Player createPlayer(String name, int life) {
        this.player = new Player(name, life);
        return player;
    }

    public void addPlayerToPlayerList(Player player){
        playerList.add(player);
    }

    @Test
    public void testCreateOnePlayerLifeNotSpecified(){

        createPlayer("player1");
        addPlayerToPlayerList(player);
        assertEquals(player, playerList.get(0));
    }

    @Test
    public void testCreateOnePlayerLifeSpecified(){

        createPlayer("player1", 150);
        addPlayerToPlayerList(player);
        assertEquals(player, playerList.get(0));
    }

    @Test
    public void testCreateTwoPlayersLivesNotSpecified() {
        int ctr = 0;
        for (int i = 0; i < 2; i++) {
            createPlayer("player" + ctr);
            addPlayerToPlayerList(player);
            ctr++;
        }

        for (Player player : playerList) {
            assertTrue(playerList.contains(player));
        }
    }

    @Test
    public void testCreateTwoPlayersDifferentLivesSpecified() {
        int ctr = 0;
        for (int i = 0; i < 2; i++) {
            createPlayer("player" + ctr, 100+ctr);
            addPlayerToPlayerList(player);
            ctr++;
        }

        for (Player player : playerList) {
            assertTrue(playerList.contains(player));
        }
    }

    @Test
    public void testCreateRandomAmountOfPlayersInterval1to100LivesNotSpecified(){

        Random rnd = new Random();
        int rndInt = rnd.nextInt(100);
        for (int i = 0; i <= rndInt; i++){
            createPlayer("player"+i);
            addPlayerToPlayerList(player);
        }

        for (Player player : playerList) {
            assertTrue(playerList.contains(player));
        }
    }

    @Test
    public void testCreateRandomAmountOfPlayersInterval1to100DifferentLivesSpecified(){

        Random rnd = new Random();
        int rndInt = rnd.nextInt(100);
        for (int i = 0; i <= rndInt; i++){
            createPlayer("player"+i, 100+i);
            addPlayerToPlayerList(player);
        }

        for (Player player : playerList) {
            assertTrue(playerList.contains(player));
        }
    }

}