/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.validation.constraints.Size;

/**
 *
 * @author 7system01
 */
@Entity
@Table(name = "producao_leite")
@NamedQueries({
    @NamedQuery(name = "ProducaoLeite.findAll", query = "SELECT p FROM ProducaoLeite p")})
public class ProducaoLeite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_ordenha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataOrdenha = new Date();
    @Size(max = 255)
    @Column(name = "observacao")
    private String observacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantidade")
    private float quantidade;
    @JoinColumn(name = "animal_id", referencedColumnName = "id")
    @ManyToOne
    private Animal animal = new Animal();

    public ProducaoLeite() {
    }

    public ProducaoLeite(Integer id) {
        this.id = id;
    }

    public ProducaoLeite(Integer id, Date dataOrdenha, float quantidade) {
        this.id = id;
        this.dataOrdenha = dataOrdenha;
        this.quantidade = quantidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataOrdenha() {
        return dataOrdenha;
    }

    public void setDataOrdenha(Date dataOrdenha) {
        this.dataOrdenha = dataOrdenha;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
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
        if (!(object instanceof ProducaoLeite)) {
            return false;
        }
        ProducaoLeite other = (ProducaoLeite) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.ifnmg.tcc.entidade.ProducaoLeite[ id=" + id + " ]";
    }

}
