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

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

/**
 * Class to perform system time measurements.
 *
 * <p>
 * This class should be used to perform measurements between two points on a
 * single thread flux.
 * </p>
 *
 * <h3>Measures:</h3>
 * <ul>
 * <li>System time: is the time spent running OS code on behalf of the
 * application; e.g., I/O;</li>
 * </ul>
 *
 * <p>
 * Nanosecond precision but not necessarily nanosecond accuracy (platform
 * dependent).
 * </p>
 *
 * @author Victor de Lima Soares
 * @version 1.0
 */
public class SystemTimer extends AbstractTimer{

    /**
     * Management bean to consult.
     *
     * @since 1.0
     */
    private final ThreadMXBean management = ManagementFactory.getThreadMXBean();

    /**
     * Start measuring system time.
     *
     * <p>
     * System time: is the time spent running OS code on behalf of the
     * application; e.g., I/O.
     * </p>
     *
     * <p>
     * Precision is given in nanoseconds.
     * </p>
     *
     * @since 1.0
     */
    @Override
    public void start() {
        setStart(management.isCurrentThreadCpuTimeSupported()
                ? (management.getCurrentThreadCpuTime() - management.getCurrentThreadUserTime()) : 0L);
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
    @Override
    public void stop() {
        setEnd(management.isCurrentThreadCpuTimeSupported()
                ? (management.getCurrentThreadCpuTime() - management.getCurrentThreadUserTime()) : 0L);
    }

    /**
     * Access system time, from when the meter was started until now.
     *
     * @since 1.0
     * @return User time in nanoseconds.
     */
    @Override
    public long getTimeNow() {
        return management.isCurrentThreadCpuTimeSupported()
                ? (management.getCurrentThreadCpuTime() - management.getCurrentThreadUserTime()) - getStart() : 0L;
    }
}
