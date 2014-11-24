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

import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

/**
 * A summary for several measurements.
 *
 * @author Victor de Lima Soares
 * @version 1.0
 */
public class TimerSummary {

    private long nTimes;

    private long clockTime;
    private long cpuTime;
    private long userTime;
    private long systemTime;

    /**
     * Optional name for this summary.
     *
     * @since 1.0
     */
    private String name;

    public TimerSummary() {
    }

    /**
     * constructor for a named summary.
     *
     * @since 1.0
     * @param summaryName Optional name for this summary.
     */
    public TimerSummary(String summaryName) {
        setName(summaryName);
    }

    /**
     * Adds a SimpleMeter's measurements into the summary.
     *
     * @since 1.0
     * @param meter
     */
    public void addSimpleMeter(SimpleMeter meter) {
        addNTimes(1);
        addClockTime(meter.getClockTime());
        addCpuTime(meter.getCpuTime());
        addUserTime(meter.getUserTime());
        addSystemTime(meter.getSystemTime());
    }

    /**
     * Increments the numbers of measurements.
     *
     * @since 1.0
     * @param nTimes
     */
    private void addNTimes(long nTimes) {
        this.nTimes += nTimes;
    }

    /**
     * Adds to the clock time total.
     *
     * @since 1.0
     * @param clockTime
     */
    private void addClockTime(long clockTime) {
        this.clockTime += clockTime;
    }

    /**
     * Adds to the CPU time total.
     *
     * @since 1.0
     * @param cpuTime
     */
    private void addCpuTime(long cpuTime) {
        this.cpuTime += cpuTime;
    }

    /**
     * Adds to the user time total.
     *
     * @since 1.0
     * @param userTime
     */
    private void addUserTime(long userTime) {
        this.userTime += userTime;
    }

    /**
     * Adds to the system time total.
     *
     * @since 1.0
     * @param systemTime
     */
    private void addSystemTime(long systemTime) {
        this.systemTime += systemTime;
    }

    /**
     * Returns the number of experiments.
     *
     * @since 1.0
     * @return Number of experiments.
     */
    public long getNTimes() {
        return nTimes;
    }

    /**
     * Returns the clock time total.
     *
     * @since 1.0
     * @return Clock time total.
     */
    public long getClockTime() {
        return clockTime;
    }

    /**
     * Returns the clock time total.
     *
     * @since 1.0
     * @param unit Desired unit.
     * @return Clock time total.
     */
    public long getClockTime(TimeUnit unit) {
        return unit.convert(getClockTime(), TimeUnit.NANOSECONDS);
    }

    /**
     * Returns the CPU time total.
     *
     * @since 1.0
     * @return CPU time total.
     */
    public long getCpuTime() {
        return cpuTime;
    }

    /**
     * Returns the CPU time total.
     *
     * @since 1.0
     * @param unit Desired unit.
     * @return CPU time total.
     */
    public long getCpuTime(TimeUnit unit) {
        return unit.convert(getCpuTime(), TimeUnit.NANOSECONDS);
    }

    /**
     * Returns the user time total.
     *
     * @since 1.0
     * @return User time total.
     */
    public long getUserTime() {
        return userTime;
    }

    /**
     * Returns the user time total.
     *
     * @since 1.0
     * @param unit Desired unit.
     * @return User time total.
     */
    public long getUserTime(TimeUnit unit) {
        return unit.convert(getUserTime(), TimeUnit.NANOSECONDS);
    }

    /**
     * Returns the system time total.
     *
     * @since 1.0
     * @return System time total.
     */
    public long getSystemTime() {
        return systemTime;
    }

    /**
     * Returns the system time total.
     *
     * @since 1.0
     * @param unit Desired unit.
     * @return System time total.
     */
    public long getSystemTime(TimeUnit unit) {
        return unit.convert(getSystemTime(), TimeUnit.NANOSECONDS);
    }

    /**
     * Returns the clock time average.
     *
     * @since 1.0
     * @return Clock time total divided by the number of experiments.
     */
    public double getClockTimeAvg() {
        return ((double) getClockTime()) / getNTimes();
    }

    /**
     * Returns the clock time average.
     *
     * @since 1.0
     * @param unit Desired unit.
     * @return Clock time total divided by the number of experiments.
     */
    public double getClockTimeAvg(TimeUnit unit) {
        return ((double) getClockTime(unit)) / getNTimes();
    }

    /**
     * Returns the CPU time average.
     *
     * @since 1.0
     * @return CPU time total divided by the number of experiments.
     */
    public double getCpuTimeAvg() {
        return ((double) getCpuTime()) / getNTimes();
    }

