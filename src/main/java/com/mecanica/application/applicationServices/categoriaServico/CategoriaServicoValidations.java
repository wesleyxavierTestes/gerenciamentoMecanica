package com.mecanica.application.applicationServices.categoriaServico;

import com.mecanica.application.applicationServices.BaseValidations;
import com.mecanica.domain.entities.categoria.CategoriaServico;
import com.mecanica.domain.services.categoria.CategoriaServicoService;

import org.springframework.stereotype.Service;

@Service
public class CategoriaServicoValidations extends BaseValidations<CategoriaServico, CategoriaServicoService> {

    public CategoriaServicoValidations(CategoriaServicoService serviceCategoriaServico) {
        super(serviceCategoriaServico);
    }

    @Override
    public String getNome() {
        return "CategoriaServico";
    }
}