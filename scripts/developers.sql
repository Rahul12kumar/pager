create table Public.developers(
 id BIGSERIAL UNIQUE NOT NULL,
 team_id INTEGER NOT NULL,
 name VARCHAR NOT NULL,
 mobile_number VARCHAR NOT NULL,
 created_on timestamp with time zone DEFAULT now(),
 modified_on timestamp with time zone
 );