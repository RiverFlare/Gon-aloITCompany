package com.ITCompany;


import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CompanyReport {

/*    n1: total number of project teams
    n2: total number of active programmers
    n3: total active programmers that have worked this month
    n4: days worked this month by all the programmers
    n5: days left of work by all the active programmers
    n6: team's number
    n7: performed activity's name
    n8: start date of work
    n9: end date of work
    n10: duration of work
    n11: days worked this month by this programmer
    n12: salary of this person by days worked, assuming the different salary types.*/
    int projectTeams;
    int activeProg;
    int activeProgWorked;
    int daysWorked;
    int daysLeftWork;
    String projectName;
    String activityName;
    /*date startDate;
    date endDate;*/
    int duration;
    int daysWorkedProg;
    int salaryProg;


    public static void companyReport(ArrayList<ActiveProgrammers> list1, ArrayList<ProjectTeam> list2) {

        Date timeX = new Date();
        Calendar time = Calendar.getInstance();
        time.setTime(timeX);
        YearMonth dayCount = YearMonth.of(timeX.getYear(), timeX.getMonth());
        int daysInMonth = dayCount.lengthOfMonth();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");


        int totalActiveDays = 0;
        int active = 0;
        int daysLeft = 0;

        for (ActiveProgrammers prog : list1) {
            totalActiveDays += prog.getDaysWorked();
            if (prog.isActive() || (!prog.isActive() && prog.getDaysWorked() != 0)) {
                active++;
            }
        }

        for (ProjectTeam proj : list2) {
            Calendar end = Calendar.getInstance();
            end.setTime(proj.getEndDate());
            if (time.get(Calendar.MONTH) == end.get(Calendar.MONTH)) {
                daysLeft += end.get(Calendar.DAY_OF_MONTH) - time.get(Calendar.DAY_OF_MONTH);
            } else {
                daysLeft += (daysInMonth - time.get(Calendar.DAY_OF_MONTH)) * proj.getProgrammers().size();
            }
        }

        System.out.println("IT COMPANY REPORT");
        System.out.println("");
        System.out.println("IT Company is currently composed of " + list2.size() + " project teams, and " + list1.size() + " programmers.");
        System.out.println("This month, " + active + " programmers have worked a total of " + totalActiveDays + " days, with " + daysLeft + " days left of work for the of the month.");

        System.out.println("Project Team details: ");
        for (ProjectTeam proj : list2) {
            System.out.println("Project Team " + proj.getId());
            for (int i = 0; i < proj.getProgrammers().size(); i++) {
                for (ActiveProgrammers prog : list1) {
                    if (prog.getId() == proj.getProgrammers().get(i)) {
                        System.out.println(prog.getLastName() + ", " + prog.getFirstName() + ", in charge of " + proj.getActivity().get(i) + " from " + dateFormat.format(prog.getStartDate()) + " to " + dateFormat.format(proj.getEndDate()) + ", has worked " + prog.getDaysWorked() + " days this month. Total salary: " + prog.calculateSalary(prog) + "€");
                        break;
                    }
                }

            }
        }
    }
}
