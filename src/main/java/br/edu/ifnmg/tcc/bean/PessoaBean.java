package br.edu.ifnmg.tcc.bean;

import br.edu.ifnmg.tcc.dao.PessoaDAO;
import br.edu.ifnmg.tcc.entidade.Pessoa;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class PessoaBean extends CrudBean<Pessoa, PessoaDAO> {

    private PessoaDAO pessoaDao;

    @Override
    public PessoaDAO getDAO() {
        if (pessoaDao == null) {
            pessoaDao = new PessoaDAO();
        }
        return pessoaDao;
    }
    
    @Override
    public Pessoa criarNovaEntidade() {
        return new Pessoa();

    }
    
}
