/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leoandresm
 */
@Entity
@Table(name = "solucion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Solucion.findAll", query = "SELECT s FROM Solucion s"),
    @NamedQuery(name = "Solucion.findByIdSolucion", query = "SELECT s FROM Solucion s WHERE s.idSolucion = :idSolucion"),
    @NamedQuery(name = "Solucion.findByIdIncidente", query = "SELECT s FROM Solucion s WHERE s.idIncidente = :idIncidente"),
    @NamedQuery(name = "Solucion.findByFechaSolucion", query = "SELECT s FROM Solucion s WHERE s.fechaSolucion = :fechaSolucion"),
    @NamedQuery(name = "Solucion.findByDescripcionSolucion", query = "SELECT s FROM Solucion s WHERE s.descripcionSolucion = :descripcionSolucion")})
public class Solucion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solucion")
    private Integer idSolucion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_solucion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSolucion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "descripcion_solucion")
    private String descripcionSolucion;
    @JoinColumn(name = "id_incidente", referencedColumnName = "id_incidente")
    @ManyToOne(optional = false)
    private Incidente idIncidente;
    @JoinColumn(name = "id_usuario_tecnico", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuarioTecnico;

    public Solucion() {
    }

    public Solucion(Integer idSolucion) {
        this.idSolucion = idSolucion;
    }

    public Solucion(Integer idSolucion, Date fechaSolucion, String descripcionSolucion) {
        this.idSolucion = idSolucion;
        this.fechaSolucion = fechaSolucion;
        this.descripcionSolucion = descripcionSolucion;
    }

    public Integer getIdSolucion() {
        return idSolucion;
    }

    public void setIdSolucion(Integer idSolucion) {
        this.idSolucion = idSolucion;
    }

    public Date getFechaSolucion() {
        return fechaSolucion;
    }

    public void setFechaSolucion(Date fechaSolucion) {
        this.fechaSolucion = fechaSolucion;
    }

    public String getDescripcionSolucion() {
        return descripcionSolucion;
    }

    public void setDescripcionSolucion(String descripcionSolucion) {
        this.descripcionSolucion = descripcionSolucion;
    }

    public Incidente getIdIncidente() {
        return idIncidente;
    }

    public void setIdIncidente(Incidente idIncidente) {
        this.idIncidente = idIncidente;
    }

    public Usuario getIdUsuarioTecnico() {
        return idUsuarioTecnico;
    }

    public void setIdUsuarioTecnico(Usuario idUsuarioTecnico) {
        this.idUsuarioTecnico = idUsuarioTecnico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSolucion != null ? idSolucion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solucion)) {
            return false;
        }
        Solucion other = (Solucion) object;
        if ((this.idSolucion == null && other.idSolucion != null) || (this.idSolucion != null && !this.idSolucion.equals(other.idSolucion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idSolucion + " " + descripcionSolucion;
    }
    
}
