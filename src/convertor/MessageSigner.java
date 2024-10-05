package convertor;

import java.io.*;
import java.security.*;
import java.security.cert.*;
import java.security.cert.Certificate;
import java.util.*;
import org.bouncycastle.cms.*;
import org.bouncycastle.asn1.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder;
import org.bouncycastle.util.Store;
import sun.misc.BASE64Encoder;
import org.enhydra.oyster.exception.SMIMEException;
import org.enhydra.oyster.util.PFXUtils;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MessageSigner {

	private static final Logger logger = Logger.getLogger(Signer.class.getName());
    private static final String HEXES = "0123456789ABCDEF";
    private static PrivateKey pk = null;
    private static X509Certificate cert = null;
    private static String alias = "MyAlias";
    private static Date expDate = null;
    private static int daysLeft;
    private static CertStore certes = null;
    private static ArrayList<Certificate> certList = new ArrayList<>();

    
    public MessageSigner(String pass) {
    	
    	try {
            logger.setLevel(Level.ALL);
            FileHandler fh = new FileHandler(".//log//app.log");
            logger.addHandler(fh);
            fh.setFormatter(new SimpleFormatter());
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error creating log file", e);
        }
        try {
            System.out.println("Inside Message Signed : " + pass);
            Security.addProvider(new BouncyCastleProvider());
            KeyStore newKs = getKeyStore(false, ".\\TEAMSHA2.pfx", pass);
            String alias1 = (String) newKs.aliases().nextElement();
            pk = getPrivateKey(newKs, alias1, pass);
            System.out.println("PK: " + pk);

            X509Certificate[] certs = PFXUtils.getAllX509Certificate(newKs);
            alias = alias1;
            expDate = ((X509Certificate) newKs.getCertificate(alias)).getNotAfter();
            Date date1 = new Date();
            daysLeft = (int) ((expDate.getTime() - date1.getTime()) / 86400000L);

            cert = (X509Certificate) newKs.getCertificate(alias);
            System.out.println("Certificates: " + cert);

            Certificate[] certChain = newKs.getCertificateChain(alias);
            certList.addAll(Arrays.asList(certChain));
            certes = CertStore.getInstance("Collection", new CollectionCertStoreParameters(certList), "BC");

        } catch (GeneralSecurityException | IOException | SMIMEException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static KeyStore getKeyStore(boolean paramBoolean, String paramString1, String paramString2)
            throws KeyStoreException, NoSuchAlgorithmException, IOException, CertificateException {
        KeyStore localKeyStore = null;
        char[] arrayOfChar = paramString2.toCharArray();
        if (!paramBoolean) {
            System.out.println("Loading keystore from signing file");
            try (FileInputStream localFileInputStream = new FileInputStream(paramString1)) {
                localKeyStore = KeyStore.getInstance("pkcs12");
                localKeyStore.load(localFileInputStream, arrayOfChar);
            }
        }
        return localKeyStore;
    }

    private static PrivateKey getPrivateKey(KeyStore ks, String alias, String password)
            throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException {
        return (PrivateKey) ks.getKey(alias, password.toCharArray());
    }

    public static String getRtgsSignature(String data, String pass) {
		
		new MessageSigner(pass);
        byte[] content = data.getBytes();
        StringBuilder buffer = new StringBuilder();
        Security.addProvider(new BouncyCastleProvider());

        try {
            CMSSignedDataGenerator generator = new CMSSignedDataGenerator();
            generator.addSigner(pk, cert, CMSSignedDataGenerator.DIGEST_MD5);
            generator.addCertificatesAndCRLs(certes);
            CMSSignedData signedData = generator.generate(new CMSProcessableByteArray(content), false, "BC");
            byte[] csd = signedData.getEncoded();
            byte[] signedMessage = Base64.encode(csd);

            for (byte b : signedMessage) {
                buffer.append((char) b);
            }

        } catch (GeneralSecurityException | CMSException | IOException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return buffer.toString();
    }

    public static String getNeftSignature(String data, String pass) {
    	
    	new MessageSigner(pass);
    
        byte[] content = data.getBytes();
        StringBuilder buffer = new StringBuilder();
        Security.addProvider(new BouncyCastleProvider());

        try {
            CMSSignedDataGenerator generator = new CMSSignedDataGenerator();
            generator.addSigner(pk, cert, CMSSignedDataGenerator.DIGEST_MD5);
            generator.addCertificatesAndCRLs(certes);
            CMSSignedData signedData = generator.generate(new CMSProcessableByteArray(content), true, "BC");
            byte[] csd = signedData.getEncoded();
            byte[] signedMessage = Base64.encode(csd);

            for (byte b : signedMessage) {
                buffer.append((char) b);
            }

        } catch (GeneralSecurityException | CMSException | IOException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return buffer.toString();
    }

    public static String getLcSignature(String data, String pass) throws OperatorCreationException {
		
		new MessageSigner(pass);
        String stringbuffer = "";
        Security.addProvider(new BouncyCastleProvider());

        try {
            Signature signature = Signature.getInstance("SHA1WithRSA", "BC");
            signature.initSign(pk);
            signature.update(data.getBytes());
            CMSTypedData msg = new CMSProcessableByteArray(signature.sign());
            Store certs = new JcaCertStore(certList);
            CMSSignedDataGenerator gen = new CMSSignedDataGenerator();
            ContentSigner sha1Signer = new JcaContentSignerBuilder("SHA1withRSA").setProvider("BC").build(pk);
            gen.addSignerInfoGenerator(new JcaSignerInfoGeneratorBuilder(new JcaDigestCalculatorProviderBuilder().setProvider("BC").build()).build(sha1Signer, cert));
            gen.addCertificates(certs);

            CMSSignedData sigData = gen.generate(msg, true);
            BASE64Encoder encoder = new BASE64Encoder();
            stringbuffer = encoder.encode(sigData.getEncoded());

        } catch (GeneralSecurityException | CMSException | IOException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        return stringbuffer;
    }
}
