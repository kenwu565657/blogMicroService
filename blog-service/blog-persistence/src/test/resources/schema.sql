CREATE TABLE IF NOT EXISTS BLOG_POST (
    ID CHAR(100) NOT NULL,
    TITLE CHAR(100) NOT NULL,
    AUTHOR_ID CHAR(100) NOT NULL,
    SUMMARY TEXT,
    CONTENT_TYPE VARCHAR(100) NOT NULL,
    CONTENT_FILE_NAME VARCHAR(200) NOT NULL,
    CREATED_DATE_TIME TIMESTAMP NOT NULL,
    LAST_UPDATED_DATE_TIME TIMESTAMP
    );

ALTER TABLE BLOG_POST DROP CONSTRAINT IF EXISTS PK_BLOG_POST;
ALTER TABLE BLOG_POST ADD CONSTRAINT PK_BLOG_POST PRIMARY KEY (ID);

CREATE TABLE IF NOT EXISTS BLOG_POST_TAG (
    ID VARCHAR(100) NOT NULL,
    TAG_NAME VARCHAR(100) NOT NULL
    );

ALTER TABLE BLOG_POST_TAG DROP CONSTRAINT IF EXISTS PK_BLOG_POST_TAG;
ALTER TABLE BLOG_POST ADD CONSTRAINT PK_BLOG_POST_TAG PRIMARY KEY (ID);

CREATE UNIQUE INDEX IF NOT EXISTS BLOG_POST_TAG_TAG_NAME ON BLOG_POST_TAG (TAG_NAME);