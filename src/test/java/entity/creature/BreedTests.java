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

    @Test
    public void testAllBulldogValues() {
        assertEquals("Bulldog", Breed.BULLDOG.getName());
        assertEquals(6, Breed.BULLDOG.getStrength());
        assertEquals(5, Breed.BULLDOG.getDexterity());
        assertEquals(7, Breed.BULLDOG.getConstitution());
    }

    @Test
    public void testAllChihuahuaValues() {
        assertEquals("Chihuahua", Breed.CHIHUAHUA.getName());
        assertEquals(5, Breed.CHIHUAHUA.getStrength());
        assertEquals(8, Breed.CHIHUAHUA.getDexterity());
        assertEquals(5, Breed.CHIHUAHUA.getConstitution());
    }

    @Test
    public void testAllDachshundValues(){
        assertEquals("Dachshund", Breed.DACHSHUND.getName());
        assertEquals(5, Breed.DACHSHUND.getStrength());
        assertEquals(7, Breed.DACHSHUND.getDexterity());
        assertEquals(6, Breed.DACHSHUND.getConstitution());
    }

    @Test
    public void testAllDobermannValues(){
        assertEquals("Dobermann", Breed.DOBERMANN.getName());
        assertEquals(7, Breed.DOBERMANN.getStrength());
        assertEquals(6, Breed.DOBERMANN.getDexterity());
        assertEquals(5, Breed.DOBERMANN.getConstitution());
    }

    @Test
    public void testAllGreyhoundValues(){
        assertEquals("Greyhound", Breed.GREYHOUND.getName());
        assertEquals(5, Breed.GREYHOUND.getStrength());
        assertEquals(6, Breed.GREYHOUND.getDexterity());
        assertEquals(7, Breed.GREYHOUND.getConstitution());
    }
}
