CREATE TABLE IF NOT EXISTS directories;
CREATE TABLE if not exists images;

INSERT into DIRECTORIES(id, DIRECTORY_NAME)
VALUES ( 1, 'value_Directory');

INSERT into images(id, image_length, image_name, image_width, image_directory)
VALUES ( 1, 45, 'Value', 656, 1)