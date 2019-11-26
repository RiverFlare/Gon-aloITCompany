package com.ITCompany;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class ActiveProgrammers {
    private int id;
    private String lastName;
    private String firstName;
    private Date startDate;
    private boolean active;
    private int wage;


    ActiveProgrammers(int id, String firstName, String lastName, Date startDate, boolean active, int wage) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.startDate = startDate;
        this.active = active;
        this.wage = wage;
    }

    ActiveProgrammers() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    private void setWage(int wage) {
        this.wage = wage;
    }

    int getId() {
        return id;
    }

    String getLastName() {
        return lastName;
    }

    String getFirstName() {
        return firstName;
    }

    Date getStartDate() {
        return startDate;
    }

    boolean isActive() {
        return active;
    }

    int getWage() {
        return wage;
    }


    void addPerson(ArrayList<ActiveProgrammers> list1) {
        ActiveProgrammers p = new ActiveProgrammers();
        int i = list1.size();
        p = list1.get(i-1);
        int id = p.getId()+1;
        System.out.println("Insert first name:");
        String firstName =  Menu.scanChoice.next();
        System.out.println("Insert last name:");
        String lastName = Menu.scanChoice.next();
        System.out.println("Insert start Date(dd/mm/yyyy):");
        Date startDate = new Date();
        //change startDate insertion
        //change startDate insertion
        //change startDate insertion
        //change startDate insertion
        System.out.println("Insert wage(h):");
        int wage = Menu.scanChoice.nextInt();
        System.out.println("Person is created as inactive, change this setting by adding this person to a project");
        this.active = false;
        ActiveProgrammers member = new ActiveProgrammers(id, firstName, lastName, startDate, active, wage);
        list1.add(member);
        for (ActiveProgrammers element : list1) {
            System.out.println(element.getId() + ". " + element.getFirstName() + " " + element.getLastName() + ": " + element.getStartDate() + ", active: " + element.isActive() + ", wage(h):" + element.getWage());
        }
    }

    void editProgrammer(ArrayList<ActiveProgrammers> list1) {
        System.out.println("Choose person ID to edit");
        int edit = Menu.scanChoice.nextInt();
        for(ActiveProgrammers element: list1) {
            if(element.getId() == edit) {
                System.out.println("Insert new wage(h):");
                int wage = Menu.scanChoice.nextInt();
                element.setWage(wage);
                System.out.println("New wage was set!");
                System.out.println(element.getId() + ". " + element.getFirstName() + " " + element.getLastName() + ": " + element.getStartDate() + ", active: " + element.isActive() + ", wage(h):" + element.getWage());
            } else if ( edit > list1.size() ) {
                System.out.println("ID not valid");
                break;
            }
        }
    }
}
