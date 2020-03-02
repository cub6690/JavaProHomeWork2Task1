package com.gmail.sergick6690;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XmlDocument {

    public static void createXml (){
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.newDocument();
            Element rootElement = document.createElement("Trains");
            document.appendChild(rootElement);
            Element trainOne = document.createElement("TrainOne");
            trainOne.setAttribute("ID", "01");
            rootElement.appendChild(trainOne);
            Element from = document.createElement("From");
                    from.setTextContent("Kiev");
            trainOne.appendChild(from);
            Element to = document.createElement("To");
            to.setTextContent("Lviv");
            trainOne.appendChild(to);
            Element date = document.createElement("Date");
            date.setTextContent("22.12.2020");
            trainOne.appendChild(date);
            Element time = document.createElement("Time");
            time.setTextContent("22:00");
            trainOne.appendChild(time);
            //trainTwo
            Element trainTwo = document.createElement("TrainTwo");
            trainTwo.setAttribute("ID", "02");
            rootElement.appendChild(trainTwo);
            Element fromTwo = document.createElement("From");
            fromTwo.setTextContent("Lviv");
            trainTwo.appendChild(fromTwo);
            Element toTwo = document.createElement("To");
            toTwo.setTextContent("Kiev");
            trainTwo.appendChild(toTwo);
            Element dateTwo = document.createElement("Date");
            dateTwo.setTextContent("23.12.2020");
            trainTwo.appendChild(dateTwo);
            Element timeTwo = document.createElement("Time");
            timeTwo.setTextContent("14:00");
            trainTwo.appendChild(timeTwo);
           // Save

            TransformerFactory transformerFactory=TransformerFactory.newInstance();
            Transformer transformer =transformerFactory.newTransformer();
            DOMSource source= new DOMSource(document);
            StreamResult result=new StreamResult(new File("C:\\Users\\cub\\Desktop\\galya\\vvv.xml"));
            transformer.transform(source, result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
