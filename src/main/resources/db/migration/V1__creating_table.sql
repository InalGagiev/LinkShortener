create table link(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    full_link varchar(255) not null,
    shorted_link varchar(255) not null
);