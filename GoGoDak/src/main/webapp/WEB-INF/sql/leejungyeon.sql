
create sequence member_seq
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;


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



ALTER TABLE tbl_product
	ADD
		CONSTRAINT PK_tbl_product
		PRIMARY KEY (
			product_seq
		);

----���� ������!!!!!!
ALTER TABLE MY_SCHEMA.tbl_product
	ADD
		CONSTRAINT FK_tbl_maufacturer_TO_tbl_product
		FOREIGN KEY (
			fk_maufacturer_seq
		)
		REFERENCES MY_SCHEMA.tbl_maufacturer (
			maufacturer_seq
		);