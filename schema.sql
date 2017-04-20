
    alter table notification 
        drop constraint FK237A88EB1A4E20C8
;

    alter table record 
        drop constraint notification_fk1_record
;

    alter table record 
        drop constraint FKC8466C51759B96B4
;

    alter table tag 
        drop constraint template_fk1_tag
;

    alter table template 
        drop constraint FKB13ACC7A80BD5D01
;

    drop table configuration
;

    drop table notification
;

    drop table record
;

    drop table tag
;

    drop table template
;

    drop table template_def
;

    drop table user_app
;

    drop sequence s_configuration
;

    drop sequence s_notification
;

    drop sequence s_record
;

    drop sequence s_tag
;

    drop sequence s_template
;

    drop sequence s_template_def
;

    create table configuration (
        id_configuration int8 not null,
        current bool,
        mail_sender varchar(100),
        server_name varchar(100),
        server_port varchar(5),
        title_sender varchar(100),
        password varchar(200),
        config_name varchar(200),
        primary key (id_configuration)
    )
;

    create table notification (
        id_notification int8 not null,
        type varchar(255) not null,
        created timestamp not null,
        notification_number int4 not null,
        sent_number int4 not null,
        id_template int8,
        primary key (id_notification)
    )
;

    create table record (
        id_record int8 not null,
        id_notification int8,
        pk varchar(255),
        id_tag int8,
        value varchar(255),
        primary key (id_record)
    )
;

    create table tag (
        id_tag int8 not null,
        custom_code varchar(20),
        name varchar(100) not null,
        tag_order int4,
        tag varchar(100),
        id_template_def int8,
        pk bool,
        primary key (id_tag)
    )
;

    create table template (
        id_template int8 not null,
        id_template_def int8,
        description varchar(500000) not null,
        name varchar(500) not null,
        subject varchar(255) not null,
        body varchar(500000) not null,
        primary key (id_template)
    )
;

    create table template_def (
        id_template_def int8 not null,
        custom_code varchar(20),
        description varchar(500000) not null,
        name varchar(255) not null,
        tags_with_pk bool,
        primary key (id_template_def)
    )
;

    create table user_app (
        login varchar(80) not null,
        password varchar(120) not null,
        logged bool,
        primary key (login)
    )
;

    alter table notification 
        add constraint FK237A88EB1A4E20C8 
        foreign key (id_template) 
        references template
;

    alter table record 
        add constraint notification_fk1_record 
        foreign key (id_notification) 
        references notification
;

    alter table record 
        add constraint FKC8466C51759B96B4 
        foreign key (id_tag) 
        references tag
;

    alter table tag 
        add constraint template_fk1_tag 
        foreign key (id_template_def) 
        references template_def
;

    alter table template 
        add constraint FKB13ACC7A80BD5D01 
        foreign key (id_template_def) 
        references template_def
;

    create sequence s_configuration
;

    create sequence s_notification
;

    create sequence s_record
;

    create sequence s_tag
;

    create sequence s_template
;

    create sequence s_template_def
;
