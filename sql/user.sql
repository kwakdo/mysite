-- UserRepository

desc user;

select * from user;

select * from guestbook;

select no, name from user where email='vv05044@gmail.com' and password='1234';

-- findByNo
select no, name, email, gender 
from user 
where no='1' and name='dooly';
