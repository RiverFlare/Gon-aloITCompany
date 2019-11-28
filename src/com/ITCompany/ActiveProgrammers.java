package com.ITCompany;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ActiveProgrammers {
    private int id;
    private String lastName;
    private String firstName;
    private Date startDate;
    private boolean active;
    private double wage;


    ActiveProgrammers(int id, String firstName, String lastName, Date startDate, boolean active, double wage) {
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

    private void setWage(double wage) {
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

    double getWage() {
        return wage;
    }

// TO ADD A NEW PERSON
    void addPerson(ArrayList<ActiveProgrammers> list1) {
        Date newDate = Parser.readDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ActiveProgrammers p = new ActiveProgrammers();
        int i = list1.size();
        p = list1.get(i-1);
        int id = p.getId()+1;
        System.out.println("Insert first name:");
        String firstName =  Menu.scanChoice.nextLine();
        if (firstName.equals("")) {
            System.out.println("No first name inserted");
            return;
        }
        System.out.println("Insert last name:");
        String lastName = Menu.scanChoice.nextLine();
        if (lastName.equals("")) {
            System.out.println("No last name inserted");
            return;
        }
        System.out.println("Start date will be set as today's date");
        Date startDate = newDate;

        System.out.println("Insert wage(h)");
        String wageInsert = Menu.scanChoice.nextLine();
        double wage;
        try {
            wage = Double.parseDouble(wageInsert);
            if (wage < 0 ) {
                System.out.println("Negative numbers are not accepted...");
                return;
            }
        } catch (Exception e) {
            System.out.println("Please insert a valid value. Don't use letters");
            return;
        }
        System.out.println("Person is created as inactive, change this setting by adding this person to a project");
        this.active = false;
        ActiveProgrammers member = new ActiveProgrammers(id, firstName, lastName, startDate, active, wage);
        System.out.println("Antes de adicionar:" +list1.size());
        list1.add(member);
        System.out.println("Depois de adicionar:" +list1.size());
        for (ActiveProgrammers element : list1) {
            System.out.println(element.getId() + ". " + element.getFirstName() + " " + element.getLastName() + ": " + dateFormat.format(element.getStartDate()) + ", active: " + element.isActive() + ", wage(h):" + element.getWage());
        }
    }
// TO EDIT A PERSON'S WAGE
    void editProgrammer(ArrayList<ActiveProgrammers> list1) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        int edit = 0;
        Boolean next = false;
        while (!next) {
        System.out.println("Choose person ID to edit");
        String editIn = Menu.scanChoice.nextLine();
            try {
                edit = Integer.parseInt(String.valueOf(editIn));
                next = true;
            } catch (Exception e) {
                System.out.println("Please insert a number!");
            }
        }
        for (ActiveProgrammers element : list1) {
            if (element.getId() == edit) {
                System.out.println("Insert new wage(h):");
                String wageS = Menu.scanChoice.nextLine();
                next = false;
                double wage = 0;
                while (!next){
                    try {
                        wage = Double.parseDouble(wageS);
                        next = true;
                    } catch (Exception e) {
                        System.out.println("Please insert a number!");
                        return;
                    }
                }
                element.setWage(wage);
                System.out.println("New wage was set!");
                System.out.println(element.getId() + ". " + element.getFirstName() + " " + element.getLastName() + ": Start Date:" + dateFormat.format(element.getStartDate()) + ", Active: " + element.isActive() + ", Wage(h):" + element.getWage());
                return;
            }
        }
         System.out.println("ID not valid");
    }
//TO CALCULATE THE SALARY
    public double calculateSalary(ArrayList<ActiveProgrammers> list1, int id) {
        for(ActiveProgrammers person: list1) {
            if(person.getId()==id) {
                return person.getDaysWorked(list1, id)*person.getWage()*8;
            }
        }
        return 0;
    }
// TO GET THE NUMBER OF DAYS WORKED
    public double getDaysWorked(ArrayList<ActiveProgrammers> list1, int id) {
        for(ActiveProgrammers prog: list1) {
            if(prog.getId()==id) {
                LocalDate start = prog.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate today = Menu.getToday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                int monthStart = start.getMonthValue();
                int monthToday = today.getMonthValue();
                if(monthStart==monthToday){
                    long diff = Menu.getToday().getTime() - prog.getStartDate().getTime();
                    return (double) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                } else {
                    long diff = Menu.getToday().getTime() - 1;
                    return (double) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                }

            }
        }
        return 0;

    }
    Date date = new Date();
    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    int month = localDate.getMonthValue();
}
