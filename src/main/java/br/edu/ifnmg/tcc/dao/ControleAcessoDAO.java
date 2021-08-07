package br.edu.ifnmg.tcc.dao;

import br.edu.ifnmg.tcc.entidade.ControleAcesso;
import static br.edu.ifnmg.tcc.util.HibernateUtil.getSession;
import br.edu.ifnmg.tcc.util.exception.ErroSistema;
import java.util.List;
import org.hibernate.Criteria;


public class ControleAcessoDAO implements CrudDAO<ControleAcessoDAO> {

    private static final long serialVersionUID = -2987847277690262610L;

    @Override
    public ControleAcessoDAO salvar(ControleAcessoDAO entidade) throws ErroSistema {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletar(ControleAcessoDAO entidade) throws ErroSistema {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ControleAcessoDAO> buscar(ControleAcessoDAO entidade) throws ErroSistema {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<ControleAcesso> pesquisar() throws ErroSistema {
        try {
            Criteria criteria = getSession().createCriteria(ControleAcesso.class);
            return criteria.list();
        } finally {
            getSession().flush();
            getSession().clear();
            getSession().close();
        }
    }

   

}
