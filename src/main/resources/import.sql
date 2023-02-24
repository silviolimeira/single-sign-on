INSERT INTO authorities(authority) VALUES('ROLE_USER');
INSERT INTO authorities(authority) VALUES('ROLE_ADMIN');
INSERT INTO authorities(authority) VALUES('ROLE_DEVELOPER');

INSERT INTO users(USERNAME, PASSWORD, ACCOUNT_NON_EXPIRED, ACCOUNT_NON_LOCKED, CREDENTIALS_NON_EXPIRED, ENABLED) VALUES ('Developer', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', true, true, true, true);
INSERT INTO users(USERNAME, PASSWORD, ACCOUNT_NON_EXPIRED, ACCOUNT_NON_LOCKED, CREDENTIALS_NON_EXPIRED, ENABLED) VALUES ('Admin', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', true, true, true, true);
INSERT INTO users(USERNAME, PASSWORD, ACCOUNT_NON_EXPIRED, ACCOUNT_NON_LOCKED, CREDENTIALS_NON_EXPIRED, ENABLED) VALUES ('User', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', true, true, true, true);


INSERT INTO users_authorities(users_id, authorities_id) VALUES (1, 1);
INSERT INTO users_authorities(users_id, authorities_id) VALUES (1, 2);
INSERT INTO users_authorities(users_id, authorities_id) VALUES (1, 3);
INSERT INTO users_authorities(users_id, authorities_id) VALUES (2, 1);
INSERT INTO users_authorities(users_id, authorities_id) VALUES (2, 2);
INSERT INTO users_authorities(users_id, authorities_id) VALUES (3, 1);
