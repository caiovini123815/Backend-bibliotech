CREATE TABLE books(
        id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
        title VARCHAR (150) NOT NULL,
        isbn VARCHAR (13) NOT NULL UNIQUE,
        publication_Date DATE NOT NULL,
        genre VARCHAR (150) NOT NULL,
        number_Pages INTEGER NOT NULL,
        quantity INTEGER NOT NULL,
        publisher_Id BIGINT NOT NULL,
        create_dt TIMESTAMP NOT NULL,
        update_dt TIMESTAMP NOT NULL,

CONSTRAINT fk_books_publisher
        FOREIGN KEY (publisher_id)
        REFERENCES publishers(id)
);