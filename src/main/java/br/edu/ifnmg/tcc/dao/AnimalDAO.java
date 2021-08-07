package br.edu.ifnmg.tcc.dao;

import br.edu.ifnmg.tcc.entidade.Animal;
import static br.edu.ifnmg.tcc.util.HibernateUtil.getSession;
import br.edu.ifnmg.tcc.util.StringHelper;
import br.edu.ifnmg.tcc.util.StringUtil;
import br.edu.ifnmg.tcc.util.exception.ErroSistema;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class AnimalDAO implements CrudDAO<Animal> {

    private static final long serialVersionUID = -5455278210295441371L;

    @Override
    public Animal salvar(Animal entidade) throws ErroSistema {
        entidade.setAnimal(StringHelper.spaceRemover(entidade.getAnimal()));
        if (StringUtil.isEmpty(entidade.getAnimal())) {
            throw new ErroSistema("Por Favor Informe o Campo Animal!");
        }

        if (entidade.getEspecificacao() == null) {
            throw new ErroSistema("Por Favor Selecione Campo Especificação!");
        }
        if (entidade.getRaca() == null) {
            throw new ErroSistema("Por Favor Selecione o Campo Raça!");
        }
        if (StringUtil.isEmpty(entidade.getSexo())) {
            throw new ErroSistema("Por Favor Informe o Campo Sexo!");
        }
      
        try {
            Transaction t = getSession().beginTransaction();
            entidade = (Animal) getSession().merge(entidade);
            t.commit();
            return entidade;
        } finally {
            getSession().flush();
            getSession().clear();
            getSession().close();
        }
    }

    @Override
    public void deletar(Animal entidade) throws ErroSistema {
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
    public List<Animal> buscar(Animal entidade) throws ErroSistema {
        try {
            Criteria criteria = getSession().createCriteria(Animal.class);
            criteria.add(Restrictions.or(
                    Restrictions.ilike("animal", entidade.getAnimal(), MatchMode.ANYWHERE)
            ));
            if (entidade.getDataNascimento()!= null) {
                criteria.add(Restrictions.eq("dataNascimento", entidade.getDataNascimento()));
            }
               List<Animal> animais = criteria.list();
            for (Animal animal : animais) {// Método para verificar se contém ligações
                if(animal.getProducaoLeiteList()!= null){
                    animal.getProducaoLeiteList().size();
                }
                if(animal.getAlimentacaoList()!= null){
                    animal.getAlimentacaoList().size();
                }
                
            }
            return animais;
        } finally {
            getSession().flush();
            getSession().clear();
            getSession().close();
        }
    }

    public List<Animal> pesquisar() throws ErroSistema {
        try {
            Criteria criteria = getSession().createCriteria(Animal.class);
            return criteria.list();
        } finally {
            getSession().flush();
            getSession().clear();
            getSession().close();
        }
    }

  
}
