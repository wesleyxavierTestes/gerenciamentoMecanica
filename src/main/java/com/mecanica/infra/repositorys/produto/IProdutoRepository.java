package com.mecanica.infra.repositorys.produto;

import com.mecanica.domain.entities.produto.Produto;
import com.mecanica.infra.repositorys.IBaseRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IProdutoRepository extends IBaseRepository<Produto> {

    @Query(
        nativeQuery = true, 
            value = "SELECT (p.*), SUM(e.quantidade) as estoque_atual "
                    +"FROM public.produto as p "
                    +"join public.estoque as e "
                    +"on e.produto_id = p.id "                    
                    +"GROUP BY p.id "
                    +"HAVING SUM(e.quantidade) <= 0"
                    +"Union "
                    +"(SELECT (j.*), 0 as estoque_atual  "
                    +"FROM public.produto as j "
                    +"where j.id not in (SELECT e.produto_id from  public.estoque as e )) ",
                    
        countQuery = "SELECT (p.*), SUM(e.quantidade) as estoque_atual "
                    +"FROM public.produto as p "
                    +"join public.estoque as e "
                    +"on e.produto_id = p.id "                    
                    +"GROUP BY p.id "
                    +"HAVING SUM(e.quantidade) <= 0"
                    +"Union "
                    +"(SELECT (j.*), 0 as estoque_atual  "
                    +"FROM public.produto as j "
                    +"where j.id not in (SELECT e.produto_id from  public.estoque as e )) ")
    Page<Produto> findAllByCountEstoqueZero(Pageable page);
    
    @Query(
        nativeQuery = true, 
            value = "SELECT (p.*), SUM(e.quantidade) as estoque_atual "
                    +"FROM public.produto as p "
                    +"join public.estoque as e "
                    +"on e.produto_id = p.id "                    
                    +"GROUP BY p.id "
                    +"Union "
                    +"(SELECT (j.*), 0 as estoque_atual  "
                    +"FROM public.produto as j "
                    +"where j.id not in (SELECT e.produto_id from  public.estoque as e )) ",
                    
        countQuery = "SELECT (p.*), SUM(e.quantidade) as estoque_atual "
                    +"FROM public.produto as p "
                    +"join public.estoque as e "
                    +"on e.produto_id = p.id "                    
                    +"GROUP BY p.id "
                    +"Union "
                    +"(SELECT (j.*), 0 as estoque_atual  "
                    +"FROM public.produto as j "
                    +"where j.id not in (SELECT e.produto_id from  public.estoque as e )) ")
	Page<Produto> findAllByCountEstoque(Pageable page);

}