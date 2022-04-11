package it.seba.metodologie.ex03_dist_bevande;

public class Prodotto {

    private double price;

    private String name;

    private TipoProdotto type;

    public Prodotto(double prezzo, TipoProdotto tipo, String nome) {
        price = prezzo;
        name = nome;
        type = tipo;
    }

    public double getPrezzo() {
        return price;
    }

    public TipoProdotto getTipo() {
        return type;
    }

    public String getNome() {
        return name;
    }
}
