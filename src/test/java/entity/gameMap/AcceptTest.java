package entity.gameMap;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;

public class AcceptTest extends GameMapTest {

    @Test
    public void acceptValidEntity() {

        GameMap lowerLevel = createDefaultSizedGameMap();
        GameMap mockUpperLevel = mock(GameMap.class);

        assertFalse(lowerLevel.accept(mockCreature, mockUpperLevel));
        assertNotEquals(null, lowerLevel.getPosition(mockCreature));

    }
}
