
package br.edu.ifnmg.tcc.entidade;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "alimento")
@NamedQueries({
    @NamedQuery(name = "Alimento.findAll", query = "SELECT a FROM Alimento a")})
public class Alimento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "alimento")
    private String alimento;
    @OneToMany(mappedBy = "alimento")
    private List<Alimentacao> alimentacaoList;

    public Alimento() {
    }

    public Alimento(Integer id) {
        this.id = id;
    }

    public Alimento(Integer id, String nome) {
        this.id = id;
        this.alimento = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlimento() {
        return alimento;
    }

    public void setAlimento(String alimento) {
        this.alimento = alimento;
    }

   

    public List<Alimentacao> getAlimentacaoList() {
        return alimentacaoList;
    }

    public void setAlimentacaoList(List<Alimentacao> alimentacaoList) {
        this.alimentacaoList = alimentacaoList;
    }
    
      public boolean podeDeletar() {
        boolean podeDeletar = true;
        try {
            if (alimentacaoList != null && !alimentacaoList.isEmpty()) {
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
        if (!(object instanceof Alimento)) {
            return false;
        }
        Alimento other = (Alimento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.ifnmg.tcc.entidade.Alimento[ id=" + id + " ]";
    }
    
}
