package br.edu.ifnmg.tcc.bean;

import br.edu.ifnmg.tcc.dao.ConfiguracaoDAO;
import br.edu.ifnmg.tcc.entidade.Configuracao;
import br.edu.ifnmg.tcc.util.JsfUtil;
import br.edu.ifnmg.tcc.util.exception.ErroSistema;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ConfiguracaoBean extends JsfUtil {

    private static final long serialVersionUID = -3237047568756967477L;

    private ConfiguracaoDAO configuracaoDAO;
    private Configuracao preco;

    public ConfiguracaoBean() {
        buscar();
    }

    public void buscar() {
        try {
            preco = getDAO().buscar();
        } catch (ErroSistema ex) {
            addMensagemErro(ex.getMessage());
        }
    }

    public void salvar() {
        try {
            getDAO().salvar(preco);
            addMensagem("Salvo com sucesso!");
        } catch (ErroSistema ex) {
            addMensagemErro(ex.getMessage());
        }
    }

    public ConfiguracaoDAO getDAO() {
        if (configuracaoDAO == null) {
            configuracaoDAO = new ConfiguracaoDAO();
        }
        return configuracaoDAO;
    }

    public Configuracao getPreco() {
        return preco;
    }

    public void setPreco(Configuracao preco) {
        this.preco = preco;
    }

}
