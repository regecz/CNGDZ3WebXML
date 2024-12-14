import java.io.File;
import java.io.FileOutputStream;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class TransformXML {
    public static void main(String[] args) {
        try {
            // XML és XSL fájlok
            File xmlFile = new File("hallgatoNEPTUNKOD.xml");
            File xslFile = new File("hallgatoNEPTUNKOD.xsl");

            // Kimeneti HTML fájl
            File htmlFile = new File("hallgatoNEPTUNKOD.html");

            // Transformer létrehozása
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xslFile));

            // Átalakítás végrehajtása
            transformer.transform(new StreamSource(xmlFile), new StreamResult(new FileOutputStream(htmlFile)));

            System.out.println("Az átalakítás sikeres! Az eredmény itt található: " + htmlFile.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
