package it.seba.metodologie.ex02;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import it.seba.metodologie.ex02.exception.NotEnoughMoneyException;

class TestContoCorrente {

    @Test
    void newInstanceTest() {
        ContoCorrente c = new ContoCorrente("b9630d6e-48f0-435c-8cb6-6f01ac645ee1", 10.0);

        assertTrue(c instanceof ContoCorrente);
        assertEquals(c.getIdUtente(), "b9630d6e-48f0-435c-8cb6-6f01ac645ee1");
        assertEquals(c.getImporto(), 10.0);
    }

    @Test
    void newInstanceContructorOverloadTest() {
        ContoCorrente c = new ContoCorrente("b9630d6e-48f0-435c-8cb6-6f01ac645ee1");

        assertTrue(c instanceof ContoCorrente);
        assertEquals(c.getIdUtente(), "b9630d6e-48f0-435c-8cb6-6f01ac645ee1");
        assertEquals(c.getImporto(), 0.0);
    }

    @Test
    void depositTest() {
        ContoCorrente c = new ContoCorrente("b9630d6e-48f0-435c-8cb6-6f01ac645ee1", 10.0);
        c.versa(15.0);

        assertTrue(c instanceof ContoCorrente);
        assertEquals(c.getImporto(), 25.0);
    }

    @Test
    void withdrawTest() {
        ContoCorrente c = new ContoCorrente("b9630d6e-48f0-435c-8cb6-6f01ac645ee1", 10.0);
        c.versa(15.0);

        assertTrue(c instanceof ContoCorrente);
        // should not throw any exceptions here
        try {
            assertEquals(c.preleva(20), 20.0);
        } catch (NotEnoughMoneyException e) {

        }

        assertEquals(c.getImporto(), 5.0);
    }

    @Test
    void withdrawExceptionTest() {
        ContoCorrente c = new ContoCorrente("b9630d6e-48f0-435c-8cb6-6f01ac645ee1", 10.0);
        c.versa(15.0);

        assertTrue(c instanceof ContoCorrente);
        assertThrows(NotEnoughMoneyException.class, () -> c.preleva(30));
    }

    @Test
    void withdrawAllTest() {
        ContoCorrente c = new ContoCorrente("b9630d6e-48f0-435c-8cb6-6f01ac645ee1", 10.0);
        c.versa(15.0);

        assertTrue(c instanceof ContoCorrente);
        assertEquals(c.svuota(), 25.0);
        assertEquals(c.getImporto(), 0.0);
    }
}
