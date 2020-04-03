CREATE TABLE SCHEMA_INFO
	(VERSION VARCHAR(40));

CREATE SEQUENCE EVENT_SEQUENCE START WITH 1;

CREATE TABLE EVENT
	(ID INTEGER DEFAULT nextval('EVENT_SEQUENCE') NOT NULL PRIMARY KEY,
	DATE_CREATED TIMESTAMP WITH TIME ZONE DEFAULT NULL,
	NAME TEXT NOT NULL,
	EVENT_LEVEL VARCHAR(40) NOT NULL,
	OUTCOME VARCHAR(40) NOT NULL,
	ATTRIBUTES TEXT,
	USER_ID INTEGER NOT NULL,
	IP_ADDRESS VARCHAR(40),
	SERVER_ID CHARACTER VARYING(36));
	
CREATE TABLE CHANNEL
	(ID CHAR(36) NOT NULL PRIMARY KEY,
	NAME VARCHAR(40) NOT NULL,
	REVISION INTEGER,
	CHANNEL TEXT);

CREATE TABLE SCRIPT
	(GROUP_ID VARCHAR(40) NOT NULL,
	ID VARCHAR(40) NOT NULL,
	SCRIPT TEXT,
	PRIMARY KEY(GROUP_ID, ID));

CREATE SEQUENCE PERSON_SEQUENCE START WITH 1;

CREATE TABLE PERSON
	(ID INTEGER DEFAULT nextval('PERSON_SEQUENCE') NOT NULL PRIMARY KEY,
	USERNAME VARCHAR(40) NOT NULL,
	FIRSTNAME VARCHAR(40),
	LASTNAME VARCHAR(40),
	ORGANIZATION VARCHAR(255),
	INDUSTRY VARCHAR(255),
	EMAIL VARCHAR(255),
	PHONENUMBER VARCHAR(40),
	DESCRIPTION VARCHAR(255),
	LAST_LOGIN TIMESTAMP WITH TIME ZONE DEFAULT NULL,
	GRACE_PERIOD_START TIMESTAMP WITH TIME ZONE DEFAULT NULL,
	STRIKE_COUNT INTEGER,
	LAST_STRIKE_TIME TIMESTAMP WITH TIME ZONE DEFAULT NULL,
	LOGGED_IN BOOLEAN NOT NULL);
	
CREATE TABLE PERSON_PREFERENCE
	(PERSON_ID INTEGER NOT NULL REFERENCES PERSON(ID) ON DELETE CASCADE,
	NAME VARCHAR(255) NOT NULL,
	VALUE TEXT);
	
CREATE INDEX PERSON_PREFERENCE_INDEX1 ON PERSON_PREFERENCE USING BTREE (PERSON_ID);
	
CREATE TABLE PERSON_PASSWORD
	(PERSON_ID INTEGER NOT NULL REFERENCES PERSON(ID) ON DELETE CASCADE,
	PASSWORD VARCHAR(255) NOT NULL,
	PASSWORD_DATE TIMESTAMP WITH TIME ZONE DEFAULT NULL);

CREATE TABLE ALERT
	(ID CHAR(36) NOT NULL PRIMARY KEY,
	NAME VARCHAR(255) NOT NULL UNIQUE,
	ALERT TEXT NOT NULL);
	
CREATE TABLE CODE_TEMPLATE_LIBRARY
	(ID VARCHAR(255) NOT NULL PRIMARY KEY,
	NAME VARCHAR(255) NOT NULL UNIQUE,
	REVISION INTEGER,
	LIBRARY TEXT);
	
CREATE TABLE CODE_TEMPLATE
	(ID VARCHAR(255) NOT NULL PRIMARY KEY,
	NAME VARCHAR(255) NOT NULL,
	REVISION INTEGER,
	CODE_TEMPLATE TEXT);

CREATE SEQUENCE CONFIGURATION_SEQUENCE START WITH 1;

CREATE TABLE CONFIGURATION
	(CATEGORY VARCHAR(255) NOT NULL,
	NAME VARCHAR(255) NOT NULL,
	VALUE TEXT,
	PRIMARY KEY(CATEGORY, NAME));
	
CREATE TABLE CHANNEL_GROUP
	(ID VARCHAR(255) NOT NULL PRIMARY KEY,
	NAME VARCHAR(255) NOT NULL UNIQUE,
	REVISION INTEGER,
	CHANNEL_GROUP TEXT);
	
INSERT INTO PERSON (USERNAME, LOGGED_IN) VALUES('admin', FALSE);

INSERT INTO PERSON_PASSWORD (PERSON_ID, PASSWORD) VALUES(1, 'YzKZIAnbQ5m+3llggrZvNtf5fg69yX7pAplfYg0Dngn/fESH93OktQ==');

INSERT INTO SCHEMA_INFO (VERSION) VALUES ('3.8.1');

INSERT INTO CONFIGURATION (CATEGORY, NAME, VALUE) VALUES ('core', 'stats.enabled', '1');

INSERT INTO CONFIGURATION (CATEGORY, NAME, VALUE) VALUES ('core', 'server.resetglobalvariables', '1');

INSERT INTO CONFIGURATION (CATEGORY, NAME, VALUE) VALUES ('core', 'smtp.timeout', '5000');

INSERT INTO CONFIGURATION (CATEGORY, NAME, VALUE) VALUES ('core', 'smtp.auth', '0');

INSERT INTO CONFIGURATION (CATEGORY, NAME, VALUE) VALUES ('core', 'smtp.secure', '0');

INSERT INTO CONFIGURATION (CATEGORY, NAME, VALUE) VALUES ('core', 'server.queuebuffersize', '1000');