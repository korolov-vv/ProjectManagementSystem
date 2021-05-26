insert into companies (company_id, company_name, number_of_developers)
values (1, 'Epam', null);
insert into companies (company_id, company_name, number_of_developers)
values (2, 'GOIT', null);
insert into companies (company_id, company_name, number_of_developers)
values (3, 'Microsoft', null);
insert into companies (company_id, company_name, number_of_developers)
values (4, 'Apple', null);
insert into companies (company_id, company_name, number_of_developers)
values (5, 'CD Project', null);

insert into projects values (1, null, 5, null, 'Best project', 'new', 5, 1000000);
insert into projects values (2, null, 5, null, 'Proj name 2', 'new', 5, 1000000);
insert into projects values (3, null, 5, null, 'Proj name 2', 'pending', 5, 10000);
insert into projects values (4, null, 5, null, 'Proj name 3', 'new', 5, 100000);
insert into projects values (5, null, 5, null, 'Proj name 4', 'new', 5, 5000000);
insert into projects values (6, null, 5, null, 'Proj name 5', 'new', 5, 6000000);
insert into projects values (7, null, 5, null, 'Proj name 6', 'new', 5, 7000000);
insert into projects values (8, null, 5, null, 'Proj name 7', 'new', 5, 80000);
insert into projects values (9, null, 5, null, 'Proj name 8', 'new', 5, 1000);

insert into developers (developer_id, first_name, last_name, gender, age, experience_in_years, company_id, project_id)
values (1, 'Ivan', 'Petrov', 'male', 30, 10, 5, 1);
insert into developers (developer_id, first_name, last_name, gender, age, experience_in_years, company_id, project_id)
values (2, 'Petro', 'Ivanov', 'male', 30, 10, 4, 3);
insert into developers (developer_id, first_name, last_name, gender, age, experience_in_years, company_id, project_id)
values (3, 'Olexandr', 'Puszkin', 'male', 30, 10, 5, 2);
insert into developers (developer_id, first_name, last_name, gender, age, experience_in_years, company_id, project_id)
values (4, 'Anton', 'Gogol', 'male', 30, 10, 2, 4);
insert into developers (developer_id, first_name, last_name, gender, age, experience_in_years, company_id, project_id)
values (5, 'Serhii', 'Bulba', 'male', 30, 10, 1, 5);

insert into skills values (DEFAULT, 1, 'Java', 'Junior');
insert into skills values (DEFAULT, 2, 'C++', 'Junior');
insert into skills values (DEFAULT, 1, 'JS', 'Middle');
insert into skills values (DEFAULT, 3, 'C++', 'Junior');
insert into skills values (DEFAULT, 4, 'JS', 'Senior');
insert into skills values (DEFAULT, 5, 'JS', 'Junior');
insert into skills values (DEFAULT, 5, 'Java', 'Junior');
insert into skills values (DEFAULT, 1, 'Java', 'Junior');

insert into customers values (1, 'Customer', 1);
insert into customers values (2, 'Customer9', 1);
insert into customers values (3, 'Customer2', 2);
insert into customers values (4, 'Customer3', 4);
insert into customers values (5, 'Customer4', 1);
insert into customers values (6, 'Customer5', 3);
insert into customers values (7, 'Customer6', 7);
insert into customers values (8, 'Customer7', 6);
insert into customers values (9, 'Customer8', 8);

insert into developers_on_projects values (1, 1);
insert into developers_on_projects values (1, 5);
insert into developers_on_projects values (1, 2);
insert into developers_on_projects values (2, 5);
insert into developers_on_projects values (2, 3);
insert into developers_on_projects values (3, 5);
insert into developers_on_projects values (3, 3);
insert into developers_on_projects values (3, 4);
insert into developers_on_projects values (3, 1);
insert into developers_on_projects values (4, 5);
insert into developers_on_projects values (5, 5);
insert into developers_on_projects values (6, 5);
insert into developers_on_projects values (7, 5);
insert into developers_on_projects values (8, 5);
insert into developers_on_projects values (8, 4);
insert into developers_on_projects values (8, 2);

insert into customers_and_companies values (1, 1, 8);
insert into customers_and_companies values (1, 2, 7);
insert into customers_and_companies values (2, 2, 6);
insert into customers_and_companies values (3, 4, 5);
insert into customers_and_companies values (4, 5, 4);
insert into customers_and_companies values (5, 2, 1);
insert into customers_and_companies values (6, 1, 1);
insert into customers_and_companies values (6, 2, 1);
insert into customers_and_companies values (7, 2, 8);
insert into customers_and_companies values (7, 5, 1);
insert into customers_and_companies values (7, 4, 7);
insert into customers_and_companies values (8, 2, 4);
insert into customers_and_companies values (3, 2, 6);
insert into customers_and_companies values (5, 1, 2);