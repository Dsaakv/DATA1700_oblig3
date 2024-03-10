CREATE TABLE Billett (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         film VARCHAR(255) NOT NULL,
                         antall INT NOT NULL,
                         fornavn VARCHAR(255) NOT NULL,
                         etternavn VARCHAR(255) NOT NULL,
                         telefonnr VARCHAR(15),
                         epost VARCHAR(255) NOT NULL
);