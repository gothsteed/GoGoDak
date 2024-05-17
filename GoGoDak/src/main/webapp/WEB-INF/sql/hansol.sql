select *
from tbl_member;

insert into tbl_member(member_seq, email, id, password, name, tel, jubun ,point, exist_status, active_status, last_password_change, postcode,address,address_detail,address_extra,registerday)
values(member_seq.nextval, 'yssj4@naver.com','hansol',1234,'서한솔' ,'010-6625-1815', 9701011,10,1,1,'2024-05-15',04001,'서울시','마포구','서교동','2024-02-01' );

insert into tbl_member(member_seq, email, id, password, name, tel, jubun ,point, exist_status, active_status, last_password_change, postcode,address,address_detail,address_extra,registerday)
values(member_seq.nextval, 'admin@naver.com','admin',1234,'관리자' ,'010-2020-1010',9701012 ,0,1,1,'2024-05-15',04001,'서울시','마포구','서교동','2024-01-01' );

commit;
select *
from tbl_product;

insert into tbl_product(product_seq,product_name,description,price,stock,main_pic,discription_pic,product_type) 
values(product_seq.nextval, '닭가슴살1','고온의 스팀공정으로 육즙을 꽉 잡아 촉촉하게',2900,389,'닭가슴살1.jpg','disc_닭가슴살1.jpg',1);

insert into tbl_product(product_seq,product_name,description,price,stock,main_pic,discription_pic,product_type) 
values(product_seq.nextval, '닭가슴살2','고온의 스팀공정으로 육즙을 꽉 잡아 촉촉하게',3500,129,'닭가슴살2.jpg','disc_닭가슴살2.jpg',1);

insert into tbl_product(product_seq,product_name,description,price,stock,main_pic,discription_pic,product_type) 
values(product_seq.nextval, '닭가슴살3','고온의 스팀공정으로 육즙을 꽉 잡아 촉촉하게',15100,39,'닭가슴살3.jpg','disc_닭가슴살3.jpg',1);

insert into tbl_product(product_seq,product_name,description,price,stock,main_pic,discription_pic,product_type) 
values(product_seq.nextval, '닭가슴살4','고온의 스팀공정으로 육즙을 꽉 잡아 촉촉하게',3500,389,'닭가슴살4.jpg','disc_닭가슴살4.jpg',1);

insert into tbl_product(product_seq,product_name,description,price,stock,main_pic,discription_pic,product_type) 
values(product_seq.nextval, '닭가슴살5','고온의 스팀공정으로 육즙을 꽉 잡아 촉촉하게',2900,389,'닭가슴살5.jpg','disc_닭가슴살5.jpg',1);

insert into tbl_product(product_seq,product_name,description,price,stock,main_pic,discription_pic,product_type) 
values(product_seq.nextval, '닭가슴살6','직화로 구워 더 리얼한 맛',2900,389,'닭가슴살6.jpg','disc_닭가슴살6.jpg',1);

insert into tbl_product(product_seq,product_name,description,price,stock,main_pic,discription_pic,product_type) 
values(product_seq.nextval, '닭가슴살7','직화로 구워 더 리얼한 맛',3500,129,'닭가슴살7.jpg','disc_닭가슴살7.jpg',1);

insert into tbl_product(product_seq,product_name,description,price,stock,main_pic,discription_pic,product_type) 
values(product_seq.nextval, '닭가슴살8','직화로 구워 더 리얼한 맛',15100,329,'닭가슴살8.jpg','disc_닭가슴살8.jpg',1);

insert into tbl_product(product_seq,product_name,description,price,stock,main_pic,discription_pic,product_type) 
values(product_seq.nextval, '닭가슴살9','직화로 구워 더 리얼한 맛',1800,9,'닭가슴살9.jpg','disc_닭가슴살9.jpg',1);

insert into tbl_product(product_seq,product_name,description,price,stock,main_pic,discription_pic,product_type) 
values(product_seq.nextval, '닭가슴살10','직화로 구워 더 리얼한 맛',3500,11,'닭가슴살10.jpg','disc_닭가슴살10.jpg',1);

insert into tbl_product(product_seq,product_name,description,price,stock,main_pic,discription_pic,product_type) 
values(product_seq.nextval, '닭가슴살11','먹기 편한 트레이 포장',2900,39,'닭가슴살11.jpg','disc_닭가슴살11.jpg',1);

insert into tbl_product(product_seq,product_name,description,price,stock,main_pic,discription_pic,product_type) 
values(product_seq.nextval, '닭가슴살12','먹기 편한 트레이 포장',3500,19,'닭가슴살12.jpg','disc_닭가슴살12.jpg',1);

insert into tbl_product(product_seq,product_name,description,price,stock,main_pic,discription_pic,product_type) 
values(product_seq.nextval, '닭가슴살13','먹기 편한 트레이 포장',9900,35,'닭가슴살13.jpg','disc_닭가슴살13.jpg',1);

insert into tbl_product(product_seq,product_name,description,price,stock,main_pic,discription_pic,product_type) 
values(product_seq.nextval, '닭가슴살14','먹기 편한 트레이 포장',15420,501,'닭가슴살14.jpg','disc_닭가슴살14.jpg',1);

insert into tbl_product(product_seq,product_name,description,price,stock,main_pic,discription_pic,product_type) 
values(product_seq.nextval, '닭가슴살15','먹기 편한 트레이 포장',2900,389,'닭가슴살15.jpg','disc_닭가슴살15.jpg',1);

insert into tbl_product(product_seq,product_name,description,price,stock,main_pic,discription_pic,product_type) 
values(product_seq.nextval, '닭가슴살16','풍미를 살리는 12시간 저온숙성',2900,389,'닭가슴살16.jpg','disc_닭가슴살16.jpg',1);

insert into tbl_product(product_seq,product_name,description,price,stock,main_pic,discription_pic,product_type) 
values(product_seq.nextval, '닭가슴살17','풍미를 살리는 12시간 저온숙성',2422,129,'닭가슴살17.jpg','disc_닭가슴살17.jpg',1);

insert into tbl_product(product_seq,product_name,description,price,stock,main_pic,discription_pic,product_type) 
values(product_seq.nextval, '닭가슴살18','풍미를 살리는 12시간 저온숙성',15100,329,'닭가슴살18.jpg','disc_닭가슴살18.jpg',1);

insert into tbl_product(product_seq,product_name,description,price,stock,main_pic,discription_pic,product_type) 
values(product_seq.nextval, '닭가슴살19','풍미를 살리는 12시간 저온숙성',1800,9,'닭가슴살19.jpg','disc_닭가슴살19.jpg',1);

insert into tbl_product(product_seq,product_name,description,price,stock,main_pic,discription_pic,product_type) 
values(product_seq.nextval, '닭가슴살20','풍미를 살리는 12시간 저온숙성',3500,151,'닭가슴살20.jpg','disc_닭가슴살20.jpg',1);

commit;