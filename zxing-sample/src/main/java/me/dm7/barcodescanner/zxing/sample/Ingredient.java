package me.dm7.barcodescanner.zxing.sample;

public class Ingredient {

    private String nom;
    private String image;
    private int quantity;
    private long id;

    public Ingredient(long id, String nom, String image) {
        this.nom = nom;
        this.image = image;
        this.quantity = 1;
        this.id = id;
    }

    public Ingredient(String nom, String image) {
        this.nom = nom;
        this.image = image;
        this.quantity = 1;
    }

    public Ingredient(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public String getImage() {
        return image;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void incrementQuantity(){
        this.quantity++;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public long getId() {
        return id;
    }

    public void decrementQuantity() {
        this.quantity--;
    }
}
