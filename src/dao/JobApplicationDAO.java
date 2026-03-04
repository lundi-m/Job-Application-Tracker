package dao;

import model.ApplicationStatus;
import model.JobApplication;
import model.JobType;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobApplicationDAO {

    //Save new job application
    public void save(JobApplication application) {

        String query = "INSERT INTO job_applications (company_name, job_title, job_type, location, date_applied, status) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, application.getCompanyName());
            statement.setString(2, application.getJobTitle());
            statement.setString(3, application.getJobType().name());
            statement.setString(4, application.getLocation());
            statement.setDate(5, Date.valueOf(application.getDateApplied()));
            statement.setString(6, application.getStatus().name());

            statement.execute();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    // Find Job Application using id
    public JobApplication findById(int id){

        String query = "SELECT * FROM job_applications WHERE id = ?";

        JobApplication application = null;
        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                application = new JobApplication(resultSet.getInt("id"),
                        resultSet.getString("company_name"),
                        resultSet.getString("job_title"),
                        JobType.valueOf(resultSet.getString("job_type")),
                        resultSet.getString("location"),
                        resultSet.getDate("date_applied").toLocalDate(),
                        ApplicationStatus.valueOf(resultSet.getString("status"))
                );
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return application;
    }

    // Find Job Application using Application status
    public List<JobApplication> findByAppStatus(ApplicationStatus status){
        List<JobApplication> jobApplications = new ArrayList<>();

        String query = "SELECT * FROM job_applications WHERE status = ?";

        JobApplication application = null;

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){

            statement.setString(1, status.name());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                 application = new JobApplication(
                        resultSet.getInt("id"),
                        resultSet.getString("company_name"),
                        resultSet.getString("job_title"),
                        JobType.valueOf(resultSet.getString("job_type")),
                        resultSet.getString("location"),
                        resultSet.getDate ("date_applied").toLocalDate(),
                        ApplicationStatus.valueOf(resultSet.getString("status"))
                );

                jobApplications.add(application);
            }

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return jobApplications;
    }

    // View all Job Applications
    public List<JobApplication> viewAllApplications(){

        List<JobApplication> jobApplications = new ArrayList<>();

        String query = "SELECT * FROM job_applications";

        try(Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query))
        {
            while (resultSet.next()){
                JobApplication application = new JobApplication(
                resultSet.getInt("id"),
                resultSet.getString("company_name"),
                resultSet.getString("job_title"),
                JobType.valueOf(resultSet.getString("job_type")),
                resultSet.getString("location"),
                resultSet.getDate ("date_applied").toLocalDate(),
                ApplicationStatus.valueOf(resultSet.getString("status"))
                );

                jobApplications.add(application);
            }

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }

        return jobApplications;
    }

    // Update Job Application Status
    public void updateStatus(int id, ApplicationStatus status){

        String query = "UPDATE job_applications SET status = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){

            statement.setString(1, status.name());
            statement.setInt(2, id);

            statement.executeUpdate();

        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    // Delete Job Application
    public void deleteApplication(int id){

        String query = "DELETE FROM job_applications WHERE id = ?";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){

            statement.setInt(1, id);

            statement.executeUpdate();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
}
