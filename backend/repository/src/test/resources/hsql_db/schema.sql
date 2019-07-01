DROP TABLE IF EXISTS gift_certificate CASCADE;
DROP TABLE IF EXISTS tag CASCADE;
DROP TABLE IF EXISTS gift_certificate_m2m_tag CASCADE;

-- --------------------------------
-- Create table 'gift_certificate'
-- --------------------------------
CREATE TABLE IF NOT EXISTS gift_certificate
(
  id                   IDENTITY PRIMARY KEY   NOT NULL,
  name                 CHARACTER VARYING(35)  NOT NULL,
  description          CHARACTER VARYING(256) NOT NULL,
  price                NUMERIC(20, 2)         NOT NULL,
  date_of_creation     TIMESTAMP              NOT NULL,
  date_of_modification TIMESTAMP,
  duration_in_days     INTEGER                NOT NULL
);

-- -------------------
-- Create table 'tag'
-- -------------------
CREATE TABLE IF NOT EXISTS tag
(
  id   IDENTITY PRIMARY KEY  NOT NULL,
  name CHARACTER VARYING(35) NOT NULL UNIQUE
);

-- ----------------------------------------
-- Create table 'gift_certificate_m2m_tag'
-- ----------------------------------------
CREATE TABLE IF NOT EXISTS gift_certificate_m2m_tag
(
  gift_certificate_id INTEGER REFERENCES gift_certificate (id) ON DELETE CASCADE ON UPDATE CASCADE,
  tag_id              INTEGER REFERENCES tag (id) ON DELETE CASCADE ON UPDATE CASCADE
);
