INSERT INTO cliente VALUES
                        ("1223", "g+L0X/jC0XzDOli7UAY1oJytbqSzN/TeouaACEbw/5MYfq7Ltx8pBH8nwC/4tz8C", "dictum.phasellus@aol.org", "INACTIVO", "ruta foto 1", "Oren Ingram",   null,        "344990", null),
                        ("1235", "Ogj7nzD9LQ7sNN34L2iQGkcmBgmw28G1WKkKVrCi7ZVoNWiCZnwOCTniUEV91s1p", "diam.proin@google.couk",   "ACTIVO",   "https://memeguy.com/photos/images/just-some-random-profile-photo-on-okcupid-136668.png", "Ian Horn",      null,        null,     "7556"),
                        ("2345", "g+L0X/jC0XzDOli7UAY1oJytbqSzN/TeouaACEbw/5MYfq7Ltx8pBH8nwC/4tz8C", "nisi@icloud.ca",           "INACTIVO", "https://www.ourteennetwork.com/cacheimagenes/px750-278415131.jpg", "Ivor Randolph", null,        "33322",  "7665"),
                        ("9876", "g+L0X/jC0XzDOli7UAY1oJytbqSzN/TeouaACEbw/5MYfq7Ltx8pBH8nwC/4tz8C", "dignissim@google.edu",     "INACTIVO", "https://i.pinimg.com/originals/d8/88/f8/d888f8242970ff9ac5ab7e8dcc3ab824.jpg", "Kirsten Reese", "1980-02-28", null,    null),
                        ("3344", "Ogj7nzD9LQ7sNN34L2iQGkcmBgmw28G1WKkKVrCi7ZVoNWiCZnwOCTniUEV91s1p", "curabitur@google.couk",    "ACTIVO",   "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500", "Reagan Romero", "1991-06-27", null,    null);

INSERT INTO ciudad VALUES
                       (1, "Quindio", "Armenia"),
                       (2, "Risaralda", "Pereira"),
                       (3, "Caldas", "Manizales"),
                       (4, "Cundinamarca", "Bogota"),
                       (5, "Antioquia", "Medellin"),
                       (6, "Valle del Cauca", "Cali"),
                       (7, "Antioquia", "Armenia");

INSERT INTO administrador_teatro VALUES
                        ("2454", "g+L0X/jC0XzDOli7UAY1oJytbqSzN/TeouaACEbw/5MYfq7Ltx8pBH8nwC/4tz8C", "virginiaingram@outlook.couk", "ACTIVO",   "ruta foto 1", "Virginia Ingram"),
                        ("3346", "Ogj7nzD9LQ7sNN34L2iQGkcmBgmw28G1WKkKVrCi7ZVoNWiCZnwOCTniUEV91s1p", "lewisgood@outlook.ca",        "ACTIVO",   "ruta foto 2", "Lewis Good"),
                        ("9503", "Ogj7nzD9LQ7sNN34L2iQGkcmBgmw28G1WKkKVrCi7ZVoNWiCZnwOCTniUEV91s1p", "daceystanley@hotmail.net",    "INACTIVO", "ruta foto 3", "Dacey Stanley"),
                        ("4508", "g+L0X/jC0XzDOli7UAY1oJytbqSzN/TeouaACEbw/5MYfq7Ltx8pBH8nwC/4tz8C", "samu@edmodo.edu.co",          "ACTIVO",   "ruta foto 4", "Samuel García"),
                        ("7621", "Ogj7nzD9LQ7sNN34L2iQGkcmBgmw28G1WKkKVrCi7ZVoNWiCZnwOCTniUEV91s1p", "feli@ensq.edu.co",            "INACTIVO", "ruta foto 5", "Felipe Sanchez");

