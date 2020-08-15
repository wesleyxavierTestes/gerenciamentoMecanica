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
import com.mecanica.domain.entities.servico.ItemServico;
import com.mecanica.domain.services.produto.ProdutoService;

import org.springframework.stereotype.Service;

@Service
public class ProdutoValidations extends BaseValidations<Produto, ProdutoService> {

    public ProdutoValidations(ProdutoService serviceProduto) {
        super(serviceProduto);
    }

    @Override
    public String getNome() {
        return "Produto";
    }

    public List<ItemServico> findAllValidExistsByFilter(List<ItemServicoDto> itensServico) {
        List<ItemServico> lista = new ArrayList<>();
        for (ItemServicoDto item : itensServico) {
            Produto entity = this._service.find(UUID.fromString(item.getId()));
            if (!Objects.nonNull(entity))
                throw new ValidacaoControllerBaseException(this.getNome() + " inexistênte");

            ItemServico itemServico = entity.getClone(ItemServico.class);

            itemServico.setId(UUID.randomUUID());
            itemServico.setDataCadastro(LocalDateTime.now());

            lista.add(itemServico);
        }
        return lista;
    }
}