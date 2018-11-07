package me.dm7.barcodescanner.zxing.sample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Vector;

public class MyAdapterRecette extends RecyclerView.Adapter<MyViewHolderRecette> {

    Vector<Recette> list;

    //ajouter un constructeur prenant en entrée une liste
    public MyAdapterRecette(Vector<Recette> list) {
        this.list = list;
    }

    //cette fonction permet de créer les viewHolder
    //et par la même indiquer la vue à inflater (à partir des layout xml)
    @Override
    public MyViewHolderRecette onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_recipes,viewGroup,false);
        return new MyViewHolderRecette(view);
    }

    //c'est ici que nous allons remplir notre cellule avec le texte/image de chaque MyObjects
    @Override
    public void onBindViewHolder(MyViewHolderRecette myViewHolderRecette, int position) {
        Recette myObject = list.get(position);
        myViewHolderRecette.bind(myObject,this);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}