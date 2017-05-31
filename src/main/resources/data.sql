INSERT INTO Categories (Name) VALUES
  ('Mlodzik'),
  ('Mlodziczka'),
  ('Kadet'),
  ('Kadetka');

INSERT INTO teams (id, Coach, Name, Phone, TotalSeasonPoints, Category_ID, League_ID, Team_ID) VALUES (1, 'Pomykalski', 'Proxima Kraków', 6564513515, 0, 'Kadetka', NULL, NULL);
INSERT INTO teams (id, Coach, Name, Phone, TotalSeasonPoints, Category_ID, League_ID, Team_ID) VALUES (2, 'Pomykalski', 'Proxima Kraków', 541684351, 0, 'Mlodziczka', NULL, NULL);
INSERT INTO teams (id, Coach, Name, Phone, TotalSeasonPoints, Category_ID, League_ID, Team_ID) VALUES (3, 'Gebera Marian', 'Wawel Kraków', 4535435210, 0, 'Mlodzik', NULL, NULL);
INSERT INTO teams (id, Coach, Name, Phone, TotalSeasonPoints, Category_ID, League_ID, Team_ID) VALUES (4, 'Stereńczak Dariusz', 'LKS Bobowa', 5463251354, 0, 'Mlodzik', NULL, NULL);
INSERT INTO teams (id, Coach, Name, Phone, TotalSeasonPoints, Category_ID, League_ID, Team_ID) VALUES (5, 'Nurzyński S', 'Sparta Kraków', 5465351321, 0, 'Mlodzik', NULL, NULL);

INSERT INTO addresses(id, CityName, StreetName, HallName) VALUES (1, 'Miasto', 'Ulica', 'Hala');
INSERT INTO tourneys (id, Name, Date, Category_ID, Address_ID) VALUES (1, 'Turniej', '2017-06-13 00:00:00', 'Mlodzik', 1);