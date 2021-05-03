package info.delwarhossain.bjitacademyhometask.Model;

public class FinancialTermModel {
    private int id;
    private String title;
    private String details;
    private String created_at;
    private String updated_at;
    private String deleted_at;
    private boolean isExpanded;

    public FinancialTermModel(int id, String title, String details) {
        this.id = id;
        this.title = title;
        this.details = details;
        this.isExpanded = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }
}
