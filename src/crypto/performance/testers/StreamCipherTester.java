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
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import performance.SimpleMeter;

/**
 *
 * @author Victor de Lima Soares
 * @version 1.0
 */
public class StreamCipherTester extends TimeTester {

    /**
     * Executes the default test: 
     * 
     * <p>
     * Stream ciphers derived from block ciphers:
     * DESede, AES and RC2 operating on CFB.
     * </p>
     *
     * @since 1.0
     */
    public static void run() {

        Libraries.registerProviders();

        File file = new File("data/colors.jpg");
        File results = new File("data/results/stream ciphers.txt");
        int nTests = 10;

        TimeTester blockTester = new BlockCipherTester();

        //Libraries
        String[] providers = new String[]{"BC","FlexiCore", "SunJCE"};

        System.out.println("Evaluating stream ciphers...");

        try (PrintStream out = new PrintStream(results)) {

            //3DES CFB
            blockTester.execTests(nTests, file, out, "DESede/CFB/PKCS5Padding", providers);
            //AES CFB
            blockTester.execTests(nTests, file, out, "AES/CFB/PKCS5Padding", providers);
            //RC2 CFB
            blockTester.execTests(nTests, file, out, "RC2/CFB/PKCS5Padding", providers);

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
     * @throws java.security.NoSuchProviderException
     * @throws java.security.NoSuchAlgorithmException
     */
    @Override
    public SimpleMeter test(File baseInput, String algorithm, String provider)
            throws NoSuchProviderException, NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException {

        Cipher cipherEncryption;
        Cipher cipherDecription;
        SimpleMeter meter = new SimpleMeter();

        SecureRandom randGenerator = new SecureRandom();
        KeyGenerator generator;

        generator = KeyGenerator.getInstance(algorithm.split("/")[0], provider);
        generator.init(randGenerator);
        SecretKey key = generator.generateKey();

        cipherEncryption = Cipher.getInstance(algorithm, provider);
        cipherEncryption.init(Cipher.ENCRYPT_MODE, key);

        cipherDecription = Cipher.getInstance(algorithm, provider);

        if (cipherEncryption.getIV() != null) {
            cipherDecription.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(cipherEncryption.getIV()));
        } else {
            cipherDecription.init(Cipher.DECRYPT_MODE, key);
        }

        try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(baseInput))) {

            CipherInputStream encryptionStream = new CipherInputStream(input, cipherEncryption);
            CipherInputStream decryptionStream = new CipherInputStream(encryptionStream, cipherDecription);

            meter.start();

            while (decryptionStream.read() > -1);

            decryptionStream.close();//End all encryption and decryption operation

            meter.stop();
        } catch (Exception ex) {
            ex.printStackTrace();
            meter = null;
        }

        return meter;
    }

}
