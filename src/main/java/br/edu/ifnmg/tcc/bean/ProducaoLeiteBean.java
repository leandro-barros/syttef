package br.edu.ifnmg.tcc.bean;

import br.edu.ifnmg.tcc.dao.AnimalDAO;
import br.edu.ifnmg.tcc.dao.ProducaoLeiteDAO;
import br.edu.ifnmg.tcc.entidade.Animal;
import br.edu.ifnmg.tcc.entidade.ProducaoLeite;
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
public class ProducaoLeiteBean extends CrudBean<ProducaoLeite, ProducaoLeiteDAO> {

    private ProducaoLeiteDAO vacasDAO;

    private AnimalDAO animalDAO = new AnimalDAO();
    private List<Animal> listaAnimals = null;
    private long ultimaAtualizacao;

    private String nomeVaca;
    private Date dataOrdenha;

    @Override
    public ProducaoLeiteDAO getDAO() {
        if (vacasDAO == null) {
            vacasDAO = new ProducaoLeiteDAO();
        }
        return vacasDAO;
    }

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

    @Override
    public ProducaoLeite criarNovaEntidade() {
        return new ProducaoLeite();

    }

    @Override
    public void buscar() {
        if (!isBusca()) {
            setEntidade(new ProducaoLeite());
        } else {
            if (getEntidade().getAnimal() == null) {
                getEntidade().setAnimal(new Animal());
            }
            getEntidade().getAnimal().setAnimal(nomeVaca);
            getEntidade().setDataOrdenha(dataOrdenha);
        }
        super.buscar(); //To change body of generated methods, choose Tools | Templates.
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

    public String getNomeVaca() {
        return nomeVaca;
    }

    public void setNomeVaca(String nomeVaca) {
        this.nomeVaca = nomeVaca;
    }

    public Date getDataOrdenha() {
        return dataOrdenha;
    }

    public void setDataOrdenha(Date dataOrdenha) {
        this.dataOrdenha = dataOrdenha;
    }

}
