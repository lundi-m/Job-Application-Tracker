CREATE DATABASE IF NOT EXISTS job_applications;

USE job_applications;

CREATE TABLE job_applications(
	id INT PRIMARY KEY AUTO_INCREMENT,
    company_name VARCHAR(150) NOT NULL,
    job_title VARCHAR(150) NOT NULL,
    job_type VARCHAR(50) NOT NULL,
    location VARCHAR(150),
    date_applied DATE NOT NULL,
    status VARCHAR(30) NOT NULL,

    CONSTRAINT check_status
        CHECK (status IN ('APPLIED','INTERVIEW','OFFER','REJECTED'))
);