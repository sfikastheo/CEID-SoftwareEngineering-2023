INSERT INTO Tags VALUES
(NULL, "Live Music"),
(NULL, "Wine Tasting"),
(NULL, "Ancient Times"),
(NULL, "Foreign Language");

-- @block user
INSERT INTO user VALUES (NULL, 'Maria', '12345', 'up1072494@upnet.gr');

-- @block tourguide
INSERT INTO tourguide VALUES(1, '123456789012345678901234567');

-- @block Hosts
INSERT INTO USER VALUES(NULL, 'Triada', 'abcd', 'up1072476@upnet.gr');
INSERT INTO host VALUES(2, '012345678901234567890123456', 'Trinity Museum Services', 'some adress 551');

-- @block SimpleUser
INSERT INTO User VALUES (NULL, "sample-username3", "sample-password3", "sample-email3");
INSERT INTO SimpleUser VALUES(3,0);

-- @block JobOffer
INSERT INTO JobOffer(id, employer, title, descr, salary, files_path) VALUES(NULL,2,"sample-title2","sample-description2",'99.99',"sample-filepath2");

