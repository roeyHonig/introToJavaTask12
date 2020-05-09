package student.honig.roey;

public class RailwayStation {

    private Train[] _station;
    private int _noOfTrs;
    public final int TRAINS_MAX = 100;

    public RailwayStation(){
        _station = new Train[TRAINS_MAX];
        _noOfTrs = 0;
    }

    public boolean addTrain(Train f){
        if (_noOfTrs == TRAINS_MAX) {
            return false;
        }
        int firstAvailableSlot = 0;
        for (Train train: _station
             ) {
            if (f.equals(train)) {
                return false;
            }
            firstAvailableSlot++;
        }
        _station[firstAvailableSlot] = new Train(f);
        _noOfTrs++;
        return true;
    }

    public boolean removeTrain(Train f){
        if (_noOfTrs == 0) {
            return false;
        }
        for (int i = 0; i <  _noOfTrs; i++) {
            if (f.equals(_station[i])) {
                _station[i] = _station[_noOfTrs-1];
                _station[_noOfTrs-1] = null;
                _noOfTrs--;
                return true;
            }
        }
        return false;
    }

    public Time1 firstDepartureToDestination (String place){
        if (_noOfTrs == 0) {
            return null;
        }
        Time1 earliestDeparture = null;
        for (Train train: _station
        ) {
            if (train.getDestination().equals(place)) {
                if (earliestDeparture != null) {
                    earliestDeparture = earliestDeparture.before(train.getDeparture()) ? earliestDeparture : train.getDeparture();
                } else {
                    earliestDeparture = train.getDeparture();
                }
            }
        }
        return new Time1(earliestDeparture);
    }

    public String toString(){
        if (_noOfTrs == 0) {
            return "There are no trains today.";
        }
        String stringToReturn = "The trains today are:\n";
        for (Train train: _station
        ) {
            stringToReturn += "Train to ";
            stringToReturn += train.getDestination();
            stringToReturn += " departs at "+train.getDeparture()+". "+(train.isFull()? "Train is full\n":"Train is not full\n");
        }
        return stringToReturn;
    }

    public int howManyFullTrains(){
        int counter = 0;
        for (Train train: _station
        ) {
            if (train.isFull()) {
                counter++;
            }
        }
        return counter;
    }

    public String mostPopularDestination(){
        if (_noOfTrs == 0) {
            return null;
        }
        String mostPopularDestination = _station[0].getDestination();
        int mostPopularDestinationTotalNumberOfTrains = 1;
        for (Train train: _station
        ) {
            int thisTrainDestinationTotalNumberOfTrains = getTotalTrainsInStationWithDestination(train.getDestination());
            if (thisTrainDestinationTotalNumberOfTrains > mostPopularDestinationTotalNumberOfTrains) {
                mostPopularDestinationTotalNumberOfTrains = thisTrainDestinationTotalNumberOfTrains;
                mostPopularDestination = train.getDestination();
            }
        }
        return mostPopularDestination;
    }

    public Train mostExpensiveTicket(){
        if (_noOfTrs == 0) {
            return null;
        }
        Train mostExpensiveTicketTrain = _station[0];
        for (Train train: _station
        ) {
            if (train.getPrice() > mostExpensiveTicketTrain.getPrice()) {
                mostExpensiveTicketTrain = train;
            }
        }
        return new Train(mostExpensiveTicketTrain);
    }

    public Train longestTrain(){
        if (_noOfTrs == 0) {
            return null;
        }
        Train longestDurationTrain = _station[0];
        for (Train train: _station
        ) {
            if (train.getDuration() > longestDurationTrain.getDuration()) {
                longestDurationTrain = train;
            }
        }
        return new Train(longestDurationTrain);
    }

    private int getTotalTrainsInStationWithDestination(String place){
        int counter = 0;
        for (Train train: _station
             ) {
            if (train.getDestination().equals(place)){
                counter++;
            }
        }
        return counter;
    }

}
