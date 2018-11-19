package me.dm7.barcodescanner.zxing.sample;

import java.io.Serializable;

public class Etape implements Serializable {



    private String titre;
    private int URLphoto;
    private String descriptif;

    public Etape (String titre, String descriptif, int URLphoto){
        this.titre = titre;
        this.descriptif = descriptif;
        this.URLphoto = URLphoto;

    }

    public String getDescriptif() {
        return descriptif;
    }

    public void setDescriptif(String descriptif) {
        this.descriptif = descriptif;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getURLphoto() {
        return URLphoto;
    }

    public void setURLphoto(int URLphoto) {
        this.URLphoto = URLphoto;
    }

}
