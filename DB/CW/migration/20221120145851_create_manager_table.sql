-- +goose Up
-- +goose StatementBegin
create table manager
(
    manager_id integer      not null,
    name       varchar(128) not null,
    constraint check_name
        check (name ilike '_% _% _%'),
    constraint id
        primary key (manager_id)
);

alter table manager
    owner to postgres;

alter table manager
    add constraint greater_than_zero
        check (manager_id > 0);


-- +goose StatementEnd

-- +goose Down
-- +goose StatementBegin
drop table if exists manager
-- +goose StatementEnd
