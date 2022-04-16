package it.seba.metodologie.ex06_supereroi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class TestPersonaggioPubblico {

    private PersonaggioSupereroe laCosa;

    @BeforeEach
    void resetObject() {
        // create new instance
        laCosa = new PersonaggioSupereroe("La Cosa", 1000, 250);
    }

    @Test
    void newInstanceTest() {
        assertTrue(laCosa instanceof AbstractPersonaggio);
        assertTrue(laCosa instanceof PersonaggioSupereroe);
        assertTrue(laCosa instanceof Supereroe);
    }

    @Test
    void getNameTest() {
        assertEquals("La Cosa", laCosa.getNome());
    }
    
    @Test
    void getLifeTest() {
        assertEquals(1000, laCosa.getVita());
    }
    
    @Test
    void sufferAttackTest() {
        assertEquals(1000, laCosa.getVita());
        
        laCosa.subisciAttacco(200);
        assertEquals(800, laCosa.getVita());
        
        laCosa.subisciAttacco(100);
        assertEquals(700, laCosa.getVita());
        
        laCosa.subisciAttacco(500);
        assertEquals(200, laCosa.getVita());
    }

    @Test
    void loserTest() {
        assertEquals(1000, laCosa.getVita());
        assertFalse(laCosa.sconfitto());
       
        laCosa.subisciAttacco(1200);
        assertEquals(-200, laCosa.getVita());
        assertTrue(laCosa.sconfitto());
    }
    
    @Test
    void attackTest() {
        PersonaggioSupereroe magneto = new PersonaggioSupereroe("Magneto", 700, 450);
        
        assertEquals(1000, laCosa.getVita());
        assertEquals(700, magneto.getVita());
        
        laCosa.subisciAttacco(magneto.attacca());
        assertEquals(550, laCosa.getVita());
        
        magneto.subisciAttacco(laCosa.attacca());
        assertEquals(450, magneto.getVita());
    }
}
