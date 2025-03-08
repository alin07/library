CREATE TABLE Account (
    account_id SERIAL PRIMARY KEY,
    password VARCHAR(100) NOT NULL,
    salt VARCHAR(100) NOT NULL,
    first_name VARCHAR(75) NOT NULL,
    last_name VARCHAR(75) NOT NULL,
    street_address VARCHAR(75),
    city VARCHAR(75),
    state VARCHAR(75),
    zipcode VARCHAR(5),
    email VARCHAR(50) UNIQUE NOT NULL,
    phone VARCHAR(20),
    library_card_number VARCHAR(25) UNIQUE
);

CREATE TABLE Book (
    ISBN VARCHAR(13) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    genre VARCHAR(255),
    publisher VARCHAR(255),
    language VARCHAR(255)
);

CREATE TABLE Book_Format (
    book_format_id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL CHECK (name IN ('audiobook', 'ebook'))
);

CREATE TABLE Book_Copy (
    book_copy_id SERIAL PRIMARY KEY,
    ISBN VARCHAR(13) REFERENCES Book(ISBN),
    book_format_id INT REFERENCES Book_Format(book_format_id)
);

CREATE TABLE Author (
    author_id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description TEXT
);

CREATE TABLE Book_Author (
    ISBN VARCHAR(13) REFERENCES Book(ISBN),
    author_id INT REFERENCES Author(author_id),
    PRIMARY KEY (ISBN, author_id)
);

CREATE TABLE Book_Reservation (
    reservation_id SERIAL PRIMARY KEY,
    account_id INT REFERENCES Account(account_id),
    book_copy_id INT REFERENCES Book_Copy(book_copy_id),
    date_reserved DATE NOT NULL CHECK (date_reserved <= CURRENT_DATE),
    date_updated DATE NOT NULL CHECK (date_reserved <= CURRENT_DATE)
);

CREATE TABLE Book_Loaned (
    loan_id SERIAL PRIMARY KEY,
    account_id INT REFERENCES Account(account_id),
    book_copy_id INT REFERENCES Book_Copy(book_copy_id),
    date_loaned DATE NOT NULL,
    date_due DATE NOT NULL,
    date_returned DATE CHECK (date_returned IS NULL OR date_returned >= date_loaned)
);
