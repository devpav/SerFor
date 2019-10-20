CREATE TABLE IF NOT EXISTS directories;
CREATE TABLE if not exists images;

INSERT into DIRECTORIES(id, DIRECTORY_NAME, DIRECTORY_HEIGHT, DIRECTORY_WIDTH)
VALUES ( 1, 'value_directory', 500, 500);

INSERT into images(id, image_length, image_name, image_directory)
VALUES ( 1, 45, 'Value', 1)