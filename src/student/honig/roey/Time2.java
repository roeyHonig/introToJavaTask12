package student.honig.roey;

/** Represents time by the minutes from midnight. Values must represent a proper time.
 * @author Roey Honig
 * @author ID: roeyhonig94@gmail.com
 * @author ID: 060873940
 */
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

    /** Constructs a Time2 object. Construct a new time instance with the specified hour and minute . hour should be between 0-23, otherwise it should be set to 0. minute should be between 0-59, otherwise they should be set to 0.
     * @param hour - hour
     * @param minute - minute
     */
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

    /** Copy constructor for Time2. Constructs a time with the same variables as another time.
     * @param t - The time object from which to construct the new time
     */
    public Time2 (Time2 t){
        _minFromMid = t.minFromMidnight();
    }

    /** Returns the hour of the time.
     * @return The hour of the time
     */
    public int getHour() {
        return (_minFromMid-(_minFromMid % MINUTE_PER_HOUR))/MINUTE_PER_HOUR;
    }

    /** Returns the minutes of the time.
     * @return The minutes of the time
     */
    public int getMinute() {
        return _minFromMid % MINUTE_PER_HOUR;
    }

    /** Changes the hour of the time. If an illegal number is received hour will be unchanged.
     * @param num - The new hour
     */
    public void setHour(int num) {
        if (num < HOUR_MIN_VALUE || num > HOUR_MAX_VALUE) {
            return;
        }
        _minFromMid = MINUTE_PER_HOUR * num + getMinute();
    }

    /** Changes the minute of the time. If an illegal number is received minute will be unchanged.
     * @param num - The new minute
     */
    public void setMinute(int num) {
        if (num < MINUTE_MIN_VALUE || num > MINUTE_MAX_VALUE) {
            return;
        }
        _minFromMid = MINUTE_PER_HOUR * getHour() + num;
    }

    /** Returns a string representation of this time ("hh:mm").
     * @return String representation of this time ("hh:mm").
     */
    public String toString() {
        int _hour = getHour();
        int _minute = getMinute();
        return ""+(_hour < 10 ? "0"+_hour : _hour)+":"+(_minute < 10 ? "0"+_minute : _minute);
    }

    /** Return the amount of minutes since midnight.
     * @return amount of minutes since midnight.
     */
    public int minFromMidnight(){
        return  _minFromMid;
    }

    /** Checks if the received time is equal to this time.
     * @param  other The time to be compared with this time
     * @return true if the received time is equal to this time
     */
    public boolean equals(Time2 other) {
        if (this == other) return true;
        return _minFromMid == other.minFromMidnight();
    }

    /** Checks if this time is before a received time.
     * @param  other The time to check if this time is before
     * @return true if this time is before other time
     */
    public boolean before (Time2 other){
        if (_minFromMid <= other.minFromMidnight()) {
            return true;
        }
        return false;
    }

    /** Checks if this time is after a received time.
     * @param  other The time to check if this time is after
     * @return true if this time is after other time
     */
    public boolean after (Time2 other){
        return other.before(this);
    }

    /** Calculates the difference (in minutes) between two times. Assumption: this time is after other time.
     * @param  other The time to check the difference to
     * @return int difference in minutes
     */
    public int difference(Time2 other){
        return _minFromMid-other.minFromMidnight();
    }

    /** Adds num Minutes to time.
     * @param  num The number of minutes to add
     * @return the update time
     */
    public Time2 addMinutes(int num){
        int totalMinute = num + this.minFromMidnight();
        int x = Math.abs(totalMinute) % MINUTE_PER_DAY;
        totalMinute = (totalMinute >= 0)? x : MINUTE_PER_DAY - x;
        int minute = totalMinute % MINUTE_PER_HOUR;
        int hour = (totalMinute-minute) / MINUTE_PER_HOUR;
        return new Time2(hour,minute);
    }

}
