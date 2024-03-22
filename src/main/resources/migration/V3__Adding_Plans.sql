-- Create Plan Table
CREATE TABLE plan (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    price DECIMAL(10, 2) NOT NULL,
    max_devices INT
);

-- Plan Allowed Devices Table
CREATE TABLE plan_allowed_devices (
    plan_id BIGINT NOT NULL,
    device_type VARCHAR(255) NOT NULL,
    CONSTRAINT fk_plan_allowed_devices_plan FOREIGN KEY (plan_id) REFERENCES plan(id),
    CONSTRAINT uc_plan_device UNIQUE (plan_id, device_type) -- To avoid duplicate entries for the same plan
);

-- Add current_plan_id column to app_user table
ALTER TABLE app_user
ADD COLUMN current_plan_id BIGINT;

-- Add foreign key constraint for current_plan_id in app_user table
ALTER TABLE app_user
ADD CONSTRAINT fk_app_user_plan FOREIGN KEY (current_plan_id) REFERENCES plan(id);

-- Insert Plans
INSERT INTO plan (name, description, price, max_devices) VALUES
('Basic', 'Basic plan with limited device support.', 9.99, 5),
('Standard', 'Standard plan with moderate device support and automation.', 19.99, 10),
('Premium', 'Premium plan with full device support, advanced automation, and priority support.', 29.99, NULL);

-- Insert Allowed Devices for Basic Plan
INSERT INTO plan_allowed_devices (plan_id, device_type) VALUES
((SELECT id FROM plan WHERE name = 'Basic'), 'LIGHT'),
((SELECT id FROM plan WHERE name = 'Basic'), 'THERMOSTAT');

-- Insert Allowed Devices for Standard Plan
INSERT INTO plan_allowed_devices (plan_id, device_type) VALUES
((SELECT id FROM plan WHERE name = 'Standard'), 'LIGHT'),
((SELECT id FROM plan WHERE name = 'Standard'), 'THERMOSTAT'),
((SELECT id FROM plan WHERE name = 'Standard'), 'SMART_PLUG'),
((SELECT id FROM plan WHERE name = 'Standard'), 'SECURITY_CAMERA');

-- Assuming Premium Plan allows all device types, iterate through all types you have.

-- Alice == Basic
UPDATE app_user SET current_plan_id = 1 WHERE id = 1 ;
-- Bob == Standard
UPDATE app_user SET current_plan_id = 2 WHERE id = 2 ;
-- Charlie == Premium
UPDATE app_user SET current_plan_id = 3 WHERE id = 3 ;