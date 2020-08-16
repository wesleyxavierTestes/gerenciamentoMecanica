package com.mecanica.application.applicationServices.produto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.mecanica.application.applicationServices.BaseValidations;
import com.mecanica.application.dto.avaliacao.ItemServicoDto;
import com.mecanica.application.exceptions.ValidacaoControllerBaseException;
import com.mecanica.domain.entities.produto.Produto;
import com.mecanica.domain.entities.servico.ItemOrcamento;
import com.mecanica.domain.entities.servico.ItemOrdemServico;
import com.mecanica.domain.entities.servico.Servico;
import com.mecanica.domain.enuns.produto.EnumTipoProduto;
import com.mecanica.domain.services.produto.ProdutoService;

import org.springframework.stereotype.Service;

@Service
public class ProdutoValidations extends BaseValidations<Produto, ProdutoService> {

    public ProdutoValidations(ProdutoService serviceProduto) {
        super(serviceProduto);
    }

    public List<ItemOrdemServico> findAllValidExistsByFilterOrdemServico(List<ItemServicoDto> itensServico) {
        List<ItemOrdemServico> lista = new ArrayList<>();
        findAllValidExistsByFilter(itensServico, lista, ItemOrdemServico.class);
        return lista;
    }

    public List<ItemOrcamento> findAllValidExistsByFilterOrcamento(List<ItemServicoDto> itensServico) {
        List<ItemOrcamento> lista = new ArrayList<>();
        findAllValidExistsByFilter(itensServico, lista, ItemOrcamento.class);
        return lista;
    }

    private <T extends Servico> void findAllValidExistsByFilter(List<ItemServicoDto> itensServico, List<T> lista,
            Class<T> type) {
        for (ItemServicoDto item : itensServico) {
            Produto entity = Objects.nonNull(item.getId()) ? this._service.find(UUID.fromString(item.getId())) : null;
            if (!Objects.nonNull(entity))
                throw new ValidacaoControllerBaseException(this.getNome() + " inexistênte");

            T itemServico = entity.getClone(type);

            itemServico.setId(UUID.randomUUID());
            itemServico.setDataCadastro(LocalDateTime.now());
            itemServico.setTipoProduto(EnumTipoProduto.ItemServico);

            lista.add(itemServico);
        }
    }
}