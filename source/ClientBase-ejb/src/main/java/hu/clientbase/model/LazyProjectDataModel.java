package hu.clientbase.model;

import org.primefaces.model.LazyDataModel;
import hu.clientbase.entity.Project;
import hu.clientbase.facade.EntityFacade;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.primefaces.model.SortOrder;

public class LazyProjectDataModel extends LazyDataModel<Project> {

    private List<Project> datasource;

    @Inject
    private EntityFacade entityManager;

    public LazyProjectDataModel() {
        datasource = entityManager.findAll(Project.class);
    }

    @Override
    public Project getRowData(String rowKey) {
        for (Project project : datasource) {
            if (project.getId().toString().equals(rowKey)) {
                return project;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(Project project) {
        return project.getId();
    }

    @Override
    public List<Project> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        List<Project> data = new ArrayList<>();

        //filter
        for (Project project : datasource) {
            boolean match = true;

            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(project.getClass().getField(filterProperty).get(project));

                        if (filterValue == null || fieldValue.startsWith(filterValue.toString())) {
                            match = true;
                        } else {
                            match = false;
                            break;
                        }
                    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
                        match = false;
                    }
                }
            }

            if (match) {
                data.add(project);
            }
        }

        //sort
        if (sortField != null) {
            Collections.sort(data, new LazySorter(sortField, sortOrder));
        }

        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);

        //paginate
        if (dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            } catch (IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        } else {
            return data;
        }
    }
}
