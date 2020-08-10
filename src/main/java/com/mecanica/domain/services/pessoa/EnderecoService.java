package com.mecanica.domain.services.pessoa;

import com.mecanica.domain.services.BaseService;
import com.mecanica.data.repositorys.pessoa.IEnderecoRepository;
import com.mecanica.domain.entities.pessoa.Endereco;

import org.springframework.stereotype.Service;

@Service
public class EnderecoService extends BaseService<Endereco, IEnderecoRepository> {

    public EnderecoService(IEnderecoRepository repository) {
        super(repository);
    }
}
