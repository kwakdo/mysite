desc site;

insert into site value(null, 'MySite', '안녕하세요. 곽도훈의 mysite에 오신 것을 환영합니다.', '/assets/gallery/default.jpg', '이 사이트는 웹 프로그램밍 실습과제 예제 사이트입니다.\n메뉴는 사이트 소개, 방명록, 게시판이 있구요. Java 수업 + 데이터베이스 수업 + 웹프로그래밍 수업 배운 거 있는거 없는 거 다 합쳐서 만들어 놓은 사이트입니다.');

select * from site;

 select title, 
		  		 welcome_message as welcomeMessage,
		  		 profile_url as profileURL,
		  		 description as Description
		    from site;	

select * from user;

alter table user add column role enum('USER', 'ADMIN') default 'USER' after gender;

update user set role='ADMIN' where no=1 or no=3;