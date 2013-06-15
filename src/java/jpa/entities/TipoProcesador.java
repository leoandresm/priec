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
@Table(name = "tipo_procesador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoProcesador.findAll", query = "SELECT t FROM TipoProcesador t"),
    @NamedQuery(name = "TipoProcesador.findByIdProcesador", query = "SELECT t FROM TipoProcesador t WHERE t.idProcesador = :idProcesador"),
    @NamedQuery(name = "TipoProcesador.findByMarca", query = "SELECT t FROM TipoProcesador t WHERE t.marca = :marca"),
    @NamedQuery(name = "TipoProcesador.findByModelo", query = "SELECT t FROM TipoProcesador t WHERE t.modelo = :modelo"),
    @NamedQuery(name = "TipoProcesador.findByVelocidad", query = "SELECT t FROM TipoProcesador t WHERE t.velocidad = :velocidad")})
public class TipoProcesador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_procesador")
    private Integer idProcesador;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProcesador")
    private List<Equipo> equipoList;

    public TipoProcesador() {
    }

    public TipoProcesador(Integer idProcesador) {
        this.idProcesador = idProcesador;
    }

    public TipoProcesador(Integer idProcesador, String marca, String modelo, int velocidad) {
        this.idProcesador = idProcesador;
        this.marca = marca;
        this.modelo = modelo;
        this.velocidad = velocidad;
    }

    public Integer getIdProcesador() {
        return idProcesador;
    }

    public void setIdProcesador(Integer idProcesador) {
        this.idProcesador = idProcesador;
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
        hash += (idProcesador != null ? idProcesador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoProcesador)) {
            return false;
        }
        TipoProcesador other = (TipoProcesador) object;
        if ((this.idProcesador == null && other.idProcesador != null) || (this.idProcesador != null && !this.idProcesador.equals(other.idProcesador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return marca + " " + modelo + " " + velocidad ;
    }
    
}
