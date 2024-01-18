/* データベース: customer_manager_db */
DROP DATABASE IF EXISTS customer_manager_db;

CREATE DATABASE customer_manager_db CHARACTER SET utf8 COLLATE utf8_general_ci;

/* ユーザを作成 */
DROP USER IF EXISTS customerU;

CREATE USER customerU IDENTIFIED BY 'customerP';

/* 権限付与 */
GRANT ALL PRIVILEGES ON customer_manager_db.* TO 'customerU';

/* DBの選択*/
USE customer_manager_db;

/* 権限マスタテーブル */
CREATE TABLE m_authority (
  authority_code CHAR(2) UNIQUE,
  authority_name VARCHAR(10) NOT NULL
);

/* 地区マスタテーブル */
CREATE TABLE m_area (
  area_code CHAR(4),
  area_name VARCHAR(10) NOT NULL UNIQUE,
  PRIMARY KEY(area_code)
);

/* ユーザマスタテーブル */
CREATE TABLE m_user (
  user_id VARCHAR(24),
  password VARCHAR(32) NOT NULL,
  authority_code CHAR(2) NOT NULL DEFAULT 'A0',
  PRIMARY KEY(user_id),
  FOREIGN KEY(authority_code) REFERENCES m_authority(authority_code)
);

/* 顧客マスタテーブル */
CREATE TABLE m_customer (
  customer_id INT NOT NULL AUTO_INCREMENT,
  customer_name VARCHAR(20) NOT NULL,
  customer_name_kana VARCHAR(20) NOT NULL,
  post_code CHAR(7) NOT NULL,
  area_code CHAR(4) NOT NULL,
  gender CHAR(1) NOT NULL,
  birthday DATE NOT NULL,
  phone_number VARCHAR(13) NOT NULL,
  insert_datetime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_datetime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY(customer_id),
  FOREIGN KEY(area_code) REFERENCES m_area(area_code)
);

/* 現在時間UPDATEトリガー */
CREATE TRIGGER m_customer_update BEFORE
UPDATE
  ON m_customer FOR EACH ROW
SET
  NEW.update_datetime = CURRENT_TIMESTAMP;

/* 顧客履歴マスタテーブル */
CREATE TABLE m_customer_log (
  id INT NOT NULL AUTO_INCREMENT,
  customer_id INT NOT NULL,
  customer_name VARCHAR(20) DEFAULT NULL,
  customer_name_kana VARCHAR(20) DEFAULT NULL,
  post_code CHAR(7) DEFAULT NULL,
  area_code CHAR(4) DEFAULT NULL,
  gender CHAR(1) DEFAULT NULL,
  phone_number VARCHAR(13) DEFAULT NULL,
  PRIMARY KEY(id),
  FOREIGN KEY(area_code) REFERENCES m_area(area_code)
);

/* 顧客履歴INSERTトリガー */
-- CREATE TRIGGER m_customer_log_insert AFTER
-- INSERT
--   ON m_customer FOR EACH ROW
-- INSERT INTO
--   m_customer_log
-- SET
--   customer_id = NEW.customer_id,
--   customer_name = NEW.customer_name,
--   customer_name_kana = NEW.customer_name_kana,
--   post_code = NEW.post_code,
--   area_code = NEW.area_code,
--   gender = NEW.gender,
--   phone_number = NEW.phone_number;

/* 顧客履歴UPDATEトリガー */
CREATE TRIGGER m_customer_log_update BEFORE
UPDATE
  ON m_customer FOR EACH ROW
INSERT INTO
  m_customer_log
SET
  customer_id = OLD.customer_id,
  customer_name = OLD.customer_name,
  customer_name_kana = OLD.customer_name_kana,
  post_code = OLD.post_code,
  area_code = OLD.area_code,
  gender = OLD.gender,
  phone_number = OLD.phone_number;

  /* 権限マスタINSERT */
INSERT INTO
  m_authority(authority_code, authority_name)
VALUES
  ('A0', '閲覧者'),
  ('A1', '編集者'),
  ('A2', '管理者');

/* 地区マスタINSERT */
INSERT INTO
  m_area(area_code, area_name)
VALUES
  ('A000', 'Not set'),
  ('A001', '北海道'),
  ('A002', '東北'),
  ('A003', '関東'),
  ('A004', '中部'),
  ('A005', '近畿'),
  ('A006', '中国'),
  ('A007', '四国'),
  ('A008', '九州沖縄');

/* ユーザマスタINSERT */
INSERT INTO
  m_user(user_id, password, authority_code)
VALUES
  ('readerU', 'readerP', 'A0'),
  ('editU', 'editP', 'A1'),
  ('managerU', 'managerP', 'A2');

/* 顧客マスタINSERT */
INSERT INTO
  m_customer(
    customer_name,
    customer_name_kana,
    post_code,
    area_code,
    gender,
    birthday,
    phone_number
  )
VALUES
  (
    'スタンリー・チャウ',
    'すたんりー・ちゃう',
    '8120037',
    'A002',
    '男',
    '19910105',
    '08011112222'
  ),
  (
    '山田太郎',
    'やまだたろう',
    '8120000',
    'A001',
    '男',
    '19930106',
    '09011112222'
  ),
  (
    '佐藤花子',
    'さとうはなこ',
    '6120000',
    'A006',
    '女',
    '19950823',
    '09033334444'
  );

COMMIT;