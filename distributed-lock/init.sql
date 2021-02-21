create table if not exists tbl_order
(
	order_id int auto_increment
		primary key,
	order_status int null,
	order_description varchar(64) null,
	driver_id int null
);

create table if not exists tbl_order_lock
(
	order_id int not null,
	driver_id int not null
);

