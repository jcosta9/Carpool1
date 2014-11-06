package com.example.carpool.login;

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

import com.example.carpool.MeusEventos;
import com.example.carpool.R;
import com.example.carpool.R.id;
import com.example.carpool.R.layout;
import com.example.carpool.R.menu;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends ActionBarActivity {

	Button btCadastrar;
    EditText email, senha, nome, sobrenome, telefone, endereco, bairro, cidade, uf;
	HttpPost httppost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
    ProgressDialog dialog = null;
    TextView tv;
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		btCadastrar = (Button)findViewById(R.id.btCadastrar);
		tv = (TextView)findViewById(R.id.cadastroMsg);
		
		email = (EditText)findViewById(R.id.cpCadastroEmail);
		senha = (EditText)findViewById(R.id.cpCadastroSenha);
		nome = (EditText)findViewById(R.id.cpCadastroNomeUs);
		sobrenome = (EditText)findViewById(R.id.cpCadastroSobrUs);
		telefone = (EditText)findViewById(R.id.cpCadastroTel);
		endereco = (EditText)findViewById(R.id.cpCadastroEnd);
		bairro = (EditText)findViewById(R.id.cpCadastroBairro);
		cidade = (EditText)findViewById(R.id.cpCadastroCidade);
		uf = (EditText)findViewById(R.id.cpCadastroEstado);

	
	btCadastrar.setOnClickListener(new OnClickListener(){
		public void onClick(View v){
			dialog = ProgressDialog.show(Register.this, "",
                    "Cadastrando Usuario...", true);
             new Thread(new Runnable() {
                    public void run() {
                        cadastrarUsuario();                         
                    }
                  }).start(); 
		}
}
		
		);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
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
	
	void cadastrarUsuario(){
        try{           
              
            httpclient=new DefaultHttpClient();
            httppost= new HttpPost("http://192.168.1.103/carpool/cadastraUsuario.php"); // make sure the url is correct.
            //add your data
            nameValuePairs = new ArrayList<NameValuePair>(9);
            // Always use the same variable name for posting i.e the android side variable name and php side variable name should be similar,
            nameValuePairs.add(new BasicNameValuePair("email",email.getText().toString().trim()));  // $Edittext_value = $_POST['Edittext_value'];
            nameValuePairs.add(new BasicNameValuePair("senha",senha.getText().toString().trim()));
            nameValuePairs.add(new BasicNameValuePair("nome",nome.getText().toString().trim()));
            nameValuePairs.add(new BasicNameValuePair("sobrenome",sobrenome.getText().toString().trim()));
            nameValuePairs.add(new BasicNameValuePair("telefone",telefone.getText().toString().trim()));
            nameValuePairs.add(new BasicNameValuePair("endereco",endereco.getText().toString().trim()));
            nameValuePairs.add(new BasicNameValuePair("bairro",bairro.getText().toString().trim()));
            nameValuePairs.add(new BasicNameValuePair("cidade",cidade.getText().toString().trim()));
            nameValuePairs.add(new BasicNameValuePair("uf",uf.getText().toString().trim()));
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
             
            if(response.equalsIgnoreCase("Cadastro Realizado com Sucesso!")){
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(Register.this,"Cadastro Realizado com Sucesso!", Toast.LENGTH_SHORT).show();
                    }
                });
                 
                startActivity(new Intent(Register.this, MainActivity.class));
            }else{
                showAlert();               
            }
             
        }catch(Exception e){
            dialog.dismiss();
            System.out.println("Exception : " + e.getMessage());
        }
    }
    public void showAlert(){
        Register.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                builder.setTitle("Erro no Cadastro.");
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

