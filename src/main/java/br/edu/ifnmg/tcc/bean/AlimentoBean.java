package br.edu.ifnmg.tcc.bean;

import br.edu.ifnmg.tcc.dao.AlimentoDAO;
import br.edu.ifnmg.tcc.entidade.Alimento;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class AlimentoBean extends CrudBean<Alimento, AlimentoDAO> {

    private AlimentoDAO alimentoDao;

    @Override
    public AlimentoDAO getDAO() {
        if (alimentoDao == null) {
            alimentoDao = new AlimentoDAO();
        }
        return alimentoDao;
    }

    @Override
    public Alimento criarNovaEntidade() {
        return new Alimento();

    }
    
}
