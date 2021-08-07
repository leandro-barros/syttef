package br.edu.ifnmg.tcc.dao;

import br.edu.ifnmg.tcc.entidade.Pessoa;
import br.edu.ifnmg.tcc.entidade.Usuario;
import static br.edu.ifnmg.tcc.util.HibernateUtil.getSession;
import br.edu.ifnmg.tcc.util.StringUtil;
import br.edu.ifnmg.tcc.util.exception.ErroSistema;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class UsuarioDAO implements CrudDAO<Usuario> {

    private static final long serialVersionUID = -2610231183212282733L;

    @Override
    public Usuario salvar(Usuario entidade) throws ErroSistema {
        if (entidade.getPessoa() == null) {
            throw new ErroSistema("Por Favor Selecione o Campo Pessoa!");
        }
        if (StringUtil.isEmpty(entidade.getLogin())) {
            throw new ErroSistema("Por Favor Informe o Campo Login!");
        }
        if (StringUtil.isEmpty(entidade.getSenha())) {
            throw new ErroSistema("Por Favor Informe o Campo Senha!");
        }
        if (entidade.getControleAcesso()== null) {
            throw new ErroSistema("Por Favor Selecione o Campo Permissão!");
        }
        try {
            Transaction t = getSession().beginTransaction();
            entidade = (Usuario) getSession().merge(entidade);
            t.commit();
            return entidade;
        } finally {
            getSession().flush();
            getSession().clear();
            getSession().close();
        }
    }

    @Override
    public void deletar(Usuario entidade) throws ErroSistema {
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
    public List<Usuario> buscar(Usuario entidade) throws ErroSistema {
        try {
            Criteria criteria = getSession().createCriteria(Usuario.class);
            return criteria.list();
        } finally {
            getSession().flush();
            getSession().clear();
            getSession().close();
        }
    }

    public Usuario buscarPorLoginESenha(String login, String senha) {
        Usuario u;
        Criteria criteria = getSession().createCriteria(Usuario.class);
        criteria.add(Restrictions.eq("login", login));
        criteria.add(Restrictions.eq("senha", senha));
        u = (Usuario) criteria.uniqueResult();
        return u;
    }

}
