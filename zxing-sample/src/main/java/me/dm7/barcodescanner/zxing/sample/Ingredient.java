package me.dm7.barcodescanner.zxing.sample;

public class Ingredient {

    private String nom;
    private String image;

    public Ingredient(String nom, String image) {
        this.nom = nom;
        this.image = image;
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
}
