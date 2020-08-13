package com.mecanica.domain.services.categoria;

import com.mecanica.domain.services.BaseService;
import com.mecanica.infra.repositorys.categoria.ICategoriaServicoRepository;
import com.mecanica.domain.entities.categoria.CategoriaServico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServicoService extends BaseService<CategoriaServico, ICategoriaServicoRepository> {

    public CategoriaServicoService(ICategoriaServicoRepository repository) {
        super(repository);
    }

	public Page<CategoriaServico> findAllByNomeContains(String nome, int page) {
		PageRequest paginacao = PageRequest.of((page - 1), 10);
        
        Page<CategoriaServico> list = this.repository.findAllByNomeContains(nome.toLowerCase(), paginacao);

        return list;
	}
}