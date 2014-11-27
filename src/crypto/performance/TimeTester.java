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
package crypto.performance;

import java.io.File;
import java.io.PrintStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import javax.crypto.NoSuchPaddingException;
import performance.SimpleMeter;
import performance.TimerSummary;

/**
 * Perform time performance tests on JCA algorithms.
 *
 * @author Victor de Lima Soares
 * @version 1.0
 */
public abstract class TimeTester {

    /**
     * Executes n tests on the specified algorithm from the given provider.
     *
     * <p>
     * Executes n sequential tests, collecting time measurements and returning
     * the collected data in a summary.
     * </p>
     *
     * @since 1.0
     * @param nTests Number of tests to run.
     * @param baseInput Input file to run the tests.
     * @param out Results destination.
     * @param algorithm Algorithm to be used.
     * @param providers Libraries from where the algorithms comes.
     */
    public void execTests(int nTests, File baseInput, PrintStream out, String algorithm, String[] providers)
            throws NoSuchProviderException, NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException {

        TimerSummary testsSummary;

        for (String provider : providers) {
            testsSummary = execTests(nTests, baseInput, algorithm, provider);
            testsSummary.print(out);
            out.println();
        }
    }

    /**
     * Executes n tests on the specified algorithm from the given provider.
     *
     * <p>
     * Executes n sequential tests, collecting time measurements and returning
     * the collected data in a summary.
     * </p>
     *
     * @since 1.0
     * @param nTests Number of tests to run.
     * @param baseInput Input file to run the tests.
     * @param out Results destination.
     * @param algorithm Algorithm to be used.
     * @param encryptDecrypt If the tests are to be for encryption or
     * decryption.
     * <ul>
     * <li>true: if the test is for encryption;</li>
     * <li>false: otherwise.</li>
     * </ul>
     * @param providers Libraries from where the algorithms comes.
     */
    public void execTests(int nTests, File baseInput, PrintStream out, String algorithm, boolean encryptDecrypt, String[] providers)
            throws NoSuchProviderException, NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException {

        TimerSummary testsSummary;

        for (String provider : providers) {
            testsSummary = execTests(nTests, baseInput, algorithm, encryptDecrypt, provider);
            testsSummary.print(out);
            out.println();
        }
    }

    /**
     * Executes n tests on the specified algorithm from the given provider.
     *
     * <p>
     * Executes n sequential tests, collecting time measurements and returning
     * the collected data in a summary.
     * </p>
     *
     *
     * @since 1.0
     * @param nTests Number of tests to run.
     * @param baseInput Input file to run the tests.
     * @param out Results destination.
     * @param algorithm Algorithm to be used.
     * @param keySize Key size.
     * @param providers Libraries from where the algorithms comes.
     *
     * @throws java.security.NoSuchProviderException
     * @throws java.security.NoSuchAlgorithmException
     * @throws javax.crypto.NoSuchPaddingException
     * @throws java.security.InvalidAlgorithmParameterException
     * @throws java.security.InvalidKeyException
     */
    public void execTests(int nTests, File baseInput, PrintStream out, String algorithm, int keySize, String[] providers)
            throws NoSuchProviderException, NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException {

        TimerSummary testsSummary;

        for (String provider : providers) {
            testsSummary = execTests(nTests, baseInput, algorithm, keySize, provider);
            testsSummary.print(out);
            out.println();
        }
    }

