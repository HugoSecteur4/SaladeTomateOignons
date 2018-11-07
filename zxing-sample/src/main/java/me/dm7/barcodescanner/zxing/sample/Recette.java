package me.dm7.barcodescanner.zxing.sample;

import java.util.ArrayList;

public class Recette {

    private ArrayList<Etape> etapes;
    private String URLphoto;


    private Etape etapeCourante;
    private int indexCourant;
    private String nom;
    private int difficulte;
    private int duree;
    private int temps_cui;

    public Recette(ArrayList<Etape> etapes, String nom, int difficulte, int duree, int temps_cui, String URLphoto){
        this.etapes=etapes;
        this.nom=nom;
        this.difficulte=difficulte;
        this.duree=duree;
        this.temps_cui= temps_cui;
        this.URLphoto = URLphoto;
        if(this.etapes!=null && this.etapes.size()>=1){
            this.etapeCourante = this.etapes.get(0);
            this.indexCourant = 0;
        }
    }

    public ArrayList<Etape> getEtapes() {
        return etapes;
    }

    public void setEtapes(ArrayList<Etape> etapes) {
        this.etapes = etapes;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(int difficulte) {
        this.difficulte = difficulte;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getTemps_cui() {
        return temps_cui;
    }


    public String getURLphoto() {
        return URLphoto;
    }

    public void setURLphoto(String URLphoto) {
        this.URLphoto = URLphoto;
    }

    public Etape getEtapeCourante() {
        return etapeCourante;
    }

    public void setEtapeCourante(Etape etapeCourante) {
        this.etapeCourante = etapeCourante;
    }

    public int getIndexCourant() {
        return indexCourant;
    }

    public void setIndexCourant(int indexCourant) {
        this.indexCourant = indexCourant;
    }

    public void setTemps_cui(int temps_cui) {
        this.temps_cui = temps_cui;
    }

    public void addEtape(Etape etape){
        this.etapes.add(etape);
    }

    public String getImage() {
        return URLphoto;
    }
}
