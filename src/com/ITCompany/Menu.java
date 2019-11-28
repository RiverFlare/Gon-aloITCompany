package com.ITCompany;

import java.sql.SQLOutput;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

class Menu {
    private ArrayList<ActiveProgrammers> list1 = new ArrayList();
    private ArrayList<ProjectTeam> list2 = new ArrayList();
    private ActiveProgrammers createPerson = new ActiveProgrammers();
    private ActiveProgrammers editPeople = new ActiveProgrammers();
    private ProjectTeam createProject = new ProjectTeam();
    private Save saveProgram = new Save();
    private CompanyReport printReport = new CompanyReport();
    static Scanner scanChoice = new Scanner(System.in);
    private static Date today = new Date();

    public static Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    void PasswordMethod() throws ParseException {
        System.out.println("Hello!");
        Boolean pass = false;
        while (!pass) {
//        Scanner in = new Scanner(System.in);
        System.out.println("Please insert password: (20112019)");
        String in = Menu.scanChoice.nextLine();

        Integer Password;

            try {
                Password = Integer.parseInt(String.valueOf(in));
                if (Password == 20112019) {
                    pass = true;
                } else {
                    System.out.println("Incorrect Password");
                }
            } catch (NumberFormatException e) {
                System.out.println("Password consists of only numbers. Don't insert letters.");
            }

        }
        MenuMethod();
    }

    private void MenuMethod() throws ParseException {
      Parser parserPeople = new Parser();
      Parser.ParserMethodPeople(list1);
      Parser parserProjects = new Parser();
      Parser.ParserMethodProjects(list2);
      Date newDate = new Date();
      newDate = Parser.readDate();
        while (true) {
            System.out.println("Menu");
            System.out.println("1.View Programmers");
            System.out.println("2.Edit Programmers");
            System.out.println("3.Create Programmers");
            System.out.println("4.View Projects");
            System.out.println("5.Create Projects");
            System.out.println("6.Company Report");
            System.out.println("7.Save Program");
            System.out.println("8.Update System's Date");
            System.out.println("0.Exit");
            Scanner scanChoice = new Scanner(System.in);
            System.out.println();
            int menuChoice = scanChoice.nextInt();
            scanChoice.nextLine();
            switch (menuChoice) {
                case 1:
                    //View Programmers List
                    System.out.println("Programmers List");
                    System.out.println();
                    Parser printPeople = new Parser();
                    Parser.printPeopleMethod(list1);
                    System.out.println();
                    break;
                case 2:
                    //Edit Programmers List
                    System.out.println("Edit Programmers List");
                    editPeople.editProgrammer(list1);
                    System.out.println();
                   break;
                case 3:
                    //Create Programmer
                    System.out.println("Create Programmer");
                    createPerson.addPerson(list1);
                    System.out.println();
                    break;
                case 4:
                    //View Project List
                    System.out.println("View Project List");
                    Parser printProjects = new Parser();
                    Parser.printProjectsMethod(list2);
                    System.out.println();
                    break;
                case 5:
                    //Create New Project
                    System.out.println("Create New Project");
                    createProject.addProject(list2, list1, newDate);
                    System.out.println();
                    break;
                case 6:
                    //Company Report
                    System.out.println("Company Report");
                    printReport.companyReport(list1, list2);
                    System.out.println();
                    break;

                case 7:
                    //SAVE
                    saveProgram.saveMethod(list1, list2);
                    System.out.println("Program Saved!");
                    break;
                case 8:
                    Calendar c = Calendar.getInstance();
                    c.setTime(newDate);
                    c.add(Calendar.DATE, 1);
                    newDate = c.getTime();
                    setToday(newDate);
                    System.out.println("Date set to "+getToday());
                    break;
                case 0:
                    //Exit
                    System.out.println("Goodbye!");
                    System.out.println();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please Pick A Valid Option");
                    System.out.println();
            }

        }
    }
}
