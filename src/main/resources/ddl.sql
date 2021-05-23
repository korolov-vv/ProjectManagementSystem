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
    customer_id integer,
    project_id integer,
    salary integer,
    PRIMARY KEY (company_id)
);

CREATE TABLE customers
(
    customer_id SERIAL,
    customer_name character varying(255),
    project_id integer,
    PRIMARY KEY (customer_id)
);

CREATE TABLE developers
(
    developer_id SERIAL,
    first_name character varying(20),
    last_name character varying(25) NOT NULL,
    gender character varying(25) NOT NULL,
    age integer,
    experience_in_years integer NOT NULL,
    company_id integer NOT NULL,
    project_id integer NOT NULL,
    PRIMARY KEY (developer_id)
);

CREATE TABLE projects
(
    project_id SERIAL,
    developer_id integer,
    company_id integer,
    customer_id integer,
    project_name character varying(255),
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

ALTER TABLE companies
    ADD FOREIGN KEY (customer_id)
    REFERENCES customers (customer_id)
    NOT VALID;


ALTER TABLE companies
    ADD FOREIGN KEY (project_id)
    REFERENCES projects (project_id)
    NOT VALID;


ALTER TABLE customers
    ADD FOREIGN KEY (project_id)
    REFERENCES projects (project_id)
    NOT VALID;


ALTER TABLE developers
    ADD FOREIGN KEY (company_id)
    REFERENCES companies (company_id)
    NOT VALID;


ALTER TABLE developers
    ADD FOREIGN KEY (project_id)
    REFERENCES projects (project_id)
    NOT VALID;


ALTER TABLE projects
    ADD FOREIGN KEY (company_id)
    REFERENCES companies (company_id)
    NOT VALID;


ALTER TABLE projects
    ADD FOREIGN KEY (developer_id)
    REFERENCES developers (developer_id)
    NOT VALID;


ALTER TABLE projects
    ADD FOREIGN KEY (developer_id)
    REFERENCES developers (developer_id)
    NOT VALID;


ALTER TABLE skills
    ADD FOREIGN KEY (developer_id)
    REFERENCES developers (developer_id)
    NOT VALID;

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