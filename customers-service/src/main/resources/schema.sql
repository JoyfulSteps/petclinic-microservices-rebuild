create table types (
    id integer identity primary key,
    name varchar(80)
);
create index types_name on types (name);

create table owners (
    id integer identity primary key,
    first_name varchar(30),
    last_name varchar(30),
    address varchar(255),
    city varchar(80),
    telephone varchar(20)
);
create index owners_last_name on owners(last_name);

create table pets (
    id integer identity primary key,
    name varchar(30),
    birth_date DATE,
    type_id integer not null,
    owner_id integer not null
);

alter table pets add constraint fk_pets_owners foreign key (owner_id) references owners(id);
alter table pets add constraint fk_pets_types foreign key (type_id) references types (id);
create index pets_name on pets (name);