CREATE DATABASE project_management
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Polish_Poland.1250'
    LC_CTYPE = 'Polish_Poland.1250'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE TABLE companies
(
    company_id SERIAL,
    company_name character varying(255) UNIQUE,
    number_of_developers integer,
    PRIMARY KEY (company_id)
);

CREATE TABLE customers
(
    customer_id SERIAL,
    customer_name character varying(255) UNIQUE,
    PRIMARY KEY (customer_id)
);

CREATE TABLE developers
(
    developer_id SERIAL,
    first_name character varying(255),
    last_name character varying(255) NOT NULL,
    gender character varying(25) NOT NULL,
    age integer,
    experience_in_years integer NOT NULL,
    company_id integer NOT NULL,
    salary integer,
    developer_email character varying(255) UNIQUE NOT NULL,
    PRIMARY KEY (developer_id)
);

CREATE TABLE projects
(
    project_id SERIAL,
    project_name character varying(255) UNIQUE,
    stage character varying(50),
    time_period integer,
    coast integer,
    number_of_developers integer,
    date_of_beginning date,
    PRIMARY KEY (project_id)
);

CREATE TYPE stack AS ENUM('Java', 'C++', 'C#', 'JS');
CREATE TYPE level AS ENUM('Junior', 'Middle', 'Senior');

CREATE TABLE skills
(
    record_id SERIAL PRIMARY KEY,
    stack stack,
    level level,
    developer_email varchar(255),
	FOREIGN KEY (developer_email) REFERENCES developers(developer_email)
);

CREATE TABLE customers_and_companies
(
   customer_id int NOT NULL,
   company_id int NOT NULL,
   project_id int NOT NULL,
   FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
   FOREIGN KEY (company_id) REFERENCES companies(company_id),
   FOREIGN KEY (project_id) REFERENCES projects(project_id),
   PRIMARY KEY (customer_id, company_id, project_id)
);

CREATE TABLE developers_on_projects
(
   project_id int NOT NULL,
   developer_id int NOT NULL,
   FOREIGN KEY (project_id) REFERENCES projects(project_id),
   FOREIGN KEY (developer_id) REFERENCES developers(developer_id),
   PRIMARY KEY (project_id, developer_id)
);
