package me.dm7.barcodescanner.zxing.sample;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;
import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import javax.net.ssl.HttpsURLConnection;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class SimpleScannerActivity extends BaseScannerActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    public static Ingredient ingredient;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

        /*final Intent intent = getIntent();
        this.ingredients = (Vector<Ingredient>) intent.getSerializableExtra("ingredients");*/
        setContentView(R.layout.activity_simple_scanner);
        setupToolbar();

        ViewGroup contentFrame = (ViewGroup) findViewById(R.id.content_frame);
        mScannerView = new ZXingScannerView(this);
        contentFrame.addView(mScannerView);
    }

    public void setIngredient(Ingredient i) {
        ingredient = i;
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    public static JSONObject getJSONObjectFromURL(String urlString) throws IOException, JSONException {
        HttpURLConnection urlConnection = null;
        URL url = new URL(urlString);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(10000 /* milliseconds */ );
        urlConnection.setConnectTimeout(15000 /* milliseconds */ );
        urlConnection.setDoOutput(true);
        urlConnection.connect();

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();

        String jsonString = sb.toString();
        System.out.println("JSON: " + jsonString);

        return new JSONObject(jsonString);
    }



    @Override
    public void handleResult(Result rawResult) {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                    Log.println(Log.ASSERT,"tag", "debut scan");
                    String url = "https://fr.openfoodfacts.org/api/v0/produit/5050083458255.json";
                try {
                    JSONObject jsonObject = getJSONObjectFromURL(url);
                    Log.println(Log.ASSERT, "tag", ((JSONObject) jsonObject.get("product")).getString("product_name"));
                    Intent intent = new Intent(SimpleScannerActivity.this, ListeIngredients.class);
                    //Log.println(Log.ASSERT, "tadddg", ingredient.getNom());
                    MainActivity.variable = "Traisor";
                    setIngredient(new Ingredient(((JSONObject) jsonObject.get("product")).getString("product_name")));
                    intent.putExtra("ingredients", ingredient.getNom());
                    startActivity(intent);
                    //setIngredient(new Ingredient(((JSONObject) jsonObject.get("product")).getString("product_name")));
                    //ingredient = new Ingredient(((JSONObject) jsonObject.get("product")).getString("product_name"));
                    //ingredients.add(i);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
       // Toast.makeText(this, "Conttttents = ", Toast.LENGTH_SHORT).show();

        // Note:
        // * Wait 2 seconds to resume the preview.
        // * On older devices continuously stopping and resuming camera preview can result in freezing the app.
        // * I don't know why this is the case but I don't have the time to figure out.
        /*Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mScannerView.resumeCameraPreview(SimpleScannerActivity.this);
            }
        }, 2000);*/




    }


}
