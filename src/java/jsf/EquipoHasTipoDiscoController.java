package jsf;

import jpa.entities.EquipoHasTipoDisco;
import jsf.util.JsfUtil;
import jsf.util.PaginationHelper;
import jpa.sessions.EquipoHasTipoDiscoFacade;

import java.io.Serializable;
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

@ManagedBean(name = "equipoHasTipoDiscoController")
@SessionScoped
public class EquipoHasTipoDiscoController implements Serializable {

    private EquipoHasTipoDisco current;
    private DataModel items = null;
    @EJB
    private jpa.sessions.EquipoHasTipoDiscoFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public EquipoHasTipoDiscoController() {
    }

    public EquipoHasTipoDisco getSelected() {
        if (current == null) {
            current = new EquipoHasTipoDisco();
            current.setEquipoHasTipoDiscoPK(new jpa.entities.EquipoHasTipoDiscoPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private EquipoHasTipoDiscoFacade getFacade() {
        return ejbFacade;
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
        return "List?faces-redirect=true";
    }

    public String prepareView() {
        current = (EquipoHasTipoDisco) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View?faces-redirect=true";
    }

    public String prepareCreate() {
        current = new EquipoHasTipoDisco();
        current.setEquipoHasTipoDiscoPK(new jpa.entities.EquipoHasTipoDiscoPK());
        selectedItemIndex = -1;
        return "Create?faces-redirect=true";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("EquipoHasTipoDiscoCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/properties/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (EquipoHasTipoDisco) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit?faces-redirect=true";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("EquipoHasTipoDiscoUpdated"));
            return "View?faces-redirect=true";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/properties/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (EquipoHasTipoDisco) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List?faces-redirect=true";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View?faces-redirect=true";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List?faces-redirect=true";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("EquipoHasTipoDiscoDeleted"));
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
        return "List";
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

    @FacesConverter(forClass = EquipoHasTipoDisco.class)
    public static class EquipoHasTipoDiscoControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EquipoHasTipoDiscoController controller = (EquipoHasTipoDiscoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "equipoHasTipoDiscoController");
            return controller.ejbFacade.find(getKey(value));
        }

        jpa.entities.EquipoHasTipoDiscoPK getKey(String value) {
            jpa.entities.EquipoHasTipoDiscoPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new jpa.entities.EquipoHasTipoDiscoPK();
            key.setEquipoIdEquipo(Integer.parseInt(values[0]));
            key.setTipoDiscoIdDisco(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(jpa.entities.EquipoHasTipoDiscoPK value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value.getEquipoIdEquipo());
            sb.append(SEPARATOR);
            sb.append(value.getTipoDiscoIdDisco());
            return sb.toString();
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof EquipoHasTipoDisco) {
                EquipoHasTipoDisco o = (EquipoHasTipoDisco) object;
                return getStringKey(o.getEquipoHasTipoDiscoPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + EquipoHasTipoDisco.class.getName());
            }
        }
    }
}
