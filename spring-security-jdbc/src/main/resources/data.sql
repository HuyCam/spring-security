INSERT INTO users (username, password, enabled)
values ('huy','myname',true);

INSERT INTO users(username, password, enabled)
values ('admin', 'myname', true);

INSERT INTO authorities (username, authority)
values ('huy', 'ROLE_USER');

INSERT INTO authorities (username, authority)
values ('admin', 'ROLE_ADMIN');