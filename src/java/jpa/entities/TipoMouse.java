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
@Table(name = "tipo_mouse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoMouse.findAll", query = "SELECT t FROM TipoMouse t"),
    @NamedQuery(name = "TipoMouse.findByIdMouse", query = "SELECT t FROM TipoMouse t WHERE t.idMouse = :idMouse"),
    @NamedQuery(name = "TipoMouse.findByMarca", query = "SELECT t FROM TipoMouse t WHERE t.marca = :marca"),
    @NamedQuery(name = "TipoMouse.findByTecnologia", query = "SELECT t FROM TipoMouse t WHERE t.tecnologia = :tecnologia")})
public class TipoMouse implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mouse")
    private Integer idMouse;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMouse")
    private List<Equipo> equipoList;

    public TipoMouse() {
    }

    public TipoMouse(Integer idMouse) {
        this.idMouse = idMouse;
    }

    public TipoMouse(Integer idMouse, String marca, String tecnologia) {
        this.idMouse = idMouse;
        this.marca = marca;
        this.tecnologia = tecnologia;
    }

    public Integer getIdMouse() {
        return idMouse;
    }

    public void setIdMouse(Integer idMouse) {
        this.idMouse = idMouse;
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
        hash += (idMouse != null ? idMouse.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoMouse)) {
            return false;
        }
        TipoMouse other = (TipoMouse) object;
        if ((this.idMouse == null && other.idMouse != null) || (this.idMouse != null && !this.idMouse.equals(other.idMouse))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return marca + " " + tecnologia ;
    }
    
}
