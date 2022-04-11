package it.seba.metodologie.ex03_dist_bevande;

import java.util.Hashtable;
import java.util.List;

import it.seba.metodologie.ex03_dist_bevande.exception.InvalidProductNumberException;
import it.seba.metodologie.ex03_dist_bevande.exception.MaxDispencerCapacityReachedException;
import it.seba.metodologie.ex03_dist_bevande.exception.NotEnoughMoneyException;
import it.seba.metodologie.ex03_dist_bevande.exception.ProductOutOfStockException;
//import it.seba.metodologie.ex03_dist_bevande.exception.WeEatYourMoneyException;

import java.util.ArrayList;

public class DistributoreDiBevande {

    /**
     * Max number of products inside the dispenser
     */
    private static int capacity;

    /**
     * Current number of products inside the dispenser
     */
    private int productsInside;

    /**
     * Current balance
     */
    private double balance;

    /**
     * Dispenser storage<br/>
     * Implemented as dict where every key contains a list of product with the same
     * number
     */
    private Hashtable<String, List<Prodotto>> storage = new Hashtable<String, List<Prodotto>>();

    /**
     * Class Constructor
     * 
     * @param maxProdotti
     */
    public DistributoreDiBevande(int maxProdotti) {
        capacity = maxProdotti;
    }

    /**
     * Upload a new product inside the dispencer
     * 
     * @param numeroProdotto
     * @param prodotto
     */
    public void carica(String numeroProdotto, Prodotto prodotto) throws MaxDispencerCapacityReachedException {

        if (productsInside >= capacity) {
            throw new MaxDispencerCapacityReachedException();
        }

        List<Prodotto> productList = new ArrayList<Prodotto>();

        // if any product exists on the array associated to specific number
        // retrieve the array
        if (storage.containsKey(numeroProdotto)) {
            // overwrite the array with existing one inside the dict.
            productList = storage.get(numeroProdotto);
        }

        productList.add(prodotto);

        // overwrite the key at the same number or create new
        storage.put(numeroProdotto, productList);

        productsInside++;

    }

    /**
     * Return the bought product
     * 
     * @param numeroProdotto
     * 
     * @return the bought product
     * 
     * @throws ProductOutOfStockException
     * @throws InvalidProductNumberException
     */
    public Prodotto getProdotto(String numeroProdotto)
            throws ProductOutOfStockException, InvalidProductNumberException, NotEnoughMoneyException {

        if (storage.containsKey(numeroProdotto)) {
            List<Prodotto> productList = storage.get(numeroProdotto);

            if (!productList.isEmpty()) {

                // check for the product price
                if (balance - productList.get(0).getPrezzo() < 0) {
                    // don't reuse the exception of the ex2 by choice
                    throw new NotEnoughMoneyException();
                }

                // prepare product to be served
                Prodotto p = productList.remove(0);
                storage.put(numeroProdotto, productList);

                // update balance
                balance -= p.getPrezzo();

                // return the product to customer
                return p;
            }

            throw new ProductOutOfStockException();
        }

        throw new InvalidProductNumberException();

    }

    public void inserisciImporto(double importo) /*throws WeEatYourMoneyException*/ {
        if (importo < 0) {
            //throw new WeEatYourMoneyException();
            importo = 0;
        }

        balance += importo;
    }

    public double getSaldo() {
        return balance;
    }

    public double getResto() {
        double tmpChange = balance;
        balance = 0.0;
        return tmpChange;
    }

}
