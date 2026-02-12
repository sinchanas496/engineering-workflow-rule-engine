CREATE DATABASE IF NOT EXISTS engineering_workflow_db;
USE engineering_workflow_db;

CREATE TABLE IF NOT EXISTS employees (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(80) NOT NULL,
    email VARCHAR(120) NOT NULL UNIQUE,
    role VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS tasks (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(150) NOT NULL,
    description TEXT,
    status VARCHAR(20) NOT NULL,
    priority VARCHAR(20) NOT NULL,
    approvals_count INT NOT NULL DEFAULT 0,
    blocked_reason VARCHAR(255),
    blocked_at DATETIME,
    assigned_employee_id BIGINT,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    CONSTRAINT fk_tasks_employee FOREIGN KEY (assigned_employee_id) REFERENCES employees(id)
);

CREATE TABLE IF NOT EXISTS rules (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(120) NOT NULL,
    type VARCHAR(40) NOT NULL,
    active BOOLEAN NOT NULL,
    error_message TEXT,
    source_status VARCHAR(20),
    target_status VARCHAR(20),
    priority_condition VARCHAR(20),
    min_approvals INT,
    execution_order INT NOT NULL
);

CREATE TABLE IF NOT EXISTS violation_logs (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    task_id BIGINT NOT NULL,
    rule_id BIGINT NOT NULL,
    message TEXT NOT NULL,
    timestamp DATETIME NOT NULL
);
