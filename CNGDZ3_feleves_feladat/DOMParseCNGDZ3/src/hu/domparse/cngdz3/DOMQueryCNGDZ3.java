package hu.domparse.cngdz3;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMQueryCNGDZ3 {

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
            System.out.println("Rendelesek, amelyek kiszallitasa igen:");
            for (int i = 0; i < rendelesekList.getLength(); i++) {
                Node node = rendelesekList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;

                    // Beolvassuk a "Kiszallitas" elemet
                    Element kiszallitas = (Element) elem.getElementsByTagName("Kiszallitas").item(0);
                    String kerE = kiszallitas.getAttribute("Ker-e");

                    // Ha a "Kiszallitas" eleme "true", akkor kiirjuk a rendeles adatait
                    if ("true".equals(kerE)) {
                        System.out.println("RSzam: " + elem.getAttribute("RSzam"));
                        System.out.println("Datum: " + elem.getAttribute("Datum"));
                        System.out.println("Bevetel: " + elem.getAttribute("Bevetel"));
                        System.out.println("DID: " + elem.getAttribute("DID"));
                        System.out.println("VID: " + elem.getAttribute("VID"));
                        System.out.println("SzallitasiAr: " + kiszallitas.getAttribute("SzallitasiAr"));
                        System.out.println("-----------------------------------");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}