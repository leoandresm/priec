package jsf;

import jpa.entities.Usuario;
import jsf.util.JsfUtil;
import jsf.util.PaginationHelper;
import jpa.sessions.UsuarioFacade;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import jpa.entities.Roles;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

@ManagedBean(name = "usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

    private Usuario current;
    private DataModel items = null;
    private boolean estudiante = false;
    private LazyDataModel<Usuario> lazyModel;
    @EJB
    private jpa.sessions.UsuarioFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public UsuarioController() {
    }

    public LazyDataModel<Usuario> getLazyModel() {
        if (lazyModel == null) {
            lazyModel = new LazyDataModel<Usuario>() {
                @Override
                public List<Usuario> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                    int start = first;
                    int end = first + pageSize;
                    List<Usuario> usuarioList = getFacade().findAll();
                    List<Usuario> data = new ArrayList<Usuario>();
                           
                    //filter  
                    for (Usuario usuario : usuarioList) {
                        boolean match = true;

                        for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                            try {
                                String filterProperty = it.next();
                                String filterValue = filters.get(filterProperty).toLowerCase();
                                Field field = usuario.getClass().getDeclaredField(filterProperty);
                                field.setAccessible(true);
                                String fieldValue = String.valueOf(field.get(usuario)).toLowerCase();

                                if (filterValue == null || fieldValue.startsWith(filterValue)) {
                                    match = true;
                                } else {
                                    match = false;
                                    break;
                                }
                            } catch (Exception e) {
                                match = false;
                            }
                        }

                        if (match) {
                            data.add(usuario);
                        }
                    }

                    //rowCount  
                    int dataSize = data.size();
                    this.setRowCount(dataSize);

                    //paginate  
                    if (dataSize > pageSize) {
                        try {
                            return data.subList(start, end);
                        } catch (IndexOutOfBoundsException e) {
                            return data.subList(start, first + (dataSize % pageSize));
                        }
                    } else {
                        return data;
                    }
                }

                @Override
                public Object getRowKey(Usuario user) {
                    return user.getIdUsuario().toString();
                }

                @Override
                public Usuario getRowData(String rowKey) {
                    for (Usuario usuario : lazyModel) {
                        if (usuario.getIdUsuario().toString().equals(rowKey)) {
                            return usuario;
                        }
                    }
                    return null;
                }
            };
        }

        return lazyModel;
    }

    public Usuario getSelected() {
        if (current == null) {
            current = new Usuario();
            selectedItemIndex = -1;
        }
        return current;
    }

    private UsuarioFacade getFacade() {
        return ejbFacade;
    }

    public boolean isEstudiante() {
        try {
            estudiante = current.getIdTipoUsuario().getDescripcion().equals("Estudiante");            
        } catch (Exception Ex) {
            estudiante = false;
        }
        return estudiante;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(15) {
                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "/admin/usuario/List";
    }

    public String prepareView() {
        current = (Usuario) getLazyModel().getRowData();
        selectedItemIndex = lazyModel.getRowIndex();
        return "/admin/usuario/View";
    }

    public String prepareCreate() {
        current = new Usuario();
        selectedItemIndex = -1;
        return "/usuario/Create";
    }

    public String create() {
        try {
            Roles rol = new Roles("USER");
            current.setIdRol(rol);            
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("UsuarioCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/properties/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Usuario) getLazyModel().getRowData();
        selectedItemIndex = lazyModel.getRowIndex();
        return "/admin/usuario/Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("UsuarioUpdated"));
            return "/admin/usuario/View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/properties/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String updatePassword() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("UsuarioUpdatedPassword"));
            return "/admin/usuario/View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/properties/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Usuario) getLazyModel().getRowData();
        selectedItemIndex = lazyModel.getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "/admin/usuario/List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "/admin/usuario/View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "/admin/usuario/List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("UsuarioDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/properties/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "/List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    @FacesConverter(forClass = Usuario.class)
    public static class UsuarioControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuarioController controller = (UsuarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuarioController");
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