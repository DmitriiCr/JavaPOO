INSERT INTO my_bank.account (id, sort_code, account_number, current_balance, bank_name, owner_name)
VALUES (1, '77-50-20', '73084635', 1000, 'Bank Populaire', 'Dmitrii Crivoi');
INSERT INTO my_bank.account (id, sort_code, account_number, current_balance, bank_name, owner_name)
VALUES (2, '40-50-60', '21956204', 2000, 'Credit Agricole', 'Joseph Vernan');

INSERT INTO my_bank.transaction (id, source_account_id, target_account_id, target_owner_name, amount, initiation_date)
VALUES (1, 1, 2, 'Joseph Vernan', 50.00, '2019-04-01 10:30');
INSERT INTO my_bank.transaction (id, source_account_id, target_account_id, target_owner_name, amount, initiation_date)
VALUES (2, 1, 2, 'Joseph Vernan', 50.00, '2019-05-01 10:30');

INSERT INTO my_bank.transaction (id, source_account_id, target_account_id, target_owner_name, amount, initiation_date )
VALUES (3, 2, 1, 'Dmitrii Crivoi', 200.00, '2019-05-27 17:21');
