package com.mecanica.domain.entities.servico.command;

import java.util.Objects;
import java.util.UUID;

import com.mecanica.domain.entities.ordemServico.AbstractOrdemServico;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ServiceCommand<T extends AbstractOrdemServico> implements IServicoCommand<T> {

    private final T ordemServico;

    public ServiceCommand(T ordemServico) {
        this.ordemServico = ordemServico;
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