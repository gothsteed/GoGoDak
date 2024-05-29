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

select *
from tbl_board
order by board_seq desc;

desc tbl_member;
desc tbl_login_history;

select title, content, pic,board_seq
from tbl_board 
where board_seq = 39 

select *
from tbl_member
order by member_seq desc;

update tbl_member set exist_status = 0
where exist_status = 1 and id = 'qwerwqer' and password = '86fdff24aec78a01391e48e30b29bfdc0c47abdbbc6a0b9b833c5bc464a4cdbe';
-- 1 행 이(가) 업데이트되었습니다.

commit;
-- 커밋 완료.

-----------------------------------------------------------------------

desc tbl_manufacturer;

desc tbl_product;

select *
from tbl_manufacturer;

SELECT manufacturer_name, product_seq, fk_manufacturer_seq, product_name, description, base_price, stock, main_pic, discription_pic, product_type, discount_type, discount_number
FROM
(
    select manufacturer_seq, manufacturer_name
    from tbl_manufacturer 
    where manufacturer_seq = '2'
) M
JOIN tbl_product P
ON P.fk_manufacturer_seq = M.manufacturer_seq

-----------------------------------------------------------------------

desc tbl_product;

select *
from tbl_product;

update tbl_product set FK_MANUFACTURER_SEQ = 3
where PRODUCT_NAME = '닭가슴살 곤약볶음밥 퀴노아 잡채볶음밥';

commit;

-----------------------------------------------------------------------

desc tbl_member

desc tbl_login_history
desc tbl_question
desc tbl_order
desc tbl_review

select * from user_constraints where constraint_type = 'R';
select * from user_constraints where constraint_type = 'P';

ALTER TABLE tbl_order
DROP CONSTRAINT FK_TBL_ORDER_FK_MEMBER_SEQ;

ALTER TABLE tbl_order
ADD CONSTRAINT FK_TBL_ORDER_FK_MEMBER_SEQ
FOREIGN KEY (FK_MEMBER_SEQ)
REFERENCES tbl_member(MEMBER_SEQ)
ON DELETE CASCADE

select *
from tbl_member
where id = 'hansol';

delete from tbl_member
where id = 'hansol';
-- 1 행 이(가) 삭제되었습니다.

commit;
-- 커밋 완료.

-----------------------------------------------------------------------

select *
from tbl_member

update tbl_member set name = '박보검'
where id = 'bot123';

commit;

-----------------------------------------------------------------------




