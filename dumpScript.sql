-- ============================================================
-- Hospital DB init/dump script
-- Safe to run on a fresh volume (Docker init) OR manually
-- re-run against an existing database (idempotent).
-- ============================================================

CREATE DATABASE IF NOT EXISTS hospital;
USE hospital;

-- ------------------------------------------------------------
-- Tables (order matters: doctor/patient before appointment)
-- ------------------------------------------------------------

CREATE TABLE IF NOT EXISTS doctor (
    doctor_id INT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    specialty VARCHAR(100),
    phone VARCHAR(20),
    email VARCHAR(100) UNIQUE,
    hireDate DATE
);

CREATE TABLE IF NOT EXISTS patient (
    patient_id INT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    dob DATE NOT NULL,
    dateOfIntern DATE,
    dateOfRelease DATE,
    attendingDoctorId INT,
    CONSTRAINT fk_attending_doctor
        FOREIGN KEY (attendingDoctorId) REFERENCES doctor(doctor_id),
    CONSTRAINT chk_dates
        CHECK (dateOfRelease IS NULL OR dateOfRelease >= dateOfIntern)
);

CREATE TABLE IF NOT EXISTS room (
    room_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    number VARCHAR(20) NOT NULL,
    is_equipped BOOLEAN,
    in_maintenance BOOLEAN,
    is_reserved BOOLEAN
);

CREATE TABLE IF NOT EXISTS appointment (
    appointment_id INT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    patient_id INT NOT NULL,
    doctor_id INT NOT NULL,
    room_id INT,
    appointmentDate DATE,
    CONSTRAINT fk_doctor_id
        FOREIGN KEY (doctor_id) REFERENCES doctor(doctor_id),
    CONSTRAINT fk_patient_id
        FOREIGN KEY (patient_id) REFERENCES patient(patient_id),
    CONSTRAINT fk_room_id
        FOREIGN KEY (room_id) REFERENCES room(room_id)
);

-- ------------------------------------------------------------
-- Dummy data (only inserted if tables are currently empty,
-- so re-running this script won't duplicate rows)
-- ------------------------------------------------------------

INSERT INTO doctor (firstname, lastname, specialty, phone, email, hireDate)
SELECT * FROM (SELECT
    'James' AS firstname, 'Wilson' AS lastname, 'Cardiology' AS specialty,
    '404-555-0101' AS phone, 'james.wilson@clinic.com' AS email, '2015-03-12' AS hireDate
UNION ALL SELECT 'Sarah', 'Chen', 'Pediatrics', '404-555-0102', 'sarah.chen@clinic.com', '2018-07-22'
UNION ALL SELECT 'Michael', 'Rodriguez', 'Orthopedics', '404-555-0103', 'michael.rodriguez@clinic.com', '2012-11-05'
UNION ALL SELECT 'Emily', 'Johnson', 'Dermatology', '404-555-0104', 'emily.johnson@clinic.com', '2020-01-15'
UNION ALL SELECT 'David', 'Patel', 'General Practice', '404-555-0105', 'david.patel@clinic.com', '2016-09-30'
) AS seed
WHERE NOT EXISTS (SELECT 1 FROM doctor);

INSERT INTO patient (firstname, lastname, dob, dateOfIntern, dateOfRelease, attendingDoctorId)
SELECT * FROM (SELECT
    'John' AS firstname, 'Smith' AS lastname, '1985-06-14' AS dob,
    '2026-06-01' AS dateOfIntern, '2026-06-05' AS dateOfRelease, 1 AS attendingDoctorId
UNION ALL SELECT 'Maria', 'Garcia', '1990-02-28', '2026-06-10', '2026-06-15', 2
UNION ALL SELECT 'Robert', 'Brown', '1978-11-03', '2026-06-20', NULL, 3
UNION ALL SELECT 'Linda', 'Davis', '1965-09-19', '2026-05-15', '2026-05-20', 4
UNION ALL SELECT 'William', 'Miller', '2001-04-07', '2026-06-25', NULL, 5
UNION ALL SELECT 'Jennifer', 'Anderson', '1993-12-25', '2026-06-18', '2026-06-22', 1
UNION ALL SELECT 'Charles', 'Taylor', '1972-08-09', '2026-06-28', NULL, 3
) AS seed
WHERE NOT EXISTS (SELECT 1 FROM patient);

INSERT INTO room (name, number, is_equipped, in_maintenance, is_reserved)
SELECT * FROM (SELECT
    'Emergency Room' AS name, 'ER-101' AS number, TRUE AS is_equipped, FALSE AS in_maintenance, FALSE AS is_reserved
UNION ALL SELECT 'Operating Room', 'OR-201', TRUE, FALSE, FALSE
UNION ALL SELECT 'Consultation Room', 'CR-102', FALSE, FALSE, FALSE
UNION ALL SELECT 'Recovery Room', 'RR-103', TRUE, TRUE, FALSE
UNION ALL SELECT 'ICU', 'ICU-301', TRUE, FALSE, FALSE
) AS seed
WHERE NOT EXISTS (SELECT 1 FROM room);