Use insignia;

update Player set MaxDistance = (Select ceiling(rand()*100)) where True;
update Player set TimeOfFlight = (Select ceiling(rand()*100)) where True;
update Player set Bitcoins = 1000 where Id = 'IdPau';


update Player set Rol = 'Second Officer' where Id = 'IdArnau';
update Player set Rol = 'Senior First Officer' where Id = 'IdJulia';

update Player set Rol = 'First Officer' where Id = 'IdMarc';

update Player set Rol = 'Training Captain' where Id = 'IdPau';