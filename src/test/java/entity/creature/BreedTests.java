package entity.creature;

import org.junit.Test;

import static org.junit.Assert.*;

public class BreedTests {

    @Test
    public void testAllValuesAreThere() {
        assertNotNull(Breed.valueOf("LABRADOR"));
    }
}
