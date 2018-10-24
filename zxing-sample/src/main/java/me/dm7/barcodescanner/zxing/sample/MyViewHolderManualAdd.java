package me.dm7.barcodescanner.zxing.sample;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
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
    private Context cApp;
    private Context cBase;
    private WindowManager wm;

    //itemView est la vue correspondante à 1 cellule
    public MyViewHolderManualAdd(View itemView, Context contextApp, Context contexteBase, WindowManager windm) {
        super(itemView);
        cApp=contextApp;
        cBase = contexteBase;
        wm=windm;
        //c'est ici que l'on fait nos findView

        textViewView = (TextView) itemView.findViewById(R.id.text);
        imageView = (ImageView) itemView.findViewById(R.id.image);
        buttonView = (Button) itemView.findViewById(R.id.button3);
    }

    //puis ajouter une fonction pour remplir la cellule en fonction d'un MyObject
    public void bind(final Ingredient myObject){
        final Ingredient ingr = myObject;
        textViewView.setText(myObject.getNom());
        Picasso.get().load(myObject.getImage()).fit().centerInside().into(imageView);
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean inside=false;
                int i;
                for(i=0; !inside && i<MainActivity.ingredients.size();i++){
                    if(MainActivity.ingredients.get(i).getId()==myObject.getId())
                        inside=true;
                }
                if(inside)
                    MainActivity.ingredients.get(i-1).incrementQuantity();
                else
                    MainActivity.ingredients.add(myObject);

                Toast toast = new Toast(cApp);

                LinearLayout toastLayout = new LinearLayout(cBase);
                toastLayout.setOrientation(LinearLayout.HORIZONTAL);

                //ImageView view = new ImageView(getApplicationContext());
                ImageView imageView = new ImageView(cApp);
                Picasso.get().load(ingr.getImage()).into(imageView);
                TextView text = new TextView(cApp);
                text.setText(ingr.getNom());
                text.setTextSize(25);
                text.setTextColor(Color.WHITE);
                toastLayout.addView(imageView);
                toastLayout.addView(text);

                toastLayout.setBackgroundColor(Color.argb(100,20,20,20));
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