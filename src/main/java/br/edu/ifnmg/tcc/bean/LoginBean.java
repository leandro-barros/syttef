package br.edu.ifnmg.tcc.bean;

import br.edu.ifnmg.tcc.dao.UsuarioDAO;
import br.edu.ifnmg.tcc.entidade.Usuario;
import br.edu.ifnmg.tcc.util.JsfUtil;
import br.edu.ifnmg.tcc.util.StringUtil;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Leandro
 */
@ManagedBean
@SessionScoped
public class LoginBean extends JsfUtil {

    private static final long serialVersionUID = -5903054956503731566L;

    private String login;
    private String senha;
    private UsuarioDAO dao = new UsuarioDAO();
    private Usuario usuarioLogado = null;

    public void logar() {// Método para entrar no sistema
        if (StringUtil.isEmpty(login) || StringUtil.isEmpty(senha)) {
            addMensagemAviso("Informe o login e a senha!");
            return;
        }
        usuarioLogado = dao.buscarPorLoginESenha(login, senha);
        if (usuarioLogado == null) {
            addMensagemAviso("Login ou Senha inválidos!");
        } else {
            addMensagem("Bem vindo " + usuarioLogado.getPessoa().getNome());
        }
    }

    public boolean possuiPermissao(String permissao) {//Método de permissão dos usuários
        boolean possuiPermissao = false;
        if (usuarioLogado != null && usuarioLogado.getControleAcesso().getPermisao().equals(permissao)) {
            possuiPermissao = true;
        }
        return possuiPermissao;
    }

    public void redirecionaSemPermissao(String permissao) {// Método caso a pessoa queirra digitar o enderço da página.
        if (!possuiPermissao(permissao)) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("nao-permitido.jsf");
            } catch (IOException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
                addMensagemErro("Erro ao tentar redirecionar.");
            }
        }
    }

    public void redirecionarLogin() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.jsf");
        } catch (IOException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            addMensagemErro("Erro ao tentar redirecionar.");
        }
    }

    public void redirecionarIndex() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
        } catch (IOException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            addMensagemErro("Erro ao tentar redirecionar.");
        }

    }

    public void deslogar() {
        usuarioLogado = null;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

}
