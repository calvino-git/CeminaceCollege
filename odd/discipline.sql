/*
 Navicat Premium Data Transfer

 Source Server         : CeminaceCollege
 Source Server Type    : SQLite
 Source Server Version : 3030001
 Source Schema         : main

 Target Server Type    : SQLite
 Target Server Version : 3030001
 File Encoding         : 65001

 Date: 03/11/2022 20:48:49
*/

PRAGMA foreign_keys = false;

-- ----------------------------
-- Table structure for discipline
-- ----------------------------
DROP TABLE IF EXISTS "discipline";
CREATE TABLE "discipline" (
  "ID" integer NOT NULL PRIMARY KEY AUTOINCREMENT,
  "CLASSE" integer NOT NULL,
  "MATIERE" integer NOT NULL,
  "ENSEIGNANT" varchar NOT NULL,
  "COEFFICIENT" integer NOT NULL,
  "ANNEE" integer NOT NULL DEFAULT 1,
  CONSTRAINT "FK_CLASSE_ID" FOREIGN KEY ("CLASSE") REFERENCES "CLASSE" ("ID") ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT "FK_MATIERE_ID" FOREIGN KEY ("MATIERE") REFERENCES "MATIERE" ("ID") ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT "FK_ANNEE_ID" FOREIGN KEY ("ANNEE") REFERENCES "annee_academique" ("ID") ON DELETE CASCADE ON UPDATE CASCADE
);

