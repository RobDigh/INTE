package entity.creature;

import org.junit.Test;

import static org.junit.Assert.*;

public class BreedTests {

    @Test
    public void testAllValuesAreThere() {
        assertNotNull(Type.valueOf("BULLDOG"));
        assertNotNull(Type.valueOf("CHIHUAHUA"));
        assertNotNull(Type.valueOf("DACHSHUND"));
        assertNotNull(Type.valueOf("DOBERMANN"));
        assertNotNull(Type.valueOf("GREYHOUND"));
        assertNotNull(Type.valueOf("LABRADOR"));
        assertNotNull(Type.valueOf("OVTJARKA"));
        assertNotNull(Type.valueOf("SIBERIANHUSKY"));
        assertNotNull(Type.valueOf("STBERNARD"));
        assertNotNull(Type.valueOf("VIZSLA"));
    }

    @Test
    public void testAllBulldogValues() {
        assertEquals("Bulldog", Type.BULLDOG.getName());
        assertEquals(6, Type.BULLDOG.getStrength());
        assertEquals(5, Type.BULLDOG.getDexterity());
        assertEquals(7, Type.BULLDOG.getConstitution());
    }

    @Test
    public void testAllChihuahuaValues() {
        assertEquals("Chihuahua", Type.CHIHUAHUA.getName());
        assertEquals(5, Type.CHIHUAHUA.getStrength());
        assertEquals(8, Type.CHIHUAHUA.getDexterity());
        assertEquals(5, Type.CHIHUAHUA.getConstitution());
    }

    @Test
    public void testAllDachshundValues() {
        assertEquals("Dachshund", Type.DACHSHUND.getName());
        assertEquals(5, Type.DACHSHUND.getStrength());
        assertEquals(7, Type.DACHSHUND.getDexterity());
        assertEquals(6, Type.DACHSHUND.getConstitution());
    }

    @Test
    public void testAllDobermannValues() {
        assertEquals("Dobermann", Type.DOBERMANN.getName());
        assertEquals(7, Type.DOBERMANN.getStrength());
        assertEquals(6, Type.DOBERMANN.getDexterity());
        assertEquals(5, Type.DOBERMANN.getConstitution());
    }

    @Test
    public void testAllGreyhoundValues() {
        assertEquals("Greyhound", Type.GREYHOUND.getName());
        assertEquals(5, Type.GREYHOUND.getStrength());
        assertEquals(6, Type.GREYHOUND.getDexterity());
        assertEquals(7, Type.GREYHOUND.getConstitution());
    }

    @Test
    public void testAllLabradorValues() {
        assertEquals("Labrador", Type.LABRADOR.getName());
        assertEquals(6, Type.LABRADOR.getStrength());
        assertEquals(6, Type.LABRADOR.getDexterity());
        assertEquals(6, Type.LABRADOR.getConstitution());
    }

    @Test
    public void testAllOvtjarkaValues() {
        assertEquals("Ovtjarka", Type.OVTJARKA.getName());
        assertEquals(8, Type.OVTJARKA.getStrength());
        assertEquals(5, Type.OVTJARKA.getDexterity());
        assertEquals(5, Type.OVTJARKA.getConstitution());
    }

    @Test
    public void testAllHuskyValues() {
        assertEquals("Siberian husky", Type.SIBERIANHUSKY.getName());
        assertEquals(6, Type.SIBERIANHUSKY.getStrength());
        assertEquals(7, Type.SIBERIANHUSKY.getDexterity());
        assertEquals(5, Type.SIBERIANHUSKY.getConstitution());
    }

    @Test
    public void testAllStBernardValues() {
        assertEquals("St. Bernard", Type.STBERNARD.getName());
        assertEquals(7, Type.STBERNARD.getStrength());
        assertEquals(5, Type.STBERNARD.getDexterity());
        assertEquals(6, Type.STBERNARD.getConstitution());
    }

    @Test
    public void testAllVizslaValues() {
        assertEquals("Vizsla", Type.VIZSLA.getName());
        assertEquals(5, Type.VIZSLA.getStrength());
        assertEquals(5, Type.VIZSLA.getDexterity());
        assertEquals(8, Type.VIZSLA.getConstitution());
    }
}
