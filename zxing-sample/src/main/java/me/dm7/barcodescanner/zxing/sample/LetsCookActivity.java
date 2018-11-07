package me.dm7.barcodescanner.zxing.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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
        Etape etape1 = new Etape("Etape 1", "Descrip1", null);
        Etape etape2 = new Etape("Etape 2", "Descrip2", null);
        etapes.add(etape1);
        etapes.add(etape2);
        Recette recette = new Recette(etapes, "nom", 3, 30, 20, null);
        recettes.add(recette);

        recyclerView = (RecyclerView) findViewById(R.id.liste);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapterRecette(recettes);
        recyclerView.setAdapter(adapter);

    }

}



