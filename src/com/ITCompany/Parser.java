package com.ITCompany;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

class Parser {

    Parser() {
    }

// TO READ EVERY PERSON'S INFORMATION FROM XML
    static void ParserMethodPeople(ArrayList<ActiveProgrammers> list1) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("DB.xml");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            doc.getDocumentElement().normalize();
            NodeList personList = doc.getElementsByTagName("person");
            for (int i = 0; i < personList.getLength(); i++) {
                Node p = personList.item(i);
                if (p.getNodeType() == Node.ELEMENT_NODE) {
                    Element person = (Element) p;
                    int id = Integer.parseInt(person.getElementsByTagName("id").item(0).getTextContent());
                    String firstName = person.getElementsByTagName("firstName").item(0).getTextContent();
                    String lastName = person.getElementsByTagName("lastName").item(0).getTextContent();
                    String start = person.getElementsByTagName("startDate").item(0).getTextContent();
                    Date startDate = dateFormat.parse(start);
                    boolean active = Boolean.parseBoolean(person.getElementsByTagName("active").item(0).getTextContent());
                    double wage = Double.parseDouble(person.getElementsByTagName("wage").item(0).getTextContent());

                    ActiveProgrammers member = new ActiveProgrammers(id, firstName, lastName, startDate, active, wage);
                    list1.add(member);
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException | ParseException e) {
            e.printStackTrace();
        }
    }
    //PRINTS EVERY PERSON'S INFORMATION
    static void printPeopleMethod(ArrayList<ActiveProgrammers> list1) {
        for (ActiveProgrammers element : list1) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println(element.getId() + ". " + element.getFirstName() + " " + element.getLastName() + ": " + dateFormat.format(element.getStartDate()) + ", active: " + element.isActive() + ", wage(h):" + element.getWage());
        }
    }

    // TO READ EVERY PROJECT'S INFORMATION FROM XML
    static void ParserMethodProjects(ArrayList<ProjectTeam> list2) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("DB.xml");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            doc.getDocumentElement().normalize();
            NodeList projectList = doc.getElementsByTagName("project");
            for (int j = 0; j < projectList.getLength(); j++) {
                Node t = projectList.item(j);
                if (t.getNodeType() == Node.ELEMENT_NODE) {
                    Element project = (Element) t;
                    ArrayList<Integer> programmers = new ArrayList<>();
                    ArrayList<String> activity = new ArrayList<>();
                    int count = project.getElementsByTagName("programmerID").getLength();
                    int id = Integer.parseInt(project.getElementsByTagName("id").item(0).getTextContent());
                    String name = project.getElementsByTagName("name").item(0).getTextContent();
                    String start = project.getElementsByTagName("startDate").item(0).getTextContent();
                    Date startDate = dateFormat.parse(start);
                    String end = project.getElementsByTagName("endDate").item(0).getTextContent();
                    Date endDate = dateFormat.parse(end);
                    for (int k = 0; k < count; k++) {
                        int programmerID = Integer.parseInt(project.getElementsByTagName("programmerID").item(k).getTextContent());
                        programmers.add(programmerID);
                    }
                    for (int k = 0; k < count; k++) {
                        String programmerActivity = project.getElementsByTagName("programmerActivity").item(k).getTextContent();
                        activity.add(programmerActivity);
                    }

                    ProjectTeam team = new ProjectTeam(id, name, startDate, endDate, programmers, activity);
                    list2.add(team);
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException | ParseException e) {
            e.printStackTrace();
        }
    }
    //PRINTS EVERY PROJECT'S INFORMATION
    static void printProjectsMethod(ArrayList<ProjectTeam> list2) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (ProjectTeam element : list2) {
            System.out.println(element.getId() + ". " + element.getName() + ": start:" + dateFormat.format(element.getStartDate()) + ", end: " + dateFormat.format(element.getEndDate()) + ", programmers: " + element.getProgrammers() + ", activity: " + element.getActivity());
        }
    }
// TO READ THE DATE SAVED IN XML
    public static Date readDate() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("DB.xml");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            doc.getDocumentElement().normalize();
            NodeList projectList = doc.getElementsByTagName("date");
            Node node = projectList.item(0);
            Element single = (Element) node;
            Date newDate = dateFormat.parse(single.getElementsByTagName("value").item(0).getTextContent());
            return newDate;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Date r = new Date();
        return r;
    }
}



