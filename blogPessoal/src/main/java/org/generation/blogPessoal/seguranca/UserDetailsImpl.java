package org.generation.blogPessoal.seguranca;

import java.util.Collection;

import org.generation.blogPessoal.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails
{
	private static final long serialVersionUID = 1L;//para controle interno - perguntar
	
	//atributos
	private String userName;
	private String password;
	
	//construtor
	public UserDetailsImpl(Usuario user) { //populando
		this.userName = user.getUsuario();
		this.password = user.getSenha();		
	}
	//construtor vazio
	public UserDetailsImpl() {}
	
	//implantar métodos
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword()
	{
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername()
	{
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean isAccountNonExpired()
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked()
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled()
	{
		// TODO Auto-generated method stub
		return true;
	}

}
