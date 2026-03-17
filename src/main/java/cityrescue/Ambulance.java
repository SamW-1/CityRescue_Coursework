package cityrescue;

import cityrescue.enums.IncidentType;

class Ambulance extends Unit {
    public Ambulance() {
        super(2);
    }
    @ Override
    public boolean canHandle(IncidentType type) {
        return false;
    }
    @ Override
    public int getTicksToResolve(int severity) {
        return 0;
    }
}