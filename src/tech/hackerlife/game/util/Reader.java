package tech.hackerlife.game.util;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.xml.parsers.*;
import org.xml.sax.*;
import tech.hackerlife.game.Main;
import org.w3c.dom.*;

public class Reader extends FileConstants {
	
	public static void setNewDirectory(String directoryName) {
		new File(logLocation).mkdir();
		new File(logLocation + "/" + directoryName).mkdir();
		directory = directoryName;
	}

	public static BufferedImage loadBufferedImage(String filePath) {
		try {
			BufferedImage image = ImageIO.read(Reader.class.getResource(filePath));
			return image;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void parseSettings() {
		setNewDirectory("config");
		File settings = new File(logLocation + "/" + directory + "/" + "settings.xml");
		Document dom;
		// Make an instance of the DocumentBuilderFactory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			//create files if they don't exist
			if (!settings.exists()) {
				settings.createNewFile();
				Logger.appendSettings();
				new File(logLocation + "/" + directory + "/" + "settings.dtd").createNewFile();
				PrintWriter out = new PrintWriter(new BufferedWriter(
						new FileWriter(logLocation + "/" + directory + "/" + "settings.dtd", true)));
				out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				out.println("<!ELEMENT settings (resolution,windowed,fpscounter)>");
				out.println("<!ELEMENT resolution (#PCDATA)>");
				out.println("<!ELEMENT windowed (#PCDATA)>");
				out.println("<!ELEMENT fpscounter (#PCDATA)>");
				out.close();
			}
			// use the factory to take an instance of the document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
			// parse using the builder to get the DOM mapping of the
			// XML file
			dom = db.parse(logLocation + "/" + directory + "/" + "settings.xml");

			Element doc = dom.getDocumentElement();

			resolution = getTextValue(resolution, doc, "resolution");
			if (resolution != null) {
				if (!resolution.isEmpty())
					Main.HEIGHT = Integer.parseInt(resolution);
			}
			windowed = getTextValue(windowed, doc, "windowed");
			if (windowed != null) {
				if (!windowed.isEmpty())
					Main.decoration = !Boolean.parseBoolean(windowed);
			}
			fpscounter = getTextValue(fpscounter, doc, "fpscounter");
			if (fpscounter != null) {
				if (!fpscounter.isEmpty())
					Main.displayFPS = Boolean.parseBoolean(fpscounter);
			}
		} catch (ParserConfigurationException pce) {
			System.out.println(pce.getMessage());
		} catch (SAXException se) {
			System.out.println(se.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
	}
	
	private static String getTextValue(String def, Element doc, String tag) {
	    String value = def;
	    NodeList nl;
	    nl = doc.getElementsByTagName(tag);
	    if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
	        value = nl.item(0).getFirstChild().getNodeValue();
	    }
	    return value;
	}

}