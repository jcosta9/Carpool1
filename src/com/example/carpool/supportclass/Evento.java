package com.example.carpool.supportclass;

//import android.media.Image;


public class Evento {
	String titulo;
	String descricao;
	//Image icone;
	
	
	//Respons�vel por criar cada item que ser� exibido na tela de Eventos
	//TODO: Adicionar a imagem que identificar� se para tal evento o usu�rio
	//poder� dar carona.
	public Evento(String titulo, String descricao){//, Image icone){
		this.descricao = descricao;
		this.titulo = titulo;
		//this.icone = icone;
	}
	
	//gets and sets
	public void setTitulo(String titulo){
		this.titulo = titulo;
	}
	public void setDescricao(String descricao){
		this.descricao = descricao;
	}
	public String getTitulo(){
		return this.titulo;
	}
	public String getDescricao(){
		return this.descricao;
	}

}
