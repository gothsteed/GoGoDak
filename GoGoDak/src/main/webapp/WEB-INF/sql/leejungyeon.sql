
create sequence member_seq
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;

desc tbl_member;

CREATE TABLE tbl_member (
	member_seq Number NOT NULL, /* ����������ȣ */
	email VARCHAR2(200) NOT NULL, /* �̸��� */
	id VARCHAR2(40) NOT NULL, /* ���̵� */
	password VARCHAR2(200) NOT NULL, /* ��й��? */
	name VARCHAR2(30) NOT NULL, /* �̸� */
	tel varchar2(200)  NOT NULL, /* �ڵ�����ȣ */
	jubun VARCHAR2(7) NOT NULL, /* �ֹε�Ϲ��? */
	point Number default 0 NOT NULL, /* ����Ʈ */
	register_date DATE NOT NULL, /* ���Գ�¥ */
	exist_status NUMBER(1) DEFAULT 1 NOT NULL, /* ȸ��Ż������ */
	active_status NUMBER(1) DEFAULT 1 NOT NULL, /* �޸����� */
	last_password_change DATE DEFAULT sysdate NOT NULL /* ������ ��й��? ������ */
);

ALTER TABLE tbl_member
	ADD
		CONSTRAINT PK_tbl_member
		PRIMARY KEY (
			member_seq
		);


ALTER TABLE tbl_member
	ADD
		CONSTRAINT UK_tbl_member
		UNIQUE (
			id,
			email
		);
        
        
