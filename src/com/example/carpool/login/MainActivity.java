package com.example.carpool.login;

import com.example.carpool.MeusEventos;
import com.example.carpool.R;
import com.example.carpool.R.id;
import com.example.carpool.R.layout;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE); // tira a barra de título da página
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button button = (Button)findViewById(R.id.butaoCadastro);
        button.setOnClickListener(new OnClickListener(){
        		public void onClick(View v){
        			//EditText login = (EditText)findViewById(R.id.editText1);
        			//EditText senha = (EditText)findViewById(R.id.editText2);
        			Intent intent1 = new Intent(MainActivity.this, MeusEventos.class);
					///String login = findViewById(R.id.editText1).toString();
					//String senha = findViewById(R.id.editText1).toString();
				//intent1.putExtra("login", login);
        			//intent1.putExtra("senha", senha);
        			startActivity(intent1);
        		}
        }
        		
        		);
    }

   
}
