--Baudelio Perez
--Juan Rosales
--13 Nov 2020
--CSE 111 - 06L
-- Project (Phase #2 - SQL Script)

--01------------------------------------------------------------------------------------------------
INSERT INTO movies VALUES
('Toy Story 2', 1999, 'computer-animated comedy', 89.5, 'Helene Plotkin, Karen Robert Jackson', 
'Walt Disney Pictures, Pixar Animation Studios', 'United States', NULL,
'Tom Hanks, Tim Allen, Joan Cusack, Kelsey Grammer, Don Rickles, Jim Varney, 
Wallace Shawn, John Ratzenberger, Annie Potts, Estelle Harris, Wayne Knight, 
John Morris, Jodi Benson', NULL, NULL, NULL, 'English', '1999-11-24', 92, 'yes', 
'yes', NULL);

--02------------------------------------------------------------------------------------------------
INSERT INTO movies VALUES
('SOOS', 2021, 'warped internet-based comedy', 75, 'Paul Heyman, Kurt Angle', 
'WWE Entertainment Studios', 'United States', NULL,
'Baudelio Perez, Kofi Kingston, Xavier Woods, Big E', NULL, NULL, NULL, 'English', 
'2021-01-16', 92, 'yes', 'yes', NULL);

--03------------------------------------------------------------------------------------------------
INSERT INTO movies VALUES
('SOOS 2: King of EnenE', 2023, 'warped internet-based comedy', 80, 'Paul Heyman, Kurt Angle', 
'WWE Entertainment Studios', 'United States', NULL,
'Baudelio Perez, Kofi Kingston, Xavier Woods, Big E, John Cena', NULL, NULL, NULL, 'English', 
'2023-01-16', 92, 'yes', 'yes', NULL);

--04------------------------------------------------------------------------------------------------
INSERT INTO actors VALUES ('Kelsey Grammer', NULL, '1955-02-21', 
'St. Thomas, U.S. Virgin Islands', 'active', NULL);

--05-----------------------------------------------------------------------------------------------
INSERT INTO score(s_username, s_title, s_year, s_genre, s_score, s_studios, s_yourscore, s_rtscore, s_imdbscore)
VALUES (NULL, 'Toy Story 2', 1999, 'computer-animated comedy', 89.5, 'Walt Disney Pictures, Pixar Animation Studios', NULL, 100, 79);

--06------------------------------------------------------------------------------------------------
UPDATE actors 
SET a_title = 'Toy Story 4, Toy Story 3, Toy Story 2, Toy Story, Forrest Gump' 
WHERE a_actors = 'Tom Hanks';

--07------------------------------------------------------------------------------------------------
UPDATE actors
SET a_title = 'Toy Story 4, Toy Story 3, Toy Story 2, Toy Story'
WHERE 
    a_actors = 'Tim Allen' OR a_actors = 'Don Rickles' OR
    a_actors = 'Wallace Shawn' OR a_actors = 'John Ratzenberger';

--08------------------------------------------------------------------------------------------------
UPDATE actors
SET a_title = 'Toy Story 4, Toy Story 2, Toy Story' WHERE a_actors = 'Annie Potts';

--09------------------------------------------------------------------------------------------------
UPDATE actors SET a_title = 'Toy Story 2, Jurassic Park' WHERE a_actors = 'Wayne Knight';

--10------------------------------------------------------------------------------------------------
UPDATE actors SET a_title = 'Toy Story 2' WHERE a_actors = 'Kelsey Grammer';

--11------------------------------------------------------------------------------------------------
UPDATE actors 
SET a_title = 'Toy Story 4, Toy Story 3, Toy Story 2, The Little Mermaid' 
WHERE a_actors = 'Jodi Benson';

--12-----------------------------------------------------------------------------------------------
UPDATE actors 
SET a_title = 'Toy Story 4, Toy Story 3, Toy Story 2' 
WHERE a_actors = 'Joan Cusack';

--13------------------------------------------------------------------------------------------------
INSERT INTO actors (a_actors, a_title, a_birthdate, a_birthloc, a_deathdate, a_deathloc)
VALUES ('Baudelio Perez', 'SOOS, SOOS 2: King of EnenE', 'XXXX-XX-XX', 
'XXXXXXX, CA, USA', 'active', NULL);

--14------------------------------------------------------------------------------------------------
INSERT INTO actors (a_actors, a_title, a_birthdate, a_birthloc, a_deathdate, a_deathloc)
VALUES ('Kofi Kingston', 'SOOS, SOOS 2: King of EnenE', '1981-08-14', 
'Kumasi, Ashanti, Ghana', 'active', NULL);

--15------------------------------------------------------------------------------------------------
INSERT INTO actors (a_actors, a_title, a_birthdate, a_birthloc, a_deathdate, a_deathloc)
VALUES ('Xavier Woods', 'SOOS, SOOS 2: King of EnenE', '1986-09-04', 'Columbus, GA, USA', 
'active', NULL);

--16------------------------------------------------------------------------------------------------
INSERT INTO actors (a_actors, a_title, a_birthdate, a_birthloc, a_deathdate, a_deathloc)
VALUES ('Big E', 'SOOS, SOOS 2: King of EnenE', '1986-03-01', 'Tampa, FL, USA', 'active', NULL);

--17------------------------------------------------------------------------------------------------
INSERT INTO actors (a_actors, a_title, a_birthdate, a_birthloc, a_deathdate, a_deathloc)
VALUES ('John Cena', 'SOOS 2: King of EnenE', '1977-04-23', 'West Newbury, MA, USA', 'active', NULL);

--18-------------------------------------------------------------------------------------------------
INSERT INTO purchases 
VALUES ('bperezSuS', 6, 990, 'Toy Story 4', 2019, 
'comedy-drama', NULL, 10, 'yes', 'credit');

--19-------------------------------------------------------------------------------------------------
UPDATE purchases
    SET p_acctbal = 990 WHERE p_username = 'bperezSuS';

--20-------------------------------------------------------------------------------------------------
UPDATE purchases
    SET p_numpurchase = 6 WHERE p_username = 'bperezSuS';

--21-------------------------------------------------------------------------------------------------
DELETE FROM wishlist
    where w_title = 'Toy Story 4' AND 
    w_username = 'bperezSuS';

--22------------------------------------------------------------------------------------------------
DELETE FROM actors 
WHERE a_actors = 'John Cena' OR a_actors = 'Kofi Kingston' OR a_actors = 'Baudelio Perez' OR
    a_actors = 'Big E' OR a_actors = 'Xavier Woods';

--23------------------------------------------------------------------------------------------------
DELETE FROM movies
WHERE m_title = 'SOOS' OR m_title = 'SOOS 2: King of EnenE';

--24------------------------------------------------------------------------------------------------
SELECT distinct *
FROM movies
ORDER BY m_score desc;

--25------------------------------------------------------------------------------------------------
SELECT m_title FROM movies
WHERE m_year < 2000;

--26------------------------------------------------------------------------------------------------
SELECT a_actors FROM actors 
WHERE a_deathdate <> NULL;

--27------------------------------------------------------------------------------------------------
SELECT distinct *
FROM movies
WHERE m_score >= 70
ORDER BY m_length desc;

--28------------------------------------------------------------------------------------------------
SELECT distinct u_password
FROM userAcc, purchases, movies
WHERE 
    u_username = p_username AND 
    p_title = m_title AND m_length >= 100;

--29------------------------------------------------------------------------------------------------
SELECT h_title
FROM hotlist, movies, watched 
WHERE 
    h_title = m_title AND m_title = x_title AND 
    x_username = 'bperezSuS';

--30------------------------------------------------------------------------------------------------
SELECT distinct m_title, s_rtscore, s_imdbscore, x_username
FROM score, movies, watched
WHERE 
    m_title = x_title AND x_title = s_title AND 
    s_rtscore > 80.0 AND s_imdbscore > 75.0;

--31------------------------------------------------------------------------------------------------
SELECT distinct m_title, s_rtscore, s_imdbscore, m_score
FROM score, movies, purchases
WHERE 
    m_title = s_title AND p_title = m_title AND
    p_username = 'bperezSuS';

--32------------------------------------------------------------------------------------------------
SELECT distinct m_title, m_studios, m_year, s_rtscore, s_imdbscore, m_score
FROM score, movies 
WHERE   
    m_title = s_title AND 
    m_studios like '%Walt Disney%'
UNION
SELECT distinct m_title, m_studios, m_year, s_rtscore, s_imdbscore, m_score
FROM score, movies 
WHERE   
    m_title = s_title AND 
    m_studios like '%Amblin%'
UNION
SELECT distinct m_title, m_studios, m_year, s_rtscore, s_imdbscore, m_score
FROM score, movies 
WHERE   
    m_title = s_title AND 
    m_studios like '%Marvel%'
ORDER BY m_score desc

--33------------------------------------------------------------------------------------------------
SELECT distinct m_title, s_rtscore, s_imdbscore, m_score
FROM score, movies 
WHERE   
    m_title = s_title AND m_actors like '%Robert Downey Jr.%'
UNION
SELECT distinct m_title, s_rtscore, s_imdbscore, m_score
FROM score, movies 
WHERE   
    m_title = s_title AND m_actors like '%Tom Hanks%'
ORDER BY m_score desc

--34------------------------------------------------------------------------------------------
SELECT distinct m_title
FROM movies
WHERE m_score = (SELECT min(m_score) FROM movies);

--35------------------------------------------------------------------------------------------
SELECT distinct m_title
FROM movies
WHERE m_length = (SELECT min(m_length) FROM movies);

--36-------------------------------------------------------------------------------------------
SELECT p_username as cheepythecheapskate
FROM purchases
WHERE p_paid = 'no';
