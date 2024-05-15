
create sequence member_seq
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;

desc tbl_member;

CREATE TABLE tbl_member (
	member_seq Number NOT NULL, /* 유저고유번호 */
	email VARCHAR2(200) NOT NULL, /* 이메일 */
	id VARCHAR2(40) NOT NULL, /* 아이디 */
	password VARCHAR2(200) NOT NULL, /* 비밀번호 */
	name VARCHAR2(30) NOT NULL, /* 이름 */
	tel varchar2(200)  NOT NULL, /* 핸드폰번호 */
	jubun VARCHAR2(7) NOT NULL, /* 주민등록번호 */
	point Number default 0 NOT NULL, /* 포인트 */
	register_date DATE NOT NULL, /* 가입날짜 */
	exist_status NUMBER(1) DEFAULT 1 NOT NULL, /* 회원탈퇴유무 */
	active_status NUMBER(1) DEFAULT 1 NOT NULL, /* 휴먼유무 */
	last_password_change DATE DEFAULT sysdate NOT NULL /* 마지막 비밀번호 변경일 */
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
        
        
        
        
        
        
        
---------------------------------------------------------------------prodcut----------------------------------------------------------------


create sequence product_seq
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
/* 제품 */
CREATE TABLE tbl_product (
	product_seq number NOT NULL, /* 제품고유번호 */
	fk_maufacturer_seq Number, /* 제조업체고유번호 */
	product_name VARCHAR2(200) NOT NULL, /* 제품이름 */
	description VARCHAR2(1000) NOT NULL, /* 제품설명 */
	price FLOAT NOT NULL, /* 가격 */
	stock NUMBER NOT NULL, /* 재고 */
	main_pic VARCHAR2(400), /* 메인사진 */
	discription_pic VARCHAR2(400) /* 상세 사진 */
);

ALTER TABLE tbl_product RENAME COLUMN fk_maufacturer_seq TO fk_manufacturer_seq;




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
	manufacturer_seq number NOT NULL, /* 제조업체고유번호 */
	manufacturer_name VARCHAR(200) not null, /* 제조업체이름 */
	manufacturer_tel VARCHAR(200), /* 제조업체번호 */
	location VARCHAR(300) /* 제조업체위치 */
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
