package student.honig.roey;

public class Time1 {

    private int _hour; // 0-23
    private int _minute; // 0-59
    public final int DEFAULT_VALUE = 0;
    public final int HOUR_MIN_VALUE = 0;
    public final int HOUR_MAX_VALUE = 23;
    public final int MINUTE_MIN_VALUE = 0;
    public final int MINUTE_MAX_VALUE = 59;
    public final int MINUTE_PER_HOUR = 60;
    public final int HOUR_PER_DAY = 24;
    public final int MINUTE_PER_DAY = HOUR_PER_DAY * MINUTE_PER_HOUR;

    // Constructors
    public Time1(int hour, int minute) {
        _hour = hour;
        _minute = minute;
        if (hour < HOUR_MIN_VALUE || hour > HOUR_MAX_VALUE) {
            _hour = DEFAULT_VALUE;
        }
        if (minute < MINUTE_MIN_VALUE || minute > MINUTE_MAX_VALUE) {
            _minute = DEFAULT_VALUE;
        }
    }

    public Time1 (Time1 t){
        this(t.getHour(), t.getMinute());
    }

    // getters
    public int getHour() {
        return _hour;
    }

    public int getMinute() {
        return _minute;
    }

    // setters
    public void setHour(int num) {
        if (num < HOUR_MIN_VALUE || num > HOUR_MAX_VALUE) {
            return;
        }
        _hour = num;
    }

    public void setMinute(int num) {
        if (num < MINUTE_MIN_VALUE || num > MINUTE_MAX_VALUE) {
            return;
        }
        _minute = num;
    }

    public String toString() {
        return ""+(_hour < 10 ? "0"+_hour : _hour)+":"+(_minute < 10 ? "0"+_minute : _minute);
    }

    public int minFromMidnight(){
        return  MINUTE_PER_HOUR*_hour+ _minute;
    }

    public boolean equals(Time1 other) {
        if (this == other) return true;
        return _hour == other.getHour() && _minute == other.getMinute();
    }

    public boolean before (Time1 other){
        if (this.minFromMidnight() <= other.minFromMidnight()) {
            return true;
        }
        return false;
    }

    public boolean after (Time1 other){
        return other.before(this);
    }

    public int difference(Time1 other){
        int thisMinFromMidnight = this.minFromMidnight();
        int otherMinFromMidnight = other.minFromMidnight();
        return thisMinFromMidnight-otherMinFromMidnight;
    }

    public Time1 addMinutes(int num){
            int totalMinute = num + this.minFromMidnight();
            int x = Math.abs(totalMinute) % MINUTE_PER_DAY;
            totalMinute = (totalMinute >= 0)? x : MINUTE_PER_DAY - x;
            int minute = totalMinute % MINUTE_PER_HOUR;
            int hour = (totalMinute-minute) / MINUTE_PER_HOUR;
            return new Time1(hour,minute);
    }

}
