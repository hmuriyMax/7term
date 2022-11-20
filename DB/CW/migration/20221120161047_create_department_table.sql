-- +goose Up
-- +goose StatementBegin

create table department
(
    department_id integer     not null,
    branch_id     integer,
    name          varchar(64) not null,
    constraint department_pk
        primary key (department_id),
    constraint department_branch_null_fk
        foreign key (branch_id) references branch
            on update cascade on delete cascade
);

comment on table department is 'Таблица ';

comment on column department.department_id is 'Идентификатор';

comment on column department.branch_id is 'Идентификатор филиала';

comment on column department.name is 'Название отдела';

alter table department
    owner to postgres;

alter table department
    add constraint greater_than_zero
        check ((department_id > 0) AND (branch_id > 0));

comment on constraint greater_than_zero on department is 'Проверка на неотрицательность';

alter table department
    add constraint check_name
        check ((name)::text ~~* '_%'::text);

-- +goose StatementEnd

-- +goose Down
-- +goose StatementBegin
drop table if exists department
-- +goose StatementEnd
