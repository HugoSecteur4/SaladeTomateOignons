package me.dm7.barcodescanner.zxing.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Vector;

public class MesIngredients extends AppCompatActivity {
    //private Vector<Ingredient> ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //final Intent intent = getIntent();
        //this.ingredients = (Vector<Ingredient>) intent.getSerializableExtra("ingredients");
        //TextView view = (TextView) findViewById(R.id.textView7);
        //view.setText(this.ingredients.get(0).getNom());
        setContentView(R.layout.activity_mes_ingredients2);
    }
}
