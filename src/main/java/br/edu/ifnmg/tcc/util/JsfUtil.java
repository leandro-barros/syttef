package br.edu.ifnmg.tcc.util;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JsfUtil implements Serializable{
    
    enum EstadoTela{
        EDITANDO,
        PESQUISANDO,
        INSERINDO
    }
    
    private EstadoTela estado = EstadoTela.PESQUISANDO;
    
    public boolean isPesquisando(){
        return EstadoTela.PESQUISANDO.equals(estado);
    }
    public boolean isEditando(){
        return EstadoTela.EDITANDO.equals(estado);
    }
    public boolean isInserindo(){
        return EstadoTela.INSERINDO.equals(estado);
    }
    
    public void alterarParaPesquisando(){
        estado = EstadoTela.PESQUISANDO;
    }
    public void alterarParaEditando(){
        estado = EstadoTela.EDITANDO;
    }
    public void alterarParaInserindo(){
        estado = EstadoTela.INSERINDO;
    }
    
     public void addMensagem(String mensagem, String titulo,FacesMessage.Severity tipo){
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(tipo, titulo,mensagem));
     }
     public void addMensagem(String mensagem){
         addMensagem(mensagem, "info", FacesMessage.SEVERITY_INFO);
     }
         public void addMensagemAviso(String mensagem){
         addMensagem(mensagem, "aviso", FacesMessage.SEVERITY_WARN);
     }
             public void addMensagemErro(String mensagem){
         addMensagem(mensagem, "Erro", FacesMessage.SEVERITY_ERROR);
     }
}
