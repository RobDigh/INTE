package player;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PlayerTest {

    List<Player> playerList = new ArrayList();
    Player player;

    public Player createPlayer(String name) {
        this.player = new Player(name);
        return player;
    }

    public void addPlayerToPlayerList(){
        playerList.add(player);
    }

    @Test
    public void testCreateOnePlayer(){

        createPlayer("player1");
        playerList.add(player);
        assertEquals(player, playerList.get(0));
    }

//    @Test
//    public void testCreateTwoPlayers(){
//
//        for (int i = 0; i < 1; i++){
//            createPlayer();
//            playerList.add(player);
//        }
//
//        for (int i = 0; i < playerList.size(); i++){
//            assertEquals(player, playerList.get(i));
//        }
//    }

}