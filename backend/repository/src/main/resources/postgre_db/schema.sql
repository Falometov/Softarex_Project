DROP TABLE IF EXISTS gift_certificate CASCADE;
DROP TABLE IF EXISTS tag CASCADE;
DROP TABLE IF EXISTS gift_certificate_m2m_tag CASCADE;
DROP TABLE IF EXISTS certificate_user CASCADE;
DROP TABLE IF EXISTS purchase CASCADE;
DROP TABLE IF EXISTS purchase_m2m_gift_certificate CASCADE;

-- --------------------------------
-- Create table 'gift_certificate'
-- --------------------------------
CREATE TABLE IF NOT EXISTS gift_certificate
(
  id                   BIGSERIAL PRIMARY KEY NOT NULL,
  name                 CHARACTER VARYING(60) NOT NULL,
  description          TEXT                  NOT NULL,
  price                NUMERIC(20, 2)        NOT NULL,
  date_of_creation     TIMESTAMP             NOT NULL,
  date_of_modification TIMESTAMP             NOT NULL,
  duration_in_days     INTEGER               NOT NULL,
  active               BOOLEAN               NOT NULL DEFAULT ('true')
);

-- -------------------
-- Create table 'tag'
-- -------------------
CREATE TABLE IF NOT EXISTS tag
(
  id   BIGSERIAL PRIMARY KEY NOT NULL,
  name CHARACTER VARYING(35) NOT NULL UNIQUE
);

-- ----------------------------------------
-- Create table 'gift_certificate_m2m_tag'
-- ----------------------------------------
CREATE TABLE IF NOT EXISTS gift_certificate_m2m_tag
(
  gift_certificate_id BIGSERIAL NOT NULL REFERENCES gift_certificate (id) ON DELETE CASCADE ON UPDATE CASCADE,
  tag_id              BIGSERIAL NOT NULL REFERENCES tag (id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ---------------------------------
-- Create table 'certificate_user'
-- ---------------------------------
CREATE TABLE IF NOT EXISTS certificate_user
(
  id        BIGSERIAL PRIMARY KEY NOT NULL,
  username  CHARACTER VARYING(35) NOT NULL UNIQUE,
  password  CHARACTER VARYING(60) NOT NULL,
  user_role CHARACTER VARYING(10) NOT NULL
);

-- --------------------------
-- Create table 'purchase'
-- --------------------------
CREATE TABLE IF NOT EXISTS purchase
(
  id                  BIGSERIAL PRIMARY KEY NOT NULL,
  cost                NUMERIC(20, 2)        NOT NULL,
  timestamp           TIMESTAMP             NOT NULL,
  certificate_user_id BIGSERIAL             NOT NULL REFERENCES certificate_user (id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ----------------------------------------------
-- Create table 'purchase_m2m_gift_certificate'
-- ----------------------------------------------
CREATE TABLE IF NOT EXISTS purchase_m2m_gift_certificate
(
  purchase_id         BIGSERIAL      NOT NULL REFERENCES purchase (id) ON DELETE CASCADE ON UPDATE CASCADE,
  gift_certificate_id BIGSERIAL      NOT NULL REFERENCES gift_certificate (id) ON DELETE CASCADE ON UPDATE CASCADE,
  purchase_price      NUMERIC(20, 2) NOT NULL,
  count               BIGINT         NOT NULL
);