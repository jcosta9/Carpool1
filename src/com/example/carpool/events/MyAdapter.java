package com.example.carpool.events;

import java.util.ArrayList;

import com.example.carpool.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

//Cria a listView
public class MyAdapter extends ArrayAdapter<Item>{
	
	private final Context context;
	private final ArrayList<Item> itemsArrayList;
	
	public MyAdapter(Context context, ArrayList<Item> itemsArrayList){
		super(context, R.layout.row, itemsArrayList);
		this.context = context;
		this.itemsArrayList = itemsArrayList;
	}
	
	//@override
	public View getView(int position, View convertView, ViewGroup parent){
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.row, parent, false);
		//TextView labelView = (TextView) rowView.findViewById(R.id.firstLine);//??
		TextView valueView = (TextView) rowView.findViewById(R.id.secondLine);
	}
	

}