INSERT INTO teatro VALUES
                       (1, "Calle 3 #5",                     "https://www.money.com.bo/wp-content/uploads/2020/09/cine.jpg", "Multiplex Unicentro",   "2454", 1),
                       (2, "Carrera 22 #33-12",              "https://img.ecartelera.com/noticias/42700/42728-m.jpg", "Cineplaza",             "3346", 2),
                       (3, "Carrera 23 - 12",                "https://www.oregonlive.com/resizer/HTL5G5X0T978-f6X979beLAazbI=/1280x0/smart/advancelocal-adapter-image-uploads.s3.amazonaws.com/image.oregonlive.com/home/olive-media/width2048/img/ent_impact_tvfilm/photo/cinema-21-marquee-61a584c883868fbd.jpg", "Multicines AstorPlaza", "7621", 3),
                       (4, "Al lado del Ara de Las Acacias", "https://img.ecartelera.com/noticias/42700/42728-m.jpg", "Portal del Circaso",    "9503", 5),
                       (5, "Sidney, Bogotá Cr 15 - 19",      "https://www.money.com.bo/wp-content/uploads/2020/09/cine.jpg", "Arboleda Theaters",     "4508", 4);

INSERT INTO sala VALUES
                    -- id, # sillas, tipo, teatro
                       (1,     70, "SALA_2D", 1),
                       (2,     80, "SALA_3D", 1),
                       (3,     65, "SALA_XD", 2),
                       (4,     60, "SALA_2D", 2),
                       (5,     90, "SALA_XD", 1);

 INSERT INTO genero VALUES
                        (1, "Terror"),
                        (2, "Romance"),
                        (3, "Accion"),
                        (4, "Ciencia Ficcion"),
                        (5, "Fantasia"),
                        (6, "Musical"),
                        (7, "Infantil"),
                        (8, "Comedia");

INSERT INTO pelicula VALUES
                         ("One Piece Film: Red", "ESTRENO",           "https://ogre.natalie.mu/media/news/eiga/2022/0608/onepiecefilmred_poster202206.jpg?imwidth=750&imdensity=1",                                                   "Actores de voz",   "Piratas",           "https://www.youtube-nocookie.com/embed/kE8lM3tAeGg"),
                         ("Fast and Furious 10", "PREVENTA",          "http://www.cinepsis.fr/wp-content/uploads/2014/11/fast-furious-poster-big-new-fast-furious-7-poster-brings-the-awesome-previous-6-posters-bring-the-sad.jpeg", "Actores",          "Carros y bala",     "https://www.youtube.com/embed/inW3uM3j5G4"),
                         ("Taxi Driver",         "ESTRENO",           "https://image.tmdb.org/t/p/original/9l2kF2lTcLb4LtSMh5MU0dZEjQK.jpg",                                                                                          "Actores",          "Taxi, accion",      "https://www.youtube.com/embed/T5IligQP7Fo"),
                         ("The Batman",          "ULTIMAS_FUNCIONES", "https://wallpapercave.com/wp/wp7245952.jpg",                                                                                                                   "Actores",          "Otra de Batman",    "https://www.youtube.com/embed/fWQrd6cwJ0A" ),
                         ("The Truman Show",     "PREVENTA",          "https://image.tmdb.org/t/p/original/vuza0WqY239yBXOadKlGwJsZJFE.jpg",                                                                                          "actores de voice", "Comedia Dramatica", "https://www.youtube.com/embed/dlnmQbPGuls"),
                         ("Lilo Lilo Cocodrilo", "PREVENTA",          "https://www.sonypictures.com.mx/sites/mexico/files/2022-08/LLC_1400x2100.jpg",                                                                                 "Actores",          "Cocodrilo chistoso", "https://www.youtube.com/embed/JZckRZLzmU4");

INSERT INTO pelicula_generos VALUES
                         ("One Piece Film: Red", 5),
                         ("Fast and Furious 10", 3),
                         ("Taxi Driver", 3),
                         ("The Batman", 3),
                         ("The Truman Show", 8),
                         ("Lilo Lilo Cocodrilo", 6),
                         ("Lilo Lilo Cocodrilo", 8);

INSERT INTO horario VALUES
                        (1, "2022-10-06", "14:00"),
                        (2, "2022-10-06", "16:00"),
                        (3, "2022-10-06", "18:00"),
                        (4, "2022-10-06", "20:00"),
                        (5, "2022-10-07", "14:00"),
                        (6, "2022-10-07", "16:00"),
                        (7, "2022-10-07", "18:00"),
                        (8, "2022-10-07", "20:00");

