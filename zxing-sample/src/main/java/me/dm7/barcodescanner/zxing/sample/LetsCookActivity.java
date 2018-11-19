package me.dm7.barcodescanner.zxing.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Vector;
public class LetsCookActivity extends AppCompatActivity {

    private Class<?> mClss;
    private RecyclerView recyclerView;
    private MyAdapterRecette adapter;
    Vector<Recette> recettes = new Vector<Recette>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lets_cook);

        ArrayList<Etape> etapes = new ArrayList<Etape>();
        Etape etape1 = new Etape("Etape 1", "Faire bouillir l’eau dans une casserole. Mettre le riz et le laisser bouillir 2 à 3 minutes.", R.drawable.etape1r);
        Etape etape2 = new Etape("Etape 2", "Égoutter le riz. ", R.drawable.etape2r);
        Etape etape3 = new Etape("Etape 3", "Faire chauffer le lait avec le zeste râpé du citron, et les sucres. ", R.drawable.etape3r);
        Etape etape4 = new Etape("Etape 4", "Verser le riz.", R.drawable.etape4r);
        Etape etape5 = new Etape("Etape 5", "Faire bouillir, puis laisser cuire à feu doux sans remuer. ", R.drawable.etape5r);
        Etape etape6 = new Etape("Etape 6", "Le riz est cuit lorsqu’il a absorbé tout le lait. ", R.drawable.etape6r);
        Etape etape7 = new Etape("Etape 7", "Servir froid ou tiède.", R.drawable.etape7r);
        etapes.add(etape1);
        etapes.add(etape2);
        etapes.add(etape3);
        etapes.add(etape4);
        etapes.add(etape5);
        etapes.add(etape6);
        etapes.add(etape7);
        Recette RizAuLait = new Recette(etapes, "Riz au lait", 1, 65, 60, "https://image.afcdn.com/recipe/20171124/75539_w600cxt0cyt0cxb5520cyb3773.jpg");


        ArrayList<Etape> recetteEau = new ArrayList<Etape>();
        Etape etape1_eau = new Etape("Etape 1", "Prenez le verre avec le numéro deux dans votre main gauche.", R.drawable.etape1);
        Etape etape2_eau = new Etape("Etape 2", "Prenez la bouteille d'eau dans votre main droite.", R.drawable.etape2);
        Etape etape3_eau = new Etape("Etape 3", "Remplir le verre numéro deux jusqu'au trait bleu.", R.drawable.etape3);
        Etape etape4_eau = new Etape("Etape 4", "Répétez l'opération précédente avec le verre numéro trois.", R.drawable.etape4);
        Etape etape5_eau = new Etape("Etape 5", "Remplir le verre numéro un avec les contenus des verres numéros deux et trois. Ce cocktail est à boire très frais !", R.drawable.etape5);
        recetteEau.add(etape1_eau);
        recetteEau.add(etape2_eau);
        recetteEau.add(etape3_eau);
        recetteEau.add(etape4_eau);
        recetteEau.add(etape5_eau);
        Recette EauDesAlpes = new Recette(recetteEau, "Cocktail Aquatique", 1, 5, 0, "http://img.over-blog-kiwi.com/1/49/01/89/20160621/ob_38cde5_eaurenverse.jpg");
        recettes.add(EauDesAlpes);
        recettes.add(RizAuLait);


        recyclerView = (RecyclerView) findViewById(R.id.liste);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapterRecette(recettes);
        recyclerView.setAdapter(adapter);

    }

}



