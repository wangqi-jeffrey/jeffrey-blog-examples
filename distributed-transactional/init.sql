create table if not exists tbl_inventory
(
	good_id int not null,
	num int null,
	update_time timestamp null
);

create table if not exists tbl_two
(
	id int not null,
	name varchar(32) null
);

create table if not exists tbl_one
(
	id int not null,
	name varchar(32) null
);

create table if not exists tbl_order_one
(
	order_id int not null,
	buyer varchar(32) null,
	update_time timestamp null
);





