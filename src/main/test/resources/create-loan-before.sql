delete from loan_items;
delete from loan;
delete from item;
delete from client;

insert into client(id, first_name, last_name, date_of_birth, email) values
(1,'Andy','Test',to_date('17/12/2015', 'DD/MM/YYYY'),'mail@info.com'),
(2,'Geo','Les',to_date('13/08/2010', 'DD/MM/YYYY'),'notmail@info.com');

insert into item(id, name, type) values
(1,'book',0),
(2,'cd',1),
(3,'magazine',2),
(4,'thing',3),
(5,'Spring in Action',0);

insert into loan(id, start_date, end_date, client_id) values
(1,to_date('17/12/2021', 'DD/MM/YYYY'),to_date('23/12/2021', 'DD/MM/YYYY'),1),
(2,to_date('13/12/2021', 'DD/MM/YYYY'),to_date('25/12/2021', 'DD/MM/YYYY'),2);

insert into loan_items(loan_id,items_id) values
(1,1),
(2,2),
(2,3);