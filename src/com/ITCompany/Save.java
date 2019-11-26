package com.ITCompany;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Save {

    public static void saveMethod(ArrayList<ActiveProgrammers> list1, ArrayList<ProjectTeam> list2) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("DB.xml");

            NodeList projectList = doc.getElementsByTagName("project");
            //DELETE
            for (int i = 0; i < projectList.getLength() ; i++) {
                Node eachNode = projectList.item(i);
                eachNode.getParentNode().removeChild(eachNode);
            }

            //ADD

            for (int k = 0; k < list2.size(); k++) {
                int size = list2.get(k).getProgrammers().size();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String id = String.valueOf(list2.get(k).getId());
                String name = list2.get(k).getName();
                String startDate = (dateFormat.format(list2.get(k).getStartDate()));
                String endDate = (dateFormat.format(list2.get(k).getEndDate()));
                //project
                NodeList database = doc.getElementsByTagName("database");
                Element projectEle = doc.createElement("project");
                database.item(0).appendChild(projectEle);
                //id
                Element pID = doc.createElement("id");
                pID.appendChild(doc.createTextNode(id));
                projectEle.appendChild(pID);
                //name
                Element pName = doc.createElement("name");
                pName.appendChild(doc.createTextNode(name));
                projectEle.appendChild(pName);
                //Start Date
                Element pStartDate = doc.createElement("startDate");
                pStartDate.appendChild(doc.createTextNode(startDate));
                projectEle.appendChild(pStartDate);
                //End Date
                Element pEndDate = doc.createElement("endDate");
                pEndDate.appendChild(doc.createTextNode(endDate));
                projectEle.appendChild(pEndDate);
                for (int i = 0; i < size; i++) {
                    Element programmerID = doc.createElement("ProgrammerID");
                    programmerID.appendChild(doc.createTextNode(Integer.toString(list2.get(k).getProgrammers().get(i))));
                    Element programmerActivity = doc.createElement("programmerActivity");
                    programmerActivity.appendChild(doc.createTextNode(list2.get(k).getActivity().get(i)));
                    projectEle.appendChild(programmerID);
                    projectEle.appendChild(programmerActivity);
                }
            }

            NodeList programmerList = doc.getElementsByTagName("person");
            //Delete
            for (int i = 0; i < programmerList.getLength(); i++) {
                Node eachNode = programmerList.item(i);
                eachNode.getParentNode().removeChild(eachNode);
            }

            //ADD Programmers
            for (ActiveProgrammers programmer: list1) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String id = String.valueOf(programmer.getId());
                String firstName = programmer.getFirstName();
                String lastName = programmer.getLastName();
                String startDate = (dateFormat.format(programmer.getStartDate()));
                String active = String.valueOf(programmer.isActive());
                String wage = String.valueOf(programmer.getWage());

                //person
                NodeList database = doc.getElementsByTagName("database");
                Element personEle = doc.createElement("person");
                database.item(0).appendChild(personEle);

                //id
                Element pID = doc.createElement("id");
                pID.appendChild(doc.createTextNode(id));
                personEle.appendChild(pID);

                //name
                Element pFirstName = doc.createElement("firstName");
                pFirstName.appendChild(doc.createTextNode(firstName));
                personEle.appendChild(pFirstName);

                //name
                Element pLastName = doc.createElement("lastName");
                pLastName.appendChild(doc.createTextNode(lastName));
                personEle.appendChild(pLastName);

                //Start Date
                Element pStartDate = doc.createElement("startDate");
                pStartDate.appendChild(doc.createTextNode(startDate));
                personEle.appendChild(pStartDate);

                //Active
                Element pActive = doc.createElement("active");
                pActive.appendChild(doc.createTextNode(active));
                personEle.appendChild(pActive);

                //Wage
                Element pWage = doc.createElement("wage");
                pWage.appendChild(doc.createTextNode(wage));
                personEle.appendChild(pWage);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("DB.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
        } catch (SAXException | IOException | ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }
}
