-- Create User Table
CREATE TABLE app_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    is_admin BOOLEAN NOT NULL
);

-- Create SmartDevice Table
CREATE TABLE smart_device (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    device_type_enum VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    owner_id BIGINT,
    CONSTRAINT fk_smart_device_user FOREIGN KEY (owner_id) REFERENCES app_user (id)
);

-- Create AutomationRule Table
CREATE TABLE automation_rule (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    condition VARCHAR(255) NOT NULL,
    action VARCHAR(255) NOT NULL,
    device_id BIGINT,
    CONSTRAINT fk_automation_rule_smart_device FOREIGN KEY (device_id) REFERENCES smart_device (id)
);
