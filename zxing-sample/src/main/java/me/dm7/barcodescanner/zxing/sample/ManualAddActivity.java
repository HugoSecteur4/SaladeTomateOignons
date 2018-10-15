package me.dm7.barcodescanner.zxing.sample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.SearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class ManualAddActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapterManualAdd adapter;
    private Vector<Ingredient> search_ingredients;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_add);


        recyclerView = (RecyclerView) findViewById(R.id.search_liste);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        search_ingredients = new Vector<>();
        adapter = new MyAdapterManualAdd(this.search_ingredients);
        recyclerView.setAdapter(adapter);

        SearchView view = (SearchView) findViewById(R.id.search_ingredient);
        SearchView.OnQueryTextListener listener = new SearchView.OnQueryTextListener() {

            Vector<Ingredient> temp = new Vector<>();
            @Override
            public boolean onQueryTextSubmit(String query) {
                final String name = query;
                AsyncTask.execute(new Runnable() {
                      @Override
                      public void run() {
                          temp.clear();
                          String url = "https://fr.openfoodfacts.org/cgi/search.pl?search_terms=" + name + "&json=1";
                          try {
                              JSONObject jsonObject = SimpleScannerActivity.getJSONObjectFromURL(url);
                              JSONArray products = jsonObject.getJSONArray("products");
                              for (int i = 0; i < products.length(); i++) {
                                  JSONObject product = (JSONObject) products.get(i);
                                  temp.add(new Ingredient(product.getString("product_name"), product.getString("image_small_url")));

                              }

                          } catch (IOException e) {
                              e.printStackTrace();
                          } catch (JSONException e) {
                              e.printStackTrace();
                          }

                          runOnUiThread(new Runnable() {
                              @Override
                              public void run() {
                                  search_ingredients.clear();
                                  adapter.notifyDataSetChanged();
                                  search_ingredients.addAll(temp);
                                  adapter.notifyDataSetChanged();
                              }
                          });

                      }
                                  }
                );
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        };
        view.setOnQueryTextListener(listener);
    }
}
