CREATE TABLE APP_USER (
    ID VARCHAR(100) NOT NULL,
    USERNAME VARCHAR(100) NOT NULL,
    EMAIL VARCHAR(100) NOT NULL,
    STATUS VARCHAR(20) NOT NULL
);

ALTER TABLE APP_USER ADD CONSTRAINT APP_USER_PK PRIMARY KEY ( ID );

CREATE TABLE APP_USER_PASSWORD (
    ID CHAR(100) NOT NULL,
    APP_USER_ID CHAR(100) NOT NULL,
    HASHED_PASSWORD CHAR(200) NOT NULL,
    STATUS CHAR(1) NOT NULL,
    FAILED_LOGIN_TIMES INTEGER NOT NULL,
    LAST_LOGIN_DATE_TIME TIMESTAMP,
    CREATED_DATE_TIME TIMESTAMP NOT NULL
);

ALTER TABLE APP_USER_PASSWORD ADD CONSTRAINT APP_USER_PASSWORD_PK PRIMARY KEY ( ID );
ALTER TABLE APP_USER_PASSWORD ADD CONSTRAINT APP_USER_PASSWORD_REFERENCE_APP_USER FOREIGN KEY (USER_ID) REFERENCES APP_USER(ID);

CREATE TABLE BLOG_POST (
    ID CHAR(100) NOT NULL,
    TITLE CHAR(100) NOT NULL,
    AUTHOR_ID CHAR(100) NOT NULL,
    CONTENT TEXT,
    CREATED_DATE_TIME TIMESTAMP NOT NULL
);

ALTER TABLE BLOG_POST ADD CONSTRAINT BLOG_POST_REFERENCE_APP_USER FOREIGN KEY (AUTHOR_ID) REFERENCES APP_USER(ID);
