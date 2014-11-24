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
package crypto.performance.testers;

import performance.TimerSummary;

/**
 * Executes automated tests on pre-configured cipher.
 *
 * <p>
 * For all ciphers and hashes, the program will collect time related performance
 * metrics, recording all experiments on ./data/results.
 * </p>
 *
 * <p>
 * For ciphers information, see the proper Tester, where the default tests are
 * configurated.
 * </p>
 *
 * <p>
 * All performance metrics are collect by a self-contained performance library,
 * developed for this project. Measuring, only when encryption/decryption
 * operations occur.
 * </p>
 *
 * @author Victor de Lima Soares
 * @version 1.0
 *
 * @see BlockCipherTester
 * @see HashTester
 * @see AsymmetricCipherTester
 * @see StreamCipherTester
 * @see TimerSummary
 */
public class GeneralTester {

    public static void main(String[] args) {
        BlockCipherTester.run();
        HashTester.run();
        AsymmetricCipherTester.run();
        StreamCipherTester.run();
    }
}
