package it.seba.metodologie.ex01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestRettangolo {

    @Test
    void newInstanceTest() {
        Rettangolo r = new Rettangolo(0, 0, 25, 15);

        assertTrue(r instanceof Rettangolo);
        assertEquals(r.toString(), "(0, 0)->(25, 15)");
    }

    @Test
    void translateRectangle() {
        Rettangolo r = new Rettangolo(0, 0, 20, 10);

        assertEquals(r.toString(), "(0, 0)->(20, 10)");

        r.trasla(10, 5);

        assertEquals(r.toString(), "(10, 5)->(30, 15)");
    }
}
