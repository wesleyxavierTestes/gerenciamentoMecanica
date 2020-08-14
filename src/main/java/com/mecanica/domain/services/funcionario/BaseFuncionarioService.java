
package com.mecanica.domain.services.funcionario;

import com.mecanica.domain.entities.funcionario.Funcionario;
import com.mecanica.domain.services.BaseService;
import com.mecanica.infra.repositorys.funcionario.IBaseFuncionarioRepository;

public class BaseFuncionarioService<T  extends Funcionario, Y extends IBaseFuncionarioRepository<T>> extends BaseService<T, Y> {

    public BaseFuncionarioService(Y repository) {
        super(repository);
    }

    public T findByCpf(String cpf) {
		return this.repository.findByCpfEquals(cpf);
	}
}