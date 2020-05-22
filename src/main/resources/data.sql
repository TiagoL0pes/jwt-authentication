-- Insert Roles
INSERT INTO roles(role_id, description)
	SELECT * FROM (SELECT 1, 'ADMIN') AS value
		WHERE NOT EXISTS (
			SELECT * FROM roles WHERE description LIKE '%ADMIN%'
		) LIMIT 1;
		
INSERT INTO roles(role_id, description)
	SELECT * FROM (SELECT 2, 'USER') AS value
		WHERE NOT EXISTS (
			SELECT * FROM roles WHERE description LIKE '%USER%'
		) LIMIT 1;

-- Insert Authorities		
INSERT INTO authorities(authority_id, description)
	SELECT * FROM (SELECT 1, 'ADD_USER') AS value
		WHERE NOT EXISTS (
			SELECT * FROM authorities WHERE description LIKE '%ADD_USER%'
		) LIMIT 1;
		
INSERT INTO authorities(authority_id, description)
	SELECT * FROM (SELECT 2, 'DETAIL_USER') AS value
		WHERE NOT EXISTS (
			SELECT * FROM authorities WHERE description LIKE '%DETAIL_USER%'
		) LIMIT 1;
		
INSERT INTO authorities(authority_id, description)
	SELECT * FROM (SELECT 3, 'LIST_USERS') AS value
		WHERE NOT EXISTS (
			SELECT * FROM authorities WHERE description LIKE '%LIST_USERS%'
		) LIMIT 1;
		
INSERT INTO authorities(authority_id, description)
	SELECT * FROM (SELECT 4, 'UPDATE_USER') AS value
		WHERE NOT EXISTS (
			SELECT * FROM authorities WHERE description LIKE '%UPDATE_USER%'
		) LIMIT 1;
		
INSERT INTO authorities(authority_id, description)
	SELECT * FROM (SELECT 5, 'DELETE_USER') AS value
		WHERE NOT EXISTS (
			SELECT * FROM authorities WHERE description LIKE '%DELETE_USER%'
		) LIMIT 1;

-- Insert Users
INSERT INTO users(user_id, email, password, enabled, account_non_expired, account_non_locked, credentials_non_expired)
	SELECT * FROM (
		SELECT 
			1 AS user_id, 
			'admin@email.com' AS email, 
			'$2a$10$pcyxW/BjRaHDIWQlhT1BzOthFtY3Qe./wNchnFzRh/eoc7ifXlCM2' AS password, 
			1 AS enabled, 
			1 AS account_non_expired, 
			1 AS account_non_locked, 
			1 AS credentials_non_expired
		) AS value
		WHERE NOT EXISTS (
			SELECT * FROM users WHERE email LIKE '%admin@email.com%'
		) LIMIT 1;

-- Insert Roles To User
INSERT INTO user_role(user_id, role_id)
	SELECT * FROM (SELECT 1 AS user_id, 1 AS role_id) AS value
		WHERE NOT EXISTS (
			SELECT * FROM user_role WHERE user_id = 1 AND role_id = 1
		) LIMIT 1;

-- Insert Authorities To User		
INSERT INTO user_authority(user_id, authority_id)
	SELECT * FROM (SELECT 1 AS user_id, 1 AS authority_id) AS value
		WHERE NOT EXISTS (
			SELECT * FROM user_authority WHERE user_id = 1 AND authority_id = 1
		) LIMIT 1;
		