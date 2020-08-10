package com.mecanica.domain.services.itemServico.orcamento;

import com.mecanica.domain.services.BaseService;
import com.mecanica.data.repositorys.itemServico.orcamento.ItemOrcamentoRepository;
import com.mecanica.domain.entities.itemServico.orcamento.ItemOrcamento;

import org.springframework.stereotype.Service;

@Service
public class ItemOrcamentoService extends BaseService<ItemOrcamento, ItemOrcamentoRepository> {

    public ItemOrcamentoService(ItemOrcamentoRepository repository) {
        super(repository);
    }
}
