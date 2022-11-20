-- +goose Up
-- +goose StatementBegin

create table repair_request
(
    request_id     integer not null
        constraint repair_request_pk
            primary key,
    device_id      integer not null
        constraint repair_request_device_null_fk
            references device (device_id)
            on update cascade on delete cascade,
    repair_type_id integer not null
        constraint repair_request_repair_type_null_fk
            references repair_type (repair_type_id)
            on update cascade on delete set null,
    worker_id      integer not null
        constraint repair_request_performer_null_fk
            references performer (worker_id)
            on update cascade on delete set null,
    duration       interval,
    constraint greater_than_zero
        check (request_id > 0 and device_id > 0 and repair_type_id > 0 and worker_id > 0)
);

comment on table repair_request is 'Запрос на ремонт';

comment on column repair_request.request_id is 'Идентификатор';

comment on column repair_request.device_id is 'Идентификатор устройства';

comment on column repair_request.repair_type_id is 'Идентификатор типа';

comment on column repair_request.worker_id is 'Идентификатор работиника';

comment on column repair_request.duration is 'Время окончания работ';

-- +goose StatementEnd

-- +goose Down
-- +goose StatementBegin
drop table if exists repair_request;
-- +goose StatementEnd
