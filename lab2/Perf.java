
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
// Joshua Beaulieu
/*
* To Compile: place the DesEncrypter.java and this (Perf.java) in the same directory
*             and type:    javac *.java
*
* To Execute: java Perf
*/

public class Perf {

	public static void main(String [] args) {

		try {
			/***********************************************************************
			*       ENCRYPT AND THEN DECRYPT USING DES
			***********************************************************************/
			DesEncrypter DES = new DesEncrypter("test passphrase", "PBEWithMD5AndDES" );
			int s = 1024*100000;

			ByteArrayInputStream  bais  = new ByteArrayInputStream(new byte[s]);
			ByteArrayOutputStream baos  = new ByteArrayOutputStream();
			ByteArrayOutputStream baos2  = new ByteArrayOutputStream();

			//long start= System.currentTimeMillis();

			for(int i=0;i<400; i++){
				long start= System.nanoTime();
				for(int j=0;j<100; j++){
					DES.encrypt(bais, baos);
					DES.decrypt(new ByteArrayInputStream(baos.toByteArray()), baos2);
					//bais.reset();
					baos.reset();
					baos2.reset();
				}
				long end= System.nanoTime();
				long elaspedTime = (end - start) / 100;

				System.out.println("DES,"+elaspedTime);
			}





			/***********************************************************************
			*       ENCRYPT AND THEN DECRYPT USING Triple DES
			***********************************************************************/
			DesEncrypter TDES = new DesEncrypter("test passphrase", "PBEWithMD5AndTripleDES" );
			bais  = new ByteArrayInputStream(new byte[s]);
			baos  = new ByteArrayOutputStream();
			baos2  = new ByteArrayOutputStream();

			for(int i=0;i<400; i++){
				long start= System.nanoTime();
				for(int j=0;j<100; j++){
					TDES.encrypt(bais, baos);
					TDES.decrypt(new ByteArrayInputStream(baos.toByteArray()), baos2);
					//bais.reset();
					baos.reset();
					baos2.reset();
				}
				long end= System.nanoTime();
				long elaspedTime = (end - start) / 100;

				System.out.println("TDES,"+elaspedTime);
			}


		}
		catch (Exception e) { System.err.println(e); }
	}
}
