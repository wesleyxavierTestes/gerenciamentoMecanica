package com.mecanica.domain.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IBaseEntity {
    
    public UUID getId();

    public void setId(UUID id);

    public LocalDateTime getDataCadastro();

    public void setDataCadastro(LocalDateTime dataCadastro);
}