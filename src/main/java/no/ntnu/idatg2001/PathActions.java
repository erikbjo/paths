package no.ntnu.idatg2001;

public class PathActions {
    private String actionName;

    private String pathReference;

    public PathActions(String actionName, String pathReference) {
        this.actionName = actionName;
        this.pathReference = pathReference;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getPathReference() {
        return pathReference;
    }

    public void setPathReference(String pathReference) {
        this.pathReference = pathReference;
    }
}
