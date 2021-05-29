CREATE DATABASE data_base_for_hw3
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
    company_name character varying(255),
    number_of_developers integer,
    PRIMARY KEY (company_id)
);

CREATE TABLE customers
(
    customer_id SERIAL,
    customer_name character varying(255)
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
    PRIMARY KEY (developer_id)
);

CREATE TABLE projects
(
    project_id SERIAL,
    project_name character varying(255) UNIQUE,
    stage character varying(50),
    time_period integer,
    coast integer,
    PRIMARY KEY (project_id)
);

CREATE TYPE stack AS ENUM('Java', 'C++', 'C#', 'JS');
CREATE TYPE level AS ENUM('Junior', 'Middle', 'Senior');

CREATE TABLE skills
(
    record_id SERIAL PRIMARY KEY,
	developer_id integer NOT NULL,
    stack stack,
    level level,
	FOREIGN KEY (developer_id) REFERENCES developers(developer_id)
);

CREATE TABLE customers_and_companies
(
   customer_id int NOT NULL,
   company_id int NOT NULL,
   project_id int NOT NULL,
   FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
   FOREIGN KEY (company_id) REFERENCES companies(company_id),
   FOREIGN KEY (project_id) REFERENCES projects(project_id),
   UNIQUE (customer_id, company_id, project_id)
);

CREATE TABLE developers_on_projects
(
   project_id int NOT NULL,
   developer_id int NOT NULL,
   FOREIGN KEY (project_id) REFERENCES projects(project_id),
   FOREIGN KEY (developer_id) REFERENCES developers(developer_id),
   UNIQUE (project_id, developer_id)
);

ALTER TABLE developers
ADD COLUMN developer_email character varying(255) UNIQUE;

UPDATE developers
SET developer_email='a@gmail.com'
WHERE developer_id=1;

UPDATE developers
SET developer_email='b@gmail.com'
WHERE developer_id=2;

UPDATE developers
SET developer_email='c@gmail.com'
WHERE developer_id=3;

UPDATE developers
SET developer_email='d@gmail.com'
WHERE developer_id=4;

UPDATE developers
SET developer_email='e@gmail.com'
WHERE developer_id=5;

UPDATE developers
SET developer_email='f@gmail.com'
WHERE developer_id=6;

UPDATE developers
SET developer_email='v@gmail.com'
WHERE developer_id=27;

ALTER TABLE developers
ALTER COLUMN developer_email SET NOT NULL;

ALTER TABLE developers
ADD CONSTRAINT unique_email
UNIQUE (developer_email);

ALTER TABLE companies
ADD CONSTRAINT unique_name
UNIQUE (company_name);

ALTER TABLE customers
ADD CONSTRAINT unique_cust_name
UNIQUE (customer_name);

ALTER TABLE projects
ADD CONSTRAINT unique_proj_name
UNIQUE (project_name);

ALTER TABLE projects
ADD COLUMN number_of_developers int;

ALTER TABLE projects
ADD COLUMN date_of_begining date;

UPDATE projects
SET number_of_developers=10
WHERE project_id=1;

UPDATE projects
SET number_of_developers=90
WHERE project_id=2;

UPDATE projects
SET number_of_developers=16
WHERE project_id=3;

UPDATE projects
SET number_of_developers=40
WHERE project_id=4;

UPDATE projects
SET number_of_developers=7
WHERE project_id=5;

UPDATE projects
SET number_of_developers=19
WHERE project_id=6;

UPDATE projects
SET number_of_developers=20
WHERE project_id=7;

UPDATE projects
SET number_of_developers=2
WHERE project_id=8;

UPDATE projects
SET number_of_developers=1
WHERE project_id=9;

UPDATE projects
SET date_of_begining='2021-01-01'
WHERE project_id=1;

UPDATE projects
SET date_of_begining='2020-01-01'
WHERE project_id=2;

UPDATE projects
SET date_of_begining='2021-02-01'
WHERE project_id=3;

UPDATE projects
SET date_of_begining='2021-03-01'
WHERE project_id=4;

UPDATE projects
SET date_of_begining='2021-01-07'
WHERE project_id=5;

UPDATE projects
SET date_of_begining='2021-01-09'
WHERE project_id=6;

UPDATE projects
SET date_of_begining='2021-01-10'
WHERE project_id=7;

UPDATE projects
SET date_of_begining='2020-10-01'
WHERE project_id=8;

UPDATE projects
SET date_of_begining='2021-01-20'
WHERE project_id=9;