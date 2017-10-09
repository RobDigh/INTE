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

    public void addPlayerToPlayerList(Player player){
        playerList.add(player);
    }

    @Test
    public void testCreateOnePlayer(){

        createPlayer("player1");
        addPlayerToPlayerList(player);
        assertEquals(player, playerList.get(0));
    }

    @Test
    public void testCreateTwoPlayers() {
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
    public void testCreateRandomAmountOfPlayersInterval1to100(){

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

}