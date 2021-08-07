package br.edu.ifnmg.tcc.dao;

import br.edu.ifnmg.tcc.entidade.Alimentacao;
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

public class AlimentacaoDAO implements CrudDAO<Alimentacao> {

    private static final long serialVersionUID = -4268101299236419201L;

    @Override
    public Alimentacao salvar(Alimentacao entidade) throws ErroSistema {
        if (entidade.getAnimal() == null) {
            throw new ErroSistema("Por Favor Selecione o Campo Animal!");
        }
        if (entidade.getAlimento() == null) {
            throw new ErroSistema("Por Favor Selecione o Campo Alimento!");
        }
        if (entidade.getQuantidade() == 0) {
            throw new ErroSistema("Por Favor Informe o Campo Quantidade!");
        }
        try {
            Transaction t = getSession().beginTransaction();
            entidade = (Alimentacao) getSession().merge(entidade);
            t.commit();
            return entidade;
        } finally {
            getSession().flush();
            getSession().clear();
            getSession().close();
        }
    }

    @Override
    public void deletar(Alimentacao entidade) throws ErroSistema {
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
    public List<Alimentacao> buscar(Alimentacao entidade) throws ErroSistema {
        try {
            Criteria criteria = getSession().createCriteria(Alimentacao.class);
            if (entidade.getAnimal() != null && !StringUtil.isEmpty(entidade.getAnimal().getAnimal())) {
                criteria.createAlias("animal", "a");
                criteria.add(Restrictions.ilike("a.animal", entidade.getAnimal().getAnimal(), MatchMode.ANYWHERE));
            }
            if (entidade.getDataAlimentacao()!= null) {// Método que converte DateTime para Data
                Date dataInicio = entidade.getDataAlimentacao();
                dataInicio = DataUtil.truncate(dataInicio);
                Date dataFim = entidade.getDataAlimentacao();
                dataFim = DataUtil.truncateEndDay(dataFim);
                criteria.add(Restrictions.between("dataAlimentacao", dataInicio, dataFim));
            }
            return criteria.list();
        } finally {
            getSession().flush();
            getSession().clear();
            getSession().close();
        }
    }

  
}
