package cityrescue;

import cityrescue.enums.IncidentType;
import cityrescue.enums.UnitType;
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
    public int addStation(String name, int x, int y) throws InvalidNameException, InvalidLocationException {
        // TODO: implement
        if (name == null || name == "") { throw new InvalidNameException("Inputted Name is blank"); }
        if (x >= map.getWidth() || x < 0 || y >= map.getHeight() || y < 0) {
            throw new InvalidLocationException("Invalid inputted location");
        }

        Station newStation = new Station(name);
        map.addStation(newStation, x, y);
        Station.stations[Station.stationsCount] = newStation;
        Station.stationsCount++;
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
    public int addUnit(int stationId, UnitType type) throws IDNotRecognisedException, InvalidUnitException, IllegalStateException {
        // TODO: implement
        if (!Station.isStation(stationId)) { throw new IDNotRecognisedException("Station does not exist"); }
        Station station = Station.getStation(stationId);
        if (type == null) { throw new InvalidUnitException("Unit does not exist"); }
        
    }

    @Override
    public void decommissionUnit(int unitId) throws IDNotRecognisedException, IllegalStateException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void transferUnit(int unitId, int newStationId) throws IDNotRecognisedException, IllegalStateException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void setUnitOutOfService(int unitId, boolean outOfService) throws IDNotRecognisedException, IllegalStateException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int[] getUnitIds() {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public String viewUnit(int unitId) throws IDNotRecognisedException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int reportIncident(IncidentType type, int severity, int x, int y) throws InvalidSeverityException, InvalidLocationException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void cancelIncident(int incidentId) throws IDNotRecognisedException, IllegalStateException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void escalateIncident(int incidentId, int newSeverity) throws IDNotRecognisedException, InvalidSeverityException, IllegalStateException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int[] getIncidentIds() {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public String viewIncident(int incidentId) throws IDNotRecognisedException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void dispatch() {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
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
