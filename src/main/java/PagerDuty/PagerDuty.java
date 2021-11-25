package PagerDuty;

import controller.PagerDutyController;
import utils.ScannerSingleton;

import java.util.Scanner;

public class PagerDuty {

    public static void main(String[] args) {

        System.out.println("Welcome to Pager Duty");

        Scanner sc = ScannerSingleton.getInstance();

        System.out.println("Please Enter Your Choice");
        System.out.println("1.Enter Team and developer details");
        System.out.println("2.Enter Alerting details");
        System.out.println("3.Exit");

        PagerDutyController controller = new PagerDutyController();
        while (true)
        {
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    sc.nextLine();
                    String text = sc.nextLine();
                    try {
                        controller.insertTeamAndDeveloperDetails(text);
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    int teamId = sc.nextInt();
                    controller.sendAlertToDevelopers(teamId);
                    break;
                default:
                    System.out.println("Thank you using Pager duty");
                    System.exit(0);
            }
        }

    }
}
