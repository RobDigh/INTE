package entity.creature;

import org.junit.Test;

import static org.junit.Assert.*;

public class BreedTests {

    @Test
    public void testAllValuesAreThere() {
        assertNotNull(Breed.valueOf("BULLDOG"));
        assertNotNull(Breed.valueOf("CHIHUAHUA"));
        assertNotNull(Breed.valueOf("DACHSHUND"));
        assertNotNull(Breed.valueOf("DOBERMANN"));
        assertNotNull(Breed.valueOf("GREYHOUND"));
        assertNotNull(Breed.valueOf("LABRADOR"));
        assertNotNull(Breed.valueOf("OVTJARKA"));
        assertNotNull(Breed.valueOf("SIBERIANHUSKY"));
        assertNotNull(Breed.valueOf("STBERNARD"));
        assertNotNull(Breed.valueOf("VIZSLA"));
    }

    public void testAllBulldogValues(){
        assertEquals(6, Breed.BULLDOG.getStrength());
    }

    public void testAllChihuahuaValues(){
        assertEquals(5, Breed.CHIHUAHUA.getStrength());
    }
}
