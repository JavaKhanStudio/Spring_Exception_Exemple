INSERT INTO app_user (username, password, email, is_admin) VALUES
('Alice', 'password123', 'alice@example.com', TRUE),
('Bob', 'password456', 'bob@example.com', FALSE),
('Charlie', 'password789', 'charlie@example.com', FALSE);

-- Devices for Alice
INSERT INTO smart_device (name, location, device_type_enum, status, owner_id) VALUES
('Living Room Light', 'Living Room', 'LIGHT', 'ON', (SELECT id FROM app_user WHERE username = 'Alice')),
('Kitchen Thermostat', 'Kitchen', 'THERMOSTAT', 'STANDBY', (SELECT id FROM app_user WHERE username = 'Alice'));

-- Devices for Bob
INSERT INTO smart_device (name, location, device_type_enum, status, owner_id) VALUES
('Front Door Lock', 'Entrance', 'DOOR_LOCK', 'OFF', (SELECT id FROM app_user WHERE username = 'Bob')),
('Security Camera', 'Backyard', 'SECURITY_CAMERA', 'ON', (SELECT id FROM app_user WHERE username = 'Bob'));

-- Rules for Alice's devices
INSERT INTO automation_rule (name, condition, action, device_id) VALUES
('Turn off Living Room Light at Midnight', 'TIME_OF_DAY', 'TURN_OFF_DEVICE', (SELECT id FROM smart_device WHERE name = 'Living Room Light')),
('Heat up Kitchen in the Morning', 'TIME_OF_DAY', 'TURN_ON_DEVICE', (SELECT id FROM smart_device WHERE name = 'Kitchen Thermostat'));

-- Rules for Bob's devices
INSERT INTO automation_rule (name, condition, action, device_id) VALUES
('Secure Front Door at Night', 'TIME_OF_DAY', 'LOCK_DOOR', (SELECT id FROM smart_device WHERE name = 'Front Door Lock')),
('Activate Backyard Camera on Motion', 'MOTION_DETECTED', 'RECORD_VIDEO', (SELECT id FROM smart_device WHERE name = 'Security Camera'));
