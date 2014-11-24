/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
}
