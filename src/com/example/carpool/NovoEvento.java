package com.example.carpool;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class NovoEvento extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_novo_evento);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.novo_evento, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch(id){
			case (R.id.action_settings): {
				Intent intent1 = new Intent(NovoEvento.this, Settings.class);
				startActivity(intent1);
				return true;
			}
			case(R.id.action_new) :{
				Intent intent1 = new Intent(NovoEvento.this, NovoEvento.class);
				startActivity(intent1);
				return true;
			}
			case(R.id.action_search): {
				Intent intent1 = new Intent(NovoEvento.this, Pesquisar.class);
				startActivity(intent1);
				return true;
			}
			case(R.id.action_events): {
				Intent intent1 = new Intent(NovoEvento.this, MeusEventos.class);
				startActivity(intent1);
				return true;
			}
		}
		return super.onOptionsItemSelected(item);
	}
}
