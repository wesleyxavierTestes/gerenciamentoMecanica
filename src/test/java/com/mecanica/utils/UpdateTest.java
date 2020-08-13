package com.mecanica.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import com.mecanica.application.config.UpdateConfig;
import com.mecanica.domain.entities.categoria.CategoriaProduto;
import com.mecanica.domain.entities.cliente.Cliente;
import com.mecanica.domain.entities.pessoa.Endereco;
import com.mecanica.domain.entities.pessoa.Pessoa;

import org.junit.jupiter.api.Test;

public class UpdateTest {
    
    @Test
    public void testConversaoCategoriaUpdate() {
        CategoriaProduto entityUpdate = new CategoriaProduto();
        CategoriaProduto entity = new CategoriaProduto();
        entity.setId(UUID.randomUUID());
        entity.setNome("wesley");

        assertNotEquals(entity.getNome(), entityUpdate.getNome());

        UpdateConfig.by(entity, entityUpdate);
        Class<?>[] classes = entityUpdate.getClass().getDeclaredClasses();

        assertEquals(entity.getNome(), entityUpdate.getNome());
    }


    @Test
    public void testConversaoClienteUpdate() {
        Cliente entityUpdate = new Cliente();
        Cliente entity = new Cliente();
        entity.setId(UUID.randomUUID());
        entity.setNome("wesley");
        Endereco endereco = new Endereco();
        endereco.setCep("87103292");
        entity.setEndereco(endereco);;

        assertNotEquals(entity.getNome(), entityUpdate.getNome());

        UpdateConfig.by(entity, entityUpdate);

        assertEquals(entity.getNome(), entityUpdate.getNome());
    }

    @Test
    public void testClienteInstance() {
        UpdateConfig update = new UpdateConfig();
        Cliente[] entityUpdate = {new Cliente()};

        boolean condition = entityUpdate instanceof Pessoa[];
        assertTrue(condition);
    }
}