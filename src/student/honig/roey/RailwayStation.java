package student.honig.roey;

/** represents a RailwayStation with a listing of today's Trains
 * @author Roey Honig
 * @author ID: roeyhonig94@gmail.com
 * @author ID: 060873940
 */
public class RailwayStation {

    private Train[] _station;
    private int _noOfTrs;
    public final int TRAINS_MAX = 100;

    /** Empty Constructor of class RailwayStation. Makes sure the List of Trains isn't bigger then the Max Allowed.
     */
    public RailwayStation(){
        _station = new Train[TRAINS_MAX];
        _noOfTrs = 0;
    }

    /** Adds a train to first available slot in the list if exists. If The Train already exsists in the list no addition is executed.
     * @param f - train to add.
     * @return True if Addition of the Train was successful, FALSE if no addition was made due to the list being full or a similar train already exists.
     */
    public boolean addTrain(Train f){
        if (_noOfTrs == TRAINS_MAX) {
            return false;
        }
        int firstAvailableSlot = 0;
        for (Train train: _station
             ) {
            if (train != null && f.equals(train)) {
                return false;
            }
        }
        if (_noOfTrs != 0) {
            firstAvailableSlot = _noOfTrs;
        }
        _station[firstAvailableSlot] = new Train(f);
        _noOfTrs++;
        return true;
    }

    /** Removes a train from the list if exists.
     * @param f - train to remove.
     * @return True if removal of the Train was successful, FALSE if no removal was made.
     */
    public boolean removeTrain(Train f){
        if (_noOfTrs == 0) {
            return false;
        }
        for (int i = 0; i <  _noOfTrs; i++) {
            if (_station[i] != null && f.equals(_station[i])) {
                _station[i] = _station[_noOfTrs-1];
                _station[_noOfTrs-1] = null;
                _noOfTrs--;
                return true;
            }
        }
        return false;
    }

    /** Find the earliest departing train to a specific place.
     * @param place - the destination of the train
     * @return Time1 Object or null if the destination provided doesn't match any destination of the Trains in the list.
     */
    public Time1 firstDepartureToDestination (String place){
        if (_noOfTrs == 0) {
            return null;
        }
        Time1 earliestDeparture = null;
        for (Train train: _station
        ) {
            if (train != null && train.getDestination().equals(place)) {
                if (earliestDeparture != null) {
                    earliestDeparture = earliestDeparture.before(train.getDeparture()) ? earliestDeparture : train.getDeparture();
                } else {
                    earliestDeparture = train.getDeparture();
                }
            }
        }
        return new Time1(earliestDeparture);
    }

    /** Return a string representation of the RailwayStation.
     * @return String representation of the RailwayStation.
     */
    public String toString(){
        if (_noOfTrs == 0) {
            return "There are no trains today.";
        }
        String stringToReturn = "The trains today are:\n";
        for (Train train: _station
        ) {
            if (train == null) {
                break;
            }
            stringToReturn += "Train to ";
            stringToReturn += train.getDestination();
            stringToReturn += " departs at "+train.getDeparture()+". "+(train.isFull()? "Train is full\n":"Train is not full\n");
        }
        return stringToReturn;
    }

    /** Find out how many of the trains are full.
     * @return Number of full trains.
     */
    public int howManyFullTrains(){
        int counter = 0;
        for (Train train: _station
        ) {
            if (train != null && train.isFull()) {
                counter++;
            }
        }
        return counter;
    }

    /** Place to which most trains depart towards.
     * @return Place with the highest number of trains having as a destination. if multiple exist returns the first one.
     */
    public String mostPopularDestination(){
        if (_noOfTrs == 0) {
            return null;
        }
        String mostPopularDestination = _station[0].getDestination();
        int mostPopularDestinationTotalNumberOfTrains = 1;
        for (Train train: _station
        ) {
            if (train != null) {
                int thisTrainDestinationTotalNumberOfTrains = getTotalTrainsInStationWithDestination(train.getDestination());
                if (thisTrainDestinationTotalNumberOfTrains > mostPopularDestinationTotalNumberOfTrains) {
                    mostPopularDestinationTotalNumberOfTrains = thisTrainDestinationTotalNumberOfTrains;
                    mostPopularDestination = train.getDestination();
                }
            }
        }
        return mostPopularDestination;
    }

    /** Most Expensive train.
     * @return Train Object corresponds to the most expensive train or null if no trains are listed.
     */
    public Train mostExpensiveTicket(){
        if (_noOfTrs == 0) {
            return null;
        }
        Train mostExpensiveTicketTrain = _station[0];
        for (Train train: _station
        ) {
            if (train != null && train.getPrice() > mostExpensiveTicketTrain.getPrice()) {
                mostExpensiveTicketTrain = train;
            }
        }
        return new Train(mostExpensiveTicketTrain);
    }

    /** Train with the longest duration time.
     * @return Train Object corresponds to the train with the longest duration time or null if no trains are listed.
     */
    public Train longestTrain(){
        if (_noOfTrs == 0) {
            return null;
        }
        Train longestDurationTrain = _station[0];
        for (Train train: _station
        ) {
            if (train != null && train.getDuration() > longestDurationTrain.getDuration()) {
                longestDurationTrain = train;
            }
        }
        return new Train(longestDurationTrain);
    }

    /** Get the total number of trains departing to the same place.
     * @param place - the destination of the train.
     * @return Returns a number of the total trains departing to the same destination.
     */
    private int getTotalTrainsInStationWithDestination(String place){
        int counter = 0;
        for (Train train: _station
             ) {
            if (train != null && train.getDestination().equals(place)){
                counter++;
            }
        }
        return counter;
    }

}
