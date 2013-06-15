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
@Table(name = "tipo_disco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDisco.findAll", query = "SELECT t FROM TipoDisco t"),
    @NamedQuery(name = "TipoDisco.findByIdDisco", query = "SELECT t FROM TipoDisco t WHERE t.idDisco = :idDisco"),
    @NamedQuery(name = "TipoDisco.findByMarca", query = "SELECT t FROM TipoDisco t WHERE t.marca = :marca"),
    @NamedQuery(name = "TipoDisco.findByModelo", query = "SELECT t FROM TipoDisco t WHERE t.modelo = :modelo"),
    @NamedQuery(name = "TipoDisco.findByVelocidad", query = "SELECT t FROM TipoDisco t WHERE t.velocidad = :velocidad"),
    @NamedQuery(name = "TipoDisco.findByCapacidad", query = "SELECT t FROM TipoDisco t WHERE t.capacidad = :capacidad"),
    @NamedQuery(name = "TipoDisco.findByTecnologia", query = "SELECT t FROM TipoDisco t WHERE t.tecnologia = :tecnologia")})
public class TipoDisco implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_disco")
    private Integer idDisco;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "marca")
    private String marca;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "modelo")
    private String modelo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "velocidad")
    private int velocidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "capacidad")
    private int capacidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "tecnologia")
    private String tecnologia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoDisco")
    private List<EquipoHasTipoDisco> equipoHasTipoDiscoList;

    public TipoDisco() {
    }

    public TipoDisco(Integer idDisco) {
        this.idDisco = idDisco;
    }

    public TipoDisco(Integer idDisco, String marca, String modelo, int velocidad, int capacidad, String tecnologia) {
        this.idDisco = idDisco;
        this.marca = marca;
        this.modelo = modelo;
        this.velocidad = velocidad;
        this.capacidad = capacidad;
        this.tecnologia = tecnologia;
    }

    public Integer getIdDisco() {
        return idDisco;
    }

    public void setIdDisco(Integer idDisco) {
        this.idDisco = idDisco;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    @XmlTransient
    public List<EquipoHasTipoDisco> getEquipoHasTipoDiscoList() {
        return equipoHasTipoDiscoList;
    }

    public void setEquipoHasTipoDiscoList(List<EquipoHasTipoDisco> equipoHasTipoDiscoList) {
        this.equipoHasTipoDiscoList = equipoHasTipoDiscoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDisco != null ? idDisco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDisco)) {
            return false;
        }
        TipoDisco other = (TipoDisco) object;
        if ((this.idDisco == null && other.idDisco != null) || (this.idDisco != null && !this.idDisco.equals(other.idDisco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return marca + " " + modelo + " " + capacidad + " " + tecnologia  ;
    }
    
}
