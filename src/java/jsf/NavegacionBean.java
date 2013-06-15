/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class NavegacionBean {
    private String page = "/equipo/List";
    
    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

}
