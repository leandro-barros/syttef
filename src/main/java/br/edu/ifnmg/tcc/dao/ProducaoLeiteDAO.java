package br.edu.ifnmg.tcc.dao;

import br.edu.ifnmg.tcc.entidade.ProducaoLeite;
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

public class ProducaoLeiteDAO implements CrudDAO<ProducaoLeite> {

    private static final long serialVersionUID = 5809165829125247591L;

    @Override
    public ProducaoLeite salvar(ProducaoLeite entidade) throws ErroSistema {
        if (entidade.getAnimal() == null) {
            throw new ErroSistema("Por Favor Selecione o Campo Animal!");
        }
        if (entidade.getQuantidade() == 0) {
            throw new ErroSistema("Por Favor informe o Campo Quantidade!");
        }
        try {
            Transaction t = getSession().beginTransaction();
            entidade = (ProducaoLeite) getSession().merge(entidade);
            t.commit();
            return entidade;
        } finally {
            getSession().flush();
            getSession().clear();
            getSession().close();
        }
    }

    @Override
    public void deletar(ProducaoLeite entidade) throws ErroSistema {
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
    public List<ProducaoLeite> buscar(ProducaoLeite entidade) throws ErroSistema {
        try {
            Criteria criteria = getSession().createCriteria(ProducaoLeite.class);
            if (entidade.getAnimal() != null && !StringUtil.isEmpty(entidade.getAnimal().getAnimal())) {
                criteria.createAlias("animal", "a");
                criteria.add(Restrictions.ilike("a.animal", entidade.getAnimal().getAnimal(), MatchMode.ANYWHERE));
            }
            if (entidade.getDataOrdenha()!= null) {
                Date dataInicio = entidade.getDataOrdenha();
                dataInicio = DataUtil.truncate(dataInicio);//Zerando a hora
                Date dataFim = entidade.getDataOrdenha();// pegar dois valores do menos dia
                dataFim = DataUtil.truncateEndDay(dataFim);//pegando a última hora do dia
                criteria.add(Restrictions.between("dataOrdenha", dataInicio, dataFim));
            }
            return criteria.list();
        } finally {
            getSession().flush();
            getSession().clear();
            getSession().close();
        }
    }

  
}