    /**
     * Executes n tests on the specified algorithm from the given provider.
     *
     * <p>
     * Executes n sequential tests, collecting time measurements and returning
     * the collected data in a summary.
     * </p>
     *
     *
     * @since 1.0
     * @param nTests Number of tests to run.
     * @param baseInput Input file to run the tests.
     * @param out Results destination.
     * @param algorithm Algorithm to be used.
     * @param encryptDecrypt If the tests are to be for encryption or
     * decryption.
     * <ul>
     * <li>true: if the test is for encryption;</li>
     * <li>false: otherwise.</li>
     * </ul>
     * @param keySize Key size.
     * @param providers Libraries from where the algorithms comes.
     *
     * @throws java.security.NoSuchProviderException
     * @throws java.security.NoSuchAlgorithmException
     * @throws javax.crypto.NoSuchPaddingException
     * @throws java.security.InvalidAlgorithmParameterException
     * @throws java.security.InvalidKeyException
     */
    public void execTests(int nTests, File baseInput, PrintStream out, String algorithm, boolean encryptDecrypt, int keySize, String[] providers)
            throws NoSuchProviderException, NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException {

        TimerSummary testsSummary;

        for (String provider : providers) {
            testsSummary = execTests(nTests, baseInput, algorithm, encryptDecrypt, keySize, provider);
            testsSummary.print(out);
            out.println();
        }
    }

    /**
     * Executes n tests on the specified algorithm from the given provider.
     *
     * <p>
     * Executes n sequential tests, collecting time measurements and returning
     * the collected data in a summary.
     * </p>
     *
     * @since 1.0
     * @param nTests Number of tests to run.
     * @param baseInput Input file to run the tests.
     * @param algorithm Algorithm to be used.
     * @param provider Library from where the algorithm comes.
     * @return A Summary containing the time measurements.
     *
     * @throws java.security.NoSuchProviderException
     * @throws java.security.NoSuchAlgorithmException
     * @throws javax.crypto.NoSuchPaddingException
     * @throws java.security.InvalidAlgorithmParameterException
     * @throws java.security.InvalidKeyException
     */
    public TimerSummary execTests(int nTests, File baseInput, String algorithm, String provider)
            throws NoSuchProviderException, NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException {

        TimerSummary summary = new TimerSummary(algorithm + "-" + provider);

        for (int i = 0; i < nTests; i++) {
            summary.addSimpleMeter(test(baseInput, algorithm, provider));
        }

        return summary;
    }

    /**
     * Executes n tests on the specified algorithm from the given provider.
     *
     * <p>
     * Executes n sequential tests, collecting time measurements and returning
     * the collected data in a summary.
     * </p>
     * <p>
     * This method will test encryption <b>or</b> decryption only.
     * </p>
     *
     * @since 1.0
     * @param nTests Number of tests to run.
     * @param baseInput Input file to run the tests.
     * @param algorithm Algorithm to be used.
     * @param encryptDecrypt If the tests are to be for encryption or
     * decryption.
     * <ul>
     * <li>true: if the test is for encryption;</li>
     * <li>false: otherwise.</li>
     * </ul>
     * @param provider Library from where the algorithm comes.
     * @return A Summary containing the time measurements.
     *
     * @throws java.security.NoSuchProviderException
     * @throws java.security.NoSuchAlgorithmException
     * @throws javax.crypto.NoSuchPaddingException
     * @throws java.security.InvalidAlgorithmParameterException
     * @throws java.security.InvalidKeyException
     */
    public TimerSummary execTests(int nTests, File baseInput, String algorithm, boolean encryptDecrypt, String provider)
            throws NoSuchProviderException, NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException {

        TimerSummary summary = new TimerSummary(algorithm + "-" + provider);

        for (int i = 0; i < nTests; i++) {
            if (encryptDecrypt) {
                summary.addSimpleMeter(testEncryption(baseInput, algorithm,  provider));
            } else {
                summary.addSimpleMeter(testDecryption(baseInput, algorithm, provider));
            }
        }

        return summary;
    }

