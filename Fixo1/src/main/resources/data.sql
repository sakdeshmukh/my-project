-- Sample data for Urban Company-like application

-- Insert Categories
INSERT INTO categories (category_name, description, created_on, updated_on) VALUES
('Appliance Repair', 'Home appliance repair services', NOW(), NOW()),
('Salon & Spa', 'Beauty and wellness services', NOW(), NOW()),
('Cleaning', 'Home and office cleaning services', NOW(), NOW()),
('Plumbing', 'Plumbing and water system services', NOW(), NOW()),
('Electrical', 'Electrical repair and installation services', NOW(), NOW());

-- Insert Services
INSERT INTO services (service_name, base_price, description, category_id, created_on, updated_on) VALUES
('AC Repair', 500.00, 'Air conditioner repair and maintenance', 1, NOW(), NOW()),
('Refrigerator Repair', 400.00, 'Refrigerator repair and maintenance', 1, NOW(), NOW()),
('Haircut & Styling', 300.00, 'Professional haircut and styling', 2, NOW(), NOW()),
('Facial Treatment', 800.00, 'Rejuvenating facial treatment', 2, NOW(), NOW()),
('Deep House Cleaning', 1200.00, 'Complete house deep cleaning', 3, NOW(), NOW()),
('Regular Cleaning', 600.00, 'Regular house cleaning service', 3, NOW(), NOW()),
('Pipe Repair', 350.00, 'Plumbing pipe repair and maintenance', 4, NOW(), NOW()),
('Tap Installation', 250.00, 'New tap and faucet installation', 4, NOW(), NOW()),
('Wiring Installation', 800.00, 'Electrical wiring installation', 5, NOW(), NOW()),
('Switch & Socket Repair', 200.00, 'Electrical switch and socket repair', 5, NOW(), NOW());

-- Insert Admin User
INSERT INTO users (email, password, phone_no, user_role, created_on, updated_on) VALUES
('admin@fixo.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', '9876543210', 'ADMIN', NOW(), NOW());

-- Insert Sample Customers
INSERT INTO users (email, password, phone_no, user_role, created_on, updated_on) VALUES
('john.doe@email.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', '9876543211', 'CUSTOMER', NOW(), NOW()),
('jane.smith@email.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', '9876543212', 'CUSTOMER', NOW(), NOW()),
('mike.wilson@email.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', '9876543213', 'CUSTOMER', NOW(), NOW());

INSERT INTO customers (first_name, last_name, address, user_id, created_on, updated_on) VALUES
('John', 'Doe', '123 Main Street, Downtown, City', 2, NOW(), NOW()),
('Jane', 'Smith', '456 Oak Avenue, Suburb, City', 3, NOW(), NOW()),
('Mike', 'Wilson', '789 Pine Road, Uptown, City', 4, NOW(), NOW());

-- Insert Sample Service Providers
INSERT INTO users (email, password, phone_no, user_role, created_on, updated_on) VALUES
('ac.expert@fixo.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', '9876543221', 'SERVICEPROVIDER', NOW(), NOW()),
('beauty.salon@fixo.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', '9876543222', 'SERVICEPROVIDER', NOW(), NOW()),
('clean.pro@fixo.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', '9876543223', 'SERVICEPROVIDER', NOW(), NOW()),
('plumber.pro@fixo.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', '9876543224', 'SERVICEPROVIDER', NOW(), NOW()),
('electrician.pro@fixo.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', '9876543225', 'SERVICEPROVIDER', NOW(), NOW());

INSERT INTO service_providers (service_provider_name, business_address, service_category, license_number, business_description, rating, is_verified, years_of_exp, service_id, user_id, created_on, updated_on) VALUES
('AC Expert Services', '123 Service Lane, Industrial Area', 'Appliance Repair', 'AC001', 'Professional AC repair and maintenance services with 5+ years experience', 4, true, 5, 1, 5, NOW(), NOW()),
('Beauty Paradise Salon', '456 Beauty Street, Mall Road', 'Salon & Spa', 'SP001', 'Premium beauty and wellness services with certified professionals', 5, true, 8, 3, 6, NOW(), NOW()),
('Clean Pro Services', '789 Clean Avenue, Business District', 'Cleaning', 'CL001', 'Professional cleaning services for homes and offices', 4, true, 6, 5, 7, NOW(), NOW()),
('Plumber Pro', '321 Pipe Street, Industrial Area', 'Plumbing', 'PL001', 'Expert plumbing services with guaranteed work', 4, true, 7, 7, 8, NOW(), NOW()),
('Electric Solutions', '654 Wire Road, Tech Park', 'Electrical', 'EL001', 'Licensed electrical services for all types of installations', 5, true, 10, 9, 9, NOW(), NOW()); 