    /**
     * Returns the CPU time average.
     *
     * @since 1.0
     * @param unit Desired unit.
     * @return CPU time total divided by the number of experiments.
     */
    public double getCpuTimeAvg(TimeUnit unit) {
        return ((double) getCpuTime(unit)) / getNTimes();
    }

    /**
     * Returns the user time average.
     *
     * @since 1.0
     * @return user time total divided by the number of experiments.
     */
    public double getUserTimeAvg() {
        return ((double) getUserTime()) / getNTimes();
    }

    /**
     * Returns the user time average.
     *
     * @since 1.0
     * @param unit Desired unit.
     * @return user time total divided by the number of experiments.
     */
    public double getUserTimeAvg(TimeUnit unit) {
        return ((double) getUserTime(unit)) / getNTimes();
    }

    /**
     * Returns the system time average.
     *
     * @since 1.0
     * @return System time total divided by the number of experiments.
     */
    public double getSystemTimeAvg() {
        return ((double) getSystemTime()) / getNTimes();
    }

    /**
     * Returns the system time average.
     *
     * @since 1.0
     * @param unit Desired unit.
     * @return System time total divided by the number of experiments.
     */
    public double getSystemTimeAvg(TimeUnit unit) {
        return ((double) getSystemTime(unit)) / getNTimes();
    }

    /**
     * Returns the string representation for all averages.
     * <p>
     * The string will follow the order: n experiments, clock, CPU, user and
     * system times ("%f\t%f\t%f\t%f\t%f\n").
     * </p>
     *
     * @since 1.0
     * @return String representation for the averages.
     */
    @Override
    public String toString() {
        return String.format("%d\t%f\t%f\t%f\t%f\n",
                getNTimes(),
                getClockTimeAvg(),
                getCpuTimeAvg(),
                getUserTimeAvg(),
                getSystemTimeAvg()
        );
    }

    /**
     * Returns the string representation for all averages.
     * <p>
     * The string will follow the order: n experiments, clock, CPU, user and
     * system times ("%f\t%f\t%f\t%f\t%f\n").
     * </p>
     *
     * @since 1.0
     * @param unit Desired unit.
     * @return String representation for the averages.
     */
    public String toString(TimeUnit unit) {
        return String.format("%d\t%f\t%f\t%f\t%f\n",
                getNTimes(),
                getClockTimeAvg(unit),
                getCpuTimeAvg(unit),
                getUserTimeAvg(unit),
                getSystemTimeAvg(unit)
        );
    }

    /**
     * Returns the string representation for all averages.
     * <p>
     * The string will follow the order: n experiments, clock, CPU, user and
     * system times ("%f\t%f\t%f\t%f\t%f\n").
     * </p>
     *
     * @since 1.0
     * @param unit Desired unit.
     * @param precision Desired precision, for float point string
     * representation.
     * @return String representation for the averages.
     */
    public String toString(TimeUnit unit, int precision) {
        return String.format("%15d\t%10." + precision + "f\t%10." + precision + "f\t%10." + precision + "f\t%11." + precision + "f\n",
                getNTimes(),
                getClockTimeAvg(unit),
                getCpuTimeAvg(unit),
                getUserTimeAvg(unit),
                getSystemTimeAvg(unit)
        );
    }

    /**
     * Returns the header for the string representation for all averages.
     * <p>
     * The string will follow the order: n experiments, clock, CPU, user and
     * system times ("%f\t%f\t%f\t%f\t%f\n").
     * </p>
     *
     * @since 1.0
     * @param unit Desired unit.
     * @return String representation for the averages.
     */
    public String toStringHeader(TimeUnit unit) {
        return String.format("%15s\t%10s\t%10s\t%10s\t%11s\t" + unit.name() + "/experiment",
                "N. Experiments",
                "Clock Time",
                "Cpu Time",
                "User Time",
                "System Time"
        );
    }

    /**
     * Access summary's name.
     *
     * @since 1.0
     * @return Summary's name, if it has one or null, otherwise.
     */
    public String getName() {
        return name;
    }

    /**
     * Attributes a new name for this summary.
     *
     * @since 1.0
     * @param name Summary's new name.
     */
    public final void setName(String name) {
        this.name = name;
    }

    /**
     * Print the summary into the specified output.
     * 
     * @since 1.0
     * @param output Desired destination.
     */
    public void print(PrintStream output) {

        if (getName() != null) {
            output.println(getName());
        }

        output.println(toStringHeader(TimeUnit.MILLISECONDS));
        output.println(toString(TimeUnit.MILLISECONDS, 2));
    }

}
