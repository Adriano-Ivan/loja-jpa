package br.com.adriano.loja.modelo;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CategoriaId implements Serializable{

	private String nome;
	private String tipo;
	
	public CategoriaId() {
		// TODO Auto-generated constructor stub
	}

	public CategoriaId(String nome, String tipo) {
		super();
		this.nome = nome;
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
}
