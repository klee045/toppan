-- Initialize data for all tables
INSERT INTO authors (name, created_at, updated_at) VALUES 
    ('N.K. Jemisin', NOW(), NOW()),
    ('William Peter Blatty', NOW(), NOW()),
    ('Brandon Sanderson', NOW(), NOW());

INSERT INTO books (name, created_at, updated_at) VALUES 
    ('The Stone Sky', NOW(), NOW()),
    ('The Exorcist', NOW(), NOW()),
    ('The Final Empire', NOW(), NOW());

INSERT INTO people (name, country_id, created_at, updated_at) VALUES 
    ('Peggy Olsen', 702, NOW(), NOW()),
    ('Joan Harris', 702, NOW(), NOW()),
    ('Trudy Campbell', 702, NOW(), NOW()),
    ('Roger Sterling', 702, NOW(), NOW()),
    ('Don Draper', 458, NOW(), NOW()),
    ('E.B. Farnum', 458, NOW(), NOW()),
    ('Jim Halpert', 458, NOW(), NOW()),
    ('Kevin Malone', 458, NOW(), NOW()),
    ('Rachel Menken', 840, NOW(), NOW()),
    ('Betty Draper', 840, NOW(), NOW()),
    ('Michael Scott', 840, NOW(), NOW()),
    ('Darryl Philbin', 840, NOW(), NOW());

INSERT INTO author_books (author_id, book_id, created_at, updated_at) VALUES 
    (1, 1, NOW(), NOW()),
    (2, 2, NOW(), NOW()),
    (3, 3, NOW(), NOW());

INSERT INTO book_rents (person_id, book_id, created_at, updated_at) VALUES 
    (1, 1, NOW(), NOW()),
    (1, 2, NOW(), NOW()),
    (1, 3, NOW(), NOW()),
    (1, 1, NOW(), NOW()),
    (1, 2, NOW(), NOW()),
    (1, 3, NOW(), NOW()),
    (2, 1, NOW(), NOW()),
    (2, 2, NOW(), NOW()),
    (2, 3, NOW(), NOW()),
    (2, 1, NOW(), NOW()),
    (2, 2, NOW(), NOW()),
    (2, 3, NOW(), NOW()),
    (2, 1, NOW(), NOW()),
    (3, 3, NOW(), NOW()),
    (3, 2, NOW(), NOW()),
    (3, 1, NOW(), NOW()),
    (3, 3, NOW(), NOW()),
    (3, 2, NOW(), NOW()),
    (3, 2, NOW(), NOW()),
    (3, 3, NOW(), NOW()),
    (3, 1, NOW(), NOW()),
    (3, 1, NOW(), NOW()),
    (4, 1, NOW(), NOW()),
    (4, 2, NOW(), NOW()),
    (4, 3, NOW(), NOW()),
    (4, 1, NOW(), NOW()),
    (4, 3, NOW(), NOW()),
    (4, 2, NOW(), NOW()),
    (4, 3, NOW(), NOW()),
    (4, 3, NOW(), NOW()),
    (5, 1, NOW(), NOW()),
    (5, 2, NOW(), NOW()),
    (5, 3, NOW(), NOW()),
    (6, 1, NOW(), NOW()),
    (6, 2, NOW(), NOW()),
    (6, 3, NOW(), NOW()),
    (7, 1, NOW(), NOW()),
    (7, 2, NOW(), NOW()),
    (7, 3, NOW(), NOW()),
    (8, 1, NOW(), NOW()),
    (8, 2, NOW(), NOW()),
    (8, 3, NOW(), NOW()),
    (9, 1, NOW(), NOW()),
    (9, 2, NOW(), NOW()),
    (9, 3, NOW(), NOW());

INSERT INTO countries (id, country_name, country_code) VALUES 
    (1, 'Singapore', 'SG'),
    (2, 'Malaysia', 'MY'),
    (3, 'United States of America', 'US'),
    (4, 'Japan', 'JPN');
