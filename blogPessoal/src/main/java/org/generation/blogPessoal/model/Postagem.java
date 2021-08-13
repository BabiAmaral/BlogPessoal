package org.generation.blogPessoal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/*Anotações são parametros que colocamos em cima das propriedades(ou classe) 
  para que dê um certo tipo de comportamento para elas.*/

@Entity // anotção que indica que a classe é uma entidade do JPA
@Table(name = "postagem") // anotação indicando que será uma tablela de postagens
public class Postagem
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;// linha 17-19 forma a primary key

	@NotNull
	@Size(min = 5, max = 100)
	private String titulo;

	@NotNull
	@Size(min = 10, max = 500)
	private String texto;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	private Date data = new java.sql.Date(System.currentTimeMillis());// para data, importar do java util e usamos o
								                                      // metodo para ele capturar cada data.
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnoreProperties({ "minhasPostagens" })
	private Usuario criador;

	@ManyToOne(fetch = FetchType.EAGER) //anotações para que as entidades postagem e tema se relacionem //relação muitos para 1
	@JsonIgnoreProperties({"listaDePostagens"}) //o que vamos ignorar dentro de tema, ou melhor, quando chegar em () para de aprensentar dentro de tema
	private Tema tema;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getTitulo()
	{
		return titulo;
	}

	public void setTitulo(String titulo)
	{
		this.titulo = titulo;
	}

	public String getTexto()
	{
		return texto;
	}

	public void setTexto(String texto)
	{
		this.texto = texto;
	}

	public Date getData()
	{
		return data;
	}

	public void setData(Date data)
	{
		this.data = data;
	}

	public Usuario getCriador()
	{
		return criador;
	}

	public void setCriador(Usuario criador)
	{
		this.criador = criador;
	}

	public Tema getTema()
	{
		return tema;
	}

	public void setTema(Tema tema)
	{
		this.tema = tema;
	}

	
}