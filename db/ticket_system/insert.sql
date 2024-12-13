insert into roles (name)
values
    ('Administrator'),
    ('Engineer'),
    ('Accountant'),
    ('Lawyer'),
    ('Chief Executive Officer');

insert into users (name, salary, role_id)
values
    ('Alice Johnson', 5000, 1),
    ('Bob Smith', 4500, 2),
    ('Charlie Brown', 5200, 3),
    ('Diana White', 4800, 4),
    ('Evan Green', 5300, 5);

insert into rules (name)
values
    ('Read'),
    ('Create'),
    ('Update'),
    ('Delete');

insert into roles_rules (role_id, rule_id)
values
    (1, 1),
    (1, 2),
    (1, 3),
    (1, 4),
    (2, 1),
    (2, 3),
    (3, 1),
    (3, 3),
    (4, 1),
    (4, 3),
    (5, 1),
    (5, 2),
    (5, 3);

insert into categories (name)
values
    ('Technical Support'),
    ('Supply'),
    ('Maintenance and Repair'),
    ('Administrative Requests'),
    ('Suggestions');

insert into states (name)
values
    ('Created'),
    ('In Progress'),
    ('Rejected'),
    ('Resolved');

insert into items (name, user_id, category_id, state_id)
values
    ('ITM-1', 2, 3, 2),
    ('ITM-2', 1, 1, 4),
    ('ITM-3', 3, 2, 3),
    ('ITM-4', 5, 4, 4),
    ('ITM-5', 4, 1, 1);

insert into comments (item_id, message)
values
    (1, 'The quick brown fox'),
    (2, 'Jumps over the fence'),
    (3, 'Bright stars in the sky'),
    (4, 'A cup of hot tea'),
    (5, 'Walking through the park');

insert into attachs (item_id, content)
values
    (1, '\x48656c6c6f'),
    (2, '\x576f726c64'),
    (3, '\x48657921'),
    (4, '\x546573745f64617461'),
    (5, '\x426f6f6b');

commit;