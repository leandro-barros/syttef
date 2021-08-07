package br.edu.ifnmg.tcc.bean;

import br.edu.ifnmg.tcc.dao.CrudDAO;
import br.edu.ifnmg.tcc.util.exception.ErroSistema;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class CrudBean<E, D extends CrudDAO> {

    private String estadoTela = "buscar";

    E entidade;
    private List<E> entidades = new ArrayList<>();

    public CrudBean() {
        entidade = criarNovaEntidade();
    }

    public void novo() {
        entidade = criarNovaEntidade();
        mudarParaInseri();
    }

    public void salvar() {
        try {
           entidade = (E) getDAO().salvar(entidade);
           
            if (entidades.contains(entidade)) {
                entidades.remove(entidade);
            }
            entidades.add(entidade);
            entidade = criarNovaEntidade();
            adicionarMensagem("Salvo com sucesso!", FacesMessage.SEVERITY_INFO);
            mudarParaBusca();

        } catch (ErroSistema ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
            adicionarMensagem(ex.getMessage(), FacesMessage.SEVERITY_WARN);

        }

    }

    public void editar(E entidade) {
        this.entidade = entidade;
        mudarParaEdita();
    }

    public void deletar(E entidade) {
        try {
            getDAO().deletar(entidade);
            entidades.remove(entidade);
            adicionarMensagem("Deletado com sucesso!", FacesMessage.SEVERITY_INFO);
        } catch (ErroSistema ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
            adicionarMensagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }


    public void buscar() {
        if (isBusca() == false) {
            mudarParaBusca();
            return;
        }
        try {
            entidades = getDAO().buscar(entidade);
            if (entidades == null || entidades.size() < 1) {
                adicionarMensagem("Nenhum registro encontrado!", FacesMessage.SEVERITY_WARN);
            }
        } catch (ErroSistema ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void adicionarMensagem(String mensagem, FacesMessage.Severity tipoErro) {
        FacesMessage fm = new FacesMessage(tipoErro, mensagem, null);
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }

    public E getEntidade() {
        return entidade;
    }

    public void setEntidade(E entidade) {
        this.entidade = entidade;
    }

    public List<E> getEntidades() {
        return entidades;
    }

    public void setEntidades(List<E> entidades) {
        this.entidades = entidades;
    }

  

    //responsavel por ciar metodo bean
    public abstract D getDAO();

    public abstract E criarNovaEntidade();

    //métodos para controle da tela
    public boolean isInseri() {
        return "inserir".equals(estadoTela);
    }

    public boolean isEdita() {
        return "editar".equals(estadoTela);
    }

    public boolean isBusca() {
        return "buscar".equals(estadoTela);
    }

    public void mudarParaInseri() {
        estadoTela = "inserir";
    }

    public void mudarParaEdita() {
        estadoTela = "editar";
    }

    public void mudarParaBusca() {
        estadoTela = "buscar";
    }

    public String getEstadoTela() {
        return estadoTela;
    }

    public void setEstadoTela(String estadoTela) {
        this.estadoTela = estadoTela;
    }
    

}
