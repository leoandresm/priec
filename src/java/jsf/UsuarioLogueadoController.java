package jsf;

import jpa.entities.Usuario;
import jsf.util.JsfUtil;
import jpa.sessions.UsuarioFacade;

import java.io.Serializable;
import java.security.Principal;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "usuarioLogueadoController")
@SessionScoped
public class UsuarioLogueadoController implements Serializable {

    private Usuario current;
    private boolean admin = false;
    private boolean almac = false;
    private boolean tecn = false;
    private boolean user = false;
    private boolean estudiante = false;
    @EJB
    private jpa.sessions.UsuarioFacade ejbFacade;

    public UsuarioLogueadoController() {
    }

    public Usuario getSelected() {
        if (current == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context
                    .getExternalContext().getRequest();
            try {
                //Retrieve the Principal
                Principal principal = request.getUserPrincipal();
                current = getFacade().findByIdGenesis(Integer.parseInt(principal.getName()));
            } catch (Exception ex) {
                current = null;
            }
        }
        return current;
    }

    private UsuarioFacade getFacade() {
        return ejbFacade;
    }

    public boolean isAdmin() {
        try {
            return current.getIdRol().getIdRol().equals("ADMIN");
        } catch(Exception Ex) {
            return false;
        }        
    }

    public boolean isAlmac() {
        try {
         return current.getIdRol().getIdRol().equals("ALMAC");
         } catch(Exception Ex) {
            return false;
        }
    }

    public boolean isTecn() {
        try {    
        return current.getIdRol().getIdRol().equals("TECN");
        } catch(Exception Ex) {
            return false;
        }
    }

    public boolean isUser() {
        try {
    return current.getIdRol().getIdRol().equals("USER");
    } catch(Exception Ex) {
            return false;
        }
    }
    
public boolean isEstudiante() {
        try {
        estudiante = current.getIdTipoUsuario().getDescripcion().equals("Estudiante");            
        } catch (Exception Ex) {
            estudiante = false;
        }
        return estudiante;
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("UsuarioUpdated"));
            return "/user/usuario/View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/properties/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
    
    public String updatePassword() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("UsuarioUpdatedPassword"));
            return "/user/usuario/View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/properties/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
   
    @FacesConverter(forClass = Usuario.class)
    public static class UsuarioControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuarioLogueadoController controller = (UsuarioLogueadoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuarioLogueadoController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value);
            return sb.toString();
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Usuario) {
                Usuario o = (Usuario) object;
                return getStringKey(o.getIdUsuario());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Usuario.class.getName());
            }
        }
    }
}
