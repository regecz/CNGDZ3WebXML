package domcngdz31118;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;

public class DomWriteCngdz3 {

    public static void main(String[] args) {
        try {
            // Beolvasás az orarendCngdz3.xml fájlból
            File inputFile = new File("orarendCngdz3.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // Fastruktúra kiírása a konzolra
            System.out.println("XML Fájl fastruktúra:");
            printNode(doc, 0);

            // Új fájlba írás
            saveXMLDocument(doc, "orarend1Neptunkod.xml");
            System.out.println("\nAz orarend1Neptunkod.xml fájl sikeresen létrehozva.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Fastruktúra kiírása rekurzívan
    private static void printNode(Node node, int depth) {
        // Behúzás szintje (mélység alapján)
        String indent = " ".repeat(depth * 2);
        
        // Az elem nevét kiírjuk
        System.out.print(indent + "Név: " + node.getNodeName());
        
        // Szöveges tartalom lekérése
        String nodeValue = node.getNodeValue();
        String textContent = node.getTextContent(); // Ellenőrizni kell, hogy null-e

        // Ha üres szöveget tartalmaz (null vagy üres string), akkor ne folytassuk
        if ((node.getNodeType() == Node.TEXT_NODE && (textContent == null || textContent.trim().isEmpty())) || 
            (nodeValue != null && nodeValue.trim().isEmpty())) {
            return; // Ha üres szöveges tartalom, kihagyjuk
        }

        // Ha van szöveges tartalom, akkor azt is kiírjuk
        if (nodeValue != null && !nodeValue.trim().isEmpty()) {
            System.out.print(", Érték: " + nodeValue.trim());
        } else if (textContent != null && !textContent.trim().isEmpty()) {
            System.out.print(", Érték: " + textContent.trim());
        }

        System.out.println();

        // Gyermekek rekurzív feldolgozása
        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE || child.getNodeType() == Node.TEXT_NODE) {
                printNode(child, depth + 1);
            }
        }
    }




    // XML dokumentum mentése fájlba
    private static void saveXMLDocument(Document doc, String outputFileName) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(outputFileName));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
