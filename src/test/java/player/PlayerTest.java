package player;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void testCreateOnePlayer(){

        List<Player> playerList = new ArrayList();
        Player player = new Player();
        playerList.add(player);
        assertEquals(player, playerList.get(0));
    }

}