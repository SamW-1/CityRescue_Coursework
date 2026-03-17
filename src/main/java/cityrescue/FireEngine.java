package cityrescue;

import cityrescue.enums.IncidentType;

class FireEngine extends Unit {
    @ Override
    public boolean canHandle(IncidentType type) {
        return false;
    }
    @ Override
    public int getTicksToResolve(int severity) {
        return 0;
    }
}