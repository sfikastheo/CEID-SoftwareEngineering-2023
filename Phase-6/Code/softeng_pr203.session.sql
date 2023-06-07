DROP TABLE JobApplication, JOtags;
-- @block 
CREATE OR REPLACE TABLE JobOffer(
id INT NOT NULL AUTO_INCREMENT,
employer INT NOT NULL,
date_opened DATETIME DEFAULT NOW(),
expires DATETIME,
title VARCHAR(40) NOT NULL,
descr VARCHAR(500),
num_employess TINYINT DEFAULT 1,
salary FLOAT(4,2) NOT NULL,
files_path VARCHAR(250) NOT NULL,
public BOOLEAN DEFAULT 0,
PRIMARY KEY (id),
CONSTRAINT fk_joffer_host FOREIGN KEY (employer) REFERENCES Host(hid) ON UPDATE CASCADE ON DELETE CASCADE
);

-- @block 
show tables;