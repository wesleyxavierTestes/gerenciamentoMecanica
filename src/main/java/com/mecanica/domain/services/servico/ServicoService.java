package com.mecanica.domain.services.servico;

import java.util.Objects;

import com.mecanica.application.exceptions.RegraBaseException;
import com.mecanica.domain.entities.categoria.CategoriaServico;
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

    public Servico save(Servico entity, CategoriaServico categoria) {
        validarNomeDuplicado(entity);

        super.save(entity, categoria);

        return entity;
    }

    public Servico update(Servico entity, CategoriaServico categoria) {
        validarNomeDuplicado(entity);

        super.update(entity, categoria);

        return entity;
    }

    private void validarNomeDuplicado(Servico entity) {
        Servico entityExists = this.repository.findByNomeIgnoreCase(entity.getNome());

        if (Objects.nonNull(entityExists)) {
            throw new RegraBaseException("Nome existênte");
        }
    }

}