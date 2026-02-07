CREATE SCHEMA IF NOT EXISTS SECURECAPITA;

SET NAMES 'UTF8MB4';
SET TIME_ZONE = '+05:30';

USE SECURECAPITA;


DROP TABLE if exists TwoFactorVerification;
DROP TABLE IF EXISTS RestPasswordVerifications;
DROP TABLE IF EXISTS AccountVerification;
DROP TABLE IF EXISTS UserEvents;
DROP TABLE IF EXISTS UserRoles;
DROP TABLE if exists Events;
DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS Roles;

CREATE TABLE USERS
(

    id         BIGINT unsigned NOT NULL auto_increment PRIMARY KEY,
    first_name varchar(50)     not null,
    last_name  varchar(50)     not null,
    email      varchar(100)    not null,
    password   varchar(255) default null,
    address    varchar(255) default null,
    phone      varchar(20)  default null,
    title      varchar(50)  default null,
    bio        varchar(255) default null,
    enabled    boolean      default false,
    non_locked boolean      default true,
    using_mfa  boolean      default false,
    created_at datetime     default current_timestamp,
    image_url  varchar(255) default 'C:\Users\Soft\Desktop\securecapia\ChatGPT Image Jan 18, 2026, 09_12_36 AM.png',
    constraint UQ_Users_Email unique (email)


);



CREATE TABLE Roles
(

    id         BIGINT unsigned not null auto_increment primary key,
    name       varchar(100)    not null,
    permission varchar(255)    not null,
    constraint UQ_Roles_Name unique (name)
);



CREATE TABLE UserRoles
(

    id      BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT UNSIGNED NOT NULL,
    role_id BIGINT unsigned NOT NULL,
    foreign key (user_id) references USERS (id) on delete cascade on update cascade,
    foreign key (role_id) references Roles (id) on delete restrict on update cascade,
    CONSTRAINT UQ_UserRoles_user_id unique (user_id)

);



CREATE TABLE Events
(

    id          BIGINT unsigned not null auto_increment primary key,
    type        varchar(50)     not null CHECK ( type IN
                                                 ('LOGIN_ATTEMPT', 'LOGIN_ATTEMPT_FAILURE', 'LOGIN_ATTEMPT_SUCCESS',
                                                  'PROFILE_UPDATE', 'PROFILE_PICTURE_UPDATE', 'ROLE_UPDATE',
                                                  'ACCOUNT_SETTING_UPDATE', 'PASSWORD_UPDATE', 'MFA_UPDATE') ),
    description varchar(250)    not null,
    CONSTRAINT UQ_Events_Type unique (type)
);


CREATE TABLE UserEvents
(

    id         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id    BIGINT UNSIGNED NOT NULL,
    event_id   BIGINT UNSIGNED NOT NULL,
    device     varchar(100) DEFAULT NULL,
    ip_address varchar(100) default null,
    created_at DATETIME     DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) references USERS (id) ON DELETE CASCADE ON UPDATE CASCADE,
    foreign key (event_id) references Events (id) ON DELETE RESTRICT ON UPDATE CASCADE


);



CREATE TABLE AccountVerification
(
    id      BIGINT unsigned not null auto_increment primary key,
    user_id BIGINT UNSIGNED not null,
    url     varchar(255)    not null,
    date    DATETIME        not null,
    foreign key (user_id) references USERS (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT UQ_AccountVerification_user_id unique (user_id),
    CONSTRAINT UQ_AccountVerification_url unique (url)
);



CREATE TABLE RestPasswordVerifications
(
    id              BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id         BIGINT unsigned not null,
    url             varchar(255)    not null,
    expiration_date DATETIME        NOT NULL,
    FOREIGN KEY (user_id) references USERS (id) on delete cascade on update cascade,
    CONSTRAINT UQ_RestPasswordVerifications_userid unique (user_id),
    CONSTRAINT UQ_RestPasswordVerification_url unique (url)

);



CREATE TABLE TwoFactorVerification
(

    id              BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id         BIGINT UNSIGNED NOT NULL,
    code            varchar(50)     not null,
    expiration_date datetime        NOT NULL,

    FOREIGN KEY (user_id) REFERENCES USERS (ID) ON DELETE CASCADE ON UPDATE CASCADE,

    CONSTRAINT UQ_TwoFactorVerification_userid unique (user_id),
    CONSTRAINT UQ_TwoFactorVerification_Code unique (code)
);