USE engineering_workflow_db;

INSERT INTO employees(full_name, email, role) VALUES
('Aditi Sharma', 'aditi.sharma@company.com', 'ADMIN'),
('Rahul Nair', 'rahul.nair@company.com', 'ENGINEER'),
('Neha Gupta', 'neha.gupta@company.com', 'ENGINEER');

INSERT INTO rules(name, type, active, error_message, source_status, target_status, priority_condition, min_approvals, execution_order) VALUES
('Review before Test', 'STATUS_PREREQUISITE', true, 'Task cannot move to TEST unless current status is REVIEW.', 'REVIEW', 'TEST', NULL, NULL, 1),
('High Priority Approval Gate', 'HIGH_PRIORITY_APPROVALS', true, 'HIGH priority tasks require at least 2 approvals before TEST.', NULL, 'TEST', 'HIGH', 2, 2),
('Blocked Metadata Required', 'BLOCKED_METADATA', true, 'BLOCKED tasks must include blocked reason and timestamp.', NULL, 'BLOCKED', NULL, NULL, 3);

INSERT INTO tasks(title, description, status, priority, approvals_count, blocked_reason, blocked_at, assigned_employee_id, created_at, updated_at) VALUES
('Implement API Gateway', 'Build edge routing and auth checks.', 'REVIEW', 'HIGH', 2, NULL, NULL, 2, NOW(), NOW()),
('Upgrade Build Pipeline', 'Improve CI speed and reliability.', 'IN_PROGRESS', 'MEDIUM', 0, NULL, NULL, 3, NOW(), NOW());
