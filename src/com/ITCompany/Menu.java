package com.ITCompany;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

class Menu {
    private ArrayList<ActiveProgrammers> list1 = new ArrayList();
    private ArrayList<ProjectTeam> list2 = new ArrayList();
    private ActiveProgrammers createPerson = new ActiveProgrammers();
    private ActiveProgrammers editPeople = new ActiveProgrammers();
    private ProjectTeam createProject = new ProjectTeam();
    static Scanner scanChoice = new Scanner(System.in);
    void PasswordMethod() throws ParseException {
        System.out.println("Hello!");
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Please insert password to continue:");
            int password = in.nextInt();

            if (password == 20112019) {
                MenuMethod();
            } else {
                System.out.println("Incorrect Password");
            }
        }
    }

    private void MenuMethod() throws ParseException {
      Parser parserPeople = new Parser();
      Parser.ParserMethodPeople(list1);
      Parser parserProjects = new Parser();
      Parser.ParserMethodProjects(list2);
//        ActiveProgrammers createPerson = new ActiveProgrammers();
//        createPerson.addPerson(list1);
        while (true) {
            System.out.println("Menu");
            System.out.println("1.View Programmers");
            System.out.println("2.Edit Programmers");
            System.out.println("3.Create Programmers");
            System.out.println("4.View Projects");
            System.out.println("5.Edit Projects");
            System.out.println("6.Create Projects");
            System.out.println("7.Company Report");
            System.out.println("8.Exit");
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
                    //Edit Project List
                    System.out.println("Edit Project List");
                    System.out.println();
                    break;
                case 6:
                    //Create New Project
                    System.out.println("Create New Project");
                    createProject.addProject(list2, list1);
                    System.out.println();
                    break;
                case 7:
                    //Company Report
                    System.out.println("Company Report");
                    System.out.println();
                    break;
                case 8:
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
