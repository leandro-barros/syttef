package br.edu.ifnmg.tcc.entidade;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "alimentacao")
@NamedQueries({
    @NamedQuery(name = "Alimentacao.findAll", query = "SELECT a FROM Alimentacao a")})
public class Alimentacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_alimentacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlimentacao = new Date();
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantidade")
    private float quantidade;
    @Column(name = "observacao")
    private String observacao;
    @JoinColumn(name = "alimento_id", referencedColumnName = "id")
    @ManyToOne
    private Alimento alimento;
    @JoinColumn(name = "animal_id", referencedColumnName = "id")
    @ManyToOne
    private Animal animal = new Animal();

    public Alimentacao() {
    }

    public Alimentacao(Integer id) {
        this.id = id;
    }

    public Alimentacao(Integer id, Date dataAlimentacao, float quantidade) {
        this.id = id;
        this.dataAlimentacao = dataAlimentacao;
        this.quantidade = quantidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataAlimentacao() {
        return dataAlimentacao;
    }

    public void setDataAlimentacao(Date dataAlimentacao) {
        this.dataAlimentacao = dataAlimentacao;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public Alimento getAlimento() {
        return alimento;
    }

    public void setAlimento(Alimento alimento) {
        this.alimento = alimento;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alimentacao)) {
            return false;
        }
        Alimentacao other = (Alimentacao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.ifnmg.tcc.entidade.Alimentacao[ id=" + id + " ]";
    }

}
