package com.bolsadeideas.springboot.form.app.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.thymeleaf.util.DateUtils;

import com.bolsadeideas.springboot.form.app.editors.NombreMayusculasEditor;
import com.bolsadeideas.springboot.form.app.editors.PaisPropertyEditor;
import com.bolsadeideas.springboot.form.app.editors.RolePropertyEditor;
import com.bolsadeideas.springboot.form.app.models.domain.Pais;
import com.bolsadeideas.springboot.form.app.models.domain.Role;
import com.bolsadeideas.springboot.form.app.models.domain.Usuario;
import com.bolsadeideas.springboot.form.app.services.PaisService;
import com.bolsadeideas.springboot.form.app.services.RoleService;
import com.bolsadeideas.springboot.form.app.validations.UsuarioValidador;

@Controller
@SessionAttributes("usuario")
public class FormController {

	@Autowired
	private UsuarioValidador validador;

	@Autowired
	private PaisService paisService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PaisPropertyEditor paisEditor;

	@Autowired
	private RolePropertyEditor propertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);

		binder.registerCustomEditor(Date.class, "fechaNacimiento", new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(String.class, "nombre", new NombreMayusculasEditor());
		binder.registerCustomEditor(String.class, "apellido", new NombreMayusculasEditor());
		binder.registerCustomEditor(Pais.class, "pais", paisEditor);
		binder.registerCustomEditor(Role.class, "roles", propertyEditor);

		binder.addValidators(validador);
	}

	@ModelAttribute("generos")
	public List<String> generos() {
		return Arrays.asList("Hombre", "Mujer");
	}

	@ModelAttribute("listaPaises")
	public List<Pais> listaPaises() {
		return this.paisService.listar();
	}

	@ModelAttribute("listaRoles")
	public List<Role> listaRoles() {
		return this.roleService.listar();
	}

	@ModelAttribute("listaRolesString")
	public List<String> listaRolesString() {
		List<String> roles = new ArrayList<>();

		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USER");
		roles.add("ROLE_MODERATOR");

		return roles;
	}

	@ModelAttribute("listaRolesMap")
	public Map<String, String> listaRolesMap() {
		Map<String, String> roles = new HashMap<>();

		roles.put("ROLE_ADMIN", "Administrador");
		roles.put("ROLE_USER", "Usuario");
		roles.put("ROLE_MODERATOR", "Moderador");

		return roles;
	}

	@ModelAttribute("paises")
	public List<String> paises() {
		return Arrays.asList("España", "México", "Chile", "Argentina", "Perú", "Colombia", "Venezuela");
	}

	@ModelAttribute("paisesMap")
	public Map<String, String> paisesMap() {
		Map<String, String> paises = new HashMap<>();

		paises.put("ES", "España");
		paises.put("MX", "México");
		paises.put("CL", "Chile");
		paises.put("AR", "Argentina");
		paises.put("PE", "Perú");
		paises.put("CO", "Colombia");
		paises.put("VE", "Venezuela");

		return paises;
	}

	@GetMapping("/form")
	public String form(Model model) {
		Usuario usuario = new Usuario();

		usuario.setIdentificador("12.345.678-K");
		usuario.setNombre("John");
		usuario.setApellido("Doe");
		usuario.setHabilitar(true);
		usuario.setGenero("Hombre");
		usuario.setEmail("pruebas@email.com");
		usuario.setUsername("pruebas");
		usuario.setFechaNacimiento(DateUtils.createNow().getTime());
		usuario.setCuenta(6);
		usuario.setValorSecreto("Algún valor secreto ****");
		usuario.setPais(new Pais(3, "CL", "Chile"));
		usuario.setRoles(Arrays.asList(new Role(2, "Usuario", "ROLE_USER")));

		model.addAttribute("title", "Formulario usuarios");
		model.addAttribute("usuario", usuario);

		return "form";
	}

	@PostMapping("/form")
	public String procesar(@Valid Usuario usuario, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("title", "Resultado form");

			return "form";
		}

		return "redirect:/ver";
	}

	@GetMapping("/ver")
	public String ver(@SessionAttribute(name = "usuario", required = false) Usuario usuario, Model model,
			SessionStatus status) {

		if (usuario == null)
			return "redirect:/form";

		model.addAttribute("title", "Resultado form");

		status.setComplete();

		return "resultado";
	}

}
