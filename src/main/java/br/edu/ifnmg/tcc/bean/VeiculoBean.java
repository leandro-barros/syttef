package br.edu.ifnmg.tcc.bean;

import br.edu.ifnmg.tcc.dao.PessoaDAO;
import br.edu.ifnmg.tcc.dao.VeiculoDAO;
import br.edu.ifnmg.tcc.entidade.ControleLeiteiro;
import br.edu.ifnmg.tcc.entidade.Pessoa;
import br.edu.ifnmg.tcc.entidade.Veiculo;
import br.edu.ifnmg.tcc.util.exception.ErroSistema;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.primefaces.event.SelectEvent;

@ManagedBean
@SessionScoped
public class VeiculoBean extends CrudBean<Veiculo, VeiculoDAO> {

    private VeiculoDAO veiculoDao;
    private PessoaDAO pessoaDAO = new PessoaDAO();
    private List<Pessoa> listaPessoas = null;
//    private Pessoa novaPessoa;
    private long ultimaAtualizacao;

    private String nomeMotorista;
    private Date dataBusca;

//    public void onDateSelect(SelectEvent event) {
//        buscar();
//    }
//    @Override
//    public void novo() {
//        super.novo(); //To change body of generated methods, choose Tools | Templates.
//        novaPessoa = new Pessoa();
//    }
//    @Override
//    public void salvar() {
//        if(getEntidade().getPessoa() == null || getEntidade().getPessoa().getId() == null){
//            getEntidade().setPessoa(novaPessoa);
//        }
//        super.salvar(); //To change body of generated methods, choose Tools | Templates.
//    }
//    
    @Override
    public VeiculoDAO getDAO() {
        if (veiculoDao == null) {
            veiculoDao = new VeiculoDAO();
        }
        return veiculoDao;
    }

    @Override
    public void buscar() {
        if (!isBusca()) {
            setEntidade(new Veiculo());
        } else {
            if (getEntidade().getPessoa() == null) {
                getEntidade().setPessoa(new Pessoa());
            }
            getEntidade().getPessoa().setNome(nomeMotorista);
            getEntidade().setDataBusca(dataBusca);
        }
        super.buscar(); //To change body of generated methods, choose Tools | Templates.
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

    @Override
    public Veiculo criarNovaEntidade() {
        return new Veiculo();

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

//    public Pessoa getNovaPessoa() {
//        return novaPessoa;
//    }
//
//    public void setNovaPessoa(Pessoa novaPessoa) {
//        this.novaPessoa = novaPessoa;
//    }
    public Date getDataBusca() {
        return dataBusca;
    }

    public void setDataBusca(Date dataBusca) {
        this.dataBusca = dataBusca;
    }

    public String getNomeMotorista() {
        return nomeMotorista;
    }

    public void setNomeMotorista(String nomeMotorista) {
        this.nomeMotorista = nomeMotorista;
    }

}
