package npc;

import player.Player;

public class Monster {

    public int getSpeed() {
        return -1;
    }

    public boolean visit(Player player) {
        return false;
    }
}
