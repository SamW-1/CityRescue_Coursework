package cityrescue;

import cityrescue.enums.IncidentType;

class PoliceCar extends Unit {
    public PoliceCar() {
        super(3);
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