package com.mcj.api.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.mcj.api.dto.GrupoDto;
import com.mcj.api.dto.TotalDto;
import com.mcj.api.form.GrupoForm;
import com.mcj.api.model.Grupo;
import com.mcj.api.service.GrupoService;

@RestController
@RequestMapping("/grupo")
public class GrupoController
{
	@Autowired
	private GrupoHistoricoController grupoHistoricoController;
	@Autowired
	private GrupoService grupoService;

	@GetMapping(value = {"", "/"})
	@CrossOrigin
	public String index()
	{
		return "Bem vindo à Entidade Grupo";
	}

	@GetMapping("/listar")
	@CrossOrigin
	public ResponseEntity<List<GrupoDto>> listar()
	{
		try
		{
			List<Grupo> grupos = grupoService.listar();
			return ResponseEntity.ok(GrupoDto.converter(grupos));
		}
		catch(Exception e)
		{
			return ResponseEntity.badRequest().build();
		}
		
	}

	@GetMapping("/listar/{id}")
	@CrossOrigin
	public ResponseEntity<List<GrupoDto>> listar(@PathVariable Long id, @RequestHeader("Authorization") String token)
	{
		try
		{
			Grupo grupo = grupoService.buscarPorId(id);

			if (grupo != null)
			{
				// dados da tabela GrupoHistorico
				// Obs.: o código 3 é referência para "acessado"
				grupoHistoricoController.cadastrarHistorico(token, grupo, Long.valueOf(3));
			}

			List<Grupo> grupos = new ArrayList<>();
			grupos.add(grupo);
			return ResponseEntity.ok(GrupoDto.converter(grupos));
		}
		catch(Exception e)
		{
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/listarTotais")
	@CrossOrigin
	public ResponseEntity<TotalDto> listarTotais()
	{
		List<Grupo> grupos = grupoService.listar();

		int totalAtivos = 0;
		int totalInativos = 0;
		for (Grupo g : grupos)
		{
			if (Boolean.TRUE.equals(g.getAtivo()))
			{
				totalAtivos++;
			}
			else
			{
				totalInativos++;
			}
		}

		TotalDto totais = new TotalDto(grupos.size(), totalAtivos, totalInativos);
		return ResponseEntity.ok(totais);
	}

	@PostMapping("/cadastrar")
	@Transactional
	@CrossOrigin
	public ResponseEntity<GrupoDto> cadastrar(@RequestBody GrupoForm form, UriComponentsBuilder uriComponentsBuilder, @RequestHeader("Authorization") String token)
	{
		try
		{
			Grupo grupo = form.converter();
			grupoService.cadastrar(grupo);

			// dados da tabela GrupoHistorico
			// Obs.: o código 1 é referência para "cadastrado"
			grupoHistoricoController.cadastrarHistorico(token, grupo, Long.valueOf(1));
			
			URI uri = uriComponentsBuilder.path("/grupo/listar/{id}").buildAndExpand(grupo.getId()).toUri();
			return ResponseEntity.created(uri).body(new GrupoDto(grupo));
		}
		catch(Exception e)
		{
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/alterar/{id}")
	@Transactional
	@CrossOrigin
	public ResponseEntity<GrupoDto> alterar(@RequestBody GrupoForm form, @PathVariable Long id, @RequestHeader("Authorization") String token)
	{
		try
		{
			Grupo grupo = grupoService.alterar(id, form);

			// dados da tabela GrupoHistorico
			// Obs.: o código 2 é referência para "alterado"
			grupoHistoricoController.cadastrarHistorico(token, grupo, Long.valueOf(2));

			return ResponseEntity.ok(new GrupoDto(grupo));
		}
		catch(Exception e)
		{
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/desativar/{id}")
	@Transactional
	@CrossOrigin
	public ResponseEntity<GrupoDto> desativar(@PathVariable Long id, @RequestHeader("Authorization") String token)
	{
		try
		{
			Grupo grupo = grupoService.desativar(id);

			// dados da tabela GrupoHistorico
			// Obs.: o código 4 é referência para "alterado"
			grupoHistoricoController.cadastrarHistorico(token, grupo, Long.valueOf(4));

			return ResponseEntity.ok(new GrupoDto(grupo));
		}
		catch(Exception e)
		{
			return ResponseEntity.badRequest().build();
		}
	}
}