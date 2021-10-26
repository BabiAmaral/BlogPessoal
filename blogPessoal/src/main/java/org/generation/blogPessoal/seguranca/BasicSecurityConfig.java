package org.generation.blogPessoal.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity// habitar a configuração de web security(segurança web)
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter 
{
	@Autowired
	private UserDetailsService service; //regra de negocio, para fazer o serviço/ contem o metodo loadUserByUsername​
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception { //configure- metodo dentro de web security, trows Exceptions- tratativa de erro
		auth.inMemoryAuthentication()
		.withUser("admin").password(passwordEncoder().encode("admin")).authorities("ROLE_ADMIN");
		auth.userDetailsService(service);
	}
	
	@Bean //passando configurações 
	public PasswordEncoder passwordEncoder () { 
		return new BCryptPasswordEncoder(); // metodo que codifica e faz a criptografia
	}
	
	@Override
	protected void configure (HttpSecurity http) throws Exception {
		http.authorizeRequests()
		//.antMatchers("/**").permitAll()
		.antMatchers("/usuarios/logar").permitAll() //serve para liberar end-points, caminhos do controler, para o cliente ter acesso sem precisar passar pelo token
		.antMatchers("/usuarios/cadastrar").permitAll()
		.antMatchers(HttpMethod.OPTIONS).permitAll()
		.anyRequest().authenticated()//todos os demais deveram ser autenticados
		.and().httpBasic()
		.and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)//um API rest não guarda sessão. o que é o stateless
		.and().cors()//habilitar o cors, mais de uma rota fazendo requisições no servidor
		.and().csrf().disable();//desabilitar o csrf, utilizará as configurações padrões.
		
	}
	
}
