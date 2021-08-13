package org.generation.blogPessoal.model;

import javax.validation.constraints.Size;

public class UserLogin // não é necessário anotação para ela, pois ela simplesmente retornará a informação quando o usuario logar
{
	private long id;
	private String nome;
	private String usuario;
	
	@Size(min = 5, max = 100, message = "Necessario min 5 caracteres")
	private String senha;
	
	private String token;
	
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public String getNome()
	{
		return nome;
	}
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	public String getUsuario()
	{
		return usuario;
	}
	public void setUsuario(String usuario)
	{
		this.usuario = usuario;
	}
	public String getSenha()
	{
		return senha;
	}
	public void setSenha(String senha)
	{
		this.senha = senha;
	}
	public String getToken()
	{
		return token;
	}
	public void setToken(String token)
	{
		this.token = token;
	}

	
	
}
