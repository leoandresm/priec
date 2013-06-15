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
@Table(name = "programa_academico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProgramaAcademico.findAll", query = "SELECT p FROM ProgramaAcademico p"),
    @NamedQuery(name = "ProgramaAcademico.findByIdProgramaAcademico", query = "SELECT p FROM ProgramaAcademico p WHERE p.idProgramaAcademico = :idProgramaAcademico"),
    @NamedQuery(name = "ProgramaAcademico.findByDescripcion", query = "SELECT p FROM ProgramaAcademico p WHERE p.descripcion = :descripcion")})
public class ProgramaAcademico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_programa_academico")
    private Integer idProgramaAcademico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProgramaAcademico")
    private List<Usuario> usuarioList;

    public ProgramaAcademico() {
    }

    public ProgramaAcademico(Integer idProgramaAcademico) {
        this.idProgramaAcademico = idProgramaAcademico;
    }

    public ProgramaAcademico(Integer idProgramaAcademico, String descripcion) {
        this.idProgramaAcademico = idProgramaAcademico;
        this.descripcion = descripcion;
    }

    public Integer getIdProgramaAcademico() {
        return idProgramaAcademico;
    }

    public void setIdProgramaAcademico(Integer idProgramaAcademico) {
        this.idProgramaAcademico = idProgramaAcademico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProgramaAcademico != null ? idProgramaAcademico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProgramaAcademico)) {
            return false;
        }
        ProgramaAcademico other = (ProgramaAcademico) object;
        if ((this.idProgramaAcademico == null && other.idProgramaAcademico != null) || (this.idProgramaAcademico != null && !this.idProgramaAcademico.equals(other.idProgramaAcademico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descripcion ;
    }
    
}
