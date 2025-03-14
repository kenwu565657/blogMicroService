CREATE TABLE if not exists BLOG_POST (
                           ID CHAR(100) NOT NULL,
                           TITLE CHAR(100) NOT NULL,
                           AUTHOR_ID CHAR(100) NOT NULL,
                           SUMMARY TEXT,
                           CONTENT_TYPE VARCHAR(100) NOT NULL,
                           CONTENT_FILE_NAME VARCHAR(200) NOT NULL,
                           CREATED_DATE_TIME TIMESTAMP NOT NULL,
                           LAST_UPDATED_DATE_TIME TIMESTAMP
);

CREATE TABLE if not exists BLOG_POST_TAG (
                        ID VARCHAR(100) NOT NULL,
                        TAG_NAME VARCHAR(100) NOT NULL
);