insert into tbl_member(member_seq, email, id, password, name, tel, jubun ,point, exist_status, active_status, last_password_change, postcode,address,address_detail,address_extra,registerday)
values(member_seq.nextval, 'Fm3kaU93VWbmQq2wh984EeBMZreWLwAxeQpgkazuLzU=','admin', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4','������' ,'k6AvvKD9cZaeKhlunBk9ew==', 9701011,10,1,1,'2024-05-15',04001,'�����?','������','������','2024-02-01' );
commit;        


delete tbl_member where id='jylee';
select *
from tbl_member;

        
delete tbl_member where id='admin';


select *
from tbl_member;


select *
from tbl_member;
        
        
---------------------------------------------------------------------prodcut----------------------------------------------------------------


create sequence product_seq
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
/* ��ǰ */
CREATE TABLE tbl_product (
	product_seq number NOT NULL, /* ��ǰ������ȣ */
	fk_maufacturer_seq Number, /* ������ü������ȣ */
	product_name VARCHAR2(200) NOT NULL, /* ��ǰ�̸� */
	description VARCHAR2(1000) NOT NULL, /* ��ǰ���� */
	price FLOAT NOT NULL, /* ���� */
	stock NUMBER NOT NULL, /* ���? */
	main_pic VARCHAR2(400), /* ���λ��� */
	discription_pic VARCHAR2(400) /* �� ���� */
);

ALTER TABLE tbl_product RENAME COLUMN fk_maufacturer_seq TO fk_manufacturer_seq;




commit;


ALTER TABLE tbl_product
	ADD
		CONSTRAINT PK_tbl_product
		PRIMARY KEY (
			product_seq
		);
        
SELECT sequence_name, min_value, max_value, increment_by, cycle_flag, order_flag, cache_size, last_number
FROM user_sequences;


ALTER TABLE tbl_product_list ADD  quantity NUMBER NOT NULL;

ALTER TABLE tbl_product
ADD CONSTRAINT fk_discount_event
FOREIGN KEY (fk_discount_event_seq)
REFERENCES tbl_discount_event (discount_event_seq);


ALTER TABLE tbl_product ADD  discount_type VARCHAR2(200);
ALTER TABLE tbl_product ADD  discount_number FLOAT;
ALTER TABLE tbl_product RENAME COLUMN price TO base_price;


select *
from tbl_member;

SELECT *
FROM tab;

select *
from TBL_LOGIN_HISTORY;

commit;

ALTER TABLE tbl_product
	ADD
		CONSTRAINT FK_tbl_manu_TO_tbl_prodct
		FOREIGN KEY (
			fk_manufacturer_seq
		)
		REFERENCES tbl_manufacturer (
			manufacturer_seq
		);

        
ALTER TABLE tbl_product;
ALTER TABLE tbl_product DROP COLUMN FK_DISCOUNT_SEQ;


ALTER TABLE tbl_product
ADD CONSTRAINT fk_product_discount
FOREIGN KEY (fk_discount_seq)
REFERENCES tbl_discount(discount_seq);


drop table tbl_discount_event;
CREATE TABLE tbl_discount_event (
	discount_event_seq NUMBER NOT NULL, /* ���ΰ�����ȣ */
	discount_name VARCHAR2(200) NOT NULL, /* ����Ÿ�� */
    pic VARCHAR2(200) 
);

ALTER TABLE tbl_discount_event
	ADD
		CONSTRAINT PK_tbl_discount_event
		PRIMARY KEY (
			discount_event_seq
		);

create sequence discount_event_seq
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;       
        
        
        
create sequence manufacturer_seq
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache; 
        
drop table tbl_manufacturer;
        
        
CREATE TABLE tbl_manufacturer (
	manufacturer_seq number NOT NULL, /* ������ü������ȣ */
	manufacturer_name VARCHAR(200) not null, /* ������ü�̸� */
	manufacturer_tel VARCHAR(200), /* ������ü��ȣ */
	location VARCHAR(300) /* ������ü��ġ */
);

ALTER TABLE tbl_manufacturer
	ADD
		CONSTRAINT PK_tbl_manufacturer
		PRIMARY KEY (
			manufacturer_seq
		);
        



DROP SEQUENCE LOGIN_MEMBER_SEQ;

create sequence login_history_seq
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;


SELECT *, TRUNC(MONTHS_BETWEEN(SYSDATE, LAST_PASSWORD_CHANGE)) AS PWDCHANGGAP
FROM tbl_member
WHERE exist_status = 1;


select *
from tbl_product
where product_type = 3;

DROP TABLE TBL_DISCOUNT_PRODUCT;


select * 
from tbl_product
where product_type = 1;

desc tbl_member;
desc tbl_order
desc tbl_product;
desc tbl_order;
desc tbl_discount_event;
desc tbl_product_list;
desc tbl_review;


select rownum as rno
from
(
select * 
from tbl_product
where product_type = 1
);

UPDATE tbl_product
SET discount_type = 'percent',
    discount_number = 15.0
WHERE product_seq = 1;

select *
from tbl_product
where product_seq = 1;

UPDATE tbl_product
SET discount_type = 'amount',
    discount_number = 1000.0
WHERE product_seq = 2;

commit;

select 
*
from tbl_order;

select *
from tbl_product_list;

select count(*)
from tbl_product
where product_type=1;

SELECT *
  FROM user_sequences;
  
  
select * from tbl_discount_event;

SELECT constraint_name
FROM user_constraints
WHERE table_name = 'TBL_PRODUCT'
AND constraint_type = 'R';

ALTER TABLE tbl_product
DROP CONSTRAINT FK_DISCOUNT_EVENT;


ALTER TABLE tbl_product
ADD CONSTRAINT fk_discount_event
FOREIGN KEY (fk_discount_event_seq)
REFERENCES tbl_discount_event (discount_event_seq)
ON DELETE SET NULL;
commit;

SELECT 
    o.order_seq,
    o.fk_member_seq,
    o.postcode,
    o.address,
    o.address_detail,
    o.address_extra,
    o.total_pay,
    p.fk_product_seq,
    p.product_name,
    p.quantity
FROM 
    tbl_order o
JOIN 
    tbl_product_list p
ON 
    o.order_seq = p.FK_ORDER_SEQ;

select *
from tbl_order;
ALTER TABLE tbl_order
DROP COLUMN delivery_status;

ALTER TABLE tbl_order
ADD delivery_status NUMBER DEFAULT 0;
ALTER TABLE tbl_order
ADD delivery_message VARCHAR2(255);



UPDATE tbl_order
SET DELIVERY_STATUS = 3
WHERE order_Seq = 12;
commit;

rollback;


select * from tbl_product;

select *
from tbl_review;

desc tbl_product_List;

select L.*, P.*
from
(
    select *
    from tbl_order
    where fk_member_seq=6
) O
join
(
    select *
    from tbl_product_list
) L
on O.order_seq = L.fk_order_seq
join
(
select product_seq, product_type
from tbl_product 
)P
on P.product_seq = L.fk_product_seq;



SELECT P.product_name, COUNT(*) AS product_count
FROM
(
    SELECT *
    FROM tbl_order
    WHERE fk_member_seq = 6
) O
JOIN
(
    SELECT *
    FROM tbl_product_list
) L
ON O.order_seq = L.fk_order_seq
JOIN
(
    SELECT product_seq, product_name, product_type
    FROM tbl_product 
) P
ON P.product_seq = L.fk_product_seq
GROUP BY P.product_type;



SELECT 
    P.product_type, 
    COUNT(*) AS product_count,
    round((COUNT(*) * 100.0 / total_count), 1) AS percentage
FROM
(
    SELECT *
    FROM tbl_order
    WHERE fk_member_seq = 6
) O
JOIN
(
    SELECT *
    FROM tbl_product_list
) L
ON O.order_seq = L.fk_order_seq
JOIN
(
    SELECT product_seq, product_type
    FROM tbl_product 
) P
ON P.product_seq = L.fk_product_seq
CROSS JOIN
(
    SELECT COUNT(*) AS total_count
    FROM tbl_order
    JOIN tbl_product_list ON tbl_order.order_seq = tbl_product_list.fk_order_seq
    JOIN tbl_product ON tbl_product_list.fk_product_seq = tbl_product.product_seq
    WHERE tbl_order.fk_member_seq = 6
) T
GROUP BY P.product_type, T.total_count;



WITH total_orders AS (
    SELECT COUNT(*) AS total_count
    FROM tbl_order
    JOIN tbl_product_list ON tbl_order.order_seq = tbl_product_list.fk_order_seq
    JOIN tbl_product ON tbl_product_list.fk_product_seq = tbl_product.product_seq
    WHERE tbl_order.fk_member_seq = 6
)
SELECT TO_CHAR(tbl_order.REGISTERDAY, 'Month') AS month, 
       tbl_product.product_type, 
       COUNT(*) AS purchase_count,
       ROUND((COUNT(*) * 100.0 / total_orders.total_count), 1) AS percentage
FROM tbl_order
JOIN tbl_product_list ON tbl_order.order_seq = tbl_product_list.fk_order_seq
JOIN tbl_product ON tbl_product_list.fk_product_seq = tbl_product.product_seq
CROSS JOIN total_orders
WHERE tbl_order.fk_member_seq = 6
GROUP BY TO_CHAR(tbl_order.REGISTERDAY, 'Month'), tbl_product.product_type, total_orders.total_count
ORDER BY TO_DATE(TO_CHAR(tbl_order.REGISTERDAY, 'Month'), 'Month');


SELECT
    product_type,
    COUNT(CASE WHEN TO_CHAR(REGISTERDAY, 'MM') = '01' THEN 1 ELSE NULL END) AS m_01,
    COUNT(CASE WHEN TO_CHAR(REGISTERDAY, 'MM') = '02' THEN 1 ELSE NULL END) AS m_02,
    COUNT(CASE WHEN TO_CHAR(REGISTERDAY, 'MM') = '03' THEN 1 ELSE NULL END) AS m_03,
    COUNT(CASE WHEN TO_CHAR(REGISTERDAY, 'MM') = '04' THEN 1 ELSE NULL END) AS m_04,
    COUNT(CASE WHEN TO_CHAR(REGISTERDAY, 'MM') = '05' THEN 1 ELSE NULL END) AS m_05,
    COUNT(CASE WHEN TO_CHAR(REGISTERDAY, 'MM') = '06' THEN 1 ELSE NULL END) AS m_06,
    COUNT(CASE WHEN TO_CHAR(REGISTERDAY, 'MM') = '07' THEN 1 ELSE NULL END) AS m_07,
    COUNT(CASE WHEN TO_CHAR(REGISTERDAY, 'MM') = '08' THEN 1 ELSE NULL END) AS m_08,
    COUNT(CASE WHEN TO_CHAR(REGISTERDAY, 'MM') = '09' THEN 1 ELSE NULL END) AS m_09,
    COUNT(CASE WHEN TO_CHAR(REGISTERDAY, 'MM') = '10' THEN 1 ELSE NULL END) AS m_10,
    COUNT(CASE WHEN TO_CHAR(REGISTERDAY, 'MM') = '11' THEN 1 ELSE NULL END) AS m_11,
    COUNT(CASE WHEN TO_CHAR(REGISTERDAY, 'MM') = '12' THEN 1 ELSE NULL END) AS m_12,
    COUNT(*) AS purchase_count,
    ROUND((COUNT(*) * 100.0 / total_order_count), 2) AS order_pct
FROM (
    SELECT
        p.product_type AS product_type,
        o.REGISTERDAY,
        (SELECT COUNT(*)
         FROM tbl_order
         JOIN tbl_product_list ON tbl_order.order_seq = tbl_product_list.fk_order_seq
         JOIN tbl_product ON tbl_product_list.fk_product_seq = tbl_product.product_seq
         WHERE fk_member_seq = 6) AS total_order_count
    FROM tbl_order o
    JOIN tbl_product_list pl ON o.order_seq = pl.fk_order_seq
    JOIN tbl_product p ON pl.fk_product_seq = p.product_seq
    WHERE o.fk_member_seq = 6
)
GROUP BY product_type, total_order_count
ORDER BY product_type;



select DELIVERY_STATUS, count(*) 
from tbl_order
 where fk_member_seq = 6
 group by DELIVERY_STATUS;
 
 desc tbl_discount_event;

ALTER TABLE tbl_PRODUCT
ADD exist_status NUMBER(1) DEFAULT 1;

commit;

select *
from tbl_order;

desc tbl_product_list;


SELECT 
    pl.*, p.* 
FROM 
    tbl_product_list pl 
JOIN 
    tbl_product p 
ON 
    pl.fk_product_seq = p.product_seq  
WHERE 
    fk_order_seq = 23;
    
select * from tbl_member where member_seq = 6;
    
SELECT DELIVERY_STATUS, COUNT(*) AS count
FROM tbl_order
WHERE fk_member_seq = 6
GROUP BY DELIVERY_STATUS;

desc tbl_product;

select *
from tbl_member;

SELECT *
FROM (
    SELECT *
    FROM tbl_product
    ORDER BY PRODUCT_SEQ DESC
)
WHERE ROWNUM <= 3 and exist_status = 1;

