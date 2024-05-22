show user;

select id, name, email, tel, postcode, address, address_detail, address_extra, 
       jubun, point, to_char(registerday, 'yyyy-mm-dd') AS registerday 
from tbl_member 
where exist_status = 1 and id = 'kpy102';
                         
                         
                         select *
                       from  tbl_member
                       
                       commit;
                       
                       update tbl_member set jubun = '9105291'
                       where id = 'erqwr'
                       