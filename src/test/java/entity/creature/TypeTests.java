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

    @Test
    public void testAllCarValues() {
        assertEquals("Car", Type.CAR.getName());
        assertEquals(7, Type.CAR.getStrength());
        assertEquals(6, Type.CAR.getDexterity());
        assertEquals(5, Type.CAR.getConstitution());
        assertFalse(Type.CAR.isPC());
    }

    @Test
    public void testAllCatValues() {
        assertEquals("Cat", Type.CAT.getName());
        assertEquals(5, Type.CAT.getStrength());
        assertEquals(7, Type.CAT.getDexterity());
        assertEquals(6, Type.CAT.getConstitution());
        assertFalse(Type.CAT.isPC());
    }

    @Test
    public void testAllFoxValues() {
        assertEquals("Fox", Type.FOX.getName());
        assertEquals(6, Type.FOX.getStrength());
        assertEquals(7, Type.FOX.getDexterity());
        assertEquals(5, Type.FOX.getConstitution());
        assertFalse(Type.FOX.isPC());
    }

    @Test
    public void testAllHumanValues() {
        assertEquals("Human", Type.HUMAN.getName());
        assertEquals(6, Type.HUMAN.getStrength());
        assertEquals(6, Type.HUMAN.getDexterity());
        assertEquals(6, Type.HUMAN.getConstitution());
        assertFalse(Type.HUMAN.isPC());
    }

    @Test
    public void testAllJoggerValues() {
        assertEquals("Jogger", Type.JOGGER.getName());
        assertEquals(6, Type.JOGGER.getStrength());
        assertEquals(5, Type.JOGGER.getDexterity());
        assertEquals(7, Type.JOGGER.getConstitution());
        assertFalse(Type.JOGGER.isPC());
    }

    @Test
    public void testAllMailmanValues() {
        assertEquals("Mailman", Type.MAILMAN.getName());
        assertEquals(5, Type.MAILMAN.getStrength());
        assertEquals(6, Type.MAILMAN.getDexterity());
        assertEquals(7, Type.MAILMAN.getConstitution());
        assertFalse(Type.MAILMAN.isPC());
    }

    @Test
    public void testAllMooseValues() {
        assertEquals("Moose", Type.MOOSE.getName());
        assertEquals(5, Type.MOOSE.getStrength());
        assertEquals(5, Type.MOOSE.getDexterity());
        assertEquals(8, Type.MOOSE.getConstitution());
        assertFalse(Type.MOOSE.isPC());
    }

    @Test
    public void testAllSquirrelValues() {
        assertEquals("Squirrel", Type.SQUIRREL.getName());
        assertEquals(5, Type.SQUIRREL.getStrength());
        assertEquals(8, Type.SQUIRREL.getDexterity());
        assertEquals(5, Type.SQUIRREL.getConstitution());
        assertFalse(Type.SQUIRREL.isPC());
    }

    @Test
    public void testAllWolfValues() {
        assertEquals("Wolf", Type.WOLF.getName());
        assertEquals(7, Type.WOLF.getStrength());
        assertEquals(5, Type.WOLF.getDexterity());
        assertEquals(6, Type.WOLF.getConstitution());
        assertFalse(Type.WOLF.isPC());
    }
}
