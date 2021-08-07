package br.edu.ifnmg.tcc.dao;

import br.edu.ifnmg.tcc.entidade.ControleLeiteiro;
import br.edu.ifnmg.tcc.util.DataUtil;
import static br.edu.ifnmg.tcc.util.HibernateUtil.getSession;
import br.edu.ifnmg.tcc.util.StringUtil;
import br.edu.ifnmg.tcc.util.exception.ErroSistema;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class ControleLeiteiroDAO implements CrudDAO<ControleLeiteiro> {

    private static final long serialVersionUID = -2540017317664096386L;

    @Override
    public ControleLeiteiro salvar(ControleLeiteiro entidade) throws ErroSistema {
        if (entidade.getPessoa() == null) {
            throw new ErroSistema("Por Favor Selecione o Campo Fazendeiro!");
        }
        if (entidade.getQuantidade() == 0) {
            throw new ErroSistema("Por Favor Informe o Campo Quantidade!");
        }
        try {
            Transaction t = getSession().beginTransaction();
            entidade = (ControleLeiteiro) getSession().merge(entidade);
            t.commit();
            return entidade;
        } finally {
            getSession().flush();
            getSession().clear();
            getSession().close();
        }
    }

    @Override
    public void deletar(ControleLeiteiro entidade) throws ErroSistema {
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
    public List<ControleLeiteiro> buscar(ControleLeiteiro entidade) throws ErroSistema {
        try {
            Criteria criteria = getSession().createCriteria(ControleLeiteiro.class);
            if (entidade.getPessoa() != null && !StringUtil.isEmpty(entidade.getPessoa().getNome())) {
                criteria.createAlias("pessoa", "p");
                criteria.add(Restrictions.ilike("p.nome", entidade.getPessoa().getNome(), MatchMode.ANYWHERE));
            }
            if (entidade.getDataEntrega()!= null) {
                Date dataInicio = entidade.getDataEntrega();
                dataInicio = DataUtil.truncate(dataInicio);
                Date dataFim = entidade.getDataEntrega();
                dataFim = DataUtil.truncateEndDay(dataFim);
                criteria.add(Restrictions.between("dataEntrega", dataInicio, dataFim));
            }
            return criteria.list();
        } finally {
            getSession().flush();
            getSession().clear();
            getSession().close();
        }
    }

   
}
