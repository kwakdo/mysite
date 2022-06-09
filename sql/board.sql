-- board

desc board;

insert
  into board
values (null, 'TEST', 'TEST', '0', now(), '1', '1', '0', '3');

select * from board;

delete from board;

select * from guestbook;

update user set name='관리자', email = '1', password='1' where no=3;

insert
  into user
values (null, 'admin', 'admin', '1234', 'male', now());