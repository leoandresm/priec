/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author leoandresm
 */
@Entity
@Table(name = "incidente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Incidente.findAll", query = "SELECT i FROM Incidente i"),
    @NamedQuery(name = "Incidente.findByIdUsuario", query = "SELECT i FROM Incidente i WHERE i.idUsuario = :idUsuario"),
    @NamedQuery(name = "Incidente.findByIdIncidente", query = "SELECT i FROM Incidente i WHERE i.idIncidente = :idIncidente"),
    @NamedQuery(name = "Incidente.findByFechaInicialReporte", query = "SELECT i FROM Incidente i WHERE i.fechaReporte >= :fechaInicialReporte and i.fechaReporte <= :fechaFinalReporte"),
    @NamedQuery(name = "Incidente.findByFechaReporte", query = "SELECT i FROM Incidente i WHERE i.fechaReporte = :fechaReporte"),
    @NamedQuery(name = "Incidente.findByDescripcionIncidente", query = "SELECT i FROM Incidente i WHERE i.descripcionIncidente = :descripcionIncidente")})
public class Incidente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_incidente")
    private Integer idIncidente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_reporte")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReporte;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "descripcion_incidente")
    private String descripcionIncidente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idIncidente")
    private List<Solucion> solucionList;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;
    @JoinColumn(name = "id_tipo_falla", referencedColumnName = "id_tipo_falla")
    @ManyToOne(optional = false)
    private TipoFalla idTipoFalla;
    @JoinColumn(name = "id_equipo", referencedColumnName = "id_equipo")
    @ManyToOne(optional = false)
    private Equipo idEquipo;
    @JoinColumn(name = "id_estado_incidente", referencedColumnName = "id_estado_incidente")
    @ManyToOne(optional = false)
    private EstadoIncidente idEstadoIncidente;

    public Incidente() {
    }

    public Incidente(Integer idIncidente) {
        this.idIncidente = idIncidente;
    }

    public Incidente(Integer idIncidente, Date fechaReporte, String descripcionIncidente) {
        this.idIncidente = idIncidente;
        this.fechaReporte = fechaReporte;
        this.descripcionIncidente = descripcionIncidente;
    }

    public Integer getIdIncidente() {
        return idIncidente;
    }

    public void setIdIncidente(Integer idIncidente) {
        this.idIncidente = idIncidente;
    }

    public Date getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public String getDescripcionIncidente() {
        return descripcionIncidente;
    }

    public void setDescripcionIncidente(String descripcionIncidente) {
        this.descripcionIncidente = descripcionIncidente;
    }

    @XmlTransient
    public List<Solucion> getSolucionList() {
        return solucionList;
    }

    public void setSolucionList(List<Solucion> solucionList) {
        this.solucionList = solucionList;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public TipoFalla getIdTipoFalla() {
        return idTipoFalla;
    }

    public void setIdTipoFalla(TipoFalla idTipoFalla) {
        this.idTipoFalla = idTipoFalla;
    }

    public Equipo getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Equipo idEquipo) {
        this.idEquipo = idEquipo;
    }

    public EstadoIncidente getIdEstadoIncidente() {
        return idEstadoIncidente;
    }

    public void setIdEstadoIncidente(EstadoIncidente idEstadoIncidente) {
        this.idEstadoIncidente = idEstadoIncidente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIncidente != null ? idIncidente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Incidente)) {
            return false;
        }
        Incidente other = (Incidente) object;
        if ((this.idIncidente == null && other.idIncidente != null) || (this.idIncidente != null && !this.idIncidente.equals(other.idIncidente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idIncidente + " " + idTipoFalla.getDescripcion() + " " + descripcionIncidente;
    }
}
