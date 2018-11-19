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
        Etape etape1 = new Etape("Etape 1", "Faire bouillir l’eau dans une casserole. Mettre le riz et le laisser bouillir 2 à 3 minutes.", 0);
        Etape etape2 = new Etape("Etape 2", "Égoutter le riz. ", 0);
        Etape etape3 = new Etape("Etape 3", "Faire chauffer le lait avec le zeste râpé du citron, et les sucres. ", 0);
        Etape etape4 = new Etape("Etape 4", "Verser le riz.", 0);
        Etape etape5 = new Etape("Etape 5", "Faire bouillir, puis laisser cuire à feu doux sans remuer. ", 0);
        Etape etape6 = new Etape("Etape 6", "Le riz est cuit lorsqu’il a absorbé tout le lait. ", 0);
        Etape etape7 = new Etape("Etape 7", "Servir froid ou tiède.", 0);
        etapes.add(etape1);
        etapes.add(etape2);
        etapes.add(etape3);
        etapes.add(etape4);
        etapes.add(etape5);
        etapes.add(etape6);
        etapes.add(etape7);
        Recette RizAuLait = new Recette(etapes, "Riz au lait", 1, 65, 60, "https://image.afcdn.com/recipe/20171124/75539_w600cxt0cyt0cxb5520cyb3773.jpg");
        recettes.add(RizAuLait);

        ArrayList<Etape> recetteEau = new ArrayList<Etape>();
        Etape etape1_eau = new Etape("Etape 1", "Prenez le verre 2 dans la main gauche.", R.drawable.etape1);
        Etape etape2_eau = new Etape("Etape 2", "Prenez la bouteille d'eau dans la main droite.", R.drawable.etape2);
        Etape etape3_eau = new Etape("Etape 3", "Remplir le verre 2 jusqu'au trait.", R.drawable.etape3);
        Etape etape4_eau = new Etape("Etape 4", "Répétez les opérations suivantes avec le verre 3.", R.drawable.etape4);
        Etape etape5_eau = new Etape("Etape 5", "Remplir le verre 1 à l'aide du verre 2 puis du verre 3. Servir bien frais !", R.drawable.etape5);
        recetteEau.add(etape1_eau);
        recetteEau.add(etape2_eau);
        recetteEau.add(etape3_eau);
        recetteEau.add(etape4_eau);
        recetteEau.add(etape5_eau);
        Recette EauDesAlpes = new Recette(recetteEau, "Cocktail d'eau des Alpes", 1, 2, 0, "https://image.afcdn.com/recipe/20171124/75539_w600cxt0cyt0cxb5520cyb3773.jpg");
        recettes.add(EauDesAlpes);


        recyclerView = (RecyclerView) findViewById(R.id.liste);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapterRecette(recettes);
        recyclerView.setAdapter(adapter);

    }

}



