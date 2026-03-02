package service;


import dao.JobApplicationDAO;
import model.ApplicationStatus;
import model.JobApplication;

import java.util.List;

public class JobApplicationService {

    private final JobApplicationDAO dao;

    public JobApplicationService(JobApplicationDAO dao) {
        this.dao = dao;
    }

    // Create New Application
    public void newJobApplication(String companyName, String jobTitle, String jobType, String location){

        if (companyName == null || companyName.trim().isEmpty()){
            throw new IllegalArgumentException("Company name cannot be empty.");
        }

        if (jobTitle == null || jobTitle.trim().isEmpty()){
            throw new IllegalArgumentException("Job title cannot be empty.");
        }

        if (jobType == null || jobType.trim().isEmpty()){
            throw new IllegalArgumentException("Job type cannot be empty.");
        }

        if (location == null || location.trim().isEmpty()){
            throw new IllegalArgumentException("Location cannot be empty.");
        }

        JobApplication newApplication = new JobApplication(companyName, jobTitle, jobType, location);
        dao.save(newApplication);

    }

    // Read From The Database
    public List<JobApplication> getAllApplications(){
        return dao.viewAllApplications();
    }

    // Update Application Status
    public void updateApplicationStatus(int id, String newUserStatus){

        JobApplication application = dao.findById(id);
        ApplicationStatus newStatus = ApplicationStatus.fromString(newUserStatus);

        if (application.getStatus() == ApplicationStatus.REJECTED &&
            newStatus == ApplicationStatus.INTERVIEW){
            throw new IllegalArgumentException("Application status cannot be changed from REJECTED to INTERVIEW");
        }
        dao.updateStatus(id, newStatus);
    }

    // Delete Application
    public void deleteApplication(int id){

        if (dao.findById(id) == null){
            throw new IllegalArgumentException("Application not found.");
        }

        dao.deleteApplication(id);
        System.out.println("Application deleted.");
    }
}
