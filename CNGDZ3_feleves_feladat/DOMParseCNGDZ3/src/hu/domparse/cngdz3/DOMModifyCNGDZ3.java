package hu.domparse.cngdz3;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMModifyCNGDZ3 {

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

            // Beolvassuk az osszes "Rendeles" elemet
            NodeList rendelesekList = document.getElementsByTagName("Rendeles");

            // Vegigmegyunk az osszes "Rendeles" elemen
            for (int i = 0; i < rendelesekList.getLength(); i++) {
                Node node = rendelesekList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;

                    // Ha a "RSzam" attribútum értéke "R001", akkor módosítjuk a "Bevetel" attribútumot
                    if ("R001".equals(elem.getAttribute("RSzam"))) {
                        elem.setAttribute("Bevetel", "25000");
                        System.out.println("Bevetel attribútum módosítva az R001 rendelésnél.");
                    }
                }
            }

            // Az XML fájl módosításainak mentése
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("XMLCNGDZ3_modified.xml"));
            transformer.transform(source, result);

            System.out.println("Az XML fájl módosításai elmentve az XMLCNGDZ3_modified.xml fájlba.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}