package cityrescue;

import cityrescue.enums.IncidentStatus;
import cityrescue.enums.UnitStatus;
public class Incident {
    public static int totalID = 1;
    private static int incidentCount = 0;
    public static final int MAX_INCIDENTS = 200;
    private static Incident[] incidentList = new Incident[MAX_INCIDENTS];
    private final int incidentID;
    private int[] location;
    private Unit respondingUnit;
    public IncidentStatus status;
    public int severity;
    public Incident(int severity, int x, int y) {
        this.severity = severity;
        incidentID = totalID;
        totalID++;
        incidentCount++;
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
        incidentCount --;
        for (int i = 0; i < MAX_INCIDENTS; i++) {
            if (incidentList[i] == incident) { 
                incidentList[i] = null; break;
            } 
        }
    }
    public void releaseUnit() {
        respondingUnit.status = UnitStatus.IDLE;
        respondingUnit = null;
    }
    public static int[] getIncidentIDs() {
        int[] IDs = new int[incidentCount];
        int count = 0;
        for (int i = 0; i < totalID; i++) {
            if (incidentList[i] != null) { 
                IDs[count] = incidentList[i].getID(); 
                count++;
            }
        }
        return IDs;
    }
}