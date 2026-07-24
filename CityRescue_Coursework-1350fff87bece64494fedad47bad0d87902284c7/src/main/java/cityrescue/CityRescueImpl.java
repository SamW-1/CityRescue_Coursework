package cityrescue;

import cityrescue.enums.IncidentStatus;
import cityrescue.enums.IncidentType;
import cityrescue.enums.UnitStatus;
import cityrescue.enums.UnitType;
import cityrescue.exceptions.CapacityExceededException;
import cityrescue.exceptions.IDNotRecognisedException;
import cityrescue.exceptions.InvalidCapacityException;
import cityrescue.exceptions.InvalidGridException;
import cityrescue.exceptions.InvalidLocationException;
import cityrescue.exceptions.InvalidNameException;
import cityrescue.exceptions.InvalidSeverityException;
import cityrescue.exceptions.InvalidUnitException;

/**
 * CityRescueImpl (Starter)
 *
 * Your task is to implement the full specification.
 * You may add additional classes in any package(s) you like.
 * 
 * 
 * CURRENT ISSUES
 * Updating the simulation map for the locations of all Incidents, Units, Stations is not implemented
 * Location coordinates are stored within individual classes but not reflected in the map
 * 
 * Next overall steps: Begin component testing (Use Ai to make sure no functionality is missing)
 */
public class CityRescueImpl implements CityRescue {

    // TODO: add fields (map, arrays for stations/units/incidents, counters, tick, etc.)
    private CityMap map;
    @Override
    public void initialise(int width, int height) throws InvalidGridException {
        // TODO: implement
        if (width < 1 || height < 1) {
            throw new InvalidGridException("Invalid Width or Height");
        } 

        map = new CityMap(width, height);
    }

    @Override
    public int[] getGridSize() {
        // TODO: implement
        return new int[] {map.getWidth(), map.getHeight()};
    }

    @Override
    public void addObstacle(int x, int y) throws InvalidLocationException {
        // TODO: implement
        if (x >= map.getWidth() || x < 0 || y >= map.getHeight() || y < 0) {
            throw new InvalidLocationException("Invalid inputted location");
        }

        map.addObstacle(x, y);
    }

    @Override
    public void removeObstacle(int x, int y) throws InvalidLocationException {
        // TODO: implement
        if (x >= map.getWidth() || x < 0 || y >= map.getHeight() || y < 0) {
            throw new InvalidLocationException("Invalid inputted location");
        }

        map.removeObstacle(x, y);
    }

    @Override
    public int addStation(String name, int x, int y) throws InvalidNameException, InvalidLocationException, CapacityExceededException {
        // TODO: implement
        if (name == null || name.isBlank()) { throw new InvalidNameException("Inputted Name is blank"); }
        if (x >= map.getWidth() || x < 0 || y >= map.getHeight() || y < 0) {throw new InvalidLocationException("Invalid inputted location");}
        if (Station.totalID >= Station.MAX_STATIONS) { throw new CapacityExceededException("Max Stations exceeded"); }


        Station newStation = new Station(name, x, y);
        map.addStation(newStation, x, y);
        Station.addStation(newStation);
        return newStation.getStationID();
    }

    @Override
    public void removeStation(int stationId) throws IDNotRecognisedException, IllegalStateException {
        // TODO: implement
        if (!Station.isStation(stationId)) { throw new IDNotRecognisedException("Station does not exist"); }
        Station station = Station.getStation(stationId);
        if (station.hasUnits()) { throw new IllegalStateException("Station still owns units"); }

        map.removeStation(stationId);
    }

    @Override
    public void setStationCapacity(int stationId, int maxUnits) throws IDNotRecognisedException, InvalidCapacityException {
        // TODO: implement
        if (!Station.isStation(stationId)) { throw new IDNotRecognisedException("Station does not exist"); }
        Station station = Station.getStation(stationId);
        if (maxUnits <= 0 || maxUnits < station.unitsCount) { throw new InvalidCapacityException("Max entered is invalid"); }

        station.setStationCapacity(maxUnits);
    }

