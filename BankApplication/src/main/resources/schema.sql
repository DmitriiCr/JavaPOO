CREATE SCHEMA my_bank;

CREATE TABLE my_bank.account (
    id bigint NOT NULL PRIMARY KEY,
    sort_code CHAR(8) NOT NULL,
    account_number CHAR(8) NOT NULL,
    current_balance NUMERIC(10,3) NOT NULL,
    bank_name VARCHAR(50) NOT NULL,
    owner_name VARCHAR(50) NOT NULL,
    UNIQUE (sort_code, account_number)
);

CREATE SEQUENCE my_bank.transaction_sequence START WITH 5;
CREATE TABLE my_bank.transaction (
    id bigint NOT NULL PRIMARY KEY,
    source_account_id bigint NOT NULL REFERENCES my_bank.account(id),
    target_account_id bigint NOT NULL REFERENCES my_bank.account(id),
    -- Partially denormalize for performance
    target_owner_name varchar(50) NOT NULL,
    amount NUMERIC(10,3) NOT NULL,
    initiation_date timestamp NOT NULL
);
