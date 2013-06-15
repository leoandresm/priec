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
@Table(name = "tipo_monitor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoMonitor.findAll", query = "SELECT t FROM TipoMonitor t"),
    @NamedQuery(name = "TipoMonitor.findByIdMonitor", query = "SELECT t FROM TipoMonitor t WHERE t.idMonitor = :idMonitor"),
    @NamedQuery(name = "TipoMonitor.findByMarca", query = "SELECT t FROM TipoMonitor t WHERE t.marca = :marca"),
    @NamedQuery(name = "TipoMonitor.findByModelo", query = "SELECT t FROM TipoMonitor t WHERE t.modelo = :modelo"),
    @NamedQuery(name = "TipoMonitor.findByTecnologia", query = "SELECT t FROM TipoMonitor t WHERE t.tecnologia = :tecnologia"),
    @NamedQuery(name = "TipoMonitor.findByTamanio", query = "SELECT t FROM TipoMonitor t WHERE t.tamanio = :tamanio")})
public class TipoMonitor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_monitor")
    private Integer idMonitor;
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
    @Size(min = 1, max = 15)
    @Column(name = "tecnologia")
    private String tecnologia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "tamanio")
    private String tamanio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMonitor")
    private List<Equipo> equipoList;

    public TipoMonitor() {
    }

    public TipoMonitor(Integer idMonitor) {
        this.idMonitor = idMonitor;
    }

    public TipoMonitor(Integer idMonitor, String marca, String modelo, String tecnologia, String tamanio) {
        this.idMonitor = idMonitor;
        this.marca = marca;
        this.modelo = modelo;
        this.tecnologia = tecnologia;
        this.tamanio = tamanio;
    }

    public Integer getIdMonitor() {
        return idMonitor;
    }

    public void setIdMonitor(Integer idMonitor) {
        this.idMonitor = idMonitor;
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

    public String getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
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
        hash += (idMonitor != null ? idMonitor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoMonitor)) {
            return false;
        }
        TipoMonitor other = (TipoMonitor) object;
        if ((this.idMonitor == null && other.idMonitor != null) || (this.idMonitor != null && !this.idMonitor.equals(other.idMonitor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return marca + " " + modelo + " " + tamanio + " " + tecnologia ;
    }
    
}
