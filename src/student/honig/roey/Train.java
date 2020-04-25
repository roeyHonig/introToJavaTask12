package student.honig.roey;

/** represents a Train
 * @author Roey Honig
 * @author ID: roeyhonig94@gmail.com
 * @author ID: 060873940
 */
public class Train {
    private String _destination;
    private  Time1 _departure;
    private int _duration;
    private int _passengers;
    private int _seats;
    private int _price;
    public final int DEFAULT_VALUE = 0;

    /** Constructor of class Train. Constructs a new train. duration should be positive, otherwise it should be set to 0. pass should be positive, otherwise it should be set to 0. pass should be less than seats otherwise it should be set to seats. seats should be positive, otherwise it should be set to 0. price should be positive, otherwise it should be set to 0.
     * @param dest - the destination of the train
     * @param h - the hour of departure.
     * @param m - the minute of departue.
     * @param duration - the duration of the travel.
     * @param pass - the number of passeners.
     * @param seats - the number of seats in the train.
     * @param price - the price of the travel.
     */
    public Train(String dest, int h, int m, int duration, int pass, int seats, int price){
        _duration = (duration > DEFAULT_VALUE) ? duration : DEFAULT_VALUE;
        _seats = (seats > DEFAULT_VALUE) ? seats : DEFAULT_VALUE;
        _passengers = (pass > DEFAULT_VALUE) ? ((pass < _seats)? pass: _seats) : DEFAULT_VALUE;
        _price = (price > DEFAULT_VALUE) ? price : DEFAULT_VALUE;
        _destination = dest;
        _departure = new Time1(h,m);
    }

    /** Copy constructor for Train. Construct a train with the same instance variables as another train.
     * @param other - The train object from which to construct the new train.
     */
    public Train(Train other){
        _duration = other.getDuration();
        _seats = other.getSeats();
        _passengers = other.getPassengers();
        _price = other.getPrice();
        _destination = other.getDestination();
        _departure = other.getDeparture();
    }

    /** Add num passengers to the train.
     * @param num - The number of passengers to add.
     * @return True if the total number of current passengers and num is less or equal to seats.
     */
    public boolean addPassengers(int num){
        _passengers += num;
        if (_passengers <= _seats) {
            return true;
        } else {
            _passengers -= num;
            return false;
        }
    }

    /** Returns true if the arrival time of this train is earlier than the arrival time of the other train. other is not null.
     * @param other - the other train to compare arrival time with.
     * @return true if the arrival time of this train is earlier than arrival time of the other train.
     */
    public boolean arrivesEarlier(Train other){
        if (getArrivalTime().before(other.getArrivalTime())) {
            return true;
        }
        return false;
    }

    /** Check if the received train is equal to this train.
     * @param other - The train to be compared with this train.
     * @return True if the received train is equal to this train.
     */
    public boolean equals(Train other) {
        if (this == other) return true;
        return _duration == other._duration &&
                _passengers == other._passengers &&
                _seats == other._seats &&
                _price == other._price &&
                _destination.equals(other._destination) &&
                _departure.equals(other._departure);
    }

    /** Returns the arrival time.
     * @return the arrival time.
     */
    public Time1 getArrivalTime(){
        Time1 arrivalTime = new Time1(getDeparture());
        arrivalTime.addMinutes(getDuration());
        return arrivalTime;
    }

    /** returns the destination.
     * the destination of the train.
     */
    public String getDestination() {
        return _destination;
    }

    /** returns the departure time.
     * the departure time.
     */
    public Time1 getDeparture() {
        return _departure;
    }

    /** returns the duration of the train.
     * the duration of the train.
     */
    public int getDuration() {
        return _duration;
    }

    /** returns the number of passengers.
     * the number of passengers.
     */
    public int getPassengers() {
        return _passengers;
    }

    /** returns the number of seats.
     * the number of seats.
     */
    public int getSeats() {
        return _seats;
    }

    /** returns the price of the train.
     * the price of the train.
     */
    public int getPrice() {
        return _price;
    }

    /** Returns true if the price for this train is cheaper than the other train. other is not null.
     * @param other - the other train to compare price with.
     * @return true if the price for this train is cheaper than the other train.
     */
    public boolean isCheaper(Train other){
        return (getPrice() < other.getPrice()) ? true : false;
    }

    /** Returns true if train is full.
     * @return true if train is full.
     */
    public boolean isFull(){
        return (_passengers == _seats) ? true : false;
    }

    /** updates the departure time of the train. t in not null.
     * @param t - the new departure time of the train.
     */
    public void setDeparture(Time1 t) {
        _departure = t;
    }

    public void setDestination(String d) {
        _destination = d;
    }

    /** updates the duration of the train. d should be positive or zero, otherwise duration is unchanged.
     * @param d - the new duration of the train.
     */
    public void setDuration(int d) {
        if (d >= DEFAULT_VALUE) {
            _duration = d;
        }
    }

    /** updates the number of passengers. p should be positive or zero, otherwise passengers is unchanged. p should be less than seats otherwise it should be set to seats.
     * @param p - the new number of passengers.
     */
    public void setPassengers(int p) {
        _passengers = (p > DEFAULT_VALUE) ? ((p <= _seats)? p : _seats) : _passengers;
    }

    /** updates the number of seats. s should be positive or zero, otherwise seats is unchanged. s should be larger than passengers, otherwise seats is unchanged.
     * @param s - the new number of seats.
     */
    public void setSeats(int s) {
        _seats = (s > DEFAULT_VALUE) ? ((s <= _passengers)? s : _seats) : _seats;
    }

    /** updates the price. p should be positive or zero, otherwise price is unchanged.
     * @param p - the new price.
     */
    public void setPrice(int p) {
        _price = (p > DEFAULT_VALUE) ? p : DEFAULT_VALUE;
    }

    /** Return a string representation of the train.
     * @return String representation of the train.
     */
    public String toString() {
        return "Train to "+ getDestination()+" departs at "+ getDeparture()+((this.isFull()) ? " Train is full" : " Train is not full");
    }

    /** Returns the total price for all passengers.
     * @return the total price for all passengers.
     */
    public int totalPrice(){
        return _price * _passengers;
    }
}