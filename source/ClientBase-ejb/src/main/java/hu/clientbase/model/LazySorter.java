package hu.clientbase.model;

import hu.clientbase.entity.Project;
import java.util.Comparator;
import org.primefaces.model.SortOrder;

class LazySorter implements Comparator<Project> {

    private String sortField;

    private SortOrder sortOrder;

    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    @Override
    public int compare(Project project1, Project project2) {
        try {
            Object value1 = Project.class.getField(this.sortField).get(project1);
            Object value2 = Project.class.getField(this.sortField).get(project2);

            int value = ((Comparable) value1).compareTo(value2);

            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
