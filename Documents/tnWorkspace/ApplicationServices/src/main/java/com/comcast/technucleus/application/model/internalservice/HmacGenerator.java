package com.comcast.technucleus.application.model.internalservice;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HmacGenerator {
	
	public static final int SIXTEEN = 16;
	public static final String HASH_ALGORITHM = "hmacsha512";
	
	public static String generateHmacHash(String message) {
		final Logger logger = Logger.getLogger("HmacGenerator");
		String ret = "";
		
			try
			{
				final String hmacKey = "84dddc72a63c84c3af2a399b7a7469b4722b7ac7a43f75346e86fcaf66ff6a5c69d2833f67ff4f4c2edf6419bacc427094c2e322fce2cbb425e91a716b1bf5ce";
				if (logger != null)
				{
					logger.debug("HmacGenerator ::: generateHmacHash ::: validating hmac key");
					logger.debug("HmacGenerator ::: generateHmacHash ::: \tmessage:" + message);
					/*logger.debug("HmacGenerator ::: generateHmacHash ::: \taccountid:" + accountBean.getAccountnumber());
					logger.debug("HmacGenerator ::: generateHmacHash ::: \tworkorderid:" + accountBean.getWorkorderid());
					logger.debug("HmacGenerator ::: generateHmacHash ::: \thmac hash:" + accountBean.getHmac());*/
				} // if
				if (null == hmacKey)
				{
					throw new Exception("HMAC Key not found in properties file");
				} // if

				byte HEX_VALUE2[] = (byte[]) null;
				HEX_VALUE2 = new byte[hmacKey.length() / 2];

				for (int i = 0; i < HEX_VALUE2.length; i++)
				{
					HEX_VALUE2[i] = (byte) Integer.parseInt(hmacKey.substring(2 * i, 2 * i + 2),
							SIXTEEN);
				}

				String resultHmacHash = "";

				final StringBuffer resultHmacReceiver = new StringBuffer();

				final SecretKeySpec secretKeySpec = new SecretKeySpec(HEX_VALUE2, HASH_ALGORITHM);

				final Mac mac = Mac.getInstance(HASH_ALGORITHM);
				mac.init(secretKeySpec);
				final String message2 = message;
				if (logger != null)
				{
					logger.debug("\n\tmessage2:" + message2);
				} // if
				final byte messageFinal[] = mac.doFinal(message2.getBytes());
				final byte encryptedMessage[] = org.apache.commons.codec.binary.Base64
						.encodeBase64(messageFinal, false);

				resultHmacHash = new String(encryptedMessage);

				// the hmac hash that is generated need to be encoded before
				// passing it as a query string.
				// resultHmacHash = URLEncoder.encode(resultHmacHash, "UTF-8");

				resultHmacReceiver.append(resultHmacHash);
				ret = resultHmacReceiver.toString();

				if (logger != null)
				{
					logger.debug("HmacGenerator ::: generateHmacHash ::: \tgenerated hmac:" + ret);
				} // if
			}
			catch (Exception e)
			{
				logger.error(e.getMessage(), e);
			}
			String newret = encodeValue(ret);
		return newret;
	}  

	public static void main (String []args){

		//String x = generateHmacHash("27D1D346-7253-11DF-96E8-94BDDFD72085-A4613D16-725311DF-EDFD72085,1:12 PM");
		//System.out.println(" Hmac generate is ::::: " + x);
	    System.out.println("Hmac for <accountid=9999349164601><workorderid=99993491646011> is " + generateHmacHash("<accountid=8773105100714695><workorderid=10011014188320620001>"));
	}
	
	public static String encodeValue(String message)
    {
      //  final String methodName = CLASSNAME + ".encodeValue] ";
        String encodedValue = null;
        try
        {
            encodedValue = URLEncoder.encode(message, "UTF-8");
          //  logger.debug(methodName + "Value: " + message +"Encoded Value:" + encodedValue);
        }
        catch(UnsupportedEncodingException uee)
        {
      //      logger.debug(methodName + "UnsupportedEncodingException" ,  uee);
        }
        catch(Exception e)
        {
            //logger.debug(methodName + "Exception", e);
        }
        return encodedValue;
    }

}
