package entity.creature;

import org.junit.Test;

import static org.junit.Assert.*;

public class TypeTests {

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

        assertNotNull(Type.valueOf("BEAR"));
        assertNotNull(Type.valueOf("CAR"));
        assertNotNull(Type.valueOf("CAT"));
        assertNotNull(Type.valueOf("FOX"));
        assertNotNull(Type.valueOf("HUMAN"));
        assertNotNull(Type.valueOf("JOGGER"));
        assertNotNull(Type.valueOf("MAILMAN"));
        assertNotNull(Type.valueOf("MOOSE"));
        assertNotNull(Type.valueOf("SQUIRREL"));
        assertNotNull(Type.valueOf("WOLF"));
    }

    @Test
    public void testAllBulldogValues() {
        assertEquals("Bulldog", Type.BULLDOG.getName());
        assertEquals(6, Type.BULLDOG.getStrength());
        assertEquals(5, Type.BULLDOG.getDexterity());
        assertEquals(7, Type.BULLDOG.getConstitution());
        assertTrue(Type.BULLDOG.isPC());
    }

    @Test
    public void testAllChihuahuaValues() {
        assertEquals("Chihuahua", Type.CHIHUAHUA.getName());
        assertEquals(5, Type.CHIHUAHUA.getStrength());
        assertEquals(8, Type.CHIHUAHUA.getDexterity());
        assertEquals(5, Type.CHIHUAHUA.getConstitution());
        assertTrue(Type.CHIHUAHUA.isPC());
    }

    @Test
    public void testAllDachshundValues() {
        assertEquals("Dachshund", Type.DACHSHUND.getName());
        assertEquals(5, Type.DACHSHUND.getStrength());
        assertEquals(7, Type.DACHSHUND.getDexterity());
        assertEquals(6, Type.DACHSHUND.getConstitution());
        assertTrue(Type.DACHSHUND.isPC());
    }

    @Test
    public void testAllDobermannValues() {
        assertEquals("Dobermann", Type.DOBERMANN.getName());
        assertEquals(7, Type.DOBERMANN.getStrength());
        assertEquals(6, Type.DOBERMANN.getDexterity());
        assertEquals(5, Type.DOBERMANN.getConstitution());
        assertTrue(Type.DOBERMANN.isPC());
    }

    @Test
    public void testAllGreyhoundValues() {
        assertEquals("Greyhound", Type.GREYHOUND.getName());
        assertEquals(5, Type.GREYHOUND.getStrength());
        assertEquals(6, Type.GREYHOUND.getDexterity());
        assertEquals(7, Type.GREYHOUND.getConstitution());
        assertTrue(Type.GREYHOUND.isPC());
    }

    @Test
    public void testAllLabradorValues() {
        assertEquals("Labrador", Type.LABRADOR.getName());
        assertEquals(6, Type.LABRADOR.getStrength());
        assertEquals(6, Type.LABRADOR.getDexterity());
        assertEquals(6, Type.LABRADOR.getConstitution());
        assertTrue(Type.LABRADOR.isPC());
    }

    @Test
    public void testAllOvtjarkaValues() {
        assertEquals("Ovtjarka", Type.OVTJARKA.getName());
        assertEquals(8, Type.OVTJARKA.getStrength());
        assertEquals(5, Type.OVTJARKA.getDexterity());
        assertEquals(5, Type.OVTJARKA.getConstitution());
        assertTrue(Type.OVTJARKA.isPC());
    }

    @Test
    public void testAllHuskyValues() {
        assertEquals("Siberian husky", Type.SIBERIANHUSKY.getName());
        assertEquals(6, Type.SIBERIANHUSKY.getStrength());
        assertEquals(7, Type.SIBERIANHUSKY.getDexterity());
        assertEquals(5, Type.SIBERIANHUSKY.getConstitution());
        assertTrue(Type.SIBERIANHUSKY.isPC());
    }

    @Test
    public void testAllStBernardValues() {
        assertEquals("St. Bernard", Type.STBERNARD.getName());
        assertEquals(7, Type.STBERNARD.getStrength());
        assertEquals(5, Type.STBERNARD.getDexterity());
        assertEquals(6, Type.STBERNARD.getConstitution());
        assertTrue(Type.STBERNARD.isPC());
    }

    @Test
    public void testAllVizslaValues() {
        assertEquals("Vizsla", Type.VIZSLA.getName());
        assertEquals(5, Type.VIZSLA.getStrength());
        assertEquals(5, Type.VIZSLA.getDexterity());
        assertEquals(8, Type.VIZSLA.getConstitution());
        assertTrue(Type.VIZSLA.isPC());
    }

    @Test
    public void testAllBearValues() {
        assertEquals("Bear", Type.BEAR.getName());
        assertEquals(8, Type.BEAR.getStrength());
        assertEquals(5, Type.BEAR.getDexterity());
        assertEquals(5, Type.BEAR.getConstitution());
        assertFalse(Type.BEAR.isPC());
    }

}
