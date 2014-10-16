package com.example.carpool;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.view.View;
import android.annotation.SuppressLint;
import android.content.Intent;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE); // tira a barra de título da página
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button button = (Button)findViewById(R.id.button1);
        button.setOnClickListener(new OnClickListener(){
        		public void onClick(View v){
        			//EditText login = (EditText)findViewById(R.id.editText1);
        			//EditText senha = (EditText)findViewById(R.id.editText2);
        			Intent intent1 = new Intent(MainActivity.this, Pagina3.class);
					///String login = findViewById(R.id.editText1).toString();
					//String senha = findViewById(R.id.editText1).toString();
				//intent1.putExtra("login", login);
        			//intent1.putExtra("senha", senha);
        			startActivity(intent1);
        		}
        }
        		
        		);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
