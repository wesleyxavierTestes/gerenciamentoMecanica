package com.mecanica.domain.services.servico;

import com.mecanica.domain.entities.servico.Servico;
import com.mecanica.domain.services.produto.AbstractProdutoService;
import com.mecanica.infra.repositorys.servico.IServicoRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ServicoService extends AbstractProdutoService<Servico, IServicoRepository> {

    public ServicoService(final IServicoRepository repository) {
        super(repository);
    }

	public Page<Servico> findAllByNomeContains(String nome, int page) {
		return this.repository.findAllByNomeContains(nome, PageRequest.of((page - 1), 10));
	}
}