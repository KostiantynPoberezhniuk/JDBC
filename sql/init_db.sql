CREATE TABLE worker(
id INT NOT NULL PRIMARY KEY,
name VARCHAR(1000) NOT NULL CHECK (LENGTH(name) >= 2),
birthday DATE NOT NULL CHECK (birthday > DATE  '1900-01-01'),
level VARCHAR NOT NULL CHECK (level IN ('Trainee', 'Junior', 'Middle', 'Senior')),
salary DECIMAL(6,2) NOT NULL CHECK (salary > 100)
);


CREATE TABLE client(
id INT NOT NULL PRIMARY KEY,
name VARCHAR(1000) NOT NULL CHECK (LENGTH(name) >= 2)
);


CREATE TABLE project (
id INT NOT NULL PRIMARY KEY,
client_id INT,
start_date DATE,
finish_date DATE
);


CREATE TABLE project_worker (
project_id INT NOT NULL,
worker_id INT NOT NULL,
PRIMARY KEY (project_id, worker_id),
FOREIGN KEY (project_id) REFERENCES project(id),
FOREIGN KEY (worker_id) REFERENCES worker(id)
);
