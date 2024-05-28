show user;

select id, name, email, tel, postcode, address, address_detail, address_extra, 
       jubun, point, to_char(registerday, 'yyyy-mm-dd') AS registerday 
from tbl_member 
where exist_status = 1 and id = 'kpy102';
                         
                         
                         select *
                       from  tbl_member
                       
                       commit;
                       
                       update tbl_member set jubun = '9105291'
                       where id = 'erqwr';
                       
                       
                     SELECT rno, id, name, email, gender
FROM (
    SELECT rownum AS rno, id, name, email, gender
    FROM (
        SELECT id, name, email,
            CASE 
                WHEN SUBSTR(jubun, 7, 1) IN ('1', '3') THEN '��'
                ELSE '��'
            END AS gender
        FROM tbl_member
        WHERE id != 'admin'
    )
);

select *
from tbl_product
where tbl_member = 'kpy102';


select *
from tbl_product;

select *
from tbl_product_list;

select *
from tbl_order;

select *
from tbl_member;
 
 
 
 SELECT m.id, m.name, p.product_name
FROM tbl_member m
JOIN tbl_order o ON m.member_seq = o.fk_member_seq
JOIN tbl_review r ON o.order_seq = r.fk_order_seq
JOIN tbl_product p ON r.fk_product_seq = p.product_seq
WHERE m.id != 'kpy102';


SELECT m.id, m.name, p.product_name
FROM tbl_member m
JOIN tbl_review r ON m.member_seq = r.fk_member_seq
JOIN tbl_product p ON r.fk_product_seq = p.product_seq
WHERE m.id != 'kpy102';


select *
from tbl_order;
select *
from tbl_member;
select *
from tbl_product_list;


Select o.*
from tbl_member m left join tbl_order o on m.member_seq = o.fk_member_seq
where m.member_seq = 'jylee';


SELECT o.*
FROM tbl_member m
LEFT JOIN tbl_order o ON m.member_seq = o.fk_member_seq
WHERE m.member_seq = 'jylee';

Select o.*
from tbl_member m left join tbl_order o on m.member_seq = o.fk_member_seq
where m.id ='jylee';




Select o.*
from tbl_member m left join tbl_order o on m.member_seq = o.fk_member_seq
left join m.tbl_order = o.fk_order_seq
where m.id ='jylee';


SELECT order_seq, total_pay, postcode, address, address_detail, address_extra, delivery_status
FROM tbl_member m
LEFT JOIN tbl_order o ON m.member_seq = o.fk_member_seq
WHERE m.id = 'jylee';


SELECT 
    o.order_seq, 
    o.total_pay, 
    o.postcode, 
    o.address, 
    o.address_detail, 
    o.address_extra, 
    o.delivery_status
FROM 
    tbl_member m
LEFT JOIN 
    tbl_order o 
ON 
    m.member_seq = o.fk_member_seq
WHERE 
    m.id = 'jylee';



select ceil(count(*)/2)
from tbl_member
where id != 'admin';



select fk_member_seq, order_seq, address,address_detail,delivery_status
from tbl_order                
where id != 'jylee';

select member_seq, id, postcode,address
from tbl_member;


select id, name, email, tel, postcode, address, address_detail, address_extra
					   from tbl_member
					    where exist_status = 1 and id = 'jylee';
                        
                        
    SELECT 
     o.order_seq,                     
    m.id, 
    m.name, 
    m.email, 
    m.tel, 

 
   o.total_pay, 

    o.address AS order_address, 
    o.address_detail AS order_address_detail, 
     o.delivery_status
FROM 
    tbl_member m
LEFT JOIN 
    tbl_order o 
ON 
    m.member_seq = o.fk_member_seq
