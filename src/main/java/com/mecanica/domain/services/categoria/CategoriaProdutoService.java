
package com.mecanica.domain.services.categoria;

import com.mecanica.domain.services.BaseService;
import com.mecanica.infra.repositorys.categoria.ICategoriaProdutoRepository;

import com.mecanica.domain.entities.categoria.CategoriaProduto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CategoriaProdutoService extends BaseService<CategoriaProduto, ICategoriaProdutoRepository> {

    public CategoriaProdutoService(ICategoriaProdutoRepository repository) {
        super(repository);
    }

    public Page<CategoriaProduto> findAllByNomeContains(String nome, int page) {
        PageRequest paginacao = PageRequest.of((page - 1), 10);

        Page<CategoriaProduto> list = this.repository.findAllByNomeContains(nome.toLowerCase(), paginacao);

        return list;
    }
}