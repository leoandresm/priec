/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import java.io.Serializable;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private static final Logger log = Logger.getLogger(LoginBean.class.getName());
    private String username;
    private String password;
    private String menu;
    private boolean regresarPrincipal = true;

    public LoginBean() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRegresarPrincipal() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context
                .getExternalContext().getRequest();

        try {
            if (request.isUserInRole("ADMIN") || request.isUserInRole("TECN")
                    || request.isUserInRole("ALMAC") || request.isUserInRole("USER")) {
                regresarPrincipal = false;
            }
            return regresarPrincipal;
        } catch (Exception ex) {
            return true;
        }
    }

    public String getMenu() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context
                .getExternalContext().getRequest();

        try {
            if (request.isUserInRole("ADMIN") || request.isUserInRole("TECN")
                    || request.isUserInRole("ALMAC") || request.isUserInRole("USER")) {
                menu = "/user";
            } else {
                menu = "";
            }
            return menu;
        } catch (Exception ex) {
            return "";
        }
    }

    public String login() {
        String message = "";
        String pagina;
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {

            //Login via the Servlet Context
            request.login(username, password);

            //Retrieve the Principal
            Principal principal = request.getUserPrincipal();

            //Display a message based on the User role
            if (request.isUserInRole("ADMIN")) {
                menu = "/admin";
                message = "Usuario : " + principal.getName() + " Usted es un Usuario Administrador";
                pagina = "/user/bienvenida?faces-redirect=true";
            } else if (request.isUserInRole("TECN")) {
                menu = "/tecn";
                message = "Usuario : " + principal.getName() + " Usted es un Usuario Técnico";
                pagina = "/user/bienvenida?faces-redirect=true";
            } else if (request.isUserInRole("ALMAC")) {
                menu = "/almac";
                message = "Usuario : " + principal.getName() + " Usted es un Usuario Almacenista";
                pagina = "/user/bienvenida?faces-redirect=true";
            } else if (request.isUserInRole("USER")) {
                menu = "/user";
                message = "Usuario : " + principal.getName() + " Usted es un Usuario Estandar";
                pagina = "/user/bienvenida?faces-redirect=true";
            } else {
                limpiar();
                pagina = "index?faces-redirect=true";
                logout();
            }
            username = "";
            password = "";
            //Add the welcome message to the faces context
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
            return pagina;
        } catch (ServletException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o Contraseña Invalida", null));
            e.printStackTrace();
            return "errorLogin";
        }
    }

    public String logout() {
        String result = "/index?faces-redirect=true";

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            request.logout();
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.invalidate();
            limpiar();
        } catch (ServletException e) {
            log.log(Level.SEVERE, "Failed to logout user!", e);
            result = "/index?faces-redirect=true";
        }

        return result;
    }

    private void limpiar() {
        menu = "";
        username = "";
        password = "";
        regresarPrincipal = true;
    }
}
