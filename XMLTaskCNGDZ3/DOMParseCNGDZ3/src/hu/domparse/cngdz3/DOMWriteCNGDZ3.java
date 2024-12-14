package hu.domparse.cngdz3;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMWriteCNGDZ3 {

    public static void main(String[] args) {
        try {
            // Letrehozzuk a DocumentBuilderFactory-t es a DocumentBuilder-t
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Beolvassuk az XML fajlt
            File file = new File("XMLCNGDZ3.xml");
            Document document = builder.parse(file);

            // Normalizaljuk az XML strukturat
            document.getDocumentElement().normalize();

            // Kiirjuk a gyokerelem nevet
            System.out.println("Root element: " + document.getDocumentElement().getNodeName());

            // Kiirjuk az XML fa strukturajat
            printNode(document.getDocumentElement(), "");

            // Az XML fajl tartalmanak mentese egy uj fajlba
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("XMLCNGDZ31.xml"));
            transformer.transform(source, result);

            System.out.println("Az XML fajl tartalma elmentve az XMLCNGDZ31.xml fajlba.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Rekurziv fuggveny az XML fa strukturajanak kiirasahoz
    private static void printNode(Node node, String indent) {
        System.out.println(indent + "Node: " + node.getNodeName() + ", Value: " + node.getTextContent().trim());

        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node childNode = nodeList.item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                printNode(childNode, indent + "  ");
            }
        }
    }
}