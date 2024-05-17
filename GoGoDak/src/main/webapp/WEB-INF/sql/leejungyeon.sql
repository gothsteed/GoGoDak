
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
	password VARCHAR2(200) NOT NULL, /* ��й�ȣ */
	name VARCHAR2(30) NOT NULL, /* �̸� */
	tel varchar2(200)  NOT NULL, /* �ڵ�����ȣ */
	jubun VARCHAR2(7) NOT NULL, /* �ֹε�Ϲ�ȣ */
	point Number default 0 NOT NULL, /* ����Ʈ */
	register_date DATE NOT NULL, /* ���Գ�¥ */
	exist_status NUMBER(1) DEFAULT 1 NOT NULL, /* ȸ��Ż������ */
	active_status NUMBER(1) DEFAULT 1 NOT NULL, /* �޸����� */
	last_password_change DATE DEFAULT sysdate NOT NULL /* ������ ��й�ȣ ������ */
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
values(member_seq.nextval, '7DiCwyc+1dXTTwg5kjvDmHehvJlz/ESJNhef/5DX+YA=','jylee', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4','������' ,'k6AvvKD9cZaeKhlunBk9ew==', 9701011,10,1,1,'2024-05-15',04001,'�����','������','������','2024-02-01' );
commit;        


delete tbl_member where id='jylee';
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
	stock NUMBER NOT NULL, /* ��� */
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
        

SELECT *
FROM tab;

select *
from TBL_LOGIN_HISTORY;


ALTER TABLE tbl_product
	ADD
		CONSTRAINT FK_tbl_manu_TO_tbl_prodct
		FOREIGN KEY (
			fk_manufacturer_seq
		)
		REFERENCES tbl_manufacturer (
			manufacturer_seq
		);
        
        
        
        
        
        
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




desc tbl_member;
desc tbl_order
