package br.edu.ifnmg.tcc.bean;

import br.edu.ifnmg.tcc.dao.RacaDAO;
import br.edu.ifnmg.tcc.entidade.Raca;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class RacaBean extends CrudBean<Raca, RacaDAO> {

    private RacaDAO racaDao;

    @Override
    public RacaDAO getDAO() {
        if (racaDao == null) {
            racaDao = new RacaDAO();
        }
        return racaDao;
    }

    @Override
    public Raca criarNovaEntidade() {
        return new Raca();

    }
    
   

}
