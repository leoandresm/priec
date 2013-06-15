package jsf;

import jpa.entities.Incidente;
import jsf.util.JsfUtil;
import jsf.util.PaginationHelper;
import jpa.sessions.IncidenteFacade;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
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
import javax.servlet.http.HttpServletRequest;
import jpa.entities.EstadoIncidente;
import jpa.entities.Solucion;
import jpa.entities.Usuario;
import jpa.sessions.SolucionFacade;
import jpa.sessions.UsuarioFacade;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

@ManagedBean(name = "incidenteController")
@SessionScoped
public class IncidenteController implements Serializable {
    private Incidente current;
    private Solucion currentSol;
    private Usuario currentUser;
    private DataModel items = null;
    private LazyDataModel<Incidente> lazyModelByIdUsuario;
    private LazyDataModel<Solucion> lazyModelByIdIncidente;
    private LazyDataModel<Incidente> lazyModel;
    @EJB
    private jpa.sessions.IncidenteFacade ejbFacade;
    @EJB
    private jpa.sessions.UsuarioFacade ejbUsuarioFacade;
    @EJB
    private jpa.sessions.SolucionFacade ejbSolucionFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private int selectedItemIndexSolucion;

    public IncidenteController() {
    }

    public LazyDataModel<Incidente> getLazyModelUsuario() {
        if (lazyModelByIdUsuario == null) {
            lazyModelByIdUsuario = new LazyDataModel<Incidente>() {
                @Override
                public List<Incidente> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                    int start = first;
                    int end = first + pageSize;
                    List<Incidente> incidenteList = getFacade().findAllByIdUsuario(getUsuarioSelected());
                   
                    List<Incidente> data = new ArrayList<Incidente>();
                           
                    //filter  
                    for (Incidente incidente : incidenteList) {
                        boolean match = true;

                        for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                            try {
                                String filterProperty = it.next();
                                String filterValue = filters.get(filterProperty).toLowerCase();
                                Field field = incidente.getClass().getDeclaredField(filterProperty);
                                field.setAccessible(true);
                                String fieldValue = String.valueOf(field.get(incidente)).toLowerCase();

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
                            data.add(incidente);
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
                public Object getRowKey(Incidente incidente) {
                    return incidente.getIdIncidente().toString();
                }

                @Override
                public Incidente getRowData(String rowKey) {
                    for (Incidente incidente : lazyModelByIdUsuario) {
                        if (incidente.getIdIncidente().toString().equals(rowKey)) {
                            return incidente;
                        }
                    }
                    return null;
                }
            };
        }

        return lazyModelByIdUsuario;
    }

    public LazyDataModel<Solucion> getLazyModelSolucion() {
        if (lazyModelByIdIncidente == null) {
            lazyModelByIdIncidente = new LazyDataModel<Solucion>() {
                @Override
                public List<Solucion> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                    int start = first;
                    int end = first + pageSize;
                    List<Solucion> solucionList = getSolucionFacade().findRangeByIdIncidente(getSelected(), new int[]{start, end});
                   
                    //rowCount  
                    int dataSize = solucionList.size();
                    this.setRowCount(dataSize);

                    return solucionList;
                }

                @Override
                public Object getRowKey(Solucion solucion) {
                    return solucion.getIdIncidente().toString();
                }

                @Override
                public Solucion getRowData(String rowKey) {
                    for (Solucion solucion : lazyModelByIdIncidente) {
                        if (solucion.getIdIncidente().toString().equals(rowKey)) {
                            return solucion;
                        }
                    }
                    return null;
                }
            };
        }

        return lazyModelByIdIncidente;
    }

    public LazyDataModel<Incidente> getLazyModel() {
        if (lazyModel == null) {
            lazyModel = new LazyDataModel<Incidente>() {
                @Override
                public List<Incidente> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                    int start = first;
                    int end = first + pageSize;
                    List<Incidente> incidenteList = getFacade().findAll();
                    List<Incidente> data = new ArrayList<Incidente>();
                           
                    //filter  
                    for (Incidente incidente : incidenteList) {
                        boolean match = true;

                        for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                            try {
                                String filterProperty = it.next();
                                String filterValue = filters.get(filterProperty).toLowerCase();
                                Field field = incidente.getClass().getDeclaredField(filterProperty);
                                field.setAccessible(true);
                                String fieldValue = String.valueOf(field.get(incidente)).toLowerCase();

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
                            data.add(incidente);
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
                public Object getRowKey(Incidente incidente) {
                    return incidente.getIdIncidente().toString();
                }

                @Override
                public Incidente getRowData(String rowKey) {
                    for (Incidente incidente : lazyModel) {
                        if (incidente.getIdIncidente().toString().equals(rowKey)) {
                            return incidente;
                        }
                    }
                    return null;
                }
            };
        }

        return lazyModel;
    }

    public Incidente getSelected() {
        if (current == null) {
            current = new Incidente();
            selectedItemIndex = -1;
            current.setFechaReporte(new Date());
            current.setIdUsuario(getUsuarioSelected());
            EstadoIncidente estIncidente = new EstadoIncidente(1);
            current.setIdEstadoIncidente(estIncidente);
        }
        return current;
    }

    public Usuario getUsuarioSelected() {
        if (currentUser == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context
                    .getExternalContext().getRequest();
            try {
                //Retrieve the Principal
                Principal principal = request.getUserPrincipal();
                currentUser = getUsuarioFacade().findByIdGenesis(Integer.parseInt(principal.getName()));
            } catch (Exception ex) {
                current = null;
            }
        }
        return currentUser;
    }

    public Solucion getSolucionSelected() {
        if (currentSol == null) {
            currentSol = new Solucion();
            currentSol.setFechaSolucion(new Date());
            currentSol.setIdIncidente(getSelected());
            currentSol.setIdUsuarioTecnico(getUsuarioSelected());
            selectedItemIndexSolucion = -1;
        }
        return currentSol;
    }

    private IncidenteFacade getFacade() {
        return ejbFacade;
    }

    private UsuarioFacade getUsuarioFacade() {
        return ejbUsuarioFacade;
    }

    private SolucionFacade getSolucionFacade() {
        return ejbSolucionFacade;
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

    public String prepareListUser() {
        recreateModel();
        return "/user/incidente/List";
    }

    public String prepareList() {
        recreateModel();
        return "/tecn/incidente/List";
    }

    public String prepareViewUser() {
        current = (Incidente) getLazyModelUsuario().getRowData();
        selectedItemIndex = lazyModelByIdUsuario.getRowIndex();
        return "/user/incidente/View";
    }

    public String prepareView() {
        current = (Incidente) getLazyModel().getRowData();
        selectedItemIndex = lazyModel.getRowIndex();
        return "/user/incidente/View";
    }

    public String prepareCreate() {
        current = new Incidente();
        selectedItemIndex = -1;
        current.setFechaReporte(new Date());
        current.setIdUsuario(getUsuarioSelected());
        EstadoIncidente estIncidente = new EstadoIncidente(1);
        current.setIdEstadoIncidente(estIncidente);
        return "/user/incidente/Create";
    }

    public void prepareCreateSolucion() {
        currentSol = new Solucion();
        currentSol.setFechaSolucion(new Date());
        currentSol.setIdIncidente(getSelected());
        currentSol.setIdUsuarioTecnico(getUsuarioSelected());
        selectedItemIndexSolucion = -1;
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("IncidenteCreated"));
            return "/user/incidente/View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/properties/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String createSolucion() {
        try {
            getSolucionFacade().create(currentSol);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("SolucionCreated"));
            return null;
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/properties/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEditUser() {
        current = (Incidente) getLazyModelUsuario().getRowData();
        selectedItemIndex = lazyModelByIdUsuario.getRowIndex();
        return "/tecn/incidente/Edit";
    }

    public String prepareEdit() {
        current = (Incidente) getLazyModel().getRowData();
        selectedItemIndex = lazyModel.getRowIndex();
        return "/tecn/incidente/Edit";
    }

    public String prepareEditSolucion() {
        currentSol = (Solucion) getLazyModelSolucion().getRowData();
        selectedItemIndex = lazyModelByIdIncidente.getRowIndex();
        return null;
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("IncidenteUpdated"));
            return "/user/incidente/View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/properties/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String updateSolucion() {
        try {
            getSolucionFacade().edit(currentSol);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("SolucionUpdated"));
            return null;
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/properties/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Incidente) getLazyModel().getRowData();
        selectedItemIndex = lazyModel.getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "/user/incidente/List";
    }

    public String destroySolucion() {
        currentSol = (Solucion) getLazyModelSolucion().getRowData();
        selectedItemIndexSolucion = lazyModelByIdIncidente.getRowIndex();
        performDestroySolucion();
//        recreatePagination();
//        recreateModel();
        return null;
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "/user/incidente/View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "/user/incidente/List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("IncidenteDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/properties/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void performDestroySolucion() {
        try {
            getSolucionFacade().remove(currentSol);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("SolucionDeleted"));
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

    @FacesConverter(forClass = Incidente.class)
    public static class IncidenteControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            IncidenteController controller = (IncidenteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "incidenteController");
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
            if (object instanceof Incidente) {
                Incidente o = (Incidente) object;
                return getStringKey(o.getIdIncidente());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Incidente.class.getName());
            }
        }
    }
}
