package br.edu.ifnmg.tcc.dao;

import br.edu.ifnmg.tcc.util.exception.ErroSistema;
import java.io.Serializable;
import java.util.List;

public interface CrudDAO<E> extends Serializable {

    public E salvar(E entidade) throws ErroSistema;

    public void deletar(E entidade) throws ErroSistema;

    public List<E> buscar(E entidade) throws ErroSistema;


}
