package com.bolsadeideas.springboot.form.app.models.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.bolsadeideas.springboot.form.app.validations.IdentificadorRegex;
import com.bolsadeideas.springboot.form.app.validations.Requerido;

public class Usuario {

	@IdentificadorRegex
	private String identificador;

	@Requerido
	private String nombre;

	@Requerido
	private String apellido;

	@Requerido
	@Size(min = 3, max = 8)
	private String username;

	@Requerido
	private String password;

	@Email
	@Requerido
	private String email;

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
