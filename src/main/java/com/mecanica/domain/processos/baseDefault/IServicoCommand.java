package com.mecanica.domain.processos.baseDefault;

import java.util.UUID;

import com.mecanica.domain.entities.ordemServico.AbstractOrdemServico;




public interface IServicoCommand<T extends AbstractOrdemServico> {
    
    boolean exists(UUID id);

    T getordemServico();
}