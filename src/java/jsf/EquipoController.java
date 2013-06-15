package jsf;

import jpa.entities.Equipo;
import jsf.util.JsfUtil;
import jsf.util.PaginationHelper;
import jpa.sessions.EquipoFacade;

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
import jpa.entities.EquipoHasTipoDisco;
import jpa.entities.EquipoHasTipoMemoria;
import jpa.entities.EquipoHasTipoUnidadOptica;
import jpa.sessions.EquipoHasTipoDiscoFacade;
import jpa.sessions.EquipoHasTipoMemoriaFacade;
import jpa.sessions.EquipoHasTipoUnidadOpticaFacade;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

@ManagedBean(name = "equipoController")
@SessionScoped
public class EquipoController implements Serializable {

    private Equipo current;
    private DataModel items = null;
    private LazyDataModel<Equipo> lazyEquipoModel;
    private List<EquipoHasTipoMemoria> listMemorias;
    private List<EquipoHasTipoDisco> listDiscos;
    private List<EquipoHasTipoUnidadOptica> listUnidades;
    private EquipoHasTipoMemoria currentMemoria;
    private EquipoHasTipoDisco currentDisco;
    private EquipoHasTipoUnidadOptica currentUnidad;
    @EJB
    private jpa.sessions.EquipoFacade ejbFacade;
    @EJB
    private jpa.sessions.EquipoHasTipoMemoriaFacade ejbFacadeMemoria;
    @EJB
    private jpa.sessions.EquipoHasTipoDiscoFacade ejbFacadeDisco;
    @EJB
    private jpa.sessions.EquipoHasTipoUnidadOpticaFacade ejbFacadeUnidad;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public EquipoController() {
    }

