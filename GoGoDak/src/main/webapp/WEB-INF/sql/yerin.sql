show user;
-- USER이(가) "SEMI_ORAUSER3"입니다.

desc tbl_member;

select *
from tbl_member
order by member_seq desc;

select id
from tbl_member
where id = 'admin';

select email
from tbl_member
where email = 'Fm3kaU93VWbmQq2wh984EeBMZreWLwAxeQpgkazuLzU=';

select ceil(count(*)/3)
from tbl_member
where id != 'admin'

select id, name, email,
       case when substr(jubun, 7, 1) in('1', '3') then '남' else '여' end AS GENDER
from tbl_member
where id != 'admin'

select *
from tbl_member
where id = 'hansol';

delete from tbl_member
where id = 'hansol';
-- 1 행 이(가) 삭제되었습니다.

commit;
-- 커밋 완료.

select id 
from tbl_member 
where EXIST_STATUS = 1 and name = '이지은' and email = 'JuM4kdOjFx0iUJ6OCxncSgwax5hwzvSA0+j1Q5q8sFM=';

select id
from tbl_member
where EXIST_STATUS = 1 and id = 'iyou99' and name = '이지은' and email = 'JuM4kdOjFx0iUJ6OCxncSgwax5hwzvSA0+j1Q5q8sFM=';

select *
from tbl_board;

update tbl_member set password = '9695b88a59a1610320897fa84cb7e144cc51f2984520efb77111d94b402a8382', last_password_change = sysdate
where id = '9195yerin'
-- 1 행 이(가) 업데이트되었습니다.

commit;
-- 커밋 완료.

-----------------------------------------------------------------------

desc tbl_product;

select *
from tbl_product;

select PRODUCT_NAME, DESCRIPTION, BASE_PRICE, STOCK, MAIN_PIC, DISCRIPTION_PIC
from tbl_product
where product_name like '%' || '닭' || '%';

-----------------------------------------------------------------------

<<<<<<< HEAD
select *
from tbl_board
order by board_seq desc;
=======
desc tbl_member;
desc tbl_login_history;
>>>>>>> branch 'main' of https://github.com/gothsteed/GoGoDak.git

<<<<<<< HEAD
select title, content, pic,board_seq
from tbl_board 
where board_seq = 39 
=======
select *
from tbl_member
order by member_seq desc;

delete from tbl_member
where id = 'qwerwqer' and password = '86fdff24aec78a01391e48e30b29bfdc0c47abdbbc6a0b9b833c5bc464a4cdbe';

update tbl_member set exist_status = 0
where exist_status = 1 and id = 'qwerwqer' and password = '86fdff24aec78a01391e48e30b29bfdc0c47abdbbc6a0b9b833c5bc464a4cdbe';
-- 1 행 이(가) 업데이트되었습니다.

commit;
-- 커밋 완료.

>>>>>>> branch 'main' of https://github.com/gothsteed/GoGoDak.git
