DROP TABLE IF EXISTS VALIDATED_WORK_ORDERS;

CREATE TABLE VALIDATED_WORK_ORDERS (
    id INT AUTO_INCREMENT PRIMARY KEY,
    date_received VARCHAR(60) NOT NULL,
    status VARCHAR(7) NOT NULL,
    type VARCHAR(60) NOT NULL,
    department VARCHAR(60) NOT NULL
);