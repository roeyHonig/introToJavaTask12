package student.honig.roey;

public class Time2 {

    private int _minFromMid;
    public final int DEFAULT_VALUE = 0;
    public final int HOUR_MIN_VALUE = 0;
    public final int HOUR_MAX_VALUE = 23;
    public final int MINUTE_MIN_VALUE = 0;
    public final int MINUTE_MAX_VALUE = 59;
    public final int MINUTE_PER_HOUR = 60;
    public final int HOUR_PER_DAY = 24;
    public final int MINUTE_PER_DAY = HOUR_PER_DAY * MINUTE_PER_HOUR;

    // Constructors
    public Time2(int hour, int minute) {
        int _hour = hour;
        int _minute = minute;
        if (hour < HOUR_MIN_VALUE || hour > HOUR_MAX_VALUE) {
            _hour = DEFAULT_VALUE;
        }
        if (minute < MINUTE_MIN_VALUE || minute > MINUTE_MAX_VALUE) {
            _minute = DEFAULT_VALUE;
        }
        _minFromMid = MINUTE_PER_HOUR  * _hour + _minute;
    }

    public Time2 (Time2 t){
        this(t.getHour(), t.getMinute());
    }

    // getters
    public int getHour() {
        return (_minFromMid-(_minFromMid % MINUTE_PER_HOUR))/MINUTE_PER_HOUR;
    }

    public int getMinute() {
        return _minFromMid % MINUTE_PER_HOUR;
    }

    // setters
    public void setHour(int num) {
        if (num < HOUR_MIN_VALUE || num > HOUR_MAX_VALUE) {
            return;
        }
        _minFromMid = MINUTE_PER_HOUR * num + getMinute();
    }

    public void setMinute(int num) {
        if (num < MINUTE_MIN_VALUE || num > MINUTE_MAX_VALUE) {
            return;
        }
        _minFromMid = MINUTE_PER_HOUR * getHour() + num;
    }

    public String toString() {
        int _hour = getHour();
        int _minute = getMinute();
        return ""+(_hour < 10 ? "0"+_hour : _hour)+":"+(_minute < 10 ? "0"+_minute : _minute);
    }

    public int minFromMidnight(){
        return  _minFromMid;
    }

    public boolean equals(Time2 other) {
        if (this == other) return true;
        return _minFromMid == other.minFromMidnight();
    }

    public boolean before (Time2 other){
        if (_minFromMid <= other.minFromMidnight()) {
            return true;
        }
        return false;
    }

    public boolean after (Time2 other){
        return other.before(this);
    }

    public int difference(Time2 other){
        return _minFromMid-other.minFromMidnight();
    }

    public Time2 addMinutes(int num){
        int totalMinute = num + this.minFromMidnight();
        int x = Math.abs(totalMinute) % MINUTE_PER_DAY;
        totalMinute = (totalMinute >= 0)? x : MINUTE_PER_DAY - x;
        int minute = totalMinute % MINUTE_PER_HOUR;
        int hour = (totalMinute-minute) / MINUTE_PER_HOUR;
        return new Time2(hour,minute);
    }

}
