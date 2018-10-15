package me.dm7.barcodescanner.zxing.sample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Vector;

public class MyAdapterManualAdd extends RecyclerView.Adapter<MyViewHolderManualAdd> {

    Vector<Ingredient> list;

    //ajouter un constructeur prenant en entrée une liste
    public MyAdapterManualAdd(Vector<Ingredient> list) {
        this.list = list;
    }

    //cette fonction permet de créer les viewHolder
    //et par la même indiquer la vue à inflater (à partir des layout xml)
    @Override
    public MyViewHolderManualAdd onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_cards_add,viewGroup,false);
        return new MyViewHolderManualAdd(view);
    }

    //c'est ici que nous allons remplir notre cellule avec le texte/image de chaque MyObjects
    @Override
    public void onBindViewHolder(MyViewHolderManualAdd myViewHolder, int position) {
        Ingredient myObject = list.get(position);
        myViewHolder.bind(myObject);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}