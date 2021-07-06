-- 帐户信息
drop table if exists  account;

create table account
(
    card_id                       	bigint(20)          not null ,
	name                            varchar(100)        not null ,
    money                           int(6),
    primary key (card_id)
);