    @Override
    public int[] getStationIds() {
        // TODO: implement
        return Station.getStationIDs();
    }
    @Override
    public int addUnit(int stationId, UnitType type) throws IDNotRecognisedException, InvalidUnitException, IllegalStateException, CapacityExceededException {
        // TODO: implement
        if (!Station.isStation(stationId)) { throw new IDNotRecognisedException("Station does not exist"); }
        Station station = Station.getStation(stationId);
        if (!station.hasCapacity()) { throw new IllegalStateException("Station doesn't have capacity"); }
        if (Unit.totalID >= Unit.MAX_UNITS) { throw new CapacityExceededException("Max Units exceeded"); }
        int[] location = station.getLocation();

        Unit unit;
        switch (type) {
            case AMBULANCE:
                unit = new Ambulance(location);
                break;
            case POLICE_CAR:
                unit = new PoliceCar(location);
                break;
            case FIRE_ENGINE:
                unit = new FireEngine(location);
                break;
            default:
                throw new InvalidUnitException("Unit does not exist");
        }
        station.addUnit(unit);
        Unit.addUnit(unit);
        return unit.getID();
    }   

    @Override
    public void decommissionUnit(int unitId) throws IDNotRecognisedException, IllegalStateException {
        // TODO: implement
        if (!Unit.isUnit(unitId)) { throw new IDNotRecognisedException("Unit does not exist"); }
        Unit unit = Unit.getUnit(unitId);
        if (unit.status == UnitStatus.EN_ROUTE || unit.status == UnitStatus.AT_SCENE) { throw new IllegalStateException("Unit either EN_ROUTE or AT_SCENE"); }

        unit.status = UnitStatus.OUT_OF_SERVICE;
        Station station = Station.getStation(unit);
        station.removeUnit(unit);
        Unit.removeUnit();
    }

    @Override
    public void transferUnit(int unitId, int newStationId) throws IDNotRecognisedException, IllegalStateException {
        // TODO: implement
        if (!Unit.isUnit(unitId)) { throw new IDNotRecognisedException("Unit does not exist"); }
        if (!Station.isStation(newStationId)) { throw new IDNotRecognisedException("Station does not exist"); }

        Unit unit = Unit.getUnit(unitId);
        Station newStation = Station.getStation(newStationId);
        Station oldStation = Station.getStation(unit);

        if (unit.status != UnitStatus.IDLE) { throw new IllegalStateException("Unit is not IDLE"); }
        if (!newStation.hasCapacity()) { throw new IllegalStateException("New station does not have capacity"); }

        oldStation.removeUnit(unit);
        newStation.addUnit(unit);
    }

    @Override
    public void setUnitOutOfService(int unitId, boolean outOfService) throws IDNotRecognisedException, IllegalStateException {
        if (!Unit.isUnit(unitId)) { throw new IDNotRecognisedException("Unit does not exist"); }

        Unit unit = Unit.getUnit(unitId);

        if (outOfService && unit.status != UnitStatus.IDLE) { throw new IllegalStateException("Unit must be IDLE to toggle out of Service"); }

        if (outOfService) {
            unit.status = UnitStatus.OUT_OF_SERVICE;
        }
        else {
            unit.status = UnitStatus.IDLE;
        }
    }

    @Override
    public int[] getUnitIds() {
        return Unit.getUnitIDs();
    }

    @Override
    public String viewUnit(int unitId) throws IDNotRecognisedException {
        if (!Unit.isUnit(unitId)) { throw new IDNotRecognisedException("Unit does not exist"); }

        Unit unit = Unit.getUnit(unitId);
        int[] location = unit.getLocation();
        Station station = Station.getStation(unit);
        Incident incident = Incident.findRespondingUnit(unit);

        String returnString = "U#%d TYPE=%S HOME=%d LOC=(%d,%d) STATUS=%S INCIDENT=%d WORK=%d";

        // NEED TO ADD INCIDENT INFORMATION: come back once incident class and functionality has been implemented
        // Currently, there is no relation between Unit and Incident: Need to track which units are responding to which incidents
        //  either within solely the Unit or Incident class for good design principles

        return String.format(returnString, unit.getID(), unit.getUnitType().toString(), station.getStationID(), location[0], location[1], unit.status.toString(), incident.getID()); 
    }

    @Override
    public int reportIncident(IncidentType type, int severity, int x, int y) throws InvalidSeverityException, InvalidLocationException {
        if (type == null) { throw new InvalidSeverityException("Type is null"); }
        if (severity < 1 || severity > 5) { throw new InvalidSeverityException("Severity must be between 1 and 5"); }
        if (x >= map.getWidth() || x < 0 || y >= map.getHeight() || y < 0 || map.locationBlocked(x, y)) {throw new InvalidLocationException("Invalid inputted location");}
        
        Incident incident = new Incident(type, severity, x, y);
        Incident.addIncident(incident);
        return incident.getID();
    }       

    @Override
    public void cancelIncident(int incidentId) throws IDNotRecognisedException, IllegalStateException {
        if (!Incident.isIncident(incidentId)) { throw new IDNotRecognisedException("Incident does not exist"); }
        Incident incident = Incident.getIncident(incidentId);
        if (incident.status != IncidentStatus.REPORTED && incident.status != IncidentStatus.DISPATCHED) { throw new IllegalStateException("Incident must be REPORTED or DISPATCHED to cancel"); }

        incident.status = IncidentStatus.CANCELLED;
        Incident.RemoveIncident(incident);
        incident.releaseUnit();
    }

    @Override
    public void escalateIncident(int incidentId, int newSeverity) throws IDNotRecognisedException, InvalidSeverityException, IllegalStateException {
        if (!Incident.isIncident(incidentId)) { throw new IDNotRecognisedException("Incident does not exist"); }

        Incident incident = Incident.getIncident(incidentId);

        if (newSeverity < 1 || newSeverity > 5) { throw new InvalidSeverityException("Severity must be between 1 and 5 (inclusive)"); }
        if (incident.status == IncidentStatus.RESOLVED || incident.status == IncidentStatus.CANCELLED) { throw new IllegalStateException("Incident cannot be Cancelled or Resolved"); }

        incident.severity = newSeverity;
    }

    @Override
    public int[] getIncidentIds() {
        return Incident.getIncidentIDs();
    }

    @Override
    public String viewIncident(int incidentId) throws IDNotRecognisedException {
        if (!Incident.isIncident(incidentId)) { throw new IDNotRecognisedException("Incident does not exist"); }

        Incident incident = Incident.getIncident(incidentId);
        String returnString = "I#%d TYPE=%s SEV=%d LOC=(%d,%d) STATUS=&s UNIT=%d";
        int[] location = incident.getLocation();
        return String.format(returnString, incident.getID(), incident.type.toString(), incident.severity, location[0], location[1], incident.status.toString(), incident.getUnit().getID());
    }

    @Override
    public void dispatch() {
        // TODO: implement
        

        int[] ids = Incident.getIncidentIDs();
        for (int id :ids) {
            Incident incident = Incident.getIncident(id);
            Integer bestUnit = null;
                if (incident != null && incident.status == IncidentStatus.REPORTED){
                        int[] ids2 = Unit.getUnitIDs();
                        for (int id2 :ids2) {
                            Unit unit = Unit.getUnit(id2);

                            if (unit != null 
                                && unit.status == UnitStatus.IDLE
                                && unit.canHandle(incident.type)){
                                int[] incidentLocation = incident.getLocation();
                                int[] unitLocation = unit.getLocation();
                                
                                int distanceX;
                                int distanceY;

                                if (incidentLocation[0] >= unitLocation[0]) {
                                    distanceX = incidentLocation[0] - unitLocation[0];
                                }
                                    else 
                                    {
                                        distanceX = unitLocation[0] - incidentLocation[0];
                                    }
                                if (incidentLocation[1] >= unitLocation[1]) {
                                    distanceY = incidentLocation[1] - unitLocation[1];
                                }
                                    else 
                                    {
                                        distanceY = unitLocation[1] - incidentLocation[1];
                                    }
                                int totalDistance = distanceX + distanceY;
                                if (bestUnit != null && bestUnit < totalDistance) {
                                    bestUnit = id2;
                                }
                                else if(bestUnit != null && bestUnit == totalDistance) {
                                    if(bestUnit < id2){
                                       ;
                                    }
                                    //still need to complete the station tiebreaker 
                                    else if(bestUnit > id2){
                                        bestUnit = id2;
                                    }
                            
                                    
                                }

                                

                                }
                                }
                                Unit unit = Unit.getUnit(bestUnit);
                                unit.status = UnitStatus.EN_ROUTE;
                                incident.status = IncidentStatus.DISPATCHED;
                                //here I will dispatch units but the code is not completed yet 
                                //unsure how to set the responding unit too, code may be incomplete

                                }
                


                }
        }
    }

    @Override
    public void tick() {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public String getStatus() {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
