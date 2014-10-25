package com.example.carpool;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import com.example.carpool.supportclass.Evento;
import com.example.carpool.supportclass.MyAdapter;

//import android.app.ListActivity;

public class MeusEventos extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_meus_eventos);
		
		// passa o contexto e os dados para o adaptador
		MyAdapter adapter = new MyAdapter(this, generateData());
		//Pega a listView do arquivo xml
		ListView listView = (ListView) findViewById(R.id.listView);
		
		listView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.meus_eventos, menu);
		return true;
	}
	
	//TODO: Método que pega os dados dos eventos que o usuário confirmou a presença
	// e o joga numa lista ordenada por data de acontecimento, exibindo tal na tela
	public void displayCurrentEvents(){
		
	}

	//Esse método será substituído pelo DisplayCurrentEvents() qndo este for implementado
	private ArrayList<Evento> generateData(){
        ArrayList<Evento> items = new ArrayList<Evento>();
        items.add(new Evento("Evento 1","Primeiro evento da Lista"));
        items.add(new Evento("Evento 2","Segundo evento da Lista"));
        items.add(new Evento("Evento 3","Third evento on the list"));
 
        return items;
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch(id){
			case (R.id.action_settings): {
				Intent intent1 = new Intent(MeusEventos.this, Settings.class);
				startActivity(intent1);
				return true;
			}
			case(R.id.action_new) :{
				Intent intent1 = new Intent(MeusEventos.this, NovoEvento.class);
				startActivity(intent1);
				return true;
			}
			case(R.id.action_search): {
				Intent intent1 = new Intent(MeusEventos.this, Pesquisar.class);
				startActivity(intent1);
				return true;
			}
			case(R.id.action_events): {
				Intent intent1 = new Intent(MeusEventos.this, MeusEventos.class);
				startActivity(intent1);
				return true;
			}
		}
		return super.onOptionsItemSelected(item);
	}
}
