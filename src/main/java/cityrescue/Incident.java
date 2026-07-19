package cityrescue;

import cityrescue.enums.IncidentStatus;
public class Incident {
    public static int totalID = 1;
    public static final int MAX_INCIDENTS = 200;
    private static Incident[] incidentList = new Incident[MAX_INCIDENTS];
    private final int incidentID;
    private int[] location;
    public IncidentStatus status;
    public Incident(int x, int y) {
        incidentID = totalID;
        totalID++;
        status = IncidentStatus.REPORTED;
        location = new int[] { x, y };
    }
    public int getID() { return incidentID; }
    public static void addIncident(Incident incident) { incidentList[totalID-1] = incident; }
    public static boolean isIncident(int ID) {
        for (Incident incident : incidentList) {
            if (incident.getID() == ID) { return true; }
        }
        return false;
    }
    public static Incident getIncident(int ID) {
        for (Incident incident : incidentList) {
            if (incident.getID() == ID) { return incident; }
        }
        return null;
    }
    public static void RemoveIncident(Incident incident) {
        for (int i = 0; i < MAX_INCIDENTS; i++) {
            if (incidentList[i] == incident) { 
                incidentList[i] = null; break;
            } 
        }
    }
}