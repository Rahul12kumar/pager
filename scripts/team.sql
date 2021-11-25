create table team(
 team_id BIGSERIAL UNIQUE NOT NULL,
 team_name varchar NOT NULl,
 created_on timestamp with time zone DEFAULT now(),
 modified_on timestamp with time zone
 );