import java.io.*;
import java.net.*;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.InvalidMarkException;
import java.nio.ReadOnlyBufferException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import Algorithm.AESExample;
public class GossipClient
{

	static Logger logger;
	FileHandler fh;
	Socket sock;
	SimpleFormatter formatter;

	public GossipClient() throws Exception {
		sock = new Socket("127.0.0.1", 3000);

		logger = Logger.getLogger("MyLog");
		fh = new FileHandler("F:/NIBM/DOC/Software security/CourseWork02/HNDSE Software security Chat App/Client Server Chat With AES/logs/ClientLogFile.log");
		logger.addHandler(fh);
		formatter = new SimpleFormatter();
		fh.setFormatter(formatter);
		logger.setUseParentHandlers(false);
		logger.info("Client Connected.\n");
	}

	public void chat() throws Exception
	{		
		// reading from keyboard (keyRead object)
		BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
		
		// sending to client (pwrite object)
		OutputStream ostream = sock.getOutputStream(); 
		PrintWriter pwrite = new PrintWriter(ostream, true);

		// receiving from server ( receiveRead  object)
		InputStream istream = sock.getInputStream();
		BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));
	

		String receiveMessage, sendMessage, encryptedString, decryptedString;  
		final String secretKey = "ssshhhhhhhhhhh!!!!";

		System.out.println("Start the chitchat, type and press Enter key");
		System.out.println("******This Chat is Encrypted Using AES Algorithm******\n");
	
		while(true)
		{	
			// keyboard reading
			sendMessage = keyRead.readLine();  
			//encrypt the message before send to server	
			encryptedString = AESExample.encrypt(sendMessage, secretKey); 

			// log
			logger.info("Original Message: " + sendMessage + "\n" + "Encrypted Message: " + encryptedString + "\n");

			// sending to server
			pwrite.println(encryptedString);   
			// flush the data    
			pwrite.flush();                    
		
			//receive from server
			if((receiveMessage = receiveRead.readLine()) != null) 
			{
			System.out.println("\nServer: " + receiveMessage); // This is encrypted message
			
			//decrypt the message receiving from server
			decryptedString = AESExample.decrypt(receiveMessage, secretKey); 
			// displaying at DOS prompt
			System.out.println("Server: " + decryptedString + "\n"); 
			}         
		} 
	}
	 
	public static void main(String[] args) 
	{
		try {
			GossipClient GC = new GossipClient();
			GC.chat();

		} catch (ClassNotFoundException exception) {
			System.out.println(exception.getMessage());
			logger.severe(exception.getMessage());
		}
		catch(FileNotFoundException exception)
		{
			System.out.println(exception.getMessage());
			logger.severe(exception.getMessage());
		}
		catch(UTFDataFormatException exception)
		{
			System.out.println(exception.getMessage());
			logger.severe(exception.getMessage());
		}
		catch(UnsupportedEncodingException exception)
		{
			System.out.println(exception.getMessage());
			logger.severe(exception.getMessage());
		}
		catch(InterruptedException exception)
		{
			System.out.println(exception.getMessage());
			logger.severe(exception.getMessage());
		}
		catch(BufferOverflowException exception)
		{
			System.out.println(exception.getMessage());
			logger.severe(exception.getMessage());
		} 
		catch(BufferUnderflowException exception)
		{
			System.out.println(exception.getMessage());
			logger.severe(exception.getMessage());
		}
		catch(InvalidMarkException exception)
		{
			System.out.println(exception.getMessage());
			logger.severe(exception.getMessage());
		}
		catch(ReadOnlyBufferException exception)
		{
			System.out.println(exception.getMessage());
			logger.severe(exception.getMessage());
		}
		catch(PortUnreachableException exception)
		{
			System.out.println(exception.getMessage());
			logger.severe(exception.getMessage());
		}
		catch(ProtocolException exception)
		{
			System.out.println(exception.getMessage());
			logger.severe(exception.getMessage());
		}
		catch(SocketTimeoutException exception)
		{
			System.out.println(exception.getMessage());
			logger.severe(exception.getMessage());
		}
		catch(ObjectStreamException exception)
		{
			System.out.println(exception.getMessage());
			logger.severe(exception.getMessage());
		}
		catch(SocketException exception) 
		{
			System.out.println(exception.getMessage());
			logger.severe(exception.getMessage());
		}
		catch(IOException exception)
		{
			System.out.println(exception.getMessage());
			logger.severe(exception.getMessage());
		}
		catch(Exception exception)
		{
			System.out.println(exception.getMessage());
			logger.severe(exception.getMessage());
		}   
	}                    
}            