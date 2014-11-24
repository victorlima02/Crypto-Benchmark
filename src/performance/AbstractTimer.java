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
public abstract class AbstractTimer implements Timer {

    private long start;
    private long end;

    /**
     * Access starting time.
     *
     * @since 1.0
     * @return Stating time in nanoseconds.
     */
    @Override
    public final long getStart() {
        return start;
    }

    /**
     * Access ending time.
     *
     * @since 1.0
     * @return Ending time in nanoseconds.
     */
    @Override
    public final long getEnd() {
        return end;
    }

    /**
     * Access elapsed time, from when the meter was started to when it was
     * finalized.
     *
     * @since 1.0
     * @return Elapsed time in nanoseconds.
     */
    @Override
    public final long getTime() {
        return end - start;
    }

    /**
     * Set stating time.
     * 
     * @since 1.0
     * @param start The start to set.
     */
    public final void setStart(long start) {
        this.start = start;
    }

    /**
     * Set ending time.
     * 
     * @since 1.0
     * @param end The end to set.
     */
    public final void setEnd(long end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return Long.toString(getTime());
    }
      
}
