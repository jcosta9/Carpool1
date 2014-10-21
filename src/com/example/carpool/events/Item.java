package com.example.carpool.events;

public class Item {
	String titulo;
	String descricao;
	
	
	//Responsável por criar cada item que será exibido na tela de Eventos
	public Item(String titulo, String descricao){
		this.descricao = descricao;
		this.titulo = titulo;
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
