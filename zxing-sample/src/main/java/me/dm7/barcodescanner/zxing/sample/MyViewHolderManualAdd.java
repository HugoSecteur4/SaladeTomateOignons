package me.dm7.barcodescanner.zxing.sample;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MyViewHolderManualAdd extends RecyclerView.ViewHolder{

    private TextView textViewView;
    private ImageView imageView;
    private Button buttonView;

    //itemView est la vue correspondante à 1 cellule
    public MyViewHolderManualAdd(View itemView) {
        super(itemView);

        //c'est ici que l'on fait nos findView

        textViewView = (TextView) itemView.findViewById(R.id.text);
        imageView = (ImageView) itemView.findViewById(R.id.image);
        buttonView = (Button) itemView.findViewById(R.id.button3);
    }

    //puis ajouter une fonction pour remplir la cellule en fonction d'un MyObject
    public void bind(Ingredient myObject){
        final Ingredient ingr = myObject;
        textViewView.setText(myObject.getNom());
        Picasso.get().load(myObject.getImage()).centerCrop().fit().into(imageView);
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.ingredients.add(new Ingredient(ingr.getNom(),ingr.getImage()));
                Toast toast = new Toast(getApplicationContext());
                LinearLayout toastLayout = new LinearLayout(getBaseContext());
                toastLayout.setOrientation(LinearLayout.HORIZONTAL);

                //ImageView view = new ImageView(getApplicationContext());
                ImageView imageView = new ImageView(getApplicationContext());
                Picasso.get().load(urlPhoto).resize(getWindowManager().getDefaultDisplay().getWidth()/4,getWindowManager().getDefaultDisplay().getHeight()/8).into(imageView);
                TextView text = new TextView(ManualAddActivity.this.getApplicationContext());
                text.setText(ingr.getNom());
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
            }
        });
    }
}