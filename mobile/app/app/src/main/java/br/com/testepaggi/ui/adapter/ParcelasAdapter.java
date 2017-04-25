package br.com.testepaggi.ui.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import br.com.testepaggi.R;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class ParcelasAdapter extends BaseAdapter {

    private Context     ctx;
    private String[]    lista;

    public ParcelasAdapter(Context context, String[] lista){
        this.ctx = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.length;
    }

    @Override
    public Object getItem(int arg0) {
        return lista[arg0];
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        LayoutInflater inflater = (LayoutInflater)   ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.view_spinner_textview, null);

        TextView txView = (TextView) view.findViewById(R.id.txView);

        txView.setText(this.lista[position]);

        return txView;

    }

}
