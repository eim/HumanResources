CREATE SCHEMA IF NOT EXISTS DUDES;

SET SCHEMA DUDES;

CREATE TABLE IF NOT EXISTS CANDIDATE(ID BIGINT AUTO_INCREMENT, NAME VARCHAR(255), DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP, DELETED BOOLEAN DEFAULT FALSE);
