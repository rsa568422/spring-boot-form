package com.bolsadeideas.springboot.form.app.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.form.app.models.domain.Role;
import com.bolsadeideas.springboot.form.app.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	private List<Role> roles;

	public RoleServiceImpl() {
		this.roles = new ArrayList<Role>();
		this.roles.add(new Role(1, "Administrador", "ROLE_ADMIN"));
		this.roles.add(new Role(2, "Usuario", "ROLE_USER"));
		this.roles.add(new Role(3, "Moderador", "ROLE_MODERATOR"));
	}

	@Override
	public List<Role> listar() {
		return this.roles;
	}

	@Override
	public Role obtenerPorId(Integer id) {
		return roles.stream()
					.filter(role -> id.equals(role.getId()))
					.findFirst()
					.orElse(null);
	}

}
