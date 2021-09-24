package com.bolsadeideas.springboot.form.app.services.implementations;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.form.app.models.domain.Pais;
import com.bolsadeideas.springboot.form.app.services.PaisService;

@Service
public class PaisServiceImpl implements PaisService {

	private List<Pais> lista;

	public PaisServiceImpl() {
		this.lista = Arrays.asList(	new Pais(1, "ES", "España"),
									new Pais(2, "MX", "México"),
									new Pais(3, "CL", "Chile"),
									new Pais(4, "AR", "Argentina"),
									new Pais(5, "PE", "Perú"),
									new Pais(6, "CO", "Colombia"),
									new Pais(7, "VE", "Venezuela"));

	}

	@Override
	public List<Pais> listar() {
		return this.lista;
	}

	@Override
	public Pais obtenerPorId(Integer id) {
		return this	.lista
					.stream()
					.filter(pais -> id.equals(pais.getId()))
					.findFirst()
					.orElse(null);
	}

}
