package br.edu.ifnmg.tcc.dao;

import br.edu.ifnmg.tcc.entidade.Configuracao;
import static br.edu.ifnmg.tcc.util.HibernateUtil.getSession;
import br.edu.ifnmg.tcc.util.exception.ErroSistema;
import org.hibernate.Transaction;

public class ConfiguracaoDAO {

    public void salvar(Configuracao entidade) throws ErroSistema {
        try {
            Transaction t = getSession().beginTransaction();
            getSession().merge(entidade);
            t.commit();
        } finally {
            getSession().flush();
            getSession().clear();
            getSession().close();
        }
    }

    public Configuracao buscar() throws ErroSistema {
        Configuracao c = getSession().get(Configuracao.class, 1);
        if(c == null){
            c = new Configuracao(1);
        }
        return c;
    }

}
