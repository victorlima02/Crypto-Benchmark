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
 *
 * @author Victor de Lima Soares
 * @version 1.0
 */
public interface Timer {

    /**
     * Start measuring time.
     *
     * <p>
     * Precision is given in nanoseconds.
     * </p>
     *
     * @since 1.0
     */
    void start();

    /**
     * Stop measuring clock time.
     *
     * @since 1.0
     */
    void stop();

    /**
     * Access starting time.
     *
     * @since 1.0
     * @return Stating time in nanoseconds.
     */
    long getStart();

    /**
     * Access ending time.
     *
     * @since 1.0
     * @return Ending time in nanoseconds.
     */
    long getEnd();

    /**
     * Access elapsed time, from when the meter was started to when it was
     * finalized.
     *
     * @since 1.0
     * @return Elapsed time in nanoseconds.
     */
    long getTime();

    /**
     * Access elapsed time, from when the meter was started until now.
     *
     * @since 1.0
     * @return CPU time in nanoseconds.
     */
    long getTimeNow();
}
