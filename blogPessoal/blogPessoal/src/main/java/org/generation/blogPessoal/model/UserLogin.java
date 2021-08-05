package org.generation.blogPessoal.model;

public class UserLogin // não é necessário anotação para ela, pois ela simplesmente retornará a informação quando o usuario logar
{
	private String nome;
	private String usuario;
	private String senha;
	private String token;
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
