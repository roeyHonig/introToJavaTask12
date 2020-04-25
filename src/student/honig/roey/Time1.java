package student.honig.roey;

/** Represents time - hours:minutes.
 * @author Roey Honig
 * @author ID: roeyhonig94@gmail.com
 * @author ID: 060873940
 */
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

    /** Constructs a Time1 object.
     * @param hour Integer representing the Hour.
     * @param minute Integer representing the Minute.
     */
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

    /**Copy constructor for Time1.
     * @param t Time1 object to be copied into a new Time1 instance.
     */
    public Time1 (Time1 t){
        _hour = t.getHour();
        _minute = t.getMinute();
    }

    /** Returns the hour of the time.
     * @return The hour of the time
     */
    public int getHour() {
        return _hour;
    }

    /** Returns the minutes of the time.
     * @return The minutes of the time
     */
    public int getMinute() {
        return _minute;
    }

    /** Changes the hour of the time. If an illegal number is received hour will be unchanged.
     * @param num - The new hour
     */
    public void setHour(int num) {
        if (num < HOUR_MIN_VALUE || num > HOUR_MAX_VALUE) {
            return;
        }
        _hour = num;
    }

    /** Changes the minute of the time. If an illegal number is received minute will be unchanged.
     * @param num - The new minute
     */
    public void setMinute(int num) {
        if (num < MINUTE_MIN_VALUE || num > MINUTE_MAX_VALUE) {
            return;
        }
        _minute = num;
    }

    /** Returns a string representation of this time ("hh:mm").
     * @return String representation of this time ("hh:mm").
     */
    public String toString() {
        return ""+(_hour < 10 ? "0"+_hour : _hour)+":"+(_minute < 10 ? "0"+_minute : _minute);
    }

    /** Return the amount of minutes since midnight.
     * @return amount of minutes since midnight.
     */
    public int minFromMidnight(){
        return  MINUTE_PER_HOUR*_hour+ _minute;
    }

    /** Checks if the received time is equal to this time.
     * @param  other The time to be compared with this time
     * @return true if the received time is equal to this time
     */
    public boolean equals(Time1 other) {
        if (this == other) return true;
        return _hour == other.getHour() && _minute == other.getMinute();
    }

    /** Checks if this time is before a received time.
     * @param  other The time to check if this time is before
     * @return true if this time is before other time
     */
    public boolean before (Time1 other){
        if (this.minFromMidnight() <= other.minFromMidnight()) {
            return true;
        }
        return false;
    }

    /** Checks if this time is after a received time.
     * @param  other The time to check if this time is after
     * @return true if this time is after other time
     */
    public boolean after (Time1 other){
        return other.before(this);
    }

    /** Calculates the difference (in minutes) between two times. Assumption: this time is after other time.
     * @param  other The time to check the difference to
     * @return int difference in minutes
     */
    public int difference(Time1 other){
        int thisMinFromMidnight = this.minFromMidnight();
        int otherMinFromMidnight = other.minFromMidnight();
        return thisMinFromMidnight-otherMinFromMidnight;
    }

    /** Adds num Minutes to time.
     * @param  num The number of minutes to add
     * @return the update time
     */
    public Time1 addMinutes(int num){
            int totalMinute = num + this.minFromMidnight();
            int x = Math.abs(totalMinute) % MINUTE_PER_DAY;
            totalMinute = (totalMinute >= 0)? x : MINUTE_PER_DAY - x;
            int minute = totalMinute % MINUTE_PER_HOUR;
            int hour = (totalMinute-minute) / MINUTE_PER_HOUR;
            return new Time1(hour,minute);
    }

}
