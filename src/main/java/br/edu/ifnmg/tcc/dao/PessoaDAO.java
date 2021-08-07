package br.edu.ifnmg.tcc.dao;

import br.edu.ifnmg.tcc.entidade.Pessoa;
import br.edu.ifnmg.tcc.util.Assert;
import static br.edu.ifnmg.tcc.util.HibernateUtil.getSession;
import br.edu.ifnmg.tcc.util.StringHelper;
import br.edu.ifnmg.tcc.util.StringUtil;
import br.edu.ifnmg.tcc.util.exception.ErroSistema;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class PessoaDAO implements CrudDAO<Pessoa> {

    private static final long serialVersionUID = -7512753626725671420L;

    @Override
    public Pessoa salvar(Pessoa entidade) throws ErroSistema {
        entidade.setNome(StringHelper.spaceRemover(entidade.getNome()));
        entidade.setNome(StringHelper.replaceAllSpecialCharacters(entidade.getNome()));
        if (StringUtil.isEmpty(entidade.getNome())) {
            throw new ErroSistema("Por Favor Informe o Campo Nome!");
        }
        entidade.setEndereco(StringHelper.spaceRemover(entidade.getEndereco()));
        if (StringUtil.isEmpty(entidade.getEndereco())) {
            throw new ErroSistema("Por Favor Informe o Campo Endereço!");
        }
        if (StringUtil.isEmpty(entidade.getTelefone())) {
            throw new ErroSistema("Por Favor Informe o Campo Telefone!");
        }
        if (!Assert.isTelefone(entidade.getTelefone())) {
            throw new ErroSistema("Telefone Inválido!");
        }
        entidade.setEmail(StringHelper.spaceRemover(entidade.getEmail()));
        if (StringUtil.isEmpty(entidade.getEmail())) {
            throw new ErroSistema("Por Favor Informe o Campo Email!");
        }
        if (!Assert.isValidEmail(entidade.getEmail())) {
            throw new ErroSistema("Email Inválido!");
        }
        if (verificaEmailCadastrado(entidade.getEmail(),entidade.getId())) {
            throw new ErroSistema("Email já cadastrado!");
        }
        if (entidade.getDataNascimento() == null) {
            throw new ErroSistema("Por Favor Informe o Campo Data de Nascimento!");
        }
        if (StringUtil.isEmpty(entidade.getCpf())) {
            throw new ErroSistema("Por Favor Informe o Campo CPF!");
        }
        if (!Assert.isCpf(entidade.getCpf())) {
            throw new ErroSistema("CPF Inválido!");
        }
        if (verificaCpfCadastrado(entidade.getCpf(), entidade.getId())) {
            throw new ErroSistema("CPF já cadastrado!");
        }
        try {
            Transaction t = getSession().beginTransaction();
            entidade = (Pessoa) getSession().merge(entidade);
            t.commit();
            return entidade;
        } finally {
            getSession().flush();
            getSession().clear();
            getSession().close();
        }
    }

    @Override
    public void deletar(Pessoa entidade) throws ErroSistema {
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
    public List<Pessoa> buscar(Pessoa entidade) throws ErroSistema {
        try {
            Criteria criteria = getSession().createCriteria(Pessoa.class);
            criteria.add(Restrictions.or(
                    Restrictions.ilike("nome", entidade.getNome(), MatchMode.ANYWHERE)
            ));
            if (entidade.getDataNascimento() != null) {
                criteria.add(Restrictions.eq("dataNascimento", entidade.getDataNascimento()));
            }
            List<Pessoa> pessoas = criteria.list();
            for (Pessoa pessoa : pessoas) {
                if (pessoa.getUsuarios() != null) {
                    pessoa.getUsuarios().size();
                }
                if(pessoa.getControleLeiteiroList() != null){
                    pessoa.getControleLeiteiroList().size();
                }
                if(pessoa.getVeiculoList() != null){
                    pessoa.getVeiculoList().size();
                }
            }
            return pessoas;
        } finally {
            getSession().flush();
            getSession().clear();
            getSession().close();
        }
    }

    public boolean verificaCpfCadastrado(String cpf, Integer id) throws ErroSistema {
        try {
            Criteria criteria = getSession().createCriteria(Pessoa.class);
            if (StringHelper.isEmpty(cpf)) {
                throw new ErroSistema("CPF é obrigatório.");
            }
            if (id != null) {
                criteria.add(Restrictions.not(Restrictions.eq("id", id)));
            }
            criteria.add(Restrictions.eq("cpf", cpf));

            return criteria.list().size() > 0;
        } finally {
            getSession().flush();
            getSession().clear();
            getSession().close();
        }
    }
    public boolean verificaEmailCadastrado(String email, Integer id) throws ErroSistema {
        try {
            Criteria criteria = getSession().createCriteria(Pessoa.class);
            if (StringHelper.isEmpty(email)) {
                throw new ErroSistema("Email é obrigatório.");
            }
            if (id != null) {
                criteria.add(Restrictions.not(Restrictions.eq("id", id)));
            }
            criteria.add(Restrictions.eq("email", email));

            return criteria.list().size() > 0;
        } finally {
            getSession().flush();
            getSession().clear();
            getSession().close();
        }
    }

    public List<Pessoa> pesquisar() throws ErroSistema {//metodo do converter
        try {
            Criteria criteria = getSession().createCriteria(Pessoa.class);
            return criteria.list();
        } finally {
            getSession().flush();
            getSession().clear();
            getSession().close();
        }
    }


}
