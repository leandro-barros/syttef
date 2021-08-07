package br.edu.ifnmg.tcc.dao;

import br.edu.ifnmg.tcc.entidade.Raca;
import static br.edu.ifnmg.tcc.util.HibernateUtil.getSession;
import br.edu.ifnmg.tcc.util.StringHelper;
import br.edu.ifnmg.tcc.util.StringUtil;
import br.edu.ifnmg.tcc.util.exception.ErroSistema;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Transaction;

public class RacaDAO implements CrudDAO<Raca> {

    private static final long serialVersionUID = -9107383058849922754L;

    @Override
    public Raca salvar(Raca entidade) throws ErroSistema {
        entidade.setRaca(StringHelper.spaceRemover(entidade.getRaca()));
        if (StringUtil.isEmpty(entidade.getRaca())) {
            throw new ErroSistema("Por Favor Informe o Campo Raça!");
        }
        try {
            Transaction t = getSession().beginTransaction();
            entidade = (Raca) getSession().merge(entidade);
            t.commit();
            return entidade;
        } finally {
            getSession().flush();
            getSession().clear();
            getSession().close();
        }
    }

    @Override
    public void deletar(Raca entidade) throws ErroSistema {
        try {
            Transaction t = getSession().beginTransaction();
            getSession().delete(entidade);
            t.commit();
        } finally {
            getSession().flush();
            getSession().clear();
            getSession().close();
        }
    }

    @Override
    public List<Raca> buscar(Raca entidade) throws ErroSistema {
        try {
            Criteria criteria = getSession().createCriteria(Raca.class);
            
              List<Raca> racas = criteria.list();
            for (Raca raca : racas) {
                if (raca.getAnimalList()!= null) {
                    raca.getAnimalList().size();
                }
               
            }
            return racas;
        } finally {
            getSession().flush();
            getSession().clear();
            getSession().close();
        }
    }

    public List<Raca> pesquisar() throws ErroSistema {
        try {
            Criteria criteria = getSession().createCriteria(Raca.class);
            return criteria.list();
        } finally {
            getSession().flush();
            getSession().clear();
            getSession().close();
        }
    }

}