INSERT INTO funcion VALUES
                    --  id, precio,  tipo,      horario,     pelicula,           sala
                        (1, 15000, "FUNCION_3D",    2,     "One Piece Film: Red",  2),
                        (2, 17000, "FUNCION_2D",    8,     "Taxi Driver",          4),
                        (3, 20000, "FUNCION_XD",    6,     "Fast and Furious 10",    3),
                        (4, 12500, "FUNCION_3D",    7,     "The Truman Show",      5),
                        (5, 6000,  "FUNCION_XD",    1,     "The Batman",           1),
                        (6, 10000, "FUNCION_3D",    6,     "Lilo Lilo Cocodrilo",  2),
                        (7, 20000, "FUNCION_XD",    8,     "Fast and Furious 10",    5);

INSERT INTO cupon VALUES
                      (1, "BIENVENIDA", "Cupon de bienvenida",                       "2022-10-05", "Hola Mundo",      5000),
                      (2, "CUMPLEANOS", "Cupon de regalo de cumpleaños",             "2023-01-03", "H-Bday",          10000),
                      (3, "BIENVENIDA", "Cupon de bienvenida al mes de agosto",      "2022-12-06", "Agosto venteado", 50000),
                      (4, "HALLOWEEN",  "Cupon de celebracion halloween",            "2023-02-25", "Halo",            20000),
                      (5, "NAVIDAD",    "Cupon para la familia uniciner en Navidad", "2022-12-12", "Mry X-Mas",        1000);

INSERT INTO compra VALUES
                    -- id,       fecha,            medio de pago,   valor, cliente, cupon_cliente, funcion
                       (1, "2022-10-05 14:37:12", "NEQUI",           0.0,  "1235",     null,          2),
                       (2, "2022-10-05 18:54:30", "TARJETA_CREDITO", 0.0,  "2345",     null,          1),
                       (3, "2022-10-03 09:18:06", "TARJETA_DEBITO",  0.0,  "9876",     null,          2),
                       (4, "2022-10-04 16:20:06", "DAVIPLATA",       0.0,  "1235",     null,          3),
                       (5, "2022-10-05 11:05:17", "EFECTY",          0.0,  "2345",     null,          1);

INSERT INTO cupon_cliente VALUES
                           -- id,  estado,    cliente, compra, cupon
                              (1, "USADO",      "1235", 1,     1),
                              (2, "DISPONIBLE", "2345", null,  1),
                              (3, "USADO",      "9876", 3,     2),
                              (4, "VENCIDO",    "3344", 3,     4),
                              (5, "USADO",      "1223", 4,     5);

INSERT INTO entrada VALUES
                    --  id, columna, fila, compra, sala
                        (1,    3,     4,     1,     2),
                        (2,    4,     4,     1,     2),
                        (3,   10,     7,     2,     1),
                        (4,    9,     7,     2,     1),
                        (5,    6,     5,     4,     5),
                        (6,    5,     2,    null,   3),
                        (7,    4,     9,    null,   4);

INSERT INTO confiteria VALUES
                           (1, "Cafe helado",                                                "AGOTADO",    "https://okdiario.com/img/recetas/2016/10/25/cafe-helado-vietnamita.jpg", "Granizado de cafe",   50000, 3),
                           (2, "Gaseosa Postobon de tamarindo con sal y limon",              "DISPONIBLE", "https://www.quericony.com/wp-content/uploads/2022/04/DSC00102-scaled.jpg", "Tamarindo michelado",  6000, 6),
                           (3, "Brownie con azucar glass y fresas",                          "AGOTADO",    "https://iambaker.net/wp-content/uploads/2017/02/brownie-valentine-day.jpg", "Brownie especial",     5000, 47),
                           (4, "Perro caliente con aji, papas, salsas y salchicha Ranchera", "DISPONIBLE", "https://fastlife.fastshop.com.br/wp-content/uploads/2018/07/shutterstock_306116708.jpg", "HotDog de la night",  30000, 12),
                           (5, "Hamburguesa con papas",                                      "DISPONIBLE", "https://s-i.huffpost.com/gen/1130209/images/o-BURGERS-facebook.jpg", "Hamburguesa",         10000, 15);

INSERT INTO compra_confiteria VALUES
                               -- id, precio, unidades compradas, comestible, compra
                                  (1, 5000,           4,              3,         1),
                                  (2, 7500,          12,              3,         5),
                                  (3, 10000,          7,              2,         3),
                                  (4, 3000,           6,              4,         4),
                                  (5, 21000,          2,              5,         1);