    public LazyDataModel<Equipo> getLazyEquipoModel() {
        if (lazyEquipoModel == null) {
            lazyEquipoModel = new LazyDataModel<Equipo>() {
                @Override
                public List<Equipo> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                    int start = first;
                    int end = first + pageSize;
                    List<Equipo> equipoList = getFacade().findAll();
                    List<Equipo> data = new ArrayList<Equipo>();
                           
                    //filter  
                    for (Equipo equipo : equipoList) {
                        boolean match = true;

                        for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                            try {
                                String filterProperty = it.next();
                                String filterValue = filters.get(filterProperty).toLowerCase();
                                Field field = equipo.getClass().getDeclaredField(filterProperty);
                                field.setAccessible(true);
                                String fieldValue = String.valueOf(field.get(equipo)).toLowerCase();

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
                            data.add(equipo);
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
                public Object getRowKey(Equipo equipo) {
                    return equipo.getIdEquipo().toString();
                }

                @Override
                public Equipo getRowData(String rowKey) {
                    for (Equipo equipo : lazyEquipoModel) {
                        if (equipo.getIdEquipo().toString().equals(rowKey)) {
                            return equipo;
                        }
                    }

                    return null;
                }
            };
        }

        return lazyEquipoModel;
    }

    public Equipo getSelected() {
        if (current == null) {
            current = new Equipo();
            selectedItemIndex = -1;
        }
        return current;
    }

    public EquipoHasTipoMemoria getMemoriaSelected() {
        if (currentMemoria == null) {
            currentMemoria = new EquipoHasTipoMemoria();
            currentMemoria.setEquipoHasTipoMemoriaPK(new jpa.entities.EquipoHasTipoMemoriaPK());
        }
        return currentMemoria;
    }

    public EquipoHasTipoDisco getDiscoSelected() {
        if (currentDisco == null) {
            currentDisco = new EquipoHasTipoDisco();
            currentDisco.setEquipoHasTipoDiscoPK(new jpa.entities.EquipoHasTipoDiscoPK());
        }
        return currentDisco;
    }

    public EquipoHasTipoUnidadOptica getUnidadSelected() {
        if (currentUnidad == null) {
            currentUnidad = new EquipoHasTipoUnidadOptica();
            currentUnidad.setEquipoHasTipoUnidadOpticaPK(new jpa.entities.EquipoHasTipoUnidadOpticaPK());
        }
        return currentUnidad;
    }

    private EquipoFacade getFacade() {
        return ejbFacade;
    }

    private EquipoHasTipoMemoriaFacade getMemoriaFacade() {
        return ejbFacadeMemoria;
    }

    private EquipoHasTipoDiscoFacade getDiscoFacade() {
        return ejbFacadeDisco;
    }

    private EquipoHasTipoUnidadOpticaFacade getUnidadFacade() {
        return ejbFacadeUnidad;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {
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
        return "/almac/equipo/List";
    }

    public String prepareView() {
        current = (Equipo) getLazyEquipoModel().getRowData();
        selectedItemIndex = lazyEquipoModel.getRowIndex();
        return "/almac/equipo/View";
    }

    public void addMemoria() {
        listMemorias.add(currentMemoria);
        currentMemoria = new EquipoHasTipoMemoria();
        currentMemoria.setEquipoHasTipoMemoriaPK(new jpa.entities.EquipoHasTipoMemoriaPK());
        //JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("EquipoHasTipoMemoriaCreated"));    
    }
    
    public void addDisco() {
        listDiscos.add(currentDisco);
        currentDisco = new EquipoHasTipoDisco();
        currentDisco.setEquipoHasTipoDiscoPK(new jpa.entities.EquipoHasTipoDiscoPK());
        //JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("EquipoCreated"));    
    }
    
    public void addUnidad() {
        listUnidades.add(currentUnidad);
        currentUnidad = new EquipoHasTipoUnidadOptica();
        currentUnidad.setEquipoHasTipoUnidadOpticaPK(new jpa.entities.EquipoHasTipoUnidadOpticaPK());
        //JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("EquipoCreated"));    
    }

    public List<EquipoHasTipoMemoria> getListMemorias() {
        return listMemorias;
    }

    public List<EquipoHasTipoDisco> getListDiscos() {
        return listDiscos;
    }

    public List<EquipoHasTipoUnidadOptica> getListUnidades() {
        return listUnidades;
    }


    public String prepareCreate() {
        current = new Equipo();
        currentMemoria = new EquipoHasTipoMemoria();
        currentMemoria.setEquipoHasTipoMemoriaPK(new jpa.entities.EquipoHasTipoMemoriaPK());
        currentDisco = new EquipoHasTipoDisco();
        currentDisco.setEquipoHasTipoDiscoPK(new jpa.entities.EquipoHasTipoDiscoPK());        
        currentUnidad = new EquipoHasTipoUnidadOptica();
        currentUnidad.setEquipoHasTipoUnidadOpticaPK(new jpa.entities.EquipoHasTipoUnidadOpticaPK());
        listMemorias = new ArrayList<EquipoHasTipoMemoria>();
        listDiscos = new ArrayList<EquipoHasTipoDisco>();
        listUnidades = new ArrayList<EquipoHasTipoUnidadOptica>();
        selectedItemIndex = -1;
        return "/almac/equipo/Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            for (EquipoHasTipoMemoria memoria : listMemorias) {
                memoria.setEquipo(current);                
                getMemoriaFacade().create(memoria);
            }
            for (EquipoHasTipoDisco disco : listDiscos) {
                disco.setEquipo(current);                
                getDiscoFacade().create(disco);
            }
            for (EquipoHasTipoUnidadOptica unidad : listUnidades) {
                unidad.setEquipo(current);                
                getUnidadFacade().create(unidad);
            }
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("EquipoCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/properties/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Equipo) getLazyEquipoModel().getRowData();
        selectedItemIndex = lazyEquipoModel.getRowIndex();
        return "/almac/equipo/Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("EquipoUpdated"));
            return "/almac/equipo/View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/properties/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Equipo) getLazyEquipoModel().getRowData();
        selectedItemIndex = lazyEquipoModel.getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "/almac/equipo/List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "/almac/equipo/View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "/almac/equipo/List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("EquipoDeleted"));
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
    
    public SelectItem[] getItemsAvailableSelectOneBySala() {
        return JsfUtil.getSelectItems(ejbFacade.findBySala(this.getSelected().getIdSala()), true);
    }

    @FacesConverter(forClass = Equipo.class)
    public static class EquipoControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EquipoController controller = (EquipoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "equipoController");
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
            if (object instanceof Equipo) {
                Equipo o = (Equipo) object;
                return getStringKey(o.getIdEquipo());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Equipo.class.getName());
            }
        }
    }
}
