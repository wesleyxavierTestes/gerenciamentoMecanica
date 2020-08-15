package com.mecanica.application.applicationServices.categoriaProduto;

import com.mecanica.application.applicationServices.BaseValidations;
import com.mecanica.domain.entities.categoria.CategoriaProduto;
import com.mecanica.domain.services.categoria.CategoriaProdutoService;

import org.springframework.stereotype.Service;

@Service
public class CategoriaProdutoValidations extends BaseValidations<CategoriaProduto, CategoriaProdutoService> {

    public CategoriaProdutoValidations(CategoriaProdutoService serviceCategoriaProduto) {
        super(serviceCategoriaProduto);
    }
}