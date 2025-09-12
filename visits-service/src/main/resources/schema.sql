Create table visits (
    id integer identity primary key,
    pet_id integer not null,
    visit_date timestamp,
    description varchar(255)
);

create index visits_pet_id on visits (pet_id);