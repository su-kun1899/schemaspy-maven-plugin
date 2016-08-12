DROP USER IF EXISTS 'test_user';
CREATE USER 'test_user' IDENTIFIED BY "hogehoge";
GRANT ALL ON test.* TO test_user;
FLUSH PRIVILEGES;