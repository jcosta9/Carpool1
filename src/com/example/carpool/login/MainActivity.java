package com.example.carpool.login;

import com.example.carpool.MeusEventos;
import com.example.carpool.R;
import com.example.carpool.R.id;
import com.example.carpool.R.layout;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;

/*
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.<span class="IL_AD" id="IL_AD2">message</span>.BasicNameValuePair;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
*/

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE); // tira a barra de título da página
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button btlogin = (Button)findViewById(R.id.btCadastro);
        TextView btEsqSenha = (TextView)findViewById(R.id.esquecisenha);
        TextView btRegistrar = (TextView)findViewById(R.id.registrar);
        
        btlogin.setOnClickListener(new OnClickListener(){
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
        btEsqSenha.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		Intent intent1 = new Intent(MainActivity.this, PasswordReset.class);
        		startActivity(intent1);
        	}
        }
        		);
        
        btRegistrar.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		Intent intent1 = new Intent(MainActivity.this, Register.class);
        		startActivity(intent1);
        	}
        });
    }

   
}
