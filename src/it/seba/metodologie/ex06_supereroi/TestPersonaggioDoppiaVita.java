package it.seba.metodologie.ex06_supereroi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class TestPersonaggioDoppiaVita {

    private PersonaggioDoppiaVita peterParker;
    
    @BeforeEach
    void resetObject() {
        // create new instance
        peterParker = new PersonaggioDoppiaVita("Peter Parker", "Spiderman", 1100, 200);
    }

    @Test
    void newInstanceTest() {
        assertTrue(peterParker instanceof AbstractPersonaggio);
        assertTrue(peterParker instanceof PersonaggioDoppiaVita);
        assertTrue(peterParker instanceof DoppiaVita);
        
    }
    
    @Test
    void getNameTest() {
        assertEquals("Peter Parker", peterParker.getNome());
    }
    
    @Test
    void getLifeTest() {
        assertEquals(1100, peterParker.getVita());
    }
    
    @Test
    void sufferAttackTest() {
        assertEquals(1100, peterParker.getVita());
        
        // if not trasformed in superhero he die fast
        peterParker.subisciAttacco(200);
        assertEquals(-200, peterParker.getVita());
    }

    @Test
    void loserTest() {
        assertEquals(1100, peterParker.getVita());
        assertFalse(peterParker.sconfitto());
       
        peterParker.subisciAttacco(200);
        assertEquals(-200, peterParker.getVita());
        assertTrue(peterParker.sconfitto());
    }
    
    @Test
    void transformInSuperheroTest() {
        // casting because method returns interface Supereroe
        PersonaggioSupereroe spiderman = (PersonaggioSupereroe) peterParker.assumiIdentitaSegreta();
        
        assertEquals("Spiderman", spiderman.getNome());
        assertEquals(1100, spiderman.getVita());
    }
    
    @Test
    void returnPersonTest() {
        PersonaggioSupereroe spiderman = (PersonaggioSupereroe) peterParker.assumiIdentitaSegreta();
        
        assertEquals("Spiderman", spiderman.getNome());
        assertEquals(1100, spiderman.getVita());
        
        // cast to DoppiaVita to get visibility to assumiIdentitaPubblica() method
        DoppiaVita spidermanTakeOffHisCostume = (DoppiaVita) spiderman;
        peterParker = (PersonaggioDoppiaVita) spidermanTakeOffHisCostume.assumiIdentitaPubblica();
        
        assertTrue(peterParker instanceof AbstractPersonaggio);
        assertTrue(peterParker instanceof PersonaggioDoppiaVita);
        assertTrue(peterParker instanceof DoppiaVita);
    }
    
}
