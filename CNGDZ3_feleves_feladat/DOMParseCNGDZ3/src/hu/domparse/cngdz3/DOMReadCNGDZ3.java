package hu.domparse.cngdz3;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMReadCNGDZ3 {

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

            // Beolvassuk es kiirjuk a Tipusok elemeket
            NodeList tipusokList = document.getElementsByTagName("Tipus");
            System.out.println("Tipusok:");
            for (int i = 0; i < tipusokList.getLength(); i++) {
                Node node = tipusokList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    System.out.println("TipKod: " + elem.getAttribute("TipKod"));
                    System.out.println("TNev: " + elem.getAttribute("TNev"));
                }
            }

            // Beolvassuk es kiirjuk a Termekek elemeket
            NodeList termekekList = document.getElementsByTagName("Termek");
            System.out.println("Termekek:");
            for (int i = 0; i < termekekList.getLength(); i++) {
                Node node = termekekList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    System.out.println("TerKod: " + elem.getAttribute("TerKod"));
                    System.out.println("TerNev: " + elem.getAttribute("TerNev"));
                    System.out.println("TermekAr: " + elem.getAttribute("TermekAr"));
                    System.out.println("TipKod: " + elem.getAttribute("TipKod"));
                }
            }

            // Beolvassuk es kiirjuk a Rendelesek elemeket
            NodeList rendelesekList = document.getElementsByTagName("Rendeles");
            System.out.println("Rendelesek:");
            for (int i = 0; i < rendelesekList.getLength(); i++) {
                Node node = rendelesekList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    System.out.println("RSzam: " + elem.getAttribute("RSzam"));
                    System.out.println("Datum: " + elem.getAttribute("Datum"));
                    System.out.println("Bevetel: " + elem.getAttribute("Bevetel"));
                    System.out.println("DID: " + elem.getAttribute("DID"));
                    System.out.println("VID: " + elem.getAttribute("VID"));

                    // Beolvassuk es kiirjuk a Kiszallitas elemeket
                    Element kiszallitas = (Element) elem.getElementsByTagName("Kiszallitas").item(0);
                    System.out.println("Kiszallitas Ker-e: " + kiszallitas.getAttribute("Ker-e"));
                    if (kiszallitas.hasAttribute("SzallitasiAr")) {
                        System.out.println("SzallitasiAr: " + kiszallitas.getAttribute("SzallitasiAr"));
                    }
                }
            }

            // Beolvassuk es kiirjuk a Dolgozok elemeket
            NodeList dolgozokList = document.getElementsByTagName("Dolgozo");
            System.out.println("Dolgozok:");
            for (int i = 0; i < dolgozokList.getLength(); i++) {
                Node node = dolgozokList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    System.out.println("DID: " + elem.getAttribute("DID"));
                    System.out.println("Nev: " + elem.getAttribute("Nev"));
                    System.out.println("TelSzam: " + elem.getAttribute("TelSzam"));
                    System.out.println("Elerheto: " + elem.getAttribute("Elerheto"));
                }
            }

            // Beolvassuk es kiirjuk a Vasarlok elemeket
            NodeList vasarlokList = document.getElementsByTagName("Vasarlo");
            System.out.println("Vasarlok:");
            for (int i = 0; i < vasarlokList.getLength(); i++) {
                Node node = vasarlokList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    System.out.println("VID: " + elem.getAttribute("VID"));
                    System.out.println("Nev: " + elem.getAttribute("Nev"));
                    System.out.println("SzuletesiIdo: " + elem.getAttribute("SzuletesiIdo"));
                    System.out.println("VTelSzam: " + elem.getAttribute("VTelSzam"));

                    // Beolvassuk es kiirjuk a Lakcim elemeket
                    Element lakcim = (Element) elem.getElementsByTagName("Lakcim").item(0);
                    System.out.println("Iranyitoszam: " + lakcim.getElementsByTagName("Iranyitoszam").item(0).getTextContent());
                    System.out.println("Telepules: " + lakcim.getElementsByTagName("Telepules").item(0).getTextContent());
                    System.out.println("UtcaesHazszam: " + lakcim.getElementsByTagName("UtcaesHazszam").item(0).getTextContent());
                    System.out.println("Emelet: " + lakcim.getElementsByTagName("Emelet").item(0).getTextContent());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}