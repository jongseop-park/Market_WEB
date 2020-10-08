create table category_tbl2 (
 catecode varchar2(30) primary key,
 catename varchar2(50),
 catecoderef varchar2(30),
 reg_dt date,
 mod_dt date,
 del_yn char(1),
  foreign key (catecoderef) references category_tbl2(catecode)
 );

create table men_product_tbl2 (
 seq number primary key,
 name varchar2(100),
 image varchar2(300),
 catecode varchar2(30),
 summary_desc varchar2(500),
 detail_desc varchar2(1000),
 brand varchar2(100),
 price number,
 dis_yn char(1),
 sel_yn char(1),
 sub_image1 varchar2(300),
 sub_image2 varchar2(300),
 sub_image3 varchar2(300),
 sub_image4 varchar2(300),
 reg_dt date,
 mod_dt date,
 del_yn char(1),
 foreign key (catecode) references category_tbl2(catecode));

 create table stock_tbl2 (
  seq number primary key,
  product_seq number references men_product_tbl2(seq),
  name varchar2(100),
  stock number);

  create table option_tbl2 (
   seq number primary key,
   product_seq number references men_product_tbl2(seq),
   name varchar2(100),
   value varchar2(100));


   create table member_tbl2(
 id varchar2(100) primary key,
 password varchar2(100),
 phone varchar2(100),
 email varchar2(100),
 reg_dt date,
 mod_dt date,
 del_yn char(1));

 create table review_tbl2 (
  seq number primary key,
  member_id varchar2(100),
  product_seq number,
  title varchar2(300),
  content varchar2(500),
  score number,
  reg_dt date,
  mod_dt date,
  del_yn char(1),
  foreign key (member_id) references member_tbl2(id),
  foreign key (product_seq) references men_product_tbl2(seq)
  );