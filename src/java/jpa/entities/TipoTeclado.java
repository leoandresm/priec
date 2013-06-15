/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author leoandresm
 */
@Entity
@Table(name = "tipo_teclado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoTeclado.findAll", query = "SELECT t FROM TipoTeclado t"),
    @NamedQuery(name = "TipoTeclado.findByIdTeclado", query = "SELECT t FROM TipoTeclado t WHERE t.idTeclado = :idTeclado"),
    @NamedQuery(name = "TipoTeclado.findByMarca", query = "SELECT t FROM TipoTeclado t WHERE t.marca = :marca"),
    @NamedQuery(name = "TipoTeclado.findByTecnologia", query = "SELECT t FROM TipoTeclado t WHERE t.tecnologia = :tecnologia")})
public class TipoTeclado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_teclado")
    private Integer idTeclado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "marca")
    private String marca;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "tecnologia")
    private String tecnologia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTeclado")
    private List<Equipo> equipoList;

    public TipoTeclado() {
    }

    public TipoTeclado(Integer idTeclado) {
        this.idTeclado = idTeclado;
    }

    public TipoTeclado(Integer idTeclado, String marca, String tecnologia) {
        this.idTeclado = idTeclado;
        this.marca = marca;
        this.tecnologia = tecnologia;
    }

    public Integer getIdTeclado() {
        return idTeclado;
    }

    public void setIdTeclado(Integer idTeclado) {
        this.idTeclado = idTeclado;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    @XmlTransient
    public List<Equipo> getEquipoList() {
        return equipoList;
    }

    public void setEquipoList(List<Equipo> equipoList) {
        this.equipoList = equipoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTeclado != null ? idTeclado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoTeclado)) {
            return false;
        }
        TipoTeclado other = (TipoTeclado) object;
        if ((this.idTeclado == null && other.idTeclado != null) || (this.idTeclado != null && !this.idTeclado.equals(other.idTeclado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return marca + " " + tecnologia ;
    }
    
}
