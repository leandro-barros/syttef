package br.edu.ifnmg.tcc.bean;

import br.edu.ifnmg.tcc.dao.AnimalDAO;
import br.edu.ifnmg.tcc.entidade.Animal;
import br.edu.ifnmg.tcc.relatorio.GerarRelatorio;
import br.edu.ifnmg.tcc.util.JsfUtil;
import br.edu.ifnmg.tcc.util.exception.ErroSistema;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.primefaces.model.ByteArrayContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@SessionScoped
public class RelatorioBean extends JsfUtil {

    private GerarRelatorio gerarRelatorio = new GerarRelatorio();
    private Date dataInicio;
    private Date dataFim = new Date();
    private Date dataInicioAlimentacao;
    private Date dataFimAlimentacao = new Date();
    private Date dataInicioProducaoLeite;
    private Date dataFimProducaoLeite = new Date();
    private AnimalDAO animalDAO = new AnimalDAO();
    private List<Animal> listaAnimals = null;
    private long ultimaAtualizacao;
    private Animal animal;
    private Animal animalAlimentacao;

    public StreamedContent getRelatorioEntregaLeitePDF() {
        try {
             if(dataInicio == null){
                adicionarMensagem("Por Favor Informe a data Inicial!",FacesMessage.SEVERITY_WARN);
                return null;
            }
           
            byte[] pdfByte = gerarRelatorio.gerarRelatorioEntregaLeite(dataInicio, dataFim);
            StreamedContent pdf = new ByteArrayContent(pdfByte, "appliction/pdf", "relatorio_entregar_leite.pdf");
            return pdf;
        } catch (Exception ex) {
            addMensagemErro(ex.getMessage());
            return null;
        }
    }

    public StreamedContent getRelatorioAlimentacaoPDF() {
        try {
            if(animalAlimentacao == null){
                adicionarMensagem("Por Favor Selecione o Animal!",FacesMessage.SEVERITY_WARN);
                return null;
            }
            if(dataInicioAlimentacao == null){
                adicionarMensagem("Por Favor Informe a Data Inicial!",FacesMessage.SEVERITY_WARN);
                return null;
            }
            byte[] pdfByte = gerarRelatorio.gerarRelatorioAlimentacao(dataInicioAlimentacao, dataFimAlimentacao, animalAlimentacao);
            StreamedContent pdf = new ByteArrayContent(pdfByte, "appliction/pdf", "relatorio_alimentacao.pdf");
            return pdf;
        } catch (Exception ex) {
            addMensagemErro(ex.getMessage());
            return null;
        }
    }
    public StreamedContent getRelatorioProducaoLeitePDF() {
        try {
            if(animal == null){
                adicionarMensagem("Por Favor Selecione o Animal!",FacesMessage.SEVERITY_WARN);
                return null;
            }
            if(dataInicioProducaoLeite == null){
                adicionarMensagem("Por Favor Informe a Data Inicial!",FacesMessage.SEVERITY_WARN);
                return null;
            }
           
            byte[] pdfByte = gerarRelatorio.gerarRelatorioProducaoLeite(dataInicioProducaoLeite, dataFimProducaoLeite, animal);
            StreamedContent pdf = new ByteArrayContent(pdfByte, "appliction/pdf", "relatorio_producao_leite.pdf");
            return pdf;
        } catch (Exception ex) {
            addMensagemErro(ex.getMessage());
            return null;
        }
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

    public void adicionarMensagem(String mensagem, FacesMessage.Severity tipoErro) {
        FacesMessage fm = new FacesMessage(tipoErro, mensagem, null);
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public GerarRelatorio getGerarRelatorio() {
        return gerarRelatorio;
    }

    public void setGerarRelatorio(GerarRelatorio gerarRelatorio) {
        this.gerarRelatorio = gerarRelatorio;
    }

    public Date getDataInicioAlimentacao() {
        return dataInicioAlimentacao;
    }

    public void setDataInicioAlimentacao(Date dataInicioAlimentacao) {
        this.dataInicioAlimentacao = dataInicioAlimentacao;
    }

    public Date getDataFimAlimentacao() {
        return dataFimAlimentacao;
    }

    public void setDataFimAlimentacao(Date dataFimAlimentacao) {
        this.dataFimAlimentacao = dataFimAlimentacao;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Date getDataInicioProducaoLeite() {
        return dataInicioProducaoLeite;
    }

    public void setDataInicioProducaoLeite(Date dataInicioProducaoLeite) {
        this.dataInicioProducaoLeite = dataInicioProducaoLeite;
    }

    public Date getDataFimProducaoLeite() {
        return dataFimProducaoLeite;
    }

    public void setDataFimProducaoLeite(Date dataFimProducaoLeite) {
        this.dataFimProducaoLeite = dataFimProducaoLeite;
    }

    public void setAnimalAlimentacao(Animal animalAlimentacao) {
        this.animalAlimentacao = animalAlimentacao;
    }

    public Animal getAnimalAlimentacao() {
        return animalAlimentacao;
    }

}
