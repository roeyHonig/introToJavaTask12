package student.honig.roey;

public class Train {
    private String _destination;
    private  Time1 _departure;
    private int _duration;
    private int _passengers;
    private int _seats;
    private int _price;
    public final int DEFAULT_VALUE = 0;

    public Train(String dest, int h, int m, int duration, int pass, int seats, int price){
        _duration = (duration > DEFAULT_VALUE) ? duration : DEFAULT_VALUE;
        _seats = (seats > DEFAULT_VALUE) ? seats : DEFAULT_VALUE;
        _passengers = (pass > DEFAULT_VALUE) ? ((pass < _seats)? pass: _seats) : DEFAULT_VALUE;
        _price = (price > DEFAULT_VALUE) ? price : DEFAULT_VALUE;
        _destination = dest;
        _departure = new Time1(h,m);
    }

    public Train(Train other){
        _duration = other.getDuration();
        _seats = other.getSeats();
        _passengers = other.getPassengers();
        _price = other.getPrice();
        _destination = other.getDestination();
        _departure = other.getDeparture();
    }

    public boolean addPassengers(int num){
        _passengers += num;
        if (_passengers <= _seats) {
            return true;
        } else {
            _passengers -= num;
            return false;
        }
    }

    public boolean arrivesEarlier(Train other){
        if (getArrivalTime().before(other.getArrivalTime())) {
            return true;
        }
        return false;
    }

    public boolean equals(Train other) {
        if (this == other) return true;
        return _duration == other._duration &&
                _passengers == other._passengers &&
                _seats == other._seats &&
                _price == other._price &&
                _destination.equals(other._destination) &&
                _departure.equals(other._departure);
    }

    public Time1 getArrivalTime(){
        Time1 arrivalTime = new Time1(getDeparture());
        arrivalTime.addMinutes(getDuration());
        return arrivalTime;
    }

    public String getDestination() {
        return _destination;
    }

    public Time1 getDeparture() {
        return _departure;
    }

    public int getDuration() {
        return _duration;
    }

    public int getPassengers() {
        return _passengers;
    }

    public int getSeats() {
        return _seats;
    }

    public int getPrice() {
        return _price;
    }

    public boolean isCheaper(Train other){
        return (getPrice() < other.getPrice()) ? true : false;
    }
    public boolean isFull(){
        return (_passengers == _seats) ? true : false;
    }

    public void setDeparture(Time1 t) {
        _departure = t;
    }

    public void setDestination(String d) {
        _destination = d;
    }


    public void setDuration(int d) {
        if (d >= DEFAULT_VALUE) {
            _duration = d;
        }
    }

    public void setPassengers(int p) {
        _passengers = (p > DEFAULT_VALUE) ? ((p <= _seats)? p : _seats) : _passengers;
    }

    public void setSeats(int s) {
        _seats = (s > DEFAULT_VALUE) ? ((s <= _passengers)? s : _seats) : _seats;
    }

    public void setPrice(int p) {
        _price = (p > DEFAULT_VALUE) ? p : DEFAULT_VALUE;
    }

    public String toString() {
        return "Train to "+ getDestination()+" departs at "+ getDeparture()+((this.isFull()) ? " Train is full" : " Train is not full");
    }

    public int totalPrice(){
        return _price * _passengers;
    }
}