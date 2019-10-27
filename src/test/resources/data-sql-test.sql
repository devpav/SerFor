CREATE TABLE IF NOT EXISTS directories;
CREATE TABLE if not exists images;

INSERT into REALMS(id, REALM_NAME)
VALUES ( 1, 'devpav-realm');

INSERT into REALM_CONFIGS(id, DIRECTORY_REALM, REALM_CONFIG_ID)
VALUES ( 1, 'virtualDirectory-devpav-realm', 1);

INSERT into DIRECTORIES(id, DIRECTORY_NAME, DIRECTORY_HEIGHT, DIRECTORY_WIDTH, DIRECTORY_APPLICATION)
VALUES ( 1, 'value_directory', 500, 500, 1);

INSERT into images(id, image_length, image_name, image_directory)
VALUES ( 1, 45, 'Value', 1)
