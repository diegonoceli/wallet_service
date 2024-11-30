INSERT INTO wallet (user_id, balance, name, surname, document_number) VALUES
('usr0', 1000.00, 'John', 'Doe', '123456789'),
('usr1', 1500.00, 'Jane', 'Smith', '987654321'),
('usr2', 2000.00, 'Alice', 'Johnson', '456123789');

INSERT INTO transaction (wallet_id, type, amount, description) VALUES
(1, 'DEPOSIT', 500.00, 'Initial deposit'),
(1, 'WITHDRAWAL', 200.00, 'ATM withdrawal');

INSERT INTO transaction (wallet_id, type, amount, description) VALUES
(2, 'DEPOSIT', 1000.00, 'Initial deposit'),
(2, 'WITHDRAWAL', 300.00, 'Online purchase');

INSERT INTO transaction (wallet_id, type, amount, description) VALUES
(3, 'DEPOSIT', 1200.00, 'Initial deposit'),
(3, 'WITHDRAWAL', 400.00, 'Bill payment');