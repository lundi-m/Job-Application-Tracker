package model;

import java.time.LocalDate;

// Constructor for reading from the database
public class JobApplication {
    private int id;
    private String companyName;
    private String jobTitle;
    private String jobType;
    private String location;
    private LocalDate dateApplied;
    private ApplicationStatus status;

    public JobApplication(int id,
                          String companyName,
                          String jobTitle,
                          String jobType,
                          String location,
                          LocalDate dateApplied,
                          ApplicationStatus status) {
        this.id = id;
        this.companyName = companyName;
        this.jobTitle = jobTitle;
        this.jobType = jobType;
        this.location = location;
        this.dateApplied = dateApplied;
        this.status = status;
    }

    // Constructor for writing in the database
    public JobApplication(String companyName,
                          String jobTitle,
                          String jobType,
                          String location) {
        this.companyName = companyName;
        this.jobTitle = jobTitle;
        this.jobType = jobType;
        this.location = location;
        this.dateApplied = LocalDate.now();
        this.status = ApplicationStatus.APPLIED;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDateApplied() {
        return dateApplied;
    }

    public void setDateApplied(LocalDate dateApplied) {
        this.dateApplied = dateApplied;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", companyName : '" + companyName + '\'' +
                ", jobTitle : '" + jobTitle + '\'' +
                ", jobType : '" + jobType + '\'' +
                ", location : '" + location + '\'' +
                ", dateApplied : " + dateApplied +
                ", status : " + status +
                '}';
    }
}

