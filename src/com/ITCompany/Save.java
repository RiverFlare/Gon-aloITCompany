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
            for (ProjectTeam project: list2) {
                String id = String.valueOf(project.getId());
                String name = project.getName();
                String startDate = project.getStartDate().toString();
                String endDate = project.getEndDate().toString();

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
            }

            NodeList programmerList = doc.getElementsByTagName("person");
            //Delete
            for (int i = 0; i < programmerList.getLength(); i++) {
                Node eachNode = programmerList.item(i);
                eachNode.getParentNode().removeChild(eachNode);
            }

            //ADD Programmers
            for (ActiveProgrammers programmer: list1) {
                String id = String.valueOf(programmer.getId());
                String firstName = programmer.getFirstName();
                String lastName = programmer.getLastName();
                String startDate = programmer.getStartDate().toString();
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
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
