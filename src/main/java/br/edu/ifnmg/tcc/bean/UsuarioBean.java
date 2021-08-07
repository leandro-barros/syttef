package br.edu.ifnmg.tcc.bean;

import br.edu.ifnmg.tcc.dao.ControleAcessoDAO;
import br.edu.ifnmg.tcc.dao.PessoaDAO;
import br.edu.ifnmg.tcc.dao.UsuarioDAO;
import br.edu.ifnmg.tcc.entidade.ControleAcesso;
import br.edu.ifnmg.tcc.entidade.Pessoa;
import br.edu.ifnmg.tcc.entidade.Usuario;
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
public class UsuarioBean extends CrudBean<Usuario, UsuarioDAO> {

    private UsuarioDAO usuarioDao;

    private PessoaDAO pessoaDAO = new PessoaDAO();
    private List<Pessoa> listaPessoas = null;
    private long ultimaAtualizacao;

    private ControleAcessoDAO controleAcessoDAO = new ControleAcessoDAO();
    private List<ControleAcesso> listaControleAcessos = null;
    private long ultimaAtualizacao1;

    private String senha;

    @Override
    public UsuarioDAO getDAO() {
        if (usuarioDao == null) {
            usuarioDao = new UsuarioDAO();
        }
        return usuarioDao;
    }

    @Override
    public Usuario criarNovaEntidade() {
        return new Usuario();

    }

    @Override
    public void salvar() {//Método editar, trocar de senha
        if (senha != null && !"".equals(senha)) {
            getEntidade().setSenha(senha);
        }
        super.salvar(); 
    }

    public List<Pessoa> getListaPessoa() {
        if (listaPessoas == null || ultimaAtualizacao < new Date().getTime()) {
            try {
                listaPessoas = pessoaDAO.pesquisar();
            } catch (ErroSistema ex) {
                adicionarMensagem(ex.getMessage(), FacesMessage.SEVERITY_WARN);
            }
            ultimaAtualizacao = new Date().getTime() + 20000;
        }
        return listaPessoas;
    }

    public List<ControleAcesso> getListaControleAcesso() {
        if (listaControleAcessos == null || ultimaAtualizacao1 < new Date().getTime()) {
            try {
                listaControleAcessos = controleAcessoDAO.pesquisar();
            } catch (ErroSistema ex) {
                adicionarMensagem(ex.getMessage(), FacesMessage.SEVERITY_WARN);
            }
            ultimaAtualizacao1 = new Date().getTime() + 20000;
        }
        return listaControleAcessos;
    }

    public Converter getPessoaConverter() {
        return new Converter() {
            @Override
            public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
                try {
                    int id = Integer.parseInt(string);
                    for (Pessoa pessoa : getListaPessoa()) {
                        if (pessoa.getId().equals(id)) {
                            return pessoa;
                        }
                    }
                } catch (NumberFormatException ex) {
                }
                return null;
            }

            @Override
            public String getAsString(FacesContext fc, UIComponent uic, Object o) {
                Pessoa pessoa = null;
                if (o != null) {
                    pessoa = (Pessoa) o;
                    return pessoa.getId() + "";
                }
                return "";
            }
        };
    }

    public Converter getControleAcessoConverter() {
        return new Converter() {
            @Override
            public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
                try {
                    int id = Integer.parseInt(string);
                    for (ControleAcesso controleAcesso : getListaControleAcesso()) {
                        if (controleAcesso.getId().equals(id)) {
                            return controleAcesso;
                        }
                    }
                } catch (NumberFormatException ex) {
                }
                return null;
            }

            @Override
            public String getAsString(FacesContext fc, UIComponent uic, Object o) {
                ControleAcesso controleAcesso = null;
                if (o != null) {
                    controleAcesso = (ControleAcesso) o;
                    return controleAcesso.getId() + "";
                }
                return "";
            }
        };
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