WHERE 
    
    m.id = 'jylee';



 SELECT rno, id, name, email, gender 
					   FROM
					   (
					   select rownum as rno, id, name, email, gender 
					     from 
					   
					        select id, name, email
					              
					    from tbl_member
					  where id != 'admin'
                      
                      
                      
 SELECT 
    m.id, 
    m.name, 
    m.email, 
    m.tel, 
    m.postcode, 
    m.address, 
    m.address_detail, 
    m.address_extra, 
    o.order_seq, 
    o.total_pay, 
    o.postcode AS order_postcode, 
    o.address AS order_address, 
    o.address_detail AS order_address_detail, 
    o.address_extra AS order_address_extra, 
    o.delivery_status
FROM 
    tbl_member m
LEFT JOIN 
    tbl_order o 
ON 
    m.member_seq = o.fk_member_seq
WHERE 
    m.exist_status = 1 
    AND m.id = '';


select product_seq,product_name,description,base_price,stock,product_type,fk_discount_event_seq, discount_type, discount_number
from tbl_product

select order_seq, total_pay,postcode,address, address_detail, address_extra,delivery_status,fk_member_seq
from tbl_order

select member_seq, email, id, name, tel, jubun, point, postcode,address, address_detail,address_extra
from tbl_member
select fk_order_seq,fk_product_seq,product_name
from tbl_product_list


SELECT 
	                   o.order_seq, o.fk_member_seq, o.postcode, o.address, o.address_detail, o.address_extra, o.total_pay, 
	                 m.id, m.name, m.tel, 
	                 p.product_seq, p.product_name, p.description, p.base_price, p.stock, p.main_pic, p.description_pic, 
	                 p.product_type, p.discount_type, dp.discount_amount, d.discount_name, o.deliverystatus 
	              FROM tbl_order o 
	               JOIN tbl_member m ON o.fk_member_seq = m.member_seq 
	                JOIN product_list pl ON o.order_seq = pl.order_seq 
	                 JOIN tbl_product p ON pl.product_seq = p.product_seq 
	                 LEFT JOIN tbl_discount_product dp ON p.product_seq = dp.product_seq
	                LEFT JOIN tbl_discount_event d ON p.fk_discount_event_seq = d.discount_event_seq 
	                 WHERE m.id = 'jylee'; 
                     
                     
                     
                     select *
                    from tbl_member;
                     
                     select *
                     from tbl_product;
                     
                     select *
                     from tbl_discount_product;
                     
                     
                     select *
                     from product_list;
                     
                     
                         SELECT 
        o.order_seq, 
        o.total_pay, 
        o.postcode AS order_postcode, 
        o.address AS order_address, 
        o.address_detail AS order_address_detail, 
        o.address_extra AS order_address_extra, 
        o.delivery_status, 
        m.member_seq, 
        m.email, 
        m.id, 
        m.name, 
        m.tel, 
        m.jubun, 
        m.point, 
        m.postcode AS member_postcode, 
        m.address AS member_address, 
        m.address_detail AS member_address_detail, 
        m.address_extra AS member_address_extra, 
        pl.fk_order_seq, 
        pl.fk_product_seq, 
        pl.product_name AS list_product_name, 
        p.product_seq, 
        p.product_name, 
        p.description, 
        p.base_price, 
        p.stock, 
        p.product_type, 
        p.fk_discount_event_seq, 
        p.discount_type, 
        p.discount_number
    FROM 
        tbl_order o
    JOIN 
        tbl_member m ON o.fk_member_seq = m.member_seq
    JOIN 
        tbl_product_list pl ON o.order_seq = pl.fk_order_seq
    JOIN 
        tbl_product p ON pl.fk_product_seq = p.product_seq
    WHERE 
        m.id = 'jylee'
        
        
        select *
        fro
        
        SELECT 
	                 o.order_seq, o.total_pay, o.postcode AS order_postcode, o.address AS order_address, o.address_detail AS order_address_detail, o.address_extra AS order_address_extra, o.delivery_status, 
	               m.member_seq, m.id, m.name, m.tel, m.point, m.postcode AS member_postcode, m.address AS member_address, m.address_detail AS member_address_detail, m.address_extra AS member_address_extra, 
	 pl.fk_order_seq, pl.fk_product_seq, pl.product_name AS list_product_name,
	               p.product_seq, p.product_name, p.description, p.base_price, p.stock, p.product_type, p.fk_discount_event_seq, p.discount_type, p.discount_number 
	               FROM tbl_order o 
	              JOIN tbl_member m ON o.fk_member_seq = m.member_seq 
	                JOIN tbl_product_list pl ON o.order_seq = pl.fk_order_seq 
	               JOIN tbl_product p ON pl.fk_product_seq = p.product_seq 
	               WHERE m.id =  'jylee'


select *
from tbl_member

desc tbl_product




 SELECT  pl.*, p.*
					FROM 
					   tbl_product_list pl 
				JOIN 
				  tbl_product p 
				ON 
					    pl.fk_product_seq = p.product_seq  
					WHERE tbl_member
                    
                    
                    
                    
                    
                    select fk_member_seq, total_pay, postcode, address, address_detail, address_extra, delivery_status, registerday
                    from tbl_order
                    where fk_member_seq = 6 ;

select *
from tbl_order;

select *
from tbl_product

select *
from tbl_member;


desc tbl_product_list;
