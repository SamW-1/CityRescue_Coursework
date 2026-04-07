package cityrescue;

import cityrescue.enums.IncidentStatus;
public class Incident {
    public static int totalID = 1;
    public static final int MAX_INCIDENTS = 200;
    private static Incident[] incidentList = new Incident[MAX_INCIDENTS];
    private final int incidentID;
    public IncidentStatus status;
    public Incident() {
        incidentID = totalID;
        totalID++;
        status = IncidentStatus.REPORTED;
    }
    public int getID() { return incidentID; }
}