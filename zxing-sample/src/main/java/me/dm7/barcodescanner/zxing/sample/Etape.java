package me.dm7.barcodescanner.zxing.sample;

import java.io.Serializable;

public class Etape implements Serializable {



    private String titre;
    private String URLphoto;
    private String descriptif;

    public Etape (String titre, String descriptif, String URLphoto){
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

    public String getURLphoto() {
        return URLphoto;
    }

    public void setURLphoto(String URLphoto) {
        this.URLphoto = URLphoto;
    }

}
