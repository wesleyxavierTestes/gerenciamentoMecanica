package com.mecanica.domain.services.itemServico.ordemServico;

import com.mecanica.domain.services.BaseService;
import com.mecanica.data.repositorys.itemServico.ordemServico.ItemOrdemServicoRepository;
import com.mecanica.domain.entities.itemServico.ordemServico.ItemOrdemServico;

import org.springframework.stereotype.Service;

@Service
public class ItemOrdemServicoService extends BaseService<ItemOrdemServico, ItemOrdemServicoRepository> {

    public ItemOrdemServicoService(ItemOrdemServicoRepository repository) {
        super(repository);
    }
}