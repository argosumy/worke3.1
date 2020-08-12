CREATE SEQUENCE BOOK_ID_CATEGORY_SEQ START WITH 1 INCREMENT BY 1/
CREATE TABLE BOOK_CATEGORIES(CATEGORY_ID INTEGER NOT NULL,
    NAME VARCHAR(20) not null,
    DESCRIPTION VARCHAR2(1000),
    PARENT_ID INTEGER,
    CONSTRAINT CATEGORY_ID_PK PRIMARY KEY (CATEGORY_ID))
/
ALTER TABLE BOOK_CATEGORIES ADD CONSTRAINT PARENT_ID_FK FOREIGN KEY (PARENT_ID) REFERENCES BOOK_CATEGORIES(CATEGORY_ID)
/
CREATE OR REPLACE TRIGGER BOOK_CATEGORY_TR BEFORE INSERT ON BOOK_CATEGORIES FOR EACH ROW BEGIN
    SELECT BOOK_ID_CATEGORY_SEQ.nextval INTO:NEW.CATEGORY_ID FROM DUAL; END;
/
CREATE SEQUENCE BOOK_ID_PRODUCT_SEQ start with 1 increment by 1
/
CREATE TABLE BOOK_PRODUCT(
    PRODUCT_ID INTEGER NOT NULL,
    NAME VARCHAR(20) not null,
    DESCRIPTION VARCHAR2(1000),
    PRICE FLOAT(126),
    IS_ACTIVE INTEGER,
    CATEGORY_ID INTEGER,
    CONSTRAINT PRODUCT_ID_PK PRIMARY KEY (PRODUCT_ID))
/
ALTER TABLE BOOK_PRODUCT ADD CONSTRAINT CATEGORY_ID_FK FOREIGN KEY (CATEGORY_ID) REFERENCES BOOK_CATEGORIES(CATEGORY_ID)
/
CREATE OR REPLACE TRIGGER BOOK_PRODUCT_TR BEFORE INSERT ON BOOK_PRODUCT FOR EACH ROW
BEGIN SELECT BOOK_ID_PRODUCT_SEQ.nextval INTO:NEW.PRODUCT_ID FROM dual;  END;
/
CREATE SEQUENCE BOOK_ID_USER_SEQ start with 1 increment by 1
/
CREATE TABLE BOOK_USERS(
    ID INTEGER NOT NULL,
    NAME VARCHAR(20) not null,
    LAST_NAME VARCHAR(20) not null,
    PHONE VARCHAR(10),
    CONSTRAINT USER_ID_PK PRIMARY KEY (ID))
/
CREATE OR REPLACE TRIGGER BOOK_USER_TR BEFORE INSERT ON BOOK_USERS FOR EACH ROW
BEGIN SELECT BOOK_ID_USER_SEQ.nextval INTO:NEW.ID FROM dual; END;
/
CREATE SEQUENCE BOOK_ID_LOGIN_SEQ start with 1 increment by 1
/
CREATE TABLE BOOK_LOGIN(
    ID INTEGER NOT NULL,
    LOGIN VARCHAR(20) not null,
    PASSWORD VARCHAR(20) not null,
    ROLE VARCHAR(10) not null,
    ID_USER INTEGER NOT NULL,
    CONSTRAINT LOGIN_ID_PK PRIMARY KEY (ID))
/
CREATE OR REPLACE TRIGGER BOOK_LOGIN_TR BEFORE INSERT ON BOOK_LOGIN FOR EACH ROW
BEGIN SELECT BOOK_ID_LOGIN_SEQ.nextval INTO:NEW.ID FROM dual; END;
/
INSERT  INTO BOOK_USERS(NAME, LAST_NAME)values ('user','last_name')/
INSERT  INTO BOOK_LOGIN(LOGIN, PASSWORD, ROLE, ID_USER)values ('user','qwerty','ADMIN','1')/
INSERT INTO BOOK_CATEGORIES(name, description, parent_id)values ('ROOT','',1)/
INSERT INTO BOOK_CATEGORIES(name, description, parent_id)values ('Herbal extracts','',1)/
INSERT INTO BOOK_CATEGORIES(name, description, parent_id)values ('Herbal elixirs','',1)/
INSERT INTO BOOK_CATEGORIES(name, description, parent_id)values ('Dietary Supplements','',1)/
INSERT INTO BOOK_CATEGORIES(name, description, parent_id)values ('Phito','',1)/
INSERT INTO BOOK_CATEGORIES(name, description, parent_id)values ('Font-Diet','',1)/

INSERT INTO BOOK_PRODUCT(name, description, PRICE, IS_ACTIVE, CATEGORY_ID)values ('AGRIMONY EXTRACT',
     'agrimony finds its application in diseases of the biliary tract and liver. It is conditioned by its ability ' ||
     'to improve the rheological properties of bile and to promote its normal flow. Agrimony is indicated for ' ||
      'treatment of chronic pancreatitis and is also administered in diseases associated with the ' ||
      'reduction in enzymatic activity of the pancreas. It is effective in treatment of gastritis, peptic ulcer, ' ||
      'colitis, and helps to get rid of the heart burn.',39,0,2)/
INSERT INTO BOOK_PRODUCT(name, description, PRICE, IS_ACTIVE, CATEGORY_ID)values ('BETULIN','as anti-inflammatory, ' ||
        'antioxidant, anti-tumor, antiviral, hepatoprotective and choleretic agent, it is used for improving the liver ' ||
        'function, increasing the bodys defenses, and for cancer prevention.',39,0,2)/
INSERT INTO BOOK_PRODUCT(name, description, PRICE, IS_ACTIVE, CATEGORY_ID)values ('CLEANSING ELIXIR','it is recommended ' ||
        'as a diuretic, choleretic, mild laxative agent, to stimulate metabolism and cleaning of gastrointestinal tract,' ||
        ' urogenital system, blood vessels, joints and spine, reduction of lipids in blood serum; promotes the dissolution ' ||
        'and removal of salts, waste and toxic substances from the body; has anti-allergic effect.',42,0,3)/