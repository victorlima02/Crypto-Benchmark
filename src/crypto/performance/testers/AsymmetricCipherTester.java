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
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import performance.SimpleMeter;

/**
 * Test and measure performance for asymmetric ciphers.
 *
 * @author Victor de Lima Soares
 * @version 1.0
 */
public class AsymmetricCipherTester extends TimeTester{

    /**
     * Executes the default test: RSA, ECIES.
     *
     * <p>
     * To encrypt large amounts of data, the ciphers where used as ECB, manually
     * created. And for diversity, RSA was used with varying padding schemes
     * applied on each iteration of the ECB mode along with 126 byte buffers.
     * </p>
     *
     * @since 1.0
     */
    public static void run() {

        Libraries.registerProviders();

        File file = new File("data/colors.jpg");
        File results = new File("data/results/asymmetric ciphers.txt");
        int nTests = 5;

        TimeTester asymTester = new AsymmetricCipherTester();

        //Libraries
        String[] providers;

        System.out.println("Evaluating asymmetric ciphers...");

        try (PrintStream out = new PrintStream(results)) {

            //RSA - PKCS1Padding
            providers = new String[]{"BC", "FlexiCore", "SunJCE"};
            asymTester.execTests(nTests, file, out, "RSA/ECB/PKCS1Padding", 2048, providers);
            //RSA - OAEPWithSHA1AndMGF1Padding
            providers = new String[]{ "BC", "FlexiCore","SunJCE"};
            asymTester.execTests(nTests, file, out, "RSA/ECB/OAEPWithSHA1AndMGF1Padding", 2048, providers);
            //RSA - OAEPWithSHA-224AndMGF1Padding
            providers = new String[]{"BC", "FlexiCore", "SunJCE"};
            asymTester.execTests(nTests, file, out, "RSA/ECB/OAEPWithSHA-224AndMGF1Padding", 2048, providers);
            //RSA - OAEPWithSHA512AndMGF1Padding
            providers = new String[]{ "BC", "FlexiCore","SunJCE"};
            asymTester.execTests(nTests, file, out, "RSA/ECB/OAEPWithSHA-512AndMGF1Padding", 2048, providers);
            //ECIES
            providers = new String[]{"BC", "FlexiEC"};
            asymTester.execTests(nTests, file, out, "ECIES", 256, providers);

        } catch (FileNotFoundException | NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException ex) {
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
     *
     * @throws UnsupportedOperationException
     */
    @Override
    public SimpleMeter test(File baseInput, String algorithm, String provider)
            throws NoSuchProviderException, NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException {
        throw new UnsupportedOperationException("For asymmetric ciphers a key size needs to be defined.");
    }

    /**
     * Test the specified scheme.
     *
     * @since 1.0
     * @param baseInput Input file to run the tests.
     * @param algorithm Algorithm to be used.
     * @param keySize Key size.
     * @param provider Library from where the algorithm comes.
     * @return A SimpleMeter containing the time measurements.
     *
     * @throws java.security.NoSuchProviderException
     * @throws java.security.NoSuchAlgorithmException
     */
    @Override
    public SimpleMeter test(File baseInput, String algorithm, int keySize, String provider)
            throws NoSuchProviderException, NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException {

        Cipher cipherEncryption;
        Cipher cipherDecription;
        SimpleMeter meter = new SimpleMeter();

        SecureRandom randGenerator = new SecureRandom();
        KeyPairGenerator generator;

        if (provider.equals("SunJCE") && algorithm.split("/")[0].equals("RSA")) {
            generator = KeyPairGenerator.getInstance(algorithm.split("/")[0], "SunRsaSign");
        } else {
            generator = KeyPairGenerator.getInstance(algorithm.split("/")[0], provider);
        }

        generator.initialize(keySize, randGenerator);
        KeyPair key = generator.generateKeyPair();
        PrivateKey privKey = key.getPrivate();
        PublicKey pubKey = key.getPublic();

        cipherEncryption = Cipher.getInstance(algorithm, provider);
        cipherEncryption.init(Cipher.ENCRYPT_MODE, pubKey);

        cipherDecription = Cipher.getInstance(algorithm, provider);
        cipherDecription.init(Cipher.DECRYPT_MODE, privKey);

        byte inputBuffer[] = new byte[126];

        byte[] bufferTmp;
        int lenght;

        try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(baseInput))) {

            meter.start();

            while ((lenght = input.read(inputBuffer)) > -1) {
                bufferTmp = cipherEncryption.doFinal(inputBuffer, 0, lenght);
                cipherDecription.doFinal(bufferTmp);
            }

            meter.stop();
        } catch (Exception ex) {
            ex.printStackTrace();
            meter = null;
        }

        return meter;
    }
}
