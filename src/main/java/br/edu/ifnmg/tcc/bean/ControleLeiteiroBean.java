package br.edu.ifnmg.tcc.bean;

import br.edu.ifnmg.tcc.dao.PessoaDAO;
import br.edu.ifnmg.tcc.dao.ControleLeiteiroDAO;
import br.edu.ifnmg.tcc.entidade.Pessoa;
import br.edu.ifnmg.tcc.entidade.ControleLeiteiro;
import br.edu.ifnmg.tcc.util.exception.ErroSistema;
import java.util.Calendar;
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
public class ControleLeiteiroBean extends CrudBean<ControleLeiteiro, ControleLeiteiroDAO> {

    private ControleLeiteiroDAO controleLeiteiroDao;

    private PessoaDAO pessoaDAO = new PessoaDAO();
    private List<Pessoa> listaPessoas = null;
    private long ultimaAtualizacao;

    private String nomeFazendeiro;
    private Date dataEntrega;

    @Override
    public ControleLeiteiroDAO getDAO() {
        if (controleLeiteiroDao == null) {
            controleLeiteiroDao = new ControleLeiteiroDAO();
        }
        return controleLeiteiroDao;
    }

   
    @Override
    public void buscar() {
        if (!isBusca()) {
            setEntidade(new ControleLeiteiro());
        } else {
            if (getEntidade().getPessoa() == null) {
                getEntidade().setPessoa(new Pessoa());
            }
            getEntidade().getPessoa().setNome(nomeFazendeiro);
            getEntidade().setDataEntrega(dataEntrega);
        }
        super.buscar(); //metodo para busca personalizada
    }

    @Override
    public ControleLeiteiro criarNovaEntidade() {
        return new ControleLeiteiro();
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

    public String getNomeFazendeiro() {
        return nomeFazendeiro;
    }

    public void setNomeFazendeiro(String nomeFazendeiro) {
        this.nomeFazendeiro = nomeFazendeiro;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

}
