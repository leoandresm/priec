package jsf;

import jpa.entities.Incidente;
import jpa.sessions.IncidenteFacade;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

@ManagedBean(name = "incidenteReportesController")
@SessionScoped
public class IncidenteReportesController implements Serializable {

    private LazyDataModel<Incidente> lazyModel;
    private Date fechaInicial;
    private Date fechaFinal;
    @EJB
    private jpa.sessions.IncidenteFacade ejbFacade;

    public IncidenteReportesController() {
    }

    public LazyDataModel<Incidente> getLazyModel() {
        if (lazyModel == null) {
            lazyModel = new LazyDataModel<Incidente>() {
                @Override
                public List<Incidente> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                    int start = first;
                    int end = first + pageSize;
                    List<Incidente> incidenteList = getFacade().findReporte(fechaInicial, fechaFinal);
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
                public void setRowIndex(int rowIndex) {
                    /*
                     * The following is in ancestor (LazyDataModel):
                     * this.rowIndex = rowIndex == -1 ? rowIndex : (rowIndex % pageSize);
                     */
                    if (rowIndex == -1 || getPageSize() == 0) {
                        super.setRowIndex(-1);
                    } else {
                        super.setRowIndex(rowIndex % getPageSize());
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

    private IncidenteFacade getFacade() {
        return ejbFacade;
    }

    public void query() {
        lazyModel = null;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    @FacesConverter(forClass = Incidente.class)
    public static class IncidenteControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            IncidenteReportesController controller = (IncidenteReportesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "incidenteReportesController");
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
