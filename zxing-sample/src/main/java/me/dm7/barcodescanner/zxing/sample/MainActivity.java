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
    private static final int ZXING_CAMERA_PERMISSION = 1;
    private Class<?> mClss;
    public static String variable = "rien pour le moment";
    //private Vector<Ingredient> ingredients;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_main);
        setupToolbar();
        /*TextView v = (TextView) findViewById(R.id.ingredient_courant);
        v.setText(variable);*/
        //this.ingredients = new Vector<>();
    }

    @Override
    public void onResume(){
        super.onResume();
        /*TextView v = (TextView) findViewById(R.id.ingredient_courant);
        v.setText(variable);*/
        // put your code here...

    }

    public void goto_lets_cook(View view){
       /* Intent intent = new Intent(this,LetsCookActivity.class);
        startActivity(intent);*/
    }

    public void goto_liste_course(View view){
       /* Intent intent = new Intent(this,ListeCourseActivity.class);
        startActivity(intent);*/
    }

    public void goto_recettes(View view){
        /*Intent intent = new Intent(this,RecetteActivity.class);
        startActivity(intent);*/
    }

    public void goto_ingredients(View view){
       /* Intent intent = new Intent(this,IngredientsActivity.class);
        startActivity(intent);*/
        launchActivity(SimpleScannerActivity.class);
    }

    public void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void launchSimpleActivity(View v) {

        Intent intent = new Intent(this, SimpleScannerActivity.class);
        //intent.putExtra("ingerdients", this.ingredients);
        //startActivity(intent);
        launchActivity(SimpleScannerActivity.class);
    }

    public void launchSimpleFragmentActivity(View v) {
        launchActivity(SimpleScannerFragmentActivity.class);
    }

    public void launchFullActivity(View v) {
        launchActivity(FullScannerActivity.class);
    }

    public void launchFullFragmentActivity(View v) {
        launchActivity(FullScannerFragmentActivity.class);
    }

    public void launchFullScreenScannerFragmentActivity(View v) {
        launchActivity(FullScreenScannerFragmentActivity.class);
    }

    public void launchCustomViewFinderScannerActivity(View v) {
        launchActivity(CustomViewFinderScannerActivity.class);
    }

    public void launchScalingScannerActivity(View v) {
        launchActivity(ScalingScannerActivity.class);
    }

    public void launchActivity(Class<?> clss) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            mClss = clss;
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, ZXING_CAMERA_PERMISSION);
        } else {
            Intent intent = new Intent(this, clss);
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String permissions[], int[] grantResults) {
        switch (requestCode) {
            case ZXING_CAMERA_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(mClss != null) {
                        Intent intent = new Intent(this, mClss);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(this, "Please grant camera permission to use the QR Scanner", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }
}