    /**
     * Executes n tests on the specified algorithm from the given provider.
     *
     * <p>
     * Executes n sequential tests, collecting time measurements and returning
     * the collected data in a summary.
     * </p>
     * <p>
     * This method will test encryption and decryption schemes.
     * </p>
     *
     * @since 1.0
     * @param nTests Number of tests to run.
     * @param baseInput Input file to run the tests.
     * @param algorithm Algorithm to be used.
     * @param keySize Key size.
     * @param provider Library from where the algorithm comes.
     * @return A Summary containing the time measurements.
     *
     * @throws java.security.NoSuchProviderException
     * @throws java.security.NoSuchAlgorithmException
     * @throws javax.crypto.NoSuchPaddingException
     * @throws java.security.InvalidAlgorithmParameterException
     * @throws java.security.InvalidKeyException
     */
    public TimerSummary execTests(int nTests, File baseInput, String algorithm, int keySize, String provider)
            throws NoSuchProviderException, NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException {

        TimerSummary summary = new TimerSummary(algorithm + "-" + provider);

        for (int i = 0; i < nTests; i++) {
            summary.addSimpleMeter(test(baseInput, algorithm, keySize, provider));
        }

        return summary;
    }

    /**
     * Executes n tests on the specified algorithm from the given provider.
     *
     * <p>
     * Executes n sequential tests, collecting time measurements and returning
     * the collected data in a summary.
     * </p>
     *
     * <p>
     * This method will test encryption or decryption only.
     * </p>
     *
     * @since 1.0
     * @param nTests Number of tests to run.
     * @param baseInput Input file to run the tests.
     * @param algorithm Algorithm to be used.
     * @param keySize Key size.
     * @param provider Library from where the algorithm comes.
     * @param encryptDecrypt If the tests are to be for encryption or
     * decryption.
     * <ul>
     * <li>true: if the test is for encryption;</li>
     * <li>false: otherwise.</li>
     * </ul>
     * @return A Summary containing the time measurements.
     *
     * @throws java.security.NoSuchProviderException
     * @throws java.security.NoSuchAlgorithmException
     * @throws javax.crypto.NoSuchPaddingException
     * @throws java.security.InvalidAlgorithmParameterException
     * @throws java.security.InvalidKeyException
     */
    public TimerSummary execTests(int nTests, File baseInput, String algorithm, boolean encryptDecrypt, int keySize, String provider)
            throws NoSuchProviderException, NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException {

        TimerSummary summary = new TimerSummary(algorithm + "-" + provider);

        for (int i = 0; i < nTests; i++) {
            if (encryptDecrypt) {
                summary.addSimpleMeter(testEncryption(baseInput, algorithm, keySize, provider));
            } else {
                summary.addSimpleMeter(testDecryption(baseInput, algorithm, keySize, provider));
            }
        }

        return summary;
    }

    /**
     * Test the specified scheme.
     *
     * <p>
     * Execute an encryption and a decryption algorithm, returning the total
     * time performance measurements.
     * </p>
     *
     * @since 1.0
     * @param baseInput Input file to run the tests.
     * @param algorithm Algorithm to be used.
     * @param provider Library from where the algorithm comes.
     * @return A SimpleMeter containing the time measurements.
     *
     * @throws java.security.NoSuchProviderException
     * @throws java.security.NoSuchAlgorithmException
     * @throws javax.crypto.NoSuchPaddingException
     * @throws java.security.InvalidAlgorithmParameterException
     * @throws java.security.InvalidKeyException
     */
    public abstract SimpleMeter test(File baseInput, String algorithm, String provider)
            throws NoSuchProviderException, NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException;

    /**
     * Test the specified scheme.
     *
     * <p>
     * By default, default key sizes are used if this method is not overwritten.
     * </p>
     *
     * <p>
     * Execute an encryption and a decryption algorithm, returning the total
     * time performance measurements.
     * </p>
     *
     * @since 1.0
     * @param baseInput Input file to run the tests.
     * @param algorithm Algorithm to be used.
     * @param keySize Key size.
     * @param provider Library from where the algorithm comes.
     *
     * @return A SimpleMeter containing the time measurements.
     *
     * @throws java.security.NoSuchProviderException
     * @throws java.security.NoSuchAlgorithmException
     * @throws javax.crypto.NoSuchPaddingException
     * @throws java.security.InvalidAlgorithmParameterException
     * @throws java.security.InvalidKeyException
     */
    public SimpleMeter test(File baseInput, String algorithm, int keySize, String provider)
            throws NoSuchProviderException, NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException {
        return test(baseInput, algorithm, provider);
    }

