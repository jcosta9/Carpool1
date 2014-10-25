package com.example.carpool.events;

import android.media.Image;


public class Item {
	String titulo;
	String descricao;
	//Image icone;
	
	
	//Responsável por criar cada item que será exibido na tela de Eventos
	//TODO: Adicionar a imagem que identificará se para tal evento o usuário
	//poderá dar carona.
	public Item(String titulo, String descricao){//, Image icone){
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
