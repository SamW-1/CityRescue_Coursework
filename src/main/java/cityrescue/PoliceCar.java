package cityrescue;

import cityrescue.enums.IncidentType;

class PoliceCar extends Unit {
    public boolean canHandle(IncidentType type) {
        return false;
    }
    public int getTicksToResolve(int severity) {
        return 0;
    }
}