package me.dm7.barcodescanner.zxing.sample;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MyViewHolderRecette extends RecyclerView.ViewHolder{

    private TextView textViewView;
    private ImageView imageView;
    private Button button;
    private TextView dureeView;

    //itemView est la vue correspondante à 1 cellule
    public MyViewHolderRecette(View itemView) {
        super(itemView);

        //c'est ici que l'on fait nos findView

        textViewView = (TextView) itemView.findViewById(R.id.textRecipe);
        imageView = (ImageView) itemView.findViewById(R.id.imageRecipe);
        button = (Button) itemView.findViewById(R.id.buttonRecipe);
        dureeView = (TextView) itemView.findViewById(R.id.duree);
    }

    //puis ajouter une fonction pour remplir la cellule en fonction d'un MyObject
    public void bind(final Recette myObject, final MyAdapterRecette adapter){
        textViewView.setText(myObject.getNom());
        dureeView.setText(Integer.toString(myObject.getDuree())+" min");
        Picasso.get().load(myObject.getImage()).fit().centerInside().into(imageView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),VoiceRecognitionActivity.class);
                intent.putExtra("recette",myObject);
                view.getContext().startActivity(intent);
                adapter.notifyDataSetChanged();
            }
        });
    }
}