import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class xPathCNDZ3 {

    public static void main(String[] args) {
        try {
            // Parse the XML document
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("studentCNGDZ3.xml");

            // Create an XPath instance
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();

            // 1. Válassza ki az összes student elemet, amely a class gyermeke!
            executeXPath(xPath, document, "/class/student", "1. Összes student elem, amely a class gyermeke:");

            // 2. Válassza ki azt a student elemet, amely rendelkezik id attribútummal, és értéke "02"!
            executeXPath(xPath, document, "/class/student[@id='02']", "2. student elem id=\"02\":");

            // 3. Kiválasztja az összes student elemet, függetlenül attól, hogy hol vannak a dokumentumban!
            executeXPath(xPath, document, "//student", "3. Összes student elem:");

            // 4. Válassza ki a második student elemet, amely a class gyermeke!
            executeXPath(xPath, document, "/class/student[2]", "4. Második student elem:");

            // 5. Válassza ki az utolsó student elemet, amely a class gyermeke!
            executeXPath(xPath, document, "/class/student[last()]", "5. Utolsó student elem:");

            // 6. Válassza ki az utolsó előtti student elemet, amely a class gyermeke!
            executeXPath(xPath, document, "/class/student[last()-1]", "6. Utolsó előtti student elem:");

            // 7. Válassza ki az első két student elemet, amelyek a class gyermekei!
            executeXPath(xPath, document, "/class/student[position() <= 2]", "7. Első két student elem:");

            // 8. Válassza ki a class root összes gyermek elemét!
            executeXPath(xPath, document, "/class/*", "8. class összes gyermek eleme:");

            // 9. Válassza ki az összes student elemet, amely rendelkezik legalább egy bármilyen attribútummal!
            executeXPath(xPath, document, "//student[@*]", "9. Student elemek attribútummal:");

            // 10. Válassza ki a dokumentum összes elemét!
            executeXPath(xPath, document, "//*", "10. Dokumentum összes eleme:");

            // 11. Válassza ki a class root összes student elemét, amelynél a kor > 20!
            executeXPath(xPath, document, "/class/student[kor > 20]", "11. Student elemek, ahol kor > 20:");

            // 12. Válassza ki az összes student elem összes keresztnev és vezeteknev csomópontját!
            executeXPath(xPath, document, "//student/(keresztnev | vezeteknev)", "12. Student keresztnev és vezeteknev elemek:");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void executeXPath(XPath xPath, Document document, String expression, String message) {
        try {
            XPathExpression xPathExpression = xPath.compile(expression);
            NodeList nodes = (NodeList) xPathExpression.evaluate(document, XPathConstants.NODESET);
            System.out.println(message);
            for (int i = 0; i < nodes.getLength(); i++) {
                System.out.println(nodes.item(i).getTextContent());
            }
            System.out.println("------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
