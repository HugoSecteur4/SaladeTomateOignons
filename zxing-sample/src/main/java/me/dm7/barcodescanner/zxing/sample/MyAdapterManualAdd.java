package me.dm7.barcodescanner.zxing.sample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.util.Vector;

public class MyAdapterManualAdd extends RecyclerView.Adapter<MyViewHolderManualAdd> {
    private Context cApp;
    private Context cBase;
    private WindowManager wm;

    Vector<Ingredient> list;

    //ajouter un constructeur prenant en entrée une liste
    public MyAdapterManualAdd(Vector<Ingredient> list, Context cApp, Context cBase, WindowManager wm) {
        this.list = list;
        this.cApp = cApp;
        this.cBase = cBase;
        this.wm = wm;

    }

    //cette fonction permet de créer les viewHolder
    //et par la même indiquer la vue à inflater (à partir des layout xml)
    @Override
    public MyViewHolderManualAdd onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_cards_add,viewGroup,false);
        return new MyViewHolderManualAdd(view, cApp,cBase,wm);
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