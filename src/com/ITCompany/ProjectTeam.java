package com.ITCompany;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ProjectTeam {
    private int id;
    private String name;
    private Date startDate;
    private Date endDate;
    private ArrayList<Integer> programmers;
    private ArrayList<String> activity;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public ProjectTeam(int id, String name, Date startDate, Date endDate, ArrayList<Integer> programmers, ArrayList<String> activity) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.programmers = programmers;
        this.activity = activity;
    }

    public ProjectTeam() {

    }

    void addProject(ArrayList<ProjectTeam> list2, ArrayList<ActiveProgrammers> list1) throws ParseException {
        ArrayList<Integer> programmers = new ArrayList();
        ArrayList<String> activity = new ArrayList();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ProjectTeam p; //= new ProjectTeam();
        int i = list2.size();
        p = list2.get(i-1);
        int id = p.getId()+1;
        System.out.println("Insert project name:");
        String name =  Menu.scanChoice.next();
        System.out.println("Today's date will be used as Start Date");
        Date startDate = new Date();
        //change startDate insertion
        //change startDate insertion
        //change startDate insertion
        //change startDate insertion
        System.out.println("Insert end Date(dd/mm/yyyy):");
        String end = Menu.scanChoice.next();
        Date endDate = dateFormat.parse(end);
        //change startDate insertion
        //change startDate insertion
        //change startDate insertion
        //change startDate insertion
        boolean exitStatus = true;
        while(exitStatus==true){
            System.out.println("Available Programmers: ");
            int count = 0;
            for (int j = 0; j < list1.size(); j++) {
                if(!list1.get(j).isActive()){
                    System.out.println(list1.get(j).getId()+" - "+list1.get(j).getFirstName());
                    count++;
                }
            }
            System.out.println("Insert programmer's ID:");
            int programmerID = Menu.scanChoice.nextInt();
            for (int j = 0; j <list1.size(); j++) {
                if(list1.get(j).getId()==programmerID&&list1.get(j).isActive()==true){
                    System.out.println("The choosen programmer is not available");
                    addProject(list2, list1);
                }

            }
            // change person's ID to active and check if it wasnt active before
            // change person's ID to active and check if it wasnt active before
            // change person's ID to active and check if it wasnt active before
            programmers.add(programmerID);
            for (int j = 0; j <list1.size(); j++) {
                if(list1.get(j).getId()==programmerID){
                    list1.get(j).setActive(true);
                    list1.get(j).setStartDate(startDate);
                    System.out.println("Programmer "+list1.get(j).getId()+" changed status to "+list1.get(j).isActive()+" starting from "+dateFormat.format(list1.get(j).getStartDate()));
                    count--;
                }

            }
//            System.out.println("Insert 2nd programmer's ID:");
//            programmerID = Menu.scanChoice.nextInt();
//            // change person's ID to active and check if it wasnt active before
//            // change person's ID to active and check if it wasnt active before
//            // change person's ID to active and check if it wasnt active before
//            programmers.add(programmerID);
            System.out.println("Insert programmer's activity:");
            String programmerActivity = Menu.scanChoice.next();
            activity.add(programmerActivity);
//            System.out.println("Insert 2nd programmer's activity:");
//            programmerActivity = Menu.scanChoice.next();
//            activity.add(programmerActivity);
            System.out.println("Do you want to add another programmer? (y - yes; n - no)");
            String choice = Menu.scanChoice.next();
                if (!choice.equals("y")) {
                    exitStatus = false;
                } else if (count==0) {
                    System.out.println("There are no more available programmers");
                    exitStatus = false;
                }

        }
        System.out.println("Exit "+exitStatus);
        ProjectTeam member = new ProjectTeam(id, name, startDate, endDate, programmers, activity);
        list2.add(member);
        for (ProjectTeam element : list2) {
            System.out.println(element.getId() + ". " + element.getName() + ": " + element.getStartDate() + ", " + element.getEndDate() + ", programmers: " + element.getProgrammers() + ", activities:" + element.getActivity());
        }
    }

    int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    ArrayList<Integer> getProgrammers() {
        return programmers;
    }


    ArrayList<String> getActivity() {
        return activity;
    }

    public void setProgrammers(ArrayList<Integer> programmers) {
        this.programmers = programmers;
    }

    public void setActivity(ArrayList<String> activity) {
        this.activity = activity;
    }
}
