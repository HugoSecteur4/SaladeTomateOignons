package me.dm7.barcodescanner.zxing.sample;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    public static Vector<Ingredient> ingredients;
    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        ingredients = new Vector<>();
        setContentView(R.layout.activity_main);
    }


    public void goto_lets_cook(View view){
        Intent intent = new Intent(this,VoiceRecognitionActivity.class);
        startActivity(intent);
    }

    public void goto_liste_course(View view){
        Intent intent = new Intent(this,ListeCourseActivity.class);
        startActivity(intent);
    }

    public void goto_recettes(View view){
        Intent intent = new Intent(this,RecetteActivity.class);
        startActivity(intent);
    }

    public void goto_ingredients(View view){
        Intent intent = new Intent(this,IngredientsActivity.class);
        startActivity(intent);
    }



}