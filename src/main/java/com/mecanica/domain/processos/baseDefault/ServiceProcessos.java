package com.mecanica.domain.processos.baseDefault;

import java.util.Objects;
import java.util.UUID;

import com.mecanica.application.exceptions.RegraBaseException;
import com.mecanica.domain.entities.ordemServico.AbstractOrdemServico;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ServiceProcessos<T extends AbstractOrdemServico> implements IServicoCommand<T> {

    protected final T ordemServico;

    public ServiceProcessos(T ordemServico) {
        this.ordemServico = ordemServico;

        if (!Objects.nonNull(this.ordemServico)) {
            throw new RegraBaseException("Pedido inexistênte");
        }
    }

    @Override
    public T getordemServico() {
        return this.ordemServico;
    }

    @Override
    public boolean exists(UUID id) {

        return Objects.nonNull(ordemServico) && ordemServico.getId().equals(id);
    }
}