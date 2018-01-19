package tech.hackerlife.game.util;

import java.io.*;
import java.text.*;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import tech.hackerlife.game.Main;

public class Logger extends FileConstants {
	static String userHomeFolder = System.getProperty("user.home");
	static String logLocation = userHomeFolder + "/.piracy";
	static DateFormat df = new SimpleDateFormat("dd-MM-yy=HH-mm-ss");
	static Date dateobj = new Date();
	static String directory;
	
	public static void setNewDirectory(String directoryName) {
		new File(logLocation).mkdir();
		new File(logLocation + "/" + directoryName).mkdir();
		directory = directoryName;
	}
	
	public static void appendSettings() {
		setNewDirectory("config");
		Document dom;
	    Element e = null;

	    // instance of a DocumentBuilderFactory
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    try {
	        // use factory to get an instance of document builder
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        // create instance of DOM
	        dom = db.newDocument();

	        // create the root element
	        Element rootEle = dom.createElement("settings");

	        // create data elements and place them under root
	        e = dom.createElement("resolution");
	        e.appendChild(dom.createTextNode(Integer.toString(Main.HEIGHT)));
	        rootEle.appendChild(e);

	        e = dom.createElement("windowed");
	        e.appendChild(dom.createTextNode(String.valueOf(!Main.decoration)));
	        rootEle.appendChild(e);

	        e = dom.createElement("fpscounter");
	        e.appendChild(dom.createTextNode(String.valueOf(Main.displayFPS)));
	        rootEle.appendChild(e);

	        dom.appendChild(rootEle);

	        try {
	            Transformer tr = TransformerFactory.newInstance().newTransformer();
	            tr.setOutputProperty(OutputKeys.INDENT, "yes");
	            tr.setOutputProperty(OutputKeys.METHOD, "xml");
	            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	            tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "settings.dtd");
	            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

	            // send DOM to file
	            tr.transform(new DOMSource(dom), 
	                                 new StreamResult(new FileOutputStream(logLocation + "/" + directory + "/" + "settings.xml")));

	        } catch (TransformerException te) {
	            System.out.println(te.getMessage());
	        } catch (IOException ioe) {
	            System.out.println(ioe.getMessage());
	        }
	    } catch (ParserConfigurationException pce) {
	        System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
	    }
	}
	
//	public static void entities(String filePath) {
//		try {
//			BufferedImage image = ImageIO.read(Reader.class.getResource(filePath));
//			Image p = image;
//			return p;
//		} catch(Exception e){
//			throw new RuntimeException(e);
//		}
//	}
	
	public static void logError(Exception x) {
		setNewDirectory("logs");
		dateobj = new Date();
		String date = df.format(dateobj);
		try {
			new File(logLocation + "/" + directory + "/" + date).createNewFile();
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(logLocation + "/" + directory + "/" + date, true)));
			x.printStackTrace(out);
			out.close();
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}

}