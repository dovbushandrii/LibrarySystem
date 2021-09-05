delete from client;

insert into client(id, first_name, last_name, date_of_birth, email) values
(1,'Andy','Test',to_date('17/12/2015', 'DD/MM/YYYY'),'mail@info.com'),
(2,'Geo','Les',to_date('13/08/2010', 'DD/MM/YYYY'),'notmail@info.com');