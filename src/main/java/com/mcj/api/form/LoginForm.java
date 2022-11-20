package com.mcj.api.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm
{
	private String login;
	private String senha;
	private String token;

	public String getLogin()
	{
		return this.login;
	}

	public String getSenha()
	{
		return this.senha;
	}

	public String getToken()
	{
		return this.token;
	}

	public void setLogin(String login)
	{
		this.login = login;
	}
	
	public void setSenha(String senha)
	{
		this.senha = senha;
	}

	public void setToken(String token)
	{
		this.token = token;
	}

	public UsernamePasswordAuthenticationToken converter(LoginForm form)
	{
		return new UsernamePasswordAuthenticationToken(form.getLogin(), form.getSenha());
	}
}