package br.edu.ifnmg.tcc.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "animal")
@NamedQueries({
    @NamedQuery(name = "Animal.findAll", query = "SELECT a FROM Animal a")})
public class Animal implements Serializable {

    @OneToMany(mappedBy = "animal")
    private List<ProducaoLeite> producaoLeiteList;
  
    @OneToMany(mappedBy = "animal")
    private List<Alimentacao> alimentacaoList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "animal")
    private String animal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_nascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "sexo")
    private String sexo;
    @Size(max = 45)
    @Column(name = "observacao")
    private String observacao;
    @Column(name = "data_cio")
    @Temporal(TemporalType.DATE)
    private Date dataCio;
    @OneToMany(mappedBy = "animal")
    private List<Animal> animalList;
    @JoinColumn(name = "animal_id", referencedColumnName = "id")
    @ManyToOne
    private Animal listaAnimal;
    @JoinColumn(name = "especificacao_id", referencedColumnName = "id")
    @ManyToOne
    private Especificacao especificacao;
    @JoinColumn(name = "raca_id", referencedColumnName = "id")
    @ManyToOne
    private Raca raca;

    public Animal() {
    }

    public Animal(Integer id) {
        this.id = id;
    }

    public Animal(Integer id, String animal, Date dataNascimento, String sexo) {
        this.id = id;
        this.animal = animal;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }

    public void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }

    public Especificacao getEspecificacao() {
        return especificacao;
    }

    public void setEspecificacao(Especificacao especificacao) {
        this.especificacao = especificacao;
    }

    public Raca getRaca() {
        return raca;
    }

    public void setRaca(Raca raca) {
        this.raca = raca;
    }

    public List<Alimentacao> getAlimentacaoList() {
        return alimentacaoList;
    }

    public void setAlimentacaoList(List<Alimentacao> alimentacaoList) {
        this.alimentacaoList = alimentacaoList;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getDataCio() {
        return dataCio;
    }

    public void setDataCio(Date dataCio) {
        this.dataCio = dataCio;
    }

    public Animal getListaAnimal() {
        return listaAnimal;
    }

    public void setListaAnimal(Animal listaAnimal) {
        this.listaAnimal = listaAnimal;
    }


    public List<ProducaoLeite> getProducaoLeiteList() {
        return producaoLeiteList;
    }

    public void setProducaoLeiteList(List<ProducaoLeite> producaoLeiteList) {
        this.producaoLeiteList = producaoLeiteList;
    }

    public boolean podeDeletar() {
        boolean podeDeletar = true;
        try {
            if (producaoLeiteList != null && !producaoLeiteList.isEmpty()) {
                podeDeletar = false;
            } else if (alimentacaoList != null && !alimentacaoList.isEmpty()) {
                podeDeletar = false;
            }
        } catch (Exception e) {
        }
        return podeDeletar;
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
        if (!(object instanceof Animal)) {
            return false;
        }
        Animal other = (Animal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.ifnmg.tcc.entidade.Animal[ id=" + id + " ]";
    }

}
