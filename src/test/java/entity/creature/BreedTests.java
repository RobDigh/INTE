package entity.creature;

import org.junit.Test;

import static org.junit.Assert.*;

public class BreedTests {

    @Test
    public void testAllValuesAreThere() {
        assertNotNull(Breed.valueOf("Bulldog"));
        assertNotNull(Breed.valueOf("Chihuahua"));
        assertNotNull(Breed.valueOf("Dachshund"));
        assertNotNull(Breed.valueOf("Dobermann"));
        assertNotNull(Breed.valueOf("Greyhound"));
        assertNotNull(Breed.valueOf("Labrador"));
        assertNotNull(Breed.valueOf("Ovtjarka"));
        assertNotNull(Breed.valueOf("Siberian husky"));
        assertNotNull(Breed.valueOf("St. Bernard"));
        assertNotNull(Breed.valueOf("Vizla"));
    }
}
