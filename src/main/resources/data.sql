insert into CUSTOMERS (CUSTOMER_NAME, EMAIL, PASSWORD) VALUES ('Bruce', 'bruce@a.com', 'PASSWORD');
insert into CUSTOMERS (CUSTOMER_NAME, EMAIL, PASSWORD) VALUES ('Paul', 'paul@b.com', 'password');
insert into CUSTOMERS (CUSTOMER_NAME, EMAIL, PASSWORD) VALUES ('Rick', 'rick@c.com', 'password');

-- Sample Events
insert into EVENTS (EVENT_NAME, DESCRIPTION, EVENT_DATE, LOCATION, MAX_CAPACITY, CURRENT_REGISTRATIONS) VALUES ('Spring Boot Workshop', 'Learn the fundamentals of Spring Boot development', '2025-11-15', 'Conference Room A', 50, 0);
insert into EVENTS (EVENT_NAME, DESCRIPTION, EVENT_DATE, LOCATION, MAX_CAPACITY, CURRENT_REGISTRATIONS) VALUES ('React Masterclass', 'Advanced React techniques and best practices', '2025-11-20', 'Tech Hub Auditorium', 75, 0);
insert into EVENTS (EVENT_NAME, DESCRIPTION, EVENT_DATE, LOCATION, MAX_CAPACITY, CURRENT_REGISTRATIONS) VALUES ('Microservices Architecture', 'Design and implement microservices using Spring Cloud', '2025-11-25', 'Virtual Event', 100, 0);
insert into EVENTS (EVENT_NAME, DESCRIPTION, EVENT_DATE, LOCATION, MAX_CAPACITY, CURRENT_REGISTRATIONS) VALUES ('DevOps with Docker', 'Containerization and deployment strategies', '2025-12-01', 'Conference Room B', 40, 0);
insert into EVENTS (EVENT_NAME, DESCRIPTION, EVENT_DATE, LOCATION, MAX_CAPACITY, CURRENT_REGISTRATIONS) VALUES ('Database Design Fundamentals', 'SQL and NoSQL database design principles', '2025-12-05', 'Learning Center', 60, 0);

-- Sample Registrations
insert into REGISTRATIONS (CUSTOMER_ID, EVENT_ID, REGISTRATION_DATE, STATUS) VALUES (1, 1, '2025-10-20', 'ACTIVE');
insert into REGISTRATIONS (CUSTOMER_ID, EVENT_ID, REGISTRATION_DATE, STATUS) VALUES (1, 3, '2025-10-20', 'ACTIVE');
insert into REGISTRATIONS (CUSTOMER_ID, EVENT_ID, REGISTRATION_DATE, STATUS) VALUES (2, 2, '2025-10-20', 'ACTIVE');
insert into REGISTRATIONS (CUSTOMER_ID, EVENT_ID, REGISTRATION_DATE, STATUS) VALUES (3, 1, '2025-10-20', 'ACTIVE');
insert into REGISTRATIONS (CUSTOMER_ID, EVENT_ID, REGISTRATION_DATE, STATUS) VALUES (3, 4, '2025-10-20', 'ACTIVE');

