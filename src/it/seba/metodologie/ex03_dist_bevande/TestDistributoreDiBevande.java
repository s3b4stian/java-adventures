package it.seba.metodologie.ex03_dist_bevande;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import it.seba.metodologie.ex03_dist_bevande.exception.InvalidProductNumberException;
import it.seba.metodologie.ex03_dist_bevande.exception.MaxDispencerCapacityReachedException;
import it.seba.metodologie.ex03_dist_bevande.exception.NotEnoughMoneyException;
import it.seba.metodologie.ex03_dist_bevande.exception.ProductOutOfStockException;
//import it.seba.metodologie.ex03_dist_bevande.exception.WeEatYourMoneyException;

@TestInstance(Lifecycle.PER_CLASS)
public class TestDistributoreDiBevande {

    private DistributoreDiBevande d;

    @BeforeEach
    void resetObject() {
        // create new instance
        d = new DistributoreDiBevande(6);
        // load new products to dispencer
        try {
            d.carica("A01", new Prodotto(0.25, TipoProdotto.CAFFE, "Espresso Corto"));
            d.carica("A02", new Prodotto(0.25, TipoProdotto.CAFFE, "Espresso Lungo"));
            d.carica("B01", new Prodotto(1.25, TipoProdotto.CAPPUCCINO, "Normale"));
            d.carica("B02", new Prodotto(1.25, TipoProdotto.CAPPUCCINO, "Al Cacao"));
            d.carica("C01", new Prodotto(1.50, TipoProdotto.CIOCCOLATO, "Al Latte"));
            d.carica("C02", new Prodotto(1.50, TipoProdotto.CIOCCOLATO, "Gianduia"));
        } catch (MaxDispencerCapacityReachedException e) {
            // should not catch any exception here
        }
    }

    @Test
    void newInstanceTest() {
        assertTrue(d instanceof DistributoreDiBevande);
    }

    @Test
    void caricaTest() {
        d.inserisciImporto(10.0);

        assertEquals(d.getSaldo(), 10.0);

        try {
            assertEquals(TipoProdotto.CAFFE, d.getProdotto("A01").getTipo());
            assertEquals(TipoProdotto.CAPPUCCINO, d.getProdotto("B01").getTipo());
            assertEquals(TipoProdotto.CIOCCOLATO, d.getProdotto("C01").getTipo());
        } catch (ProductOutOfStockException | InvalidProductNumberException | NotEnoughMoneyException e) {
            // should not catch any exception here
        }

        assertEquals(d.getSaldo(), 7.0);
    }

    @Test
    void maxDispencerCapacityReachedExceptionTest() {
        assertThrows(MaxDispencerCapacityReachedException.class,
                () -> d.carica("C02", new Prodotto(1.50, TipoProdotto.CIOCCOLATO, "Gianduia")));
    }

    @Test
    void productOutOfStockExceptionTest() {
        d.inserisciImporto(10.0);

        assertEquals(d.getSaldo(), 10.0);

        try {
            assertEquals(TipoProdotto.CAFFE, d.getProdotto("A01").getTipo());
        } catch (ProductOutOfStockException | InvalidProductNumberException | NotEnoughMoneyException e) {
            // should not catch any exception here
        }

        assertThrows(ProductOutOfStockException.class, () -> d.getProdotto("A01"));
    }

    @Test
    void invalidProductNumberExceptionTest() {
        d.inserisciImporto(10.0);

        assertEquals(d.getSaldo(), 10.0);
        assertThrows(InvalidProductNumberException.class, () -> d.getProdotto("D01"));
    }

    @Test
    void notEnoughMoneyExceptionTest() {
        d.inserisciImporto(1.0);

        assertEquals(d.getSaldo(), 1.0);
        assertThrows(NotEnoughMoneyException.class, () -> d.getProdotto("B01"));
    }

    @Test
    void getChangeTest() {
        d.inserisciImporto(1.0);

        assertEquals(d.getSaldo(), 1.0);

        try {
            assertEquals(TipoProdotto.CAFFE, d.getProdotto("A01").getTipo());
        } catch (ProductOutOfStockException | InvalidProductNumberException | NotEnoughMoneyException e) {
            // should not catch any exception here
        }

        assertEquals(d.getResto(), 0.75);
    }
}
