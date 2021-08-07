package br.edu.ifnmg.tcc.bean;

import br.edu.ifnmg.tcc.dao.EspecificacaoDAO;
import br.edu.ifnmg.tcc.entidade.Especificacao;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class EspecificacaoBean extends CrudBean<Especificacao, EspecificacaoDAO> {

    private EspecificacaoDAO especificacaoDao;

    @Override
    public EspecificacaoDAO getDAO() {
        if (especificacaoDao == null) {
            especificacaoDao = new EspecificacaoDAO();
        }
        return especificacaoDao;
    }

    @Override
    public Especificacao criarNovaEntidade() {
        return new Especificacao();

    }
    
   

}
