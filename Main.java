package com.gmail.sergick6690;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main  {
    public static void main(String[] args) {
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
            from.setTextContent(getCity());
            trainOne.appendChild(from);
            Element to = document.createElement("To");
            to.setTextContent(getCity());
            trainOne.appendChild(to);
            Element date = document.createElement("Date");
            date.setTextContent(getDate());
            trainOne.appendChild(date);
            Element time = document.createElement("Time");
            time.setTextContent(getTime());
            trainOne.appendChild(time);
            //trainTwo
            Element trainTwo = document.createElement("TrainTwo");
            trainTwo.setAttribute("ID", "02");
            rootElement.appendChild(trainTwo);
            Element fromTwo = document.createElement("From");
            fromTwo.setTextContent(getCity());
            trainTwo.appendChild(fromTwo);
            Element toTwo = document.createElement("To");
            toTwo.setTextContent(getCity());
            trainTwo.appendChild(toTwo);
            Element dateTwo = document.createElement("Date");
            dateTwo.setTextContent(getDate());
            trainTwo.appendChild(dateTwo);
            Element timeTwo = document.createElement("Time");
            timeTwo.setTextContent(getTime());
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
        parser(new File("C:\\Users\\cub\\Desktop\\galya\\vvv.xml"),"10:00","22:00");
    }
    public static String getCity () {
        String[] city = {"Kiev", "Kharkiv", "Lviv", "Odesa", "Poltava", "Ternopil"};
        int index = (int) (Math.random()*6) ;
        return city[index];
    }
    public static String getTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        int time= 10+ (int) (Math.random()*1000000000);
        String sd=  sdf.format(date.getTime()+time);
       return  sd;
    }
    public static String getDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        return sdf.format(new Date());
    }
    public static void parser(File file, String from, String to){

        try {
            File xmlFile = file;

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document document = documentBuilder.parse(xmlFile);

            Element root = document.getDocumentElement();
            NodeList nodeList = root.getChildNodes();
            boolean checker = false;
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String time = element.getElementsByTagName("Time").item(0)
                            .getChildNodes().item(0).getNodeValue();
                    SimpleDateFormat sdf = new SimpleDateFormat();
                    sdf.applyPattern("HH:mm");
                    Date date =sdf.parse(time);
                    Date from1 = sdf.parse(from);
                    Date to1 = sdf.parse(to);
                    if (date.getTime() >= from1.getTime() && date.getTime() <= to1.getTime()) {
                        checker=true;
                        System.out.println("Потяги, які відправляються в періуд часу з - "+from+" - до - "+to);
                        System.out.println(node.getNodeName());
                        System.out.println("From: " + element.getElementsByTagName("From").item(0)
                                .getChildNodes().item(0).getNodeValue());
                        System.out.println("To: " + element.getElementsByTagName("To").item(0)
                                .getChildNodes().item(0).getNodeValue());
                        System.out.println("Date:" + element.getElementsByTagName("Date").item(0)
                                .getChildNodes().item(0).getNodeValue());
                        System.out.println("Time: " + element.getElementsByTagName("Time").item(0)
                                .getChildNodes().item(0).getNodeValue());
                    }
                }
            } if(!checker){
                    System.out.println("Нажаль жодного потяга не знайдено");
            }
            } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
