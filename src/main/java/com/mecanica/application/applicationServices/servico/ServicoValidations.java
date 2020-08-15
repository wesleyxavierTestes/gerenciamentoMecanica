package com.mecanica.application.applicationServices.servico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.mecanica.application.applicationServices.BaseValidations;
import com.mecanica.application.dto.avaliacao.ItemServicoDto;
import com.mecanica.application.exceptions.ValidacaoControllerBaseException;
import com.mecanica.domain.entities.servico.Servico;
import com.mecanica.domain.entities.servico.ServicoOrcamento;
import com.mecanica.domain.services.servico.ServicoService;

import org.springframework.stereotype.Service;

@Service
public class ServicoValidations extends BaseValidations<Servico, ServicoService> {

    public ServicoValidations(ServicoService serviceServico) {
        super(serviceServico);
    }

    public List<ServicoOrcamento> findAllValidExistsByFilter(List<ItemServicoDto> servicos) {
        List<ServicoOrcamento> lista = new ArrayList<>();
        for (ItemServicoDto item : servicos) {
            Servico entity = this._service.find(UUID.fromString(item.getId()));
            if (!Objects.nonNull(entity))
                throw new ValidacaoControllerBaseException(this.getNome() + " inexistênte");

            ServicoOrcamento servicoOrcamento = entity.getClone(ServicoOrcamento.class);

            servicoOrcamento.setId(UUID.randomUUID());
            servicoOrcamento.setDataCadastro(LocalDateTime.now());

            lista.add(servicoOrcamento);
        }
        return lista;
    }
}