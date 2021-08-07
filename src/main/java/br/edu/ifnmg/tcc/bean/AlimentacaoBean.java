package br.edu.ifnmg.tcc.bean;

import br.edu.ifnmg.tcc.dao.AlimentacaoDAO;
import br.edu.ifnmg.tcc.dao.AlimentoDAO;
import br.edu.ifnmg.tcc.dao.AnimalDAO;
import br.edu.ifnmg.tcc.entidade.Alimentacao;
import br.edu.ifnmg.tcc.entidade.Alimento;
import br.edu.ifnmg.tcc.entidade.Animal;
import br.edu.ifnmg.tcc.util.exception.ErroSistema;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
@SessionScoped
public class AlimentacaoBean extends CrudBean<Alimentacao, AlimentacaoDAO> {

    private AlimentacaoDAO alimentacaoDao;

    private String nomeVaca;
    private Date dataAlimentacao;

    @Override
    public AlimentacaoDAO getDAO() {
        if (alimentacaoDao == null) {
            alimentacaoDao = new AlimentacaoDAO();
        }
        return alimentacaoDao;
    }

    @Override
    public Alimentacao criarNovaEntidade() {
        return new Alimentacao();

    }

    @Override
    public void buscar() {
        if (!isBusca()) {
            setEntidade(new Alimentacao());
        } else {
            if (getEntidade().getAnimal() == null) {
                getEntidade().setAnimal(new Animal());
            }
            getEntidade().getAnimal().setAnimal(nomeVaca);
            getEntidade().setDataAlimentacao(dataAlimentacao);
        }
        super.buscar();
    }

    private AnimalDAO animalDAO = new AnimalDAO();
    private List<Animal> listaAnimals = null;
    private long ultimaAtualizacao;

    public List<Animal> getListaAnimal() {
        if (listaAnimals == null || ultimaAtualizacao < new Date().getTime()) {
            try {
                listaAnimals = animalDAO.pesquisar();
            } catch (ErroSistema ex) {
                adicionarMensagem(ex.getMessage(), FacesMessage.SEVERITY_WARN);
            }
            ultimaAtualizacao = new Date().getTime() + 20000;
        }
        return listaAnimals;
    }

    public Converter getAnimalConverter() {
        return new Converter() {
            @Override
            public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
                try {
                    int id = Integer.parseInt(string);
                    for (Animal animal : getListaAnimal()) {
                        if (animal.getId().equals(id)) {
                            return animal;
                        }
                    }
                } catch (NumberFormatException ex) {
                }
                return null;
            }

            @Override
            public String getAsString(FacesContext fc, UIComponent uic, Object o) {
                Animal animal = null;
                if (o != null) {
                    animal = (Animal) o;
                    return animal.getId() + "";
                }
                return "";
            }
        };
    }

    private AlimentoDAO alimentoDAO = new AlimentoDAO();
    private List<Alimento> listaAlimentos = null;
    private long ultimaAtualizacao1;

    public List<Alimento> getListaAlimento() {
        if (listaAlimentos == null || ultimaAtualizacao1 < new Date().getTime()) {
            try {
                listaAlimentos = alimentoDAO.pesquisar();
            } catch (ErroSistema ex) {
                adicionarMensagem(ex.getMessage(), FacesMessage.SEVERITY_WARN);
            }
            ultimaAtualizacao = new Date().getTime() + 20000;
        }
        return listaAlimentos;
    }

    public Converter getAlimentoConverter() {
        return new Converter() {
            @Override
            public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
                try {
                    int id = Integer.parseInt(string);
                    for (Alimento alimento : getListaAlimento()) {
                        if (alimento.getId().equals(id)) {
                            return alimento;
                        }
                    }
                } catch (NumberFormatException ex) {
                }
                return null;
            }

            @Override
            public String getAsString(FacesContext fc, UIComponent uic, Object o) {
                Alimento alimento = null;
                if (o != null) {
                    alimento = (Alimento) o;
                    return alimento.getId() + "";
                }
                return "";
            }
        };
    }

    public String getNomeVaca() {
        return nomeVaca;
    }

    public void setNomeVaca(String nomeVaca) {
        this.nomeVaca = nomeVaca;
    }

    public Date getDataAlimentacao() {
        return dataAlimentacao;
    }

    public void setDataAlimentacao(Date dataAlimentacao) {
        this.dataAlimentacao = dataAlimentacao;
    }

}
