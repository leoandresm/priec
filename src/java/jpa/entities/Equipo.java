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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author leoandresm
 */
@Entity
@Table(name = "equipo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipo.findAll", query = "SELECT e FROM Equipo e"),
    @NamedQuery(name = "Equipo.findByIdEquipo", query = "SELECT e FROM Equipo e WHERE e.idEquipo = :idEquipo"),
    @NamedQuery(name = "Equipo.findByIdSala", query = "SELECT e FROM Equipo e WHERE e.idSala = :idSala"),
    @NamedQuery(name = "Equipo.findByModelo", query = "SELECT e FROM Equipo e WHERE e.modelo = :modelo"),
    @NamedQuery(name = "Equipo.findBySerial", query = "SELECT e FROM Equipo e WHERE e.serial = :serial"),
    @NamedQuery(name = "Equipo.findByObservaciones", query = "SELECT e FROM Equipo e WHERE e.observaciones = :observaciones")})
public class Equipo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_equipo")
    private Integer idEquipo;
    @Size(max = 20)
    @Column(name = "modelo")
    private String modelo;
    @Size(max = 45)
    @Column(name = "serial")
    private String serial;
    @Size(max = 255)
    @Column(name = "observaciones")
    private String observaciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipo")
    private List<EquipoHasTipoMemoria> equipoHasTipoMemoriaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipo")
    private List<EquipoHasTipoDisco> equipoHasTipoDiscoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEquipo")
    private List<Incidente> incidenteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipo")
    private List<EquipoHasTipoUnidadOptica> equipoHasTipoUnidadOpticaList;
    @JoinColumn(name = "id_teclado", referencedColumnName = "id_teclado")
    @ManyToOne(optional = true)
    private TipoTeclado idTeclado;
    @JoinColumn(name = "id_mouse", referencedColumnName = "id_mouse")
    @ManyToOne(optional = true)
    private TipoMouse idMouse;
    @JoinColumn(name = "id_tipo_equipo", referencedColumnName = "id_tipo_equipo")
    @ManyToOne(optional = false)
    private TipoEquipo idTipoEquipo;
    @JoinColumn(name = "id_sala", referencedColumnName = "id_sala")
    @ManyToOne(optional = false)
    private Sala idSala;
    @JoinColumn(name = "id_procesador", referencedColumnName = "id_procesador")
    @ManyToOne(optional = false)
    private TipoProcesador idProcesador;
    @JoinColumn(name = "id_monitor", referencedColumnName = "id_monitor")
    @ManyToOne(optional = true)
    private TipoMonitor idMonitor;
    @JoinColumn(name = "id_marca_equipo", referencedColumnName = "id_marca_equipo")
    @ManyToOne(optional = false)
    private MarcaEquipo idMarcaEquipo;
    @JoinColumn(name = "id_estado_equipo", referencedColumnName = "id_estado_equipo")
    @ManyToOne(optional = false)
    private EstadoEquipo idEstadoEquipo;

    public Equipo() {
    }

    public Equipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Integer getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @XmlTransient
    public List<EquipoHasTipoMemoria> getEquipoHasTipoMemoriaList() {
        return equipoHasTipoMemoriaList;
    }

    public void setEquipoHasTipoMemoriaList(List<EquipoHasTipoMemoria> equipoHasTipoMemoriaList) {
        this.equipoHasTipoMemoriaList = equipoHasTipoMemoriaList;
    }

    @XmlTransient
    public List<EquipoHasTipoDisco> getEquipoHasTipoDiscoList() {
        return equipoHasTipoDiscoList;
    }

    public void setEquipoHasTipoDiscoList(List<EquipoHasTipoDisco> equipoHasTipoDiscoList) {
        this.equipoHasTipoDiscoList = equipoHasTipoDiscoList;
    }

    @XmlTransient
    public List<Incidente> getIncidenteList() {
        return incidenteList;
    }

    public void setIncidenteList(List<Incidente> incidenteList) {
        this.incidenteList = incidenteList;
    }

    @XmlTransient
    public List<EquipoHasTipoUnidadOptica> getEquipoHasTipoUnidadOpticaList() {
        return equipoHasTipoUnidadOpticaList;
    }

    public void setEquipoHasTipoUnidadOpticaList(List<EquipoHasTipoUnidadOptica> equipoHasTipoUnidadOpticaList) {
        this.equipoHasTipoUnidadOpticaList = equipoHasTipoUnidadOpticaList;
    }

    public TipoTeclado getIdTeclado() {
        return idTeclado;
    }

    public void setIdTeclado(TipoTeclado idTeclado) {
        this.idTeclado = idTeclado;
    }

    public TipoMouse getIdMouse() {
        return idMouse;
    }

    public void setIdMouse(TipoMouse idMouse) {
        this.idMouse = idMouse;
    }

    public TipoEquipo getIdTipoEquipo() {
        return idTipoEquipo;
    }

    public void setIdTipoEquipo(TipoEquipo idTipoEquipo) {
        this.idTipoEquipo = idTipoEquipo;
    }

    public Sala getIdSala() {
        return idSala;
    }

    public void setIdSala(Sala idSala) {
        this.idSala = idSala;
    }

    public TipoProcesador getIdProcesador() {
        return idProcesador;
    }

    public void setIdProcesador(TipoProcesador idProcesador) {
        this.idProcesador = idProcesador;
    }

    public TipoMonitor getIdMonitor() {
        return idMonitor;
    }

    public void setIdMonitor(TipoMonitor idMonitor) {
        this.idMonitor = idMonitor;
    }

    public MarcaEquipo getIdMarcaEquipo() {
        return idMarcaEquipo;
    }

    public void setIdMarcaEquipo(MarcaEquipo idMarcaEquipo) {
        this.idMarcaEquipo = idMarcaEquipo;
    }

    public EstadoEquipo getIdEstadoEquipo() {
        return idEstadoEquipo;
    }

    public void setIdEstadoEquipo(EstadoEquipo idEstadoEquipo) {
        this.idEstadoEquipo = idEstadoEquipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEquipo != null ? idEquipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipo)) {
            return false;
        }
        Equipo other = (Equipo) object;
        if ((this.idEquipo == null && other.idEquipo != null) || (this.idEquipo != null && !this.idEquipo.equals(other.idEquipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idMarcaEquipo.getDescripcion() + " " + modelo + " S/N: " + serial;
    }
    
}
