-- UserRepository

desc user;

select * from user;


select * from guestbook;

select no, name from user where email='vv05044@gmail.com' and password='1234';

-- join
insert
  into user
values (null, '관리자', 'admin@mysite.com', '1234', 'male', now());

-- login
select no, name
  from user
 where email='kickscar@gmail.com'
   and password='1234';

-- findByNo
select no, name, email, gender 
from user 
where no='1' and name='dooly';

-- update
update user
   set name='안대혁3', gender='male'
 where no = 2;
