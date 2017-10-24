package entity.gameMap;

import entity.creature.Behaviour;
import entity.creature.InventoryFactory;

import org.junit.Test;

import entity.creature.Creature;
import entity.item.wearable.armor.Armor;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;

public class InteractTest extends GameMapTest {

    private GameMap lowerLevel = createDefaultSizedGameMap();
    private GameMap mockUpperLevel = mock(GameMap.class);

    private InventoryFactory mockInventoryFactory = mock(InventoryFactory.class);
    private Behaviour mockBehaviour = (mock(Behaviour.class));

    private Creature player = new Creature(6, 5, 7, true, mockInventoryFactory, mockBehaviour);
    private Creature monster = new Creature(7, 5, 6, false, mockInventoryFactory, mockBehaviour);
    private Armor armor = new Armor(5);
    @Test
    public void acceptValidEntity() {

        assertFalse(lowerLevel.interact(mockCreature, mockUpperLevel));
        assertNotEquals(null, lowerLevel.getPosition(mockCreature));

    }

    @Test(expected = IllegalArgumentException.class)
    public void interactWithNullEntity() {
        lowerLevel.interact(null, mockUpperLevel);
    }
    
    @Test
    public void interactWithCreatureByMonsterAndWinBattle(){
    	DEFAULT_SIZED_MAP.placeEntity(player, GameMap.DEFAULT_ENTRY_POSITION);
    	DEFAULT_SIZED_MAP.placeEntity(monster, new Point(0,1));
    	DEFAULT_SIZED_MAP.move(player, GameMap.NORTH);
    	
    	assertEquals(new Point(0,1), DEFAULT_SIZED_MAP.getPosition(player));
    	assertEquals(null, DEFAULT_SIZED_MAP.getPosition(monster));
    }
    
    @Test
    public void interactWithCreatureByMonsterAndLoseBattle(){
    	DEFAULT_SIZED_MAP.placeEntity(monster, new Point(0,1));
    	DEFAULT_SIZED_MAP.placeEntity(player, new Point(0,0));
    	player.loseHP(451);
    	DEFAULT_SIZED_MAP.move(player, GameMap.NORTH);
    	
    	assertEquals(null, DEFAULT_SIZED_MAP.getPosition(player));
    	assertEquals(new Point(0,1), DEFAULT_SIZED_MAP.getPosition(monster));
    }
    
    @Test
    public void interactWithCreatureByItemAndLeaveBoard(){
    	DEFAULT_SIZED_MAP.placeEntity(armor, GameMap.NORTH);
    	DEFAULT_SIZED_MAP.placeEntity(player, new Point(0,0));
    	DEFAULT_SIZED_MAP.move(player, GameMap.NORTH);
    	
    	assertEquals(null, DEFAULT_SIZED_MAP.getPosition(armor));
    	assertEquals(new Point(0,1), DEFAULT_SIZED_MAP.getPosition(player));
    }
}
