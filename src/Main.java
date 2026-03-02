import dao.JobApplicationDAO;
import model.JobApplication;
import service.JobApplicationService;

import java.util.List;
import java.util.Scanner;

public class Main {

    private final static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        JobApplicationDAO dao = new JobApplicationDAO();
        JobApplicationService service = new JobApplicationService(dao);

        boolean isRunning = true;

        mainMenu();

        while (isRunning){
            System.out.print("Enter Option: ");
            int option = scanner.nextInt();

            switch (option){
                case 1 -> addApplication(service);
                case 2 -> viewApplications(service);
                case 3 -> updateApplicationStatus(service);
                case 4 -> deleteApplication(service);
                case 5 -> {
                    System.out.println("=== Goodbye ===");
                    isRunning = false;
                }
            }
        }
    }

    private static void mainMenu(){
        System.out.println("=== Job Application Tracker ===");
        System.out.println("1. Add Application");
        System.out.println("2. View Applications");
        System.out.println("3. Update Application ");
        System.out.println("4. Delete Application");
        System.out.println("5. Exit");
        System.out.println();
    }

    // Add new Job Application
    private static void addApplication(JobApplicationService service){

        try{
            System.out.print("Company Name : ");
            String companyName = scanner.next();

            System.out.print("Job Title : ");
            String jobTitle = scanner.next();

            System.out.print("Job Type : ");
            String jobType = scanner.next();
            scanner.nextLine();

            System.out.print("Location : ");
            String location = scanner.next();

            service.newJobApplication(companyName, jobTitle, jobType, location);
            System.out.println("Application created successfully.");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    // View All Job Applications
    private static void viewApplications(JobApplicationService service){
        List<JobApplication> jobApplications = service.getAllApplications();

        if (jobApplications.isEmpty()){
            System.out.println("No job applications found");
            return;
        }

        for (JobApplication jobApplication : jobApplications){
            System.out.println(jobApplication.toString());
        }
    }

    // Update an existing job application
    private static void updateApplicationStatus(JobApplicationService service){

        viewApplications(service);
        try{
            scanner.nextLine();
            System.out.print("Enter application ID : ");
            int id = scanner.nextInt();

            System.out.print("Enter new application status(APPLIED, INTERVIEW, OFFER, REJECTED): ");
            String status = scanner.next();

            service.updateApplicationStatus(id, status);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Delete Application
    private static void deleteApplication(JobApplicationService service){
        try{
            System.out.print("Enter application id to delete: ");
            int id = scanner.nextInt();

             service.deleteApplication(id);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
}


