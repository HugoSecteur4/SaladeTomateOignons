package me.dm7.barcodescanner.zxing.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONObject;

public class ListeIngredients extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_liste_ingredients);

        TextView view = (TextView) findViewById(R.id.ingredient1);
        if (view == null) {
            Log.println(Log.ASSERT, "tag", "null");
        } else {
            Log.println(Log.ASSERT, "tag", "pas null");

        }

        final Intent intent = getIntent();
        String nomIngredient = intent.getStringExtra("ingredients");

        view.setText(nomIngredient);
    }
}
