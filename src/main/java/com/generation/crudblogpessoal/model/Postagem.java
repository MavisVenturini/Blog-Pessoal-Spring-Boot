package com.generation.crudblogpessoal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Postagem")
public class Postagem{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min=5,max=100,message="O campo deve ter no minimo 5 letras e no maximo 100")
	private String titulo;
	
	@NotNull
	@Size(min=10,max=500)
	private String texto;
	
	@UpdateTimestamp
	private Date data;

	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;
	
	public Tema getTema() {
		return tema;
	}


	public void setTema(Tema tema) {
		this.tema = tema;
	}


	public long getId(){
		return id;
	}


	public void setId(long id){
		this.id = id;
	}


	public String getTitulo(){
		return titulo;
	}


	public void setTitulo(String titulo){
		this.titulo = titulo;
	}


	public String getTexto(){
		return texto;
	}


	public void setTexto(String texto){
		this.texto = texto;
	}


	public Date getData(){
		return data;
	}


	public void setData(Date data){
		this.data = data;
	}
}
