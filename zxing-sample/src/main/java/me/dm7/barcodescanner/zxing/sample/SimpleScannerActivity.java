package me.dm7.barcodescanner.zxing.sample;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.zxing.Result;
import com.squareup.picasso.Picasso;

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
    //public static Ingredient ingredient;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

        /*final Intent intent = getIntent();
        this.ingredients = (Vector<Ingredient>) intent.getSerializableExtra("ingredients");*/
        setContentView(R.layout.activity_simple_scanner);
        setupToolbar();
        setTitle("Scannez vos articles");
        ViewGroup contentFrame = (ViewGroup) findViewById(R.id.content_frame);
        mScannerView = new ZXingScannerView(this);
        contentFrame.addView(mScannerView);
    }

    //public void setIngredient(Ingredient i) {
    //    ingredient = i;
    //}

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
    public void handleResult(final Result rawResult) {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                    Log.println(Log.ASSERT,"tag", "debut scan");
                    String url = "https://fr.openfoodfacts.org/api/v0/produit/" + rawResult.getText() + ".json";
                    Log.println(Log.ASSERT, "tag", url);

                try {
                    JSONObject jsonObject = getJSONObjectFromURL(url);
                    Log.println(Log.ASSERT, "tag", ((JSONObject) jsonObject.get("product")).getString("product_name"));
                    //Intent intent = new Intent(SimpleScannerActivity.this, ListeIngredients.class);
                    //Log.println(Log.ASSERT, "tadddg", ingredient.getNom());
                    //MainActivity.variable = "Traisor";
                    final String ingredient_scan = ((JSONObject) jsonObject.get("product")).getString("product_name");
                    final String urlPhoto = ((JSONObject) jsonObject.get("product")).getString("image_small_url");

                    boolean inside = false;
                    for(int i=0;!inside && i<MainActivity.ingredients.size();i++){
                        if(MainActivity.ingredients.get(i).getNom().equals(ingredient_scan) ){
                            inside=true;
                        }
                    }
                    if(!inside){

                        MainActivity.ingredients.add(new Ingredient(ingredient_scan, urlPhoto));
                    }


                    //intent.putExtra("ingredients", ingredient.getNom());
                    //startActivity(intent);
                    //setIngredient(new Ingredient(((JSONObject) jsonObject.get("product")).getString("product_name")));
                    //ingredient = new Ingredient(((JSONObject) jsonObject.get("product")).getString("product_name"));
                    //ingredients.add(i);
                    runOnUiThread(new Runnable() {
                        public void run() {

                            Toast toast = new Toast(getApplicationContext());
                            LinearLayout toastLayout = new LinearLayout(getBaseContext());
                            toastLayout.setOrientation(LinearLayout.HORIZONTAL);

                            //ImageView view = new ImageView(getApplicationContext());
                            ImageView imageView = new ImageView(getApplicationContext());
                            Picasso.get().load(urlPhoto).into(imageView);
                            TextView text = new TextView(getApplicationContext());
                            text.setText(ingredient_scan);
                            text.setTextSize(30);
                            text.setTextColor(Color.WHITE);
                            toastLayout.addView(imageView);
                            toastLayout.addView(text);
                            //InputStream is = (InputStream) new URL("https://static.openfoodfacts.org/images/products/505/008/345/8255/front_fr.93.100.jpg").getContent();
                            //Drawable d = Drawable.createFromStream(is, "src name");
                            //Bitmap b = resize(BitmapFactory.decodeStream((InputStream)new URL("https://static.openfoodfacts.org/images/products/505/008/345/8255/front_fr.93.100.jpg").getContent()),30,30);
                            // view.setImageBitmap(b);
                            toast.setView(toastLayout);
                            toast.show();
                            //Toast.makeText(SimpleScannerActivity.this, ingredient_scan, Toast.LENGTH_SHORT).show();
                            mScannerView.resumeCameraPreview(SimpleScannerActivity.this);


                        }
                    });

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
