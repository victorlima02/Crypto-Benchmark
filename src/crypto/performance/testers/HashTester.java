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

import crypto.performance.Libraries;
import crypto.performance.TimeTester;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.security.DigestInputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.NoSuchPaddingException;
import performance.SimpleMeter;

/**
 * Test and measure performance for Hash functions.
 *
 * @author Victor de Lima Soares
 * @version 1.0
 */
public class HashTester extends TimeTester {

    /**
     * Executes the default test: MD5, SHA-1, SHA-512.
     *
     *
     * @since 1.0
     */
    public static void run() {

        Libraries.registerProviders();

        File file = new File("data/colors.jpg");
        File results = new File("data/results/hash.txt");
        int nTests = 10;

        TimeTester hashTester = new HashTester();

        //Libraries
        String[] providers = new String[]{"BC", "FlexiCore", "SUN"};

        System.out.println("Evaluating hash functions...");

        try (PrintStream out = new PrintStream(results)) {

            hashTester.execTests(nTests, file, out, "MD5", providers);
            hashTester.execTests(nTests, file, out, "SHA-1", providers);
            hashTester.execTests(nTests, file, out, "SHA-512", providers);

        } catch (FileNotFoundException | NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException |
                InvalidKeyException | InvalidAlgorithmParameterException ex) {
            Logger.getLogger(HashTester.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Done.");
    }

    /**
     * Test the specified scheme.
     *
     * @since 1.0
     * @param baseInput Input file to run the tests.
     * @param algorithm Algorithm to be used.
     * @param provider Library from where the algorithm comes.
     * @return A SimpleMeter containing the time measurements.
     */
    @Override
    public SimpleMeter test(File baseInput, String algorithm, String provider)
            throws NoSuchProviderException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {

        MessageDigest hashFunction;
        SimpleMeter meter = new SimpleMeter();

        try {
            hashFunction = MessageDigest.getInstance(algorithm, provider);
        } catch (NoSuchAlgorithmException ex) {//Deals with name variation for algorithms, with our withou -
            String[] withoutMinus = algorithm.split("-", 2);
            hashFunction = MessageDigest.getInstance(withoutMinus[0] + withoutMinus[1], provider);
        }

        try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(baseInput))) {
            DigestInputStream digestStream = new DigestInputStream(input, hashFunction);

            meter.start();

            while (digestStream.read() > -1);

            hashFunction.digest();

            meter.stop();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        meter.stop();
        return meter;
    }

    @Override
    public SimpleMeter testEncryption(File baseInput, String algorithm, String provider) throws NoSuchProviderException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        throw new UnsupportedOperationException("Not supported: hash function.");
    }

    @Override
    public SimpleMeter testDecryption(File baseInput, String algorithm, String provider) throws NoSuchProviderException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        throw new UnsupportedOperationException("Not supported: hash function.");
    }
}
