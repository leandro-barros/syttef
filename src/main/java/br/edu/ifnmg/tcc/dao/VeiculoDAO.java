package br.edu.ifnmg.tcc.dao;

import br.edu.ifnmg.tcc.entidade.Veiculo;
import static br.edu.ifnmg.tcc.util.HibernateUtil.getSession;
import br.edu.ifnmg.tcc.util.StringHelper;
import br.edu.ifnmg.tcc.util.StringUtil;
import br.edu.ifnmg.tcc.util.exception.ErroSistema;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class VeiculoDAO implements CrudDAO<Veiculo> {

    private static final long serialVersionUID = -3139135062493176204L;

    @Override
    public Veiculo salvar(Veiculo entidade) throws ErroSistema {

        if (entidade.getPessoa() == null) {
            throw new ErroSistema("Por Favor Selecione o Campo Motorista!");
        }
        entidade.setModelo(StringHelper.spaceRemover(entidade.getModelo()));
        if (StringUtil.isEmpty(entidade.getModelo())) {
            throw new ErroSistema("Por Favor Informe o Campo Modelo!");
        }
        if (StringUtil.isEmpty(entidade.getPlaca())) {
            throw new ErroSistema("Por Favor Informe o Campo Placa!");
        }
        if (entidade.getQuantidade() == 0) {
            throw new ErroSistema("Por Favor Informe o Campo Quantidade!");
        }
        try {
            Transaction t = getSession().beginTransaction();
            entidade = (Veiculo) getSession().merge(entidade);
            t.commit();
            return entidade;
        } finally {
            getSession().flush();
            getSession().clear();
            getSession().close();
        }
    }

    @Override
    public void deletar(Veiculo entidade) throws ErroSistema {
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
    public List<Veiculo> buscar(Veiculo entidade) throws ErroSistema {
        try {
            Criteria criteria = getSession().createCriteria(Veiculo.class);
            if (entidade.getPessoa() != null && !StringUtil.isEmpty(entidade.getPessoa().getNome())) {
                criteria.createAlias("pessoa", "p");
                criteria.add(Restrictions.ilike("p.nome", entidade.getPessoa().getNome(), MatchMode.ANYWHERE));
            }
            if (entidade.getDataBusca()!= null) {
                criteria.add(Restrictions.eq("dataBusca", entidade.getDataBusca()));
            }
            return criteria.list();
        } finally {
            getSession().flush();
            getSession().clear();
            getSession().close();
        }
    }

}
