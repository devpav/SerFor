CREATE TABLE IF NOT EXISTS directories;
CREATE TABLE if not exists images;

INSERT into DIRECTORIES(id, DIRECTORY_NAME)
VALUES ( 1, '800x800'), ( 2, '400x700');

INSERT into images(id, image_length, image_name, image_width, image_directory)
VALUES ( 1, 45, 'Value', 656, 1)