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