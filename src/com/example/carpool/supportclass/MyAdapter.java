package com.example.carpool.supportclass;

import java.util.ArrayList;

import com.example.carpool.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//Cria a listView
public class MyAdapter extends ArrayAdapter<Evento>{
	
	private final Context context;
	private final ArrayList<Evento> itemsArrayList;
	
	public MyAdapter(Context context, ArrayList<Evento> itemsArrayList){
		super(context, R.layout.row, itemsArrayList);
		this.context = context;
		this.itemsArrayList = itemsArrayList;
	}
	
	//@override
	@SuppressLint("ViewHolder")
	public View getView(int position, View convertView, ViewGroup parent){
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.row, parent, false);
		
		TextView labelView = (TextView) rowView.findViewById(R.id.label);
		TextView valueView = (TextView) rowView.findViewById(R.id.value);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.icon); //TODO: settar o ícone específico.
		
		labelView.setText(itemsArrayList.get(position).getTitulo());
        valueView.setText(itemsArrayList.get(position).getDescricao());
        
        
        return rowView;
	}
	

}