    /**
     * Test the specified scheme, encryption only.
     *
     *
     * <p>
     * Execute an encryption algorithm, returning the total time performance
     * measurements.
     * </p>
     *
     * @since 1.0
     * @param baseInput Input file to run the tests.
     * @param algorithm Algorithm to be used.
     * @param provider Library from where the algorithm comes.
     *
     * @return A SimpleMeter containing the time measurements.
     *
     * @throws java.security.NoSuchProviderException
     * @throws java.security.NoSuchAlgorithmException
     * @throws javax.crypto.NoSuchPaddingException
     * @throws java.security.InvalidAlgorithmParameterException
     * @throws java.security.InvalidKeyException
     */
    public abstract SimpleMeter testEncryption(File baseInput, String algorithm, String provider)
            throws NoSuchProviderException, NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException;

    /**
     * Test the specified scheme, encryption only.
     *
     *
     * <p>
     * Execute an encryption algorithm, returning the total time performance
     * measurements.
     * </p>
     *
     * <p>
     * By default, default key sizes are used if this method is not overwritten.
     * </p>
     *
     * @since 1.0
     * @param baseInput Input file to run the tests.
     * @param algorithm Algorithm to be used.
     * @param keySize Key size.
     * @param provider Library from where the algorithm comes.
     *
     * @return A SimpleMeter containing the time measurements.
     *
     * @throws java.security.NoSuchProviderException
     * @throws java.security.NoSuchAlgorithmException
     * @throws javax.crypto.NoSuchPaddingException
     * @throws java.security.InvalidAlgorithmParameterException
     * @throws java.security.InvalidKeyException
     */
    public SimpleMeter testEncryption(File baseInput, String algorithm, int keySize, String provider)
            throws NoSuchProviderException, NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException {
        return testEncryption(baseInput, algorithm, provider);
    }

    /**
     * Test the specified scheme, decryption only.
     *
     *
     * <p>
     * Execute an decryption algorithm, returning the total time performance
     * measurements.
     * </p>
     *
     * @since 1.0
     * @param baseInput Input file to run the tests.
     * @param algorithm Algorithm to be used.
     * @param provider Library from where the algorithm comes.
     *
     * @return A SimpleMeter containing the time measurements.
     *
     * @throws java.security.NoSuchProviderException
     * @throws java.security.NoSuchAlgorithmException
     * @throws javax.crypto.NoSuchPaddingException
     * @throws java.security.InvalidAlgorithmParameterException
     * @throws java.security.InvalidKeyException
     */
    public abstract SimpleMeter testDecryption(File baseInput, String algorithm, String provider)
            throws NoSuchProviderException, NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException;

    /**
     * Test the specified scheme, decryption only.
     *
     *
     * <p>
     * Execute an decryption algorithm, returning the total time performance
     * measurements.
     * </p>
     *
     * <p>
     * By default, default key sizes are used if this method is not overwritten.
     * </p>
     *
     * @since 1.0
     * @param baseInput Input file to run the tests.
     * @param algorithm Algorithm to be used.
     * @param keySize Key size.
     * @param provider Library from where the algorithm comes.
     *
     * @return A SimpleMeter containing the time measurements.
     *
     * @throws java.security.NoSuchProviderException
     * @throws java.security.NoSuchAlgorithmException
     * @throws javax.crypto.NoSuchPaddingException
     * @throws java.security.InvalidAlgorithmParameterException
     * @throws java.security.InvalidKeyException
     */
    public SimpleMeter testDecryption(File baseInput, String algorithm, int keySize, String provider)
            throws NoSuchProviderException, NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException {
        return testDecryption(baseInput, algorithm, provider);
    }

}
