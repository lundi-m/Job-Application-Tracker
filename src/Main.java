import dao.JobApplicationDAO;
import model.ApplicationStatus;
import model.JobApplication;
import model.JobType;
import service.JobApplicationService;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        JobApplicationDAO dao = new JobApplicationDAO();
        JobApplicationService service = new JobApplicationService(dao);

        boolean isRunning = true;

        while (isRunning) {
            mainMenu();
            System.out.print("Enter Option: ");

            int option;
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number 1-5.");
                continue;
            }

            switch (option) {
                case 1 -> addApplication(service);
                case 2 -> viewApplications(service);
                case 3 -> findByStatus(service);
                case 4 -> updateApplicationStatus(service);
                case 5 -> deleteApplication(service);
                case 6 -> {
                    System.out.println("=== Goodbye ===");
                    isRunning = false;
                }
                default -> System.out.println("Invalid choice. Please select 1-5.");
            }
        }
    }

    private static void mainMenu() {
        System.out.println("\n=== Job Application Tracker ===");
        System.out.println("1. Add Application");
        System.out.println("2. View Applications");
        System.out.println("3. Update Application Status");
        System.out.println("4. Delete Application");
        System.out.println("5. Exit");
    }

    // Add new Job Application
    private static void addApplication(JobApplicationService service) {
        try {
            System.out.print("Company Name: ");
            String companyName = scanner.nextLine();

            System.out.print("Job Title: ");
            String jobTitle = scanner.nextLine();

            System.out.print("Job Type: ");
            String jobTypeInput = scanner.nextLine();

            System.out.print("Location: ");
            String location = scanner.nextLine();

            JobType jobType = JobType.fromString(jobTypeInput);
            service.newJobApplication(companyName, jobTitle, jobType, location);

            System.out.println("Application created successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // View All Job Applications
    private static void viewApplications(JobApplicationService service) {
        List<JobApplication> jobApplications = service.getAllApplications();

        if (jobApplications.isEmpty()) {
            System.out.println("No job applications found.");
            return;
        }

        for (JobApplication jobApplication : jobApplications) {
            System.out.println(jobApplication);
        }
    }

    private static void findByStatus(JobApplicationService service){

        System.out.print("Enter status (APPLIED, INTERVIEW, REJECTED, OFFER, ACCEPTED): ");
        String userStatus = scanner.nextLine();

        try{
           List<JobApplication> applications =  service.findByStatus(userStatus);

           if (applications.isEmpty()){
               System.out.println("Applications with status: " + userStatus + " are not found.");
               return;
           }

            for (JobApplication application : applications){
                System.out.println(application.toString());
            }

        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Update an existing job application
    private static void updateApplicationStatus(JobApplicationService service) {
        List<JobApplication> jobApplications = service.getAllApplications();

        if (jobApplications.isEmpty()) {
            System.out.println("No job applications to update.");
            return; // exit early if list is empty
        }

        // show applications
        for (JobApplication jobApplication : jobApplications) {
            System.out.println(jobApplication.toString());
        }

        try {
            System.out.print("Enter application ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter new application status (APPLIED, INTERVIEW, OFFER, REJECTED): ");
            String userStatus = scanner.nextLine().toUpperCase();

            ApplicationStatus status = ApplicationStatus.fromString(userStatus);
            service.updateApplicationStatus(id, status.name());

            System.out.println("Status updated successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Delete Application
    private static void deleteApplication(JobApplicationService service) {
        List<JobApplication> jobApplications = service.getAllApplications();

        if (jobApplications.isEmpty()) {
            System.out.println("No job applications to update.");
            return;
        }

        // show applications
        for (JobApplication jobApplication : jobApplications) {
            System.out.println(jobApplication.toString());
        }

        try {
            System.out.print("Enter application ID to delete: ");
            int id = Integer.parseInt(scanner.nextLine());

            service.deleteApplication(id);
            System.out.println("Application deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}