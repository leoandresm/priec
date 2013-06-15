/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import jsf.util.DigestUtil;

/**
 *
 * @author leoandresm
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "Usuario.findByIdGenesisUniminuto", query = "SELECT u FROM Usuario u WHERE u.idGenesisUniminuto = :idGenesisUniminuto"),
    @NamedQuery(name = "Usuario.findByNombres", query = "SELECT u FROM Usuario u WHERE u.nombres = :nombres"),
    @NamedQuery(name = "Usuario.findByPrimeraPellido", query = "SELECT u FROM Usuario u WHERE u.primeraPellido = :primeraPellido"),
    @NamedQuery(name = "Usuario.findBySegundoApellido", query = "SELECT u FROM Usuario u WHERE u.segundoApellido = :segundoApellido"),
    @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email"),
    @NamedQuery(name = "Usuario.findByTelefono", query = "SELECT u FROM Usuario u WHERE u.telefono = :telefono"),
    @NamedQuery(name = "Usuario.findByPassword", query = "SELECT u FROM Usuario u WHERE u.password = :password"),
    @NamedQuery(name = "Usuario.findByEstado", query = "SELECT u FROM Usuario u WHERE u.estado = :estado")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Min(1)
    @Max(999999999)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_genesis_uniminuto")
    private int idGenesisUniminuto;
    @Pattern(regexp = "^[a-zA-Z áéíóúAÉÍÓÚÑñ]+[a-zA-Z áéíóúAÉÍÓÚÑñ\\s]+[a-zA-Z áéíóúAÉÍÓÚÑñ]$", message = "Solo puedes utilizar letras y espacios")
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nombres")
    private String nombres;
    @Pattern(regexp = "^[a-zA-Z áéíóúAÉÍÓÚÑñ]+[a-zA-Z áéíóúAÉÍÓÚÑñ\\s]+[a-zA-Z áéíóúAÉÍÓÚÑñ]$", message = "Solo puedes utilizar letras y espacios")
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "primera_pellido")
    private String primeraPellido;
    //@Pattern(regexp = "^[a-zA-Z áéíóúAÉÍÓÚÑñ]+[a-zA-Z áéíóúAÉÍÓÚÑñ\\s]+[a-zA-Z áéíóúAÉÍÓÚÑñ]$", message = "Solo puedes utilizar letras y espacios")
    @Size(max = 20)
    @Column(name = "segundo_apellido")
    private String segundoApellido;
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@uniminuto+\\.edu*(\\.co)?", message = "Solo se admite E-mail de Uniminuto")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuarioTecnico")
    private List<Solucion> solucionList;
    @JoinColumn(name = "id_tipo_usuario", referencedColumnName = "id_tipo_usuario")
    @ManyToOne(optional = false)
    private TipoUsuario idTipoUsuario;
    @JoinColumn(name = "id_sexo", referencedColumnName = "id_sexo")
    @ManyToOne(optional = false)
    private Sexo idSexo;
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    @ManyToOne(optional = true)
    private Roles idRol;
    @JoinColumn(name = "id_programa_academico", referencedColumnName = "id_programa_academico")
    @ManyToOne(optional = false)
    private ProgramaAcademico idProgramaAcademico;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<Incidente> incidenteList;

    public Usuario() {
    }
    
    public Usuario(Integer idGenesisUniminuto) {
        this.idGenesisUniminuto = idGenesisUniminuto;
    }

    public Usuario(Integer idUsuario, int idGenesisUniminuto, String nombres, String primeraPellido, String email, String telefono, String password, boolean estado) {
        this.idUsuario = idUsuario;
        this.idGenesisUniminuto = idGenesisUniminuto;
        this.nombres = nombres;
        this.primeraPellido = primeraPellido;
        this.email = email;
        this.telefono = telefono;
        this.password = password;
        this.estado = estado;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdGenesisUniminuto() {
        return idGenesisUniminuto;
    }

    public void setIdGenesisUniminuto(int idGenesisUniminuto) {
        this.idGenesisUniminuto = idGenesisUniminuto;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPrimeraPellido() {
        return primeraPellido;
    }

    public void setPrimeraPellido(String primeraPellido) {
        this.primeraPellido = primeraPellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        try {
            this.password = DigestUtil.generateDigest(password);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<Solucion> getSolucionList() {
        return solucionList;
    }

    public void setSolucionList(List<Solucion> solucionList) {
        this.solucionList = solucionList;
    }

    public TipoUsuario getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(TipoUsuario idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public Sexo getIdSexo() {
        return idSexo;
    }

    public void setIdSexo(Sexo idSexo) {
        this.idSexo = idSexo;
    }

    public Roles getIdRol() {
        return idRol;
    }

    public void setIdRol(Roles idRol) {
        this.idRol = idRol;
    }

    public ProgramaAcademico getIdProgramaAcademico() {
        return idProgramaAcademico;
    }

    public void setIdProgramaAcademico(ProgramaAcademico idProgramaAcademico) {
        this.idProgramaAcademico = idProgramaAcademico;
    }

    @XmlTransient
    public List<Incidente> getIncidenteList() {
        return incidenteList;
    }

    public void setIncidenteList(List<Incidente> incidenteList) {
        this.incidenteList = incidenteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idGenesisUniminuto + " " + nombres + " " + primeraPellido + " " + segundoApellido;
    }
}
