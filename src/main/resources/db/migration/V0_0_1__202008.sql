create table role(
  id   varchar(30) primary key,
  name varchar(100) not null unique
);

create table admin(
id int (20)primary key,
nama varchar(30)not null unique,
email varchar(30)not null unique,
handphone varchar(12)not null unique,
active boolean not null,
username varchar(30)not null UNIQUE,
password varchar(255)not null,
createdate timestamp,
id_role varchar (30) not null,
foreign key (id_role) references role (id)
);

create table pelanggan(
id int (20)primary key,
fullname varchar(50)unique,
email varchar(50)not null unique,
handphone varchar(13)not null unique,
username varchar(30)not null unique,
active boolean not null,
createdate timestamp
);

create table password(
id int (20)primary key,
password varchar(255)not null,
foreign key (id) references pelanggan(id)
);

create table permissions(
  id    varchar(30) primary key,
  label varchar(100) not null,
  value varchar(100) not null unique

);

create table roles_permissions(
id_role varchar (30),
id_permission varchar (30),
primary key (id_role, id_permission),
foreign key (id_role) references role(id),
foreign key (id_permission) references permissions (id)
);