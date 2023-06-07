create table author
(
    id           bigint auto_increment
        primary key,
    address      varchar(255) null,
    bibliography varchar(255) null,
    full_name    varchar(255) null
);

create table artwork
(
    frame_element_height double                                     null,
    frame_element_width  double                                     null,
    graving_height       double                                     null,
    graving_width        double                                     null,
    income_price         decimal(38, 2)                             null,
    income_year          int                                        null,
    piece_deep           double                                     null,
    piece_height         double                                     null,
    piece_width          double                                     null,
    registered_date      date                                       null,
    value                decimal(38, 2)                             null,
    author_id            bigint                                     null,
    century_year         varchar(255)                               null,
    code                 varchar(255)                               not null
        primary key,
    conservation_state   enum ('BUENO', 'MALO', 'REGULAR')          null,
    country              varchar(255)                               null,
    delivery_type        enum ('ATRIBUIDO', 'DOCUMENTO', 'FIRMADO') null,
    imageurl             varchar(255)                               null,
    income_form          varchar(255)                               null,
    integrity_state      enum ('COMPLETO', 'INCOMPLETO', 'UNIDO')   null,
    location             varchar(255)                               null,
    name                 varchar(255)                               null,
    observation          varchar(255)                               null,
    other_code           varchar(255)                               null,
    recorded_by          varchar(255)                               null,
    reviewed_by          varchar(255)                               null,
    signature_location   varchar(255)                               null,
    support              varchar(255)                               null,
    technique            varchar(255)                               null,
    constraint FK1ir6ajy1eojrys8x4xpho0rjt
        foreign key (author_id) references author (id)
);

create table author_artworks
(
    author_id     bigint       not null,
    artworks_code varchar(255) not null,
    constraint UK_msn5tvykywjydq6nfkwotltqy
        unique (artworks_code),
    constraint FK4c20dgbj0hnx17kknm6roeci
        foreign key (author_id) references author (id),
    constraint FKc6sf3lnrc2qss0w3ofm4c0b4g
        foreign key (artworks_code) references artwork (code)
);

create table user
(
    id       bigint auto_increment
        primary key,
    password varchar(255) null,
    username varchar(255) null
);