-- ----------------------------
-- Records of discipline
-- ----------------------------
BEGIN;
INSERT INTO "discipline" VALUES (29, 11, 9, 'prof', 1, 1);
INSERT INTO "discipline" VALUES (30, 8, 8, 'BONDE DAIZE', 1, 1);
INSERT INTO "discipline" VALUES (31, 8, 9, 'NGOLO ELODIE', 1, 1);
INSERT INTO "discipline" VALUES (34, 8, 13, 'GOMA SYLVAIN', 1, 1);
INSERT INTO "discipline" VALUES (35, 8, 3, 'NGOMA AUGUSTE', 1, 1);
INSERT INTO "discipline" VALUES (36, 8, 12, 'GOMA SYLVAIN', 1, 1);
INSERT INTO "discipline" VALUES (37, 8, 14, 'GOMA', 1, 1);
INSERT INTO "discipline" VALUES (40, 10, 12, 'GOMA SYLVAIN', 1, 1);
INSERT INTO "discipline" VALUES (41, 10, 13, 'GOMA SYLVAIN', 1, 1);
INSERT INTO "discipline" VALUES (42, 10, 1, 'Prof SVT', 1, 1);
INSERT INTO "discipline" VALUES (43, 10, 3, 'Prof MATH', 1, 1);
INSERT INTO "discipline" VALUES (44, 10, 9, 'Mr.BATELAMA', 1, 1);
INSERT INTO "discipline" VALUES (48, 10, 8, 'Prof HISTOIRE GEO', 1, 1);
INSERT INTO "discipline" VALUES (49, 10, 6, 'M.Teacher ', 1, 1);
INSERT INTO "discipline" VALUES (50, 10, 14, 'Prof ', 1, 1);
INSERT INTO "discipline" VALUES (51, 10, 5, 'Prof ', 1, 1);
INSERT INTO "discipline" VALUES (52, 11, 1, 'prof', 1, 1);
INSERT INTO "discipline" VALUES (53, 11, 3, 'prof', 1, 1);
INSERT INTO "discipline" VALUES (55, 11, 8, 'Prof HISTOIRE GEO', 1, 1);
INSERT INTO "discipline" VALUES (56, 11, 13, 'Mr.Lawani', 1, 1);
INSERT INTO "discipline" VALUES (57, 11, 5, 'Prof.Physique', 1, 1);
INSERT INTO "discipline" VALUES (58, 11, 6, 'PROF ANGLAIS', 1, 1);
INSERT INTO "discipline" VALUES (59, 11, 12, 'prof.FRANCAIS', 1, 1);
INSERT INTO "discipline" VALUES (61, 11, 14, 'Prof Français', 1, 1);
INSERT INTO "discipline" VALUES (62, 12, 9, 'PROF EPS', 1, 1);
INSERT INTO "discipline" VALUES (63, 12, 3, 'Prof MATH', 1, 1);
INSERT INTO "discipline" VALUES (64, 12, 12, 'Prof Français', 1, 1);
INSERT INTO "discipline" VALUES (65, 12, 8, 'Prof HISTOIRE GEO', 1, 1);
INSERT INTO "discipline" VALUES (66, 12, 5, 'prof', 1, 1);
INSERT INTO "discipline" VALUES (67, 12, 6, 'M.Teacher ', 1, 1);
INSERT INTO "discipline" VALUES (68, 12, 13, 'Prof Français', 1, 1);
INSERT INTO "discipline" VALUES (69, 12, 1, 'prof', 1, 1);
INSERT INTO "discipline" VALUES (70, 12, 14, 'prof', 1, 1);
INSERT INTO "discipline" VALUES (71, 12, 12, 'prof', 1, 1);
INSERT INTO "discipline" VALUES (72, 16, 12, 'prof', 2, 1);
INSERT INTO "discipline" VALUES (73, 16, 13, 'prof', 2, 1);
INSERT INTO "discipline" VALUES (74, 16, 6, 'prof', 2, 1);
INSERT INTO "discipline" VALUES (75, 16, 5, 'PROF', 2, 1);
INSERT INTO "discipline" VALUES (76, 16, 13, 'PROF', 2, 1);
INSERT INTO "discipline" VALUES (77, 16, 1, 'prof', 2, 1);
INSERT INTO "discipline" VALUES (78, 17, 5, 'prof', 2, 1);
INSERT INTO "discipline" VALUES (79, 17, 1, 'PROF SvT', 2, 1);
INSERT INTO "discipline" VALUES (80, 17, 12, 'PROF français', 2, 1);
INSERT INTO "discipline" VALUES (81, 17, 3, 'prof', 4, 1);
INSERT INTO "discipline" VALUES (82, 17, 13, 'PROF français', 2, 1);
INSERT INTO "discipline" VALUES (83, 17, 8, 'PROF HISTOIRE-GEO', 2, 1);
INSERT INTO "discipline" VALUES (84, 17, 6, 'PROF ANGLAIS', 2, 1);
INSERT INTO "discipline" VALUES (85, 16, 3, 'prof', 4, 1);
INSERT INTO "discipline" VALUES (86, 16, 8, 'prof', 2, 1);
INSERT INTO "discipline" VALUES (87, 15, 12, 'prof', 2, 1);
INSERT INTO "discipline" VALUES (88, 15, 1, 'prof', 2, 1);
INSERT INTO "discipline" VALUES (90, 15, 6, 'prof', 2, 1);
INSERT INTO "discipline" VALUES (92, 15, 3, 'PROF Math', 4, 1);
INSERT INTO "discipline" VALUES (93, 15, 5, 'PROF PHYSIQUE', 2, 1);
INSERT INTO "discipline" VALUES (94, 15, 8, 'PROF HISTOIRE-GEO', 2, 1);
INSERT INTO "discipline" VALUES (95, 15, 14, 'PROF français', 2, 1);
INSERT INTO "discipline" VALUES (96, 15, 13, 'PROF français', 2, 1);
INSERT INTO "discipline" VALUES (97, 14, 3, 'PROF ', 4, 1);
INSERT INTO "discipline" VALUES (98, 14, 6, 'prof', 2, 1);
INSERT INTO "discipline" VALUES (99, 14, 12, 'prof', 2, 1);
INSERT INTO "discipline" VALUES (100, 14, 13, 'prof', 2, 1);
INSERT INTO "discipline" VALUES (101, 14, 5, 'prof', 2, 1);
INSERT INTO "discipline" VALUES (102, 14, 1, 'prof', 2, 1);
INSERT INTO "discipline" VALUES (103, 14, 8, 'prof', 2, 1);
INSERT INTO "discipline" VALUES (104, 14, 14, 'prof', 2, 1);
INSERT INTO "discipline" VALUES (105, 13, 3, 'PROF ', 4, 1);
INSERT INTO "discipline" VALUES (106, 13, 1, 'prof', 2, 1);
INSERT INTO "discipline" VALUES (107, 13, 14, 'prof', 2, 1);
INSERT INTO "discipline" VALUES (108, 13, 8, 'PROF HISTOIRE-GEO', 2, 1);
INSERT INTO "discipline" VALUES (109, 13, 13, 'prof', 2, 1);
INSERT INTO "discipline" VALUES (110, 13, 6, 'prof', 2, 1);
INSERT INTO "discipline" VALUES (111, 13, 5, 'prof', 2, 1);
INSERT INTO "discipline" VALUES (112, 13, 12, 'prof', 2, 1);
INSERT INTO "discipline" VALUES (114, 8, 6, 'prof', 1, 1);
INSERT INTO "discipline" VALUES (116, 8, 1, 'prof', 1, 1);
INSERT INTO "discipline" VALUES (118, 8, 10, 'prof', 1, 1);
INSERT INTO "discipline" VALUES (120, 19, 6, 'BAYAMBOUDILA Verrack', 1, 2);
INSERT INTO "discipline" VALUES (121, 26, 8, 'BIAOUA Don Juste', 2, 2);
INSERT INTO "discipline" VALUES (122, 21, 3, 'NGOMA Auguste', 1, 2);
INSERT INTO "discipline" VALUES (123, 24, 1, 'TATY COSTODES J-Pierre', 2, 2);
INSERT INTO "discipline" VALUES (124, 19, 8, 'BONDE Daiz', 1, 2);
INSERT INTO "discipline" VALUES (125, 19, 3, 'N''GANGOYI Shostelle( ', 1, 2);
INSERT INTO "discipline" VALUES (126, 19, 5, 'BISSAMOU Césaire', 1, 2);
INSERT INTO "discipline" VALUES (127, 19, 1, 'TCHIKAYA Lenous', 1, 2);
INSERT INTO "discipline" VALUES (128, 19, 14, 'POATY N''KOUTA Jean-François', 1, 2);
INSERT INTO "discipline" VALUES (129, 19, 9, 'NGOLO Elodie', 1, 2);
INSERT INTO "discipline" VALUES (130, 19, 12, 'GOMA Sylvain', 1, 2);
INSERT INTO "discipline" VALUES (131, 19, 13, 'GOMA Sylvain', 1, 2);
INSERT INTO "discipline" VALUES (132, 20, 12, 'NZOUSSI Jean-Pierre', 1, 2);
INSERT INTO "discipline" VALUES (133, 20, 13, 'NZOUSSI Jean-Pierre', 1, 2);
INSERT INTO "discipline" VALUES (134, 20, 6, 'BONDE Daiz', 1, 2);
INSERT INTO "discipline" VALUES (135, 20, 8, 'SOUKISSA Salomon Salem', 1, 2);
INSERT INTO "discipline" VALUES (136, 20, 3, 'BISSAMOU Césaire', 1, 2);
INSERT INTO "discipline" VALUES (137, 20, 5, 'N''SILOULOU B. Paterne', 1, 2);
INSERT INTO "discipline" VALUES (138, 20, 1, 'MASSENGO  José', 1, 2);
INSERT INTO "discipline" VALUES (139, 20, 9, 'NGOLO Elodie', 1, 2);
INSERT INTO "discipline" VALUES (140, 20, 14, 'POATY N''KOUTA Jean-François', 1, 2);
INSERT INTO "discipline" VALUES (141, 21, 12, 'BATCHI Nicaise', 1, 2);
INSERT INTO "discipline" VALUES (143, 21, 6, 'BONDE Daiz', 1, 2);
INSERT INTO "discipline" VALUES (144, 21, 8, 'SOUKISSA Salomon Salem', 1, 2);
INSERT INTO "discipline" VALUES (145, 21, 5, 'N''SILOULOU B. Paterne', 1, 2);
INSERT INTO "discipline" VALUES (146, 21, 1, 'TATY COSTODES J-Pierre', 1, 2);
INSERT INTO "discipline" VALUES (147, 21, 14, 'POATY N''KOUTA Jean-François', 1, 2);
INSERT INTO "discipline" VALUES (148, 21, 9, 'NGOLO Elodie', 1, 2);
INSERT INTO "discipline" VALUES (149, 22, 12, 'MOULOUNGUI Patrick', 1, 2);
INSERT INTO "discipline" VALUES (150, 22, 13, 'MOULOUNGUI Patrick', 1, 2);
INSERT INTO "discipline" VALUES (151, 22, 6, 'NDEMBI  Flaubert', 1, 2);
INSERT INTO "discipline" VALUES (152, 22, 8, 'TCHIBOUANGA Jacques', 1, 2);
INSERT INTO "discipline" VALUES (153, 22, 3, 'EBATA  Guenel', 1, 2);
INSERT INTO "discipline" VALUES (154, 22, 5, 'BISSAMOU Césaire', 1, 2);
INSERT INTO "discipline" VALUES (155, 22, 1, 'TCHIKAYA Lenous', 1, 2);
INSERT INTO "discipline" VALUES (156, 22, 14, 'POATY N''KOUTA Jean-François', 1, 2);
INSERT INTO "discipline" VALUES (157, 22, 9, 'OSSIELE David', 1, 2);
INSERT INTO "discipline" VALUES (158, 23, 12, 'MOULOUNGUI Patrick', 1, 2);
INSERT INTO "discipline" VALUES (159, 23, 13, 'MOULOUNGUI Patrick', 1, 2);
INSERT INTO "discipline" VALUES (160, 23, 6, 'BAYAMBOUDILA Verrack', 1, 2);
INSERT INTO "discipline" VALUES (161, 23, 8, 'TCHIBOUANGA Jacques', 1, 2);
INSERT INTO "discipline" VALUES (162, 23, 3, 'EBATA  Guenel', 1, 2);
INSERT INTO "discipline" VALUES (163, 23, 5, 'BISSAMOU Césaire', 1, 2);
INSERT INTO "discipline" VALUES (164, 23, 1, 'MASSENGO  José', 1, 2);
INSERT INTO "discipline" VALUES (165, 23, 14, 'POATY N''KOUTA Jean-François', 1, 2);
INSERT INTO "discipline" VALUES (166, 23, 9, 'NGOLO Elodie', 1, 2);
INSERT INTO "discipline" VALUES (167, 21, 13, 'BATCHI Nicaise', 1, 2);
INSERT INTO "discipline" VALUES (168, 24, 12, 'NZOUSSI Jean-Pierre', 2, 2);
INSERT INTO "discipline" VALUES (169, 24, 13, 'NZOUSSI Jean-Pierre', 2, 2);
INSERT INTO "discipline" VALUES (170, 24, 6, 'NDEMBI  Flaubert', 2, 2);
INSERT INTO "discipline" VALUES (171, 24, 8, 'TCHIBOUANGA Jacques', 2, 2);
INSERT INTO "discipline" VALUES (172, 24, 3, 'EBATA  Guenel', 4, 2);
INSERT INTO "discipline" VALUES (173, 24, 5, 'BIBILA Armel', 2, 2);
INSERT INTO "discipline" VALUES (174, 24, 14, 'POATY N''KOUTA Jean-François', 1, 2);
INSERT INTO "discipline" VALUES (175, 24, 9, 'OSSIELE David', 2, 2);
INSERT INTO "discipline" VALUES (176, 25, 12, 'MOULOUNGUI Patrick', 2, 2);
INSERT INTO "discipline" VALUES (177, 25, 13, 'MOULOUNGUI Patrick', 2, 2);
INSERT INTO "discipline" VALUES (178, 25, 6, 'PANDI Packson', 2, 2);
INSERT INTO "discipline" VALUES (179, 25, 8, 'TCHIBOUANGA Jacques', 2, 2);
INSERT INTO "discipline" VALUES (180, 25, 3, 'NGOMA Auguste', 4, 2);
INSERT INTO "discipline" VALUES (181, 25, 5, 'BISSAMOU Césaire', 2, 2);
INSERT INTO "discipline" VALUES (182, 25, 1, 'TCHIKAYA Lenous', 2, 2);
INSERT INTO "discipline" VALUES (183, 25, 9, 'OSSIELE David', 2, 2);
INSERT INTO "discipline" VALUES (184, 25, 14, 'POATY N''KOUTA Jean-François', 1, 2);
INSERT INTO "discipline" VALUES (185, 26, 12, 'GOMA Sylvain', 2, 2);
INSERT INTO "discipline" VALUES (186, 26, 13, 'GOMA Sylvain', 2, 2);
INSERT INTO "discipline" VALUES (187, 26, 6, 'BONDE Daiz', 2, 2);
INSERT INTO "discipline" VALUES (188, 26, 3, 'NGOMA Auguste', 4, 2);
INSERT INTO "discipline" VALUES (189, 26, 5, 'BISSAMOU Césaire', 2, 2);
INSERT INTO "discipline" VALUES (190, 26, 1, 'TATY COSTODES J-Pierre', 2, 2);
INSERT INTO "discipline" VALUES (191, 26, 9, 'OSSIELE David', 2, 2);
INSERT INTO "discipline" VALUES (192, 27, 12, 'GOMA Sylvain', 2, 2);
INSERT INTO "discipline" VALUES (193, 27, 13, 'GOMA Sylvain', 2, 2);
INSERT INTO "discipline" VALUES (194, 27, 6, 'PANDI Packson', 2, 2);
INSERT INTO "discipline" VALUES (195, 27, 8, 'SOUKISSA Salomon Salem', 2, 2);
INSERT INTO "discipline" VALUES (196, 27, 3, 'NGOMA Auguste', 4, 2);
INSERT INTO "discipline" VALUES (197, 27, 5, 'BIBILA Armel', 2, 2);
INSERT INTO "discipline" VALUES (198, 27, 1, 'TCHIKAYA Lenous', 2, 2);
INSERT INTO "discipline" VALUES (199, 27, 9, 'OSSIELE David', 2, 2);
INSERT INTO "discipline" VALUES (200, 19, 10, 'SG', 1, 2);
INSERT INTO "discipline" VALUES (201, 20, 10, 'SG', 1, 2);
INSERT INTO "discipline" VALUES (202, 21, 10, 'SG', 1, 2);
INSERT INTO "discipline" VALUES (203, 22, 10, 'SG', 1, 2);
INSERT INTO "discipline" VALUES (204, 23, 10, 'SG', 1, 2);
INSERT INTO "discipline" VALUES (205, 21, 18, 'COORDO. ART', 1, 2);
INSERT INTO "discipline" VALUES (206, 22, 18, 'COORDO. ART', 1, 2);
INSERT INTO "discipline" VALUES (207, 23, 18, 'COORDO. ART', 1, 2);
INSERT INTO "discipline" VALUES (208, 24, 18, 'COORDO. ARTS', 1, 2);
INSERT INTO "discipline" VALUES (209, 24, 10, 'SG', 1, 2);
INSERT INTO "discipline" VALUES (210, 25, 18, 'COORDO. ARTS ', 1, 2);
INSERT INTO "discipline" VALUES (211, 25, 10, 'SG', 1, 2);
INSERT INTO "discipline" VALUES (212, 26, 10, 'SG', 1, 2);
INSERT INTO "discipline" VALUES (213, 27, 10, 'SG', 1, 2);
INSERT INTO "discipline" VALUES (214, 29, 6, 'BAYAMBOUDILA Verrack', 1, 3);
INSERT INTO "discipline" VALUES (215, 29, 8, 'BONDE Daiz', 1, 3);
INSERT INTO "discipline" VALUES (216, 29, 3, 'N''GANGOYI Shostelle( ', 1, 3);
INSERT INTO "discipline" VALUES (217, 29, 5, 'BISSAMOU Césaire', 1, 3);
INSERT INTO "discipline" VALUES (218, 29, 1, 'TCHIKAYA Lenous', 1, 3);
INSERT INTO "discipline" VALUES (219, 29, 14, 'POATY N''KOUTA Jean-François', 1, 3);
INSERT INTO "discipline" VALUES (220, 29, 9, 'NGOLO Elodie', 1, 3);
INSERT INTO "discipline" VALUES (221, 29, 12, 'GOMA Sylvain', 1, 3);
INSERT INTO "discipline" VALUES (222, 29, 13, 'GOMA Sylvain', 1, 3);
INSERT INTO "discipline" VALUES (223, 29, 10, 'SG', 1, 3);
INSERT INTO "discipline" VALUES (224, 30, 12, 'NZOUSSI Jean-Pierre', 1, 3);
INSERT INTO "discipline" VALUES (225, 30, 13, 'NZOUSSI Jean-Pierre', 1, 3);
INSERT INTO "discipline" VALUES (226, 30, 6, 'BONDE Daiz', 1, 3);
INSERT INTO "discipline" VALUES (227, 30, 8, 'SOUKISSA Salomon Salem', 1, 3);
INSERT INTO "discipline" VALUES (228, 30, 3, 'BISSAMOU Césaire', 1, 3);
INSERT INTO "discipline" VALUES (229, 30, 5, 'N''SILOULOU B. Paterne', 1, 3);
INSERT INTO "discipline" VALUES (230, 30, 1, 'MASSENGO  José', 1, 3);
INSERT INTO "discipline" VALUES (231, 30, 9, 'NGOLO Elodie', 1, 3);
INSERT INTO "discipline" VALUES (232, 30, 14, 'POATY N''KOUTA Jean-François', 1, 3);
INSERT INTO "discipline" VALUES (233, 30, 10, 'SG', 1, 3);
INSERT INTO "discipline" VALUES (234, 31, 3, 'NGOMA Auguste', 1, 3);
INSERT INTO "discipline" VALUES (235, 31, 12, 'BATCHI Nicaise', 1, 3);
INSERT INTO "discipline" VALUES (236, 31, 6, 'BONDE Daiz', 1, 3);
INSERT INTO "discipline" VALUES (237, 31, 8, 'SOUKISSA Salomon Salem', 1, 3);
INSERT INTO "discipline" VALUES (238, 31, 5, 'N''SILOULOU B. Paterne', 1, 3);
INSERT INTO "discipline" VALUES (239, 31, 1, 'TATY COSTODES J-Pierre', 1, 3);
INSERT INTO "discipline" VALUES (240, 31, 14, 'POATY N''KOUTA Jean-François', 1, 3);
INSERT INTO "discipline" VALUES (241, 31, 9, 'NGOLO Elodie', 1, 3);
INSERT INTO "discipline" VALUES (242, 31, 13, 'BATCHI Nicaise', 1, 3);
INSERT INTO "discipline" VALUES (243, 31, 10, 'SG', 1, 3);
INSERT INTO "discipline" VALUES (244, 31, 18, 'COORDO. ART', 1, 3);
INSERT INTO "discipline" VALUES (245, 32, 12, 'MOULOUNGUI Patrick', 1, 3);
INSERT INTO "discipline" VALUES (246, 32, 13, 'MOULOUNGUI Patrick', 1, 3);
INSERT INTO "discipline" VALUES (247, 32, 6, 'NDEMBI  Flaubert', 1, 3);
INSERT INTO "discipline" VALUES (248, 32, 8, 'TCHIBOUANGA Jacques', 1, 3);
INSERT INTO "discipline" VALUES (249, 32, 3, 'EBATA  Guenel', 1, 3);
INSERT INTO "discipline" VALUES (250, 32, 5, 'BISSAMOU Césaire', 1, 3);
INSERT INTO "discipline" VALUES (251, 32, 1, 'TCHIKAYA Lenous', 1, 3);
INSERT INTO "discipline" VALUES (252, 32, 14, 'POATY N''KOUTA Jean-François', 1, 3);
INSERT INTO "discipline" VALUES (253, 32, 9, 'OSSIELE David', 1, 3);
INSERT INTO "discipline" VALUES (254, 32, 10, 'SG', 1, 3);
INSERT INTO "discipline" VALUES (255, 32, 18, 'COORDO. ART', 1, 3);
INSERT INTO "discipline" VALUES (256, 33, 12, 'MOULOUNGUI Patrick', 1, 3);
INSERT INTO "discipline" VALUES (257, 33, 13, 'MOULOUNGUI Patrick', 1, 3);
INSERT INTO "discipline" VALUES (258, 33, 6, 'BAYAMBOUDILA Verrack', 1, 3);
INSERT INTO "discipline" VALUES (259, 33, 8, 'TCHIBOUANGA Jacques', 1, 3);
INSERT INTO "discipline" VALUES (260, 33, 3, 'EBATA  Guenel', 1, 3);
INSERT INTO "discipline" VALUES (261, 33, 5, 'BISSAMOU Césaire', 1, 3);
INSERT INTO "discipline" VALUES (262, 33, 1, 'MASSENGO  José', 1, 3);
INSERT INTO "discipline" VALUES (263, 33, 14, 'POATY N''KOUTA Jean-François', 1, 3);
INSERT INTO "discipline" VALUES (264, 33, 9, 'NGOLO Elodie', 1, 3);
INSERT INTO "discipline" VALUES (265, 33, 10, 'SG', 1, 3);
INSERT INTO "discipline" VALUES (266, 33, 18, 'COORDO. ART', 1, 3);
INSERT INTO "discipline" VALUES (267, 34, 1, 'TATY COSTODES J-Pierre', 2, 3);
INSERT INTO "discipline" VALUES (268, 34, 12, 'NZOUSSI Jean-Pierre', 2, 3);
INSERT INTO "discipline" VALUES (269, 34, 13, 'NZOUSSI Jean-Pierre', 2, 3);
INSERT INTO "discipline" VALUES (270, 34, 6, 'NDEMBI  Flaubert', 2, 3);
INSERT INTO "discipline" VALUES (271, 34, 8, 'TCHIBOUANGA Jacques', 2, 3);
INSERT INTO "discipline" VALUES (272, 34, 3, 'EBATA  Guenel', 4, 3);
INSERT INTO "discipline" VALUES (273, 34, 5, 'BIBILA Armel', 2, 3);
INSERT INTO "discipline" VALUES (274, 34, 14, 'POATY N''KOUTA Jean-François', 1, 3);
INSERT INTO "discipline" VALUES (275, 34, 9, 'OSSIELE David', 2, 3);
INSERT INTO "discipline" VALUES (276, 34, 18, 'COORDO. ARTS', 1, 3);
INSERT INTO "discipline" VALUES (277, 34, 10, 'SG', 1, 3);
INSERT INTO "discipline" VALUES (278, 35, 12, 'MOULOUNGUI Patrick', 2, 3);
INSERT INTO "discipline" VALUES (279, 35, 13, 'MOULOUNGUI Patrick', 2, 3);
INSERT INTO "discipline" VALUES (280, 35, 6, 'PANDI Packson', 2, 3);
INSERT INTO "discipline" VALUES (281, 35, 8, 'TCHIBOUANGA Jacques', 2, 3);
INSERT INTO "discipline" VALUES (282, 35, 3, 'NGOMA Auguste', 4, 3);
INSERT INTO "discipline" VALUES (283, 35, 5, 'BISSAMOU Césaire', 2, 3);
INSERT INTO "discipline" VALUES (284, 35, 1, 'TCHIKAYA Lenous', 2, 3);
INSERT INTO "discipline" VALUES (285, 35, 9, 'OSSIELE David', 2, 3);
INSERT INTO "discipline" VALUES (286, 35, 14, 'POATY N''KOUTA Jean-François', 1, 3);
INSERT INTO "discipline" VALUES (287, 35, 18, 'COORDO. ARTS ', 1, 3);
INSERT INTO "discipline" VALUES (288, 35, 10, 'SG', 1, 3);
INSERT INTO "discipline" VALUES (289, 36, 8, 'BIAOUA Don Juste', 2, 3);
INSERT INTO "discipline" VALUES (290, 36, 12, 'GOMA Sylvain', 2, 3);
INSERT INTO "discipline" VALUES (291, 36, 13, 'GOMA Sylvain', 2, 3);
INSERT INTO "discipline" VALUES (292, 36, 6, 'BONDE Daiz', 2, 3);
INSERT INTO "discipline" VALUES (293, 36, 3, 'NGOMA Auguste', 4, 3);
INSERT INTO "discipline" VALUES (294, 36, 5, 'BISSAMOU Césaire', 2, 3);
INSERT INTO "discipline" VALUES (295, 36, 1, 'TATY COSTODES J-Pierre', 2, 3);
INSERT INTO "discipline" VALUES (296, 36, 9, 'OSSIELE David', 2, 3);
INSERT INTO "discipline" VALUES (297, 36, 10, 'SG', 1, 3);
INSERT INTO "discipline" VALUES (298, 37, 12, 'GOMA Sylvain', 2, 3);
INSERT INTO "discipline" VALUES (299, 37, 13, 'GOMA Sylvain', 2, 3);
INSERT INTO "discipline" VALUES (300, 37, 6, 'PANDI Packson', 2, 3);
INSERT INTO "discipline" VALUES (301, 37, 8, 'SOUKISSA Salomon Salem', 2, 3);
INSERT INTO "discipline" VALUES (302, 37, 3, 'NGOMA Auguste', 4, 3);
INSERT INTO "discipline" VALUES (303, 37, 5, 'BIBILA Armel', 2, 3);
INSERT INTO "discipline" VALUES (304, 37, 1, 'TCHIKAYA Lenous', 2, 3);
INSERT INTO "discipline" VALUES (305, 37, 9, 'OSSIELE David', 2, 3);
INSERT INTO "discipline" VALUES (306, 37, 10, 'SG', 1, 3);
COMMIT;

-- ----------------------------
-- Auto increment value for discipline
-- ----------------------------
UPDATE "main"."sqlite_sequence" SET seq = 306 WHERE name = 'discipline';

-- ----------------------------
-- Indexes structure for table discipline
-- ----------------------------
CREATE INDEX "main"."idx_discipline_FK_CLASSE_ID"
ON "discipline" (
  "CLASSE" ASC
);
CREATE INDEX "main"."idx_discipline_FK_ENSEIGNANT_ID"
ON "discipline" (
  "ENSEIGNANT" ASC
);
CREATE INDEX "main"."idx_discipline_FK_MATIERE_ID"
ON "discipline" (
  "MATIERE" ASC
);

PRAGMA foreign_keys = true;
