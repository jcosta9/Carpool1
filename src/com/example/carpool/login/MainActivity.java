package com.example.carpool.login;

import com.example.carpool.MeusEventos;
import com.example.carpool.R;
import com.example.carpool.R.id;
import com.example.carpool.R.layout;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;


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
import org.apache.http.message.BasicNameValuePair;
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


public class MainActivity extends ActionBarActivity {
	Button btlogin;
    TextView btEsqSenha;
    TextView btRegistrar, tv;
    EditText email, senha;
	HttpPost httppost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
    ProgressDialog dialog = null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    	
    	
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE); // tira a barra de título da página
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btlogin = (Button)findViewById(R.id.btCadastro);
        btEsqSenha = (TextView)findViewById(R.id.esquecisenha);
        btRegistrar = (TextView)findViewById(R.id.registrar);
        tv = (TextView)findViewById(R.id.tv);
        email = (EditText)findViewById(R.id.cpNomeUs);
		senha = (EditText)findViewById(R.id.cpSenhaUs);
        
        
        /**
         * Determina o que ira acontecer quando clicar no botao de login
         */
        btlogin.setOnClickListener(new OnClickListener(){
        		public void onClick(View v){
        			dialog = ProgressDialog.show(MainActivity.this, "",
                            "Validating user...", true);
                     new Thread(new Runnable() {
                            public void run() {
                                login();                         
                            }
                          }).start(); 
        			//EditText login = (EditText)findViewById(R.id.cpNomeUs);
        			//EditText senha = (EditText)findViewById(R.id.cpSenhaUs);
        			//Intent intent1 = new Intent(MainActivity.this, MeusEventos.class);
					///String login = findViewById(R.id.editText1).toString();
					//String senha = findViewById(R.id.editText1).toString();
				//intent1.putExtra("login", login);
        			//intent1.putExtra("senha", senha);
        			//startActivity(intent1);
        		}
        }
        		
        		);
        
        /**
         * Determina o que ira acontecer quando clicar na textView 'esqueci a senha'
         */
        btEsqSenha.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		Intent intent1 = new Intent(MainActivity.this, PasswordReset.class);
        		startActivity(intent1);
        	}
        }
        		);
        
        /**
         * Determina o que ira acontecer quando clicar na textView 'registrar'
         */
        btRegistrar.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		Intent intent1 = new Intent(MainActivity.this, Register.class);
        		startActivity(intent1);
        	}
        });
    }

    void login(){
        try{           
              
            httpclient=new DefaultHttpClient();
            httppost= new HttpPost("http://192.168.1.103/carpool/check.php"); // make sure the url is correct.
            //add your data
            nameValuePairs = new ArrayList<NameValuePair>(2);
            // Always use the same variable name for posting i.e the android side variable name and php side variable name should be similar,
            nameValuePairs.add(new BasicNameValuePair("email",email.getText().toString().trim()));  // $Edittext_value = $_POST['Edittext_value'];
            nameValuePairs.add(new BasicNameValuePair("senha",senha.getText().toString().trim()));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            //Execute HTTP Post Request
            response=httpclient.execute(httppost);
            // edited by James from coderzheaven.. from here....
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            final String response = httpclient.execute(httppost, responseHandler);
            System.out.println("Response : " + response);
            runOnUiThread(new Runnable() {
                public void run() {
                    tv.setText("Response from PHP : " + response);
                    dialog.dismiss();
                }
            });
             
            if(response.equalsIgnoreCase("User Found")){
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(MainActivity.this,"Login Success", Toast.LENGTH_SHORT).show();
                    }
                });
                 
                startActivity(new Intent(MainActivity.this, MeusEventos.class));
            }else{
                showAlert();               
            }
             
        }catch(Exception e){
            dialog.dismiss();
            System.out.println("Exception : " + e.getMessage());
        }
    }
    public void showAlert(){
        MainActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Login Error.");
                builder.setMessage("User not Found.") 
                       .setCancelable(false)
                       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int id) {
                           }
                       });                    
                AlertDialog alert = builder.create();
                alert.show();              
            }
        });
    }
}
