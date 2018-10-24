package me.dm7.barcodescanner.zxing.sample;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MyViewHolder extends RecyclerView.ViewHolder{

    private TextView textViewView;
    private ImageView imageView;
    private ImageButton button;
    private TextView quantityView;
    private ImageButton addButton;

    //itemView est la vue correspondante à 1 cellule
    public MyViewHolder(View itemView) {
        super(itemView);

        //c'est ici que l'on fait nos findView

        textViewView = (TextView) itemView.findViewById(R.id.text);
        imageView = (ImageView) itemView.findViewById(R.id.image);
        button = (ImageButton) itemView.findViewById(R.id.deleteButton);
        quantityView = (TextView) itemView.findViewById(R.id.quantity);
        addButton = (ImageButton) itemView.findViewById(R.id.addButton);
    }

    //puis ajouter une fonction pour remplir la cellule en fonction d'un MyObject
    public void bind(final Ingredient myObject, final MyAdapter adapter){
        textViewView.setText(myObject.getNom());
        quantityView.setText("x"+myObject.getQuantity());
        Picasso.get().load(myObject.getImage()).fit().centerInside().into(imageView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.ingredients.remove(myObject);
                adapter.notifyDataSetChanged();
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myObject.incrementQuantity();
                adapter.notifyDataSetChanged();
            }
        });
    }
}