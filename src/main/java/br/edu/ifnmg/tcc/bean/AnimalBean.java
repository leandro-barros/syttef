package br.edu.ifnmg.tcc.bean;

import br.edu.ifnmg.tcc.dao.AnimalDAO;
import br.edu.ifnmg.tcc.dao.EspecificacaoDAO;
import br.edu.ifnmg.tcc.dao.RacaDAO;
import br.edu.ifnmg.tcc.entidade.Animal;
import br.edu.ifnmg.tcc.entidade.Especificacao;
import br.edu.ifnmg.tcc.entidade.Raca;
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
public class AnimalBean extends CrudBean<Animal, AnimalDAO> {

    private AnimalDAO animalDao;
    private RacaDAO tipoDAO = new RacaDAO();
    private List<Raca> listaRacas = null;
    private long ultimaAtualizacao;
    
    private EspecificacaoDAO especificacaoDAO = new EspecificacaoDAO();
    private List<Especificacao> listaEspecificacaos = null;
    private long ultimaAtualizacao3;
    
    private AnimalDAO animalDAO = new AnimalDAO();
    private List<Animal> listaAnimais = null;
    private long ultimaAtualizacao2;

    @Override
    public AnimalDAO getDAO() {
        if (animalDao == null) {
            animalDao = new AnimalDAO();
        }
        return animalDao;
    }

     public List<Animal> getListaAnimal() {
        if (listaAnimais == null || ultimaAtualizacao2 < new Date().getTime()) {

            try {
                listaAnimais = animalDAO.pesquisar();
            } catch (ErroSistema ex) {
                adicionarMensagem(ex.getMessage(), FacesMessage.SEVERITY_WARN);
            }
            ultimaAtualizacao2 = new Date().getTime() + 20000;
        }
        return listaAnimais;
    }
     
     
     public List<Especificacao> getListaEspecificacao() {
        if (listaEspecificacaos == null || ultimaAtualizacao3 < new Date().getTime()) {

            try {
                listaEspecificacaos = especificacaoDAO.pesquisar();
            } catch (ErroSistema ex) {
                adicionarMensagem(ex.getMessage(), FacesMessage.SEVERITY_WARN);
            }
            ultimaAtualizacao3 = new Date().getTime() + 20000;
        }
        return listaEspecificacaos;
    }
    
    public List<Raca> getListaRaca() {
        if (listaRacas == null || ultimaAtualizacao < new Date().getTime()) {

            try {
                listaRacas = tipoDAO.pesquisar();
            } catch (ErroSistema ex) {
                adicionarMensagem(ex.getMessage(), FacesMessage.SEVERITY_WARN);
            }
            ultimaAtualizacao = new Date().getTime() + 20000;
        }
        return listaRacas;
    }

    @Override
    public Animal criarNovaEntidade() {
        return new Animal();

    }


    public Converter getRacaConverter() {
        return new Converter() {
            @Override
            public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
                try {
                    int id = Integer.parseInt(string);
                    for (Raca tipo : getListaRaca()) {
                        if (tipo.getId().equals(id)) {
                            return tipo;
                        }
                    }
                } catch (NumberFormatException ex) {
                }
                return null;
            }

            @Override
            public String getAsString(FacesContext fc, UIComponent uic, Object o) {
                Raca tipo = null;
                if (o != null) {
                    tipo = (Raca) o;
                    return tipo.getId() + "";
                }
                return "";
            }
        };
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
 public Converter getEspecificacaoConverter() {
        return new Converter() {
            @Override
            public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
                try {
                    int id = Integer.parseInt(string);
                    for (Especificacao especificacao : getListaEspecificacao()) {
                        if (especificacao.getId().equals(id)) {
                            return especificacao;
                        }
                    }
                } catch (NumberFormatException ex) {
                }
                return null;
            }

            @Override
            public String getAsString(FacesContext fc, UIComponent uic, Object o) {
                Especificacao animal = null;
                if (o != null) {
                    animal = (Especificacao) o;
                    return animal.getId() + "";
                }
                return "";
            }
        };
    }
   
}
