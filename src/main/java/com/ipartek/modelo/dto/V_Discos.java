package com.ipartek.modelo.dto;

public class V_Discos {
	private int id;
	private String titulo;
	private int num_canciones;
	private int fk_grupo;
	private String grupo;
	
	
	public V_Discos(int id, String titulo, int num_canciones, int fk_grupo, String grupo) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.num_canciones = num_canciones;
		this.fk_grupo = fk_grupo;
		this.grupo = grupo;
	}
	
	public V_Discos() {
		super();
		this.id = 0;
		this.titulo = "";
		this.num_canciones = 0;
		this.fk_grupo = 0;
		this.grupo = "";
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}



	public int getNum_canciones() {
		return num_canciones;
	}


	public void setNum_canciones(int num_canciones) {
		this.num_canciones = num_canciones;
	}



	public int getFk_grupo() {
		return fk_grupo;
	}



	public void setFk_grupo(int fk_grupo) {
		this.fk_grupo = fk_grupo;
	}



	public String getGrupo() {
		return grupo;
	}



	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}


	@Override
	public String toString() {
		return id+";"+titulo+";"+num_canciones+";"+fk_grupo+";"+grupo;
	}
	
	
	
	
	
}
