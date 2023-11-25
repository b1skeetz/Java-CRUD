drop table Users;

create table Users(
    id serial8 primary key,
    name varchar(20) not null,
    age int2 not null
);

drop table Vehicle;
create table Vehicle(
    id serial8 primary key,
    user_id int8 not null,
    model varchar(20) not null,
    color varchar(20) not null,
    foreign key (user_id) references Users (id)
);