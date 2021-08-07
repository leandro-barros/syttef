package br.edu.ifnmg.tcc.dao;

import br.edu.ifnmg.tcc.entidade.Alimento;
import static br.edu.ifnmg.tcc.util.HibernateUtil.getSession;
import br.edu.ifnmg.tcc.util.StringUtil;
import br.edu.ifnmg.tcc.util.exception.ErroSistema;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class AlimentoDAO implements CrudDAO<Alimento> {

    private static final long serialVersionUID = 1106554009084695882L;

    @Override
    public Alimento salvar(Alimento entidade) throws ErroSistema {
        if (StringUtil.isEmpty(entidade.getAlimento())) {
            throw new ErroSistema("Por Favor Informe o Campo Alimento!");
        }
        try {
            Transaction t = getSession().beginTransaction();
            entidade = (Alimento) getSession().merge(entidade);
            t.commit();
            return entidade;
        } finally {
            getSession().flush();
            getSession().clear();
            getSession().close();
        }
    }

    @Override
    public void deletar(Alimento entidade) throws ErroSistema {
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
    public List<Alimento> buscar(Alimento entidade) throws ErroSistema {
        try {
            Criteria criteria = getSession().createCriteria(Alimento.class);
            criteria.add(Restrictions.or(Restrictions.ilike("alimento", entidade.getAlimento(), MatchMode.ANYWHERE)));
            
             List<Alimento> alimentos = criteria.list();
            for (Alimento alimento : alimentos) {// Método para verificar se contém ligações
                if (alimento.getAlimentacaoList()!= null) {
                    alimento.getAlimentacaoList().size();
                }
            }
            return alimentos;
        } finally {
            getSession().flush();
            getSession().clear();
            getSession().close();
        }
    }

    public List<Alimento> pesquisar() throws ErroSistema {
        try {
            Criteria criteria = getSession().createCriteria(Alimento.class);
            return criteria.list();
        } finally {
            getSession().flush();
            getSession().clear();
            getSession().close();
        }
    }

   
}
