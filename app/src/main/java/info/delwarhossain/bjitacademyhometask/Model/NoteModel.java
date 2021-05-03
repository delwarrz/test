package info.delwarhossain.bjitacademyhometask.Model;

public class NoteModel {
    int id;
    String title;
    String details;
    private boolean isExpanded;

    public NoteModel() {
    }

    public NoteModel(String title, String details) {
        this.title = title;
        this.details = details;
        this.isExpanded = false;
    }

    public NoteModel(int id, String title, String details) {
        this.id = id;
        this.title = title;
        this.details = details;
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
