/*
 * This code was written for an assignment for concept demonstration purposes:
 *  caution required
 *
 * The MIT License
 *
 * Copyright 2014 Victor de Lima Soares.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package performance;

/**
 * Class to perform performance measurements.
 *
 * <p>
 * This class should be used to perform measurements between two points on a
 * single thread flux.
 * </p>
 *
 * <h3>Measures:</h3>
 * <ul>
 * <li>Clock time: time elapsed between the start and end of measurements;</li>
 * <li>User time: is the time spent running application's own code;</li>
 * <li>System time: is the time spent running OS code on behalf of the
 * application; e.g., I/O;</li>
 * <li>CPU time: is user time plus system time. It is the total time spent using
 * any CPU for the application;</li>
 * </ul>
 *
 * <p>
 * Nanosecond precision but not necessarily nanosecond accuracy (platform
 * dependent).
 * </p>
 *
 * @author Victor de Lima Soares
 * @version 1.0
 *
 * @see ClockTimer
 * @see CpuTimer
 * @see UserTimer
 * @see SystemTimer
 */
public class SimpleMeter {

    private final ClockTimer clockTimer;
    private final CpuTimer cpuTimer;
    private final UserTimer userTimer;
    private final SystemTimer systemTimer;

    public SimpleMeter() {
        clockTimer = new ClockTimer();
        cpuTimer = new CpuTimer();
        userTimer = new UserTimer();
        systemTimer = new SystemTimer();
    }

    /**
     * Starts the meter.
     *
     * @since 1.0
     */
    public void start() {
        startClockTimer();
        startCpuTimer();
        startUserTimer();
        startSystemTimer();
    }

    /**
     * Stop the meter.
     *
     * @since 1.0
     */
    public void stop() {
        stopClockTimer();
        stopCpuTimer();
        stopUserTimer();
        stopSystemTimer();
    }

    /**
     * Start measuring clock time.
     *
     * <p>
     * Precision is given in nanoseconds.
     * </p>
     *
     * @since 1.0
     */
    private void startClockTimer() {
        clockTimer.start();
    }

    /**
     * Stop measuring clock time.
     *
     * <p>
     * Precision is given in nanoseconds.
     * </p>
     *
     * @since 1.0
     */
    private void stopClockTimer() {
        clockTimer.stop();
    }

    /**
     * Access elapsed time, from when the meter was started to when it was
     * finalized.
     *
     * @since 1.0
     * @return elapsed time in nanoseconds.
     */
    public long getClockTime() {
        return clockTimer.getTime();
    }

    /**
     * Access elapsed time, from when the meter was started until now.
     *
     * @since 1.0
     * @return elapsed time in nanoseconds.
     */
    public long getElapsedTimeNow() {
        return clockTimer.getTimeNow();
    }

    /**
     * Start measuring CPU time.
     *
     * <p>
     * CPU time: is user time plus system time. It is the total time spent using
     * any CPU for the application.
     * </p>
     * <p>
     * Precision is given in nanoseconds.
     * </p>
     *
     * @since 1.0
     */
    private void startCpuTimer() {
        cpuTimer.start();
    }

    /**
     * Stop measuring CPU time.
     *
     * <p>
     * Precision is given in nanoseconds.
     * </p>
     *
     * @since 1.0
     */
    private void stopCpuTimer() {
        cpuTimer.stop();
    }

    /**
     * Access CPU time, from when the meter was started to when it was
     * finalized.
     *
     * @since 1.0
     * @return CPU time in nanoseconds.
     */
    public long getCpuTime() {
        return cpuTimer.getTime();
    }

    /**
     * Access CPU time, from when the meter was started until now.
     *
     * @since 1.0
     * @return CPU time in nanoseconds.
     */
    public long getCpuTimeNow() {
        return cpuTimer.getTimeNow();
    }

    /**
     * Start measuring user time.
     *
     * <p>
     * User time: is the time spent running application's own code.
     * </p>
     *
     * <p>
     * Precision is given in nanoseconds.
     * </p>
     *
     * @since 1.0
     */
    private void startUserTimer() {
        userTimer.start();
    }

    /**
     * Stop measuring user time.
     *
     * <p>
     * Precision is given in nanoseconds.
     * </p>
     *
     * @since 1.0
     */
    private void stopUserTimer() {
        userTimer.stop();
    }

    /**
     * Access user time, from when the meter was started to when it was
     * finalized.
     *
     * @since 1.0
     * @return User time in nanoseconds.
     */
    public long getUserTime() {
        return userTimer.getTime();
    }

    /**
     * Access user time, from when the meter was started until now.
     *
     * @since 1.0
     * @return User time in nanoseconds.
     */
    public long getUserTimeNow() {
        return userTimer.getTimeNow();
    }

    /**
     * Start measuring system time.
     *
     * <p>
     * Precision is given in nanoseconds.
     * </p>
     *
     * @since 1.0
     */
    private void startSystemTimer() {
        systemTimer.start();
    }

    /**
     * Stop measuring system time.
     *
     * <p>
     * Precision is given in nanoseconds.
     * </p>
     *
     * @since 1.0
     */
    private void stopSystemTimer() {
        systemTimer.stop();
    }

    /**
     * Access system time, from when the meter was started to when it was
     * finalized.
     *
     * @since 1.0
     * @return System time in nanoseconds.
     */
    public long getSystemTime() {
        return systemTimer.getTime();
    }

    /**
     * Access system time, from when the meter was started until now.
     *
     * @since 1.0
     * @return User time in nanoseconds.
     */
    public long getSystemTimeNow() {
        return systemTimer.getTimeNow();
    }

    /**
     * Return the string representation for all timers.
     * <p>
     * The string will follow the order: clock, CPU, user and system times
     * ("%d\t%d\t%d\t%d\n").
     * </p>
     *
     * @since 1.0
     * @return String representation for the timers.
     */
    @Override
    public String toString() {
        return String.format("%d\t%d\t%d\t%d\n", clockTimer, cpuTimer, userTimer, systemTimer);
    }

}
