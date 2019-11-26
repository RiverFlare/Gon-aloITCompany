package com.ITCompany;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.annotation.XmlRootElement;
import java.beans.XMLDecoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

@XmlRootElement
public interface Programmers {

    class person {

        private final int id;
        private final String lastName;
        private final String firstName;
        private final Date startDate;
        private final boolean active;
        private final int wage;

        public person(int id, String lastName, String firstName, Date startDate, boolean active, int wage) {
            this.id = id;
            this.lastName = lastName;
            this.firstName = firstName;
            this.startDate = startDate;
            this.active = active;
            this.wage = wage;
        }

/*        static void ParserMethodPeopleToArray () {
            try{
                FileInputStream fis = new FileInputStream(new File("/DB.xml"));
                XMLDecoder decoder = new XMLDecoder(fis);

                person p1 = (person)decoder.readObject();
                decoder.close();
                fis.close();

                System.out.print(p1.getId());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }*/

        public int getId() {
            return id;
        }

        public String getLastName() {
            return lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public Date getStartDate() {
            return startDate;
        }

        public boolean isActive() {
            return active;
        }

        public int getWage() {
            return wage;
        }


    }
}
