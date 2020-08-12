package com.mecanica.domain.services.produto;

import java.util.Optional;
import java.util.UUID;

import com.mecanica.domain.entities.categoria.Categoria;
import com.mecanica.domain.entities.categoria.ICategoria;
import com.mecanica.domain.entities.produto.baseentity.IProduto;
import com.mecanica.domain.services.BaseService;
import com.mecanica.infra.repositorys.IBaseRepository;

public class AbstractProdutoService<T extends IProduto, Y extends IBaseRepository<T>>  extends BaseService<T, Y> {

    public AbstractProdutoService(Y repository) {
        super(repository);
    }

    /**
     * setICategoria para não dar loop na adição de categoria
     * @param <Z>
     * @param entity
     * @param categoria
     * @return
     */
    public <Z extends Categoria> T save(T entity, Z categoria) {
        entity.setCodigo(UUID.randomUUID());

        entity.setICategoria((ICategoria)categoria);
        
        entity = super.save(entity);

        return entity;
    }

    /**
     * Configura os dados imutáveis do Produto
     * @param entity
     * @param categoria
     * @return
     */
    public <Z extends Categoria> T update(T entity, Z categoria) {
        Optional<T> optionalEntityUpdate = this.repository.findById(entity.getId());
        if (!optionalEntityUpdate.isPresent()) {
            return null;
        }
        T entityUpdate = optionalEntityUpdate.get();
        entity.setCodigo(entityUpdate.getCodigo());
        entity.setDataCadastro(entityUpdate.getDataCadastro());
        entity.setCodigo(entityUpdate.getCodigo());
        entity.setICategoria(categoria);

        entity = super.update(entity, entityUpdate);

        return entity;
    }

}