DROP TABLE IF EXISTS billionaires;




CREATE TABLE application_users (
  user_uuid uuid  PRIMARY KEY,
  userName VARCHAR(256) NOT NULL,
  password VARCHAR(1024) NOT NULL,
  enabled VARCHAR(1) DEFAULT 'Y'
);

CREATE TABLE application_roles (
  role_uuid uuid    PRIMARY KEY,
  roleName VARCHAR(256) NOT NULL
);
CREATE TABLE application_user_roles (
  user_uuid uuid,
  role_uuid uuid,
  primary key(user_uuid,role_uuid),
  foreign key(user_uuid) references application_users(user_uuid),
  foreign key(role_uuid) references application_roles(role_uuid)
);

CREATE TABLE user_resolutions (
  res_uuid uuid,
  resolution varchar(256),
  fk_uid uuid,
  primary key(resolution ,fk_uid),
  foreign key(fk_uid) references application_users(user_uuid)
);

select * from application_users;
select * from application_roles;
select * from application_user_roles;



