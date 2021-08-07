package br.edu.ifnmg.tcc.dao;

import br.edu.ifnmg.tcc.entidade.Especificacao;
import static br.edu.ifnmg.tcc.util.HibernateUtil.getSession;
import br.edu.ifnmg.tcc.util.StringHelper;
import br.edu.ifnmg.tcc.util.StringUtil;
import br.edu.ifnmg.tcc.util.exception.ErroSistema;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Transaction;

public class EspecificacaoDAO implements CrudDAO<Especificacao> {

    private static final long serialVersionUID = 1889153731505502063L;

    @Override
    public Especificacao salvar(Especificacao entidade) throws ErroSistema {
        try {
            entidade.setEspecificacao(StringHelper.spaceRemover(entidade.getEspecificacao()));
            if (StringUtil.isEmpty(entidade.getEspecificacao())) {
                throw new ErroSistema("Por Favor Informe o Campo Especificação!");
            }
            Transaction t = getSession().beginTransaction();
            entidade = (Especificacao) getSession().merge(entidade);
            t.commit();
            return entidade;
        } finally {
            getSession().flush();
            getSession().clear();
            getSession().close();
        }
    }

    @Override
    public void deletar(Especificacao entidade) throws ErroSistema {
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
    public List<Especificacao> buscar(Especificacao entidade) throws ErroSistema {
        try {
            Criteria criteria = getSession().createCriteria(Especificacao.class);
            
             List<Especificacao> especificacaos = criteria.list();
            for (Especificacao especificacao : especificacaos) {
                if (especificacao.getAnimalList()!= null) {
                    especificacao.getAnimalList().size();
                }
               
            }
            return especificacaos;
        } finally {
            getSession().flush();
            getSession().clear();
            getSession().close();
        }
    }

    public List<Especificacao> pesquisar() throws ErroSistema {
        try {
            Criteria criteria = getSession().createCriteria(Especificacao.class);
            return criteria.list();
        } finally {
            getSession().flush();
            getSession().clear();
            getSession().close();
        }
    }

}
