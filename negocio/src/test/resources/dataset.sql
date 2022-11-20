INSERT INTO cliente VALUES
                        ("1223", "g+L0X/jC0XzDOli7UAY1oJytbqSzN/TeouaACEbw/5MYfq7Ltx8pBH8nwC/4tz8C", "dictum.phasellus@aol.org", "INACTIVO", "ruta foto 1", "Oren Ingram",   null,        "344990", null),
                        ("1235", "Ogj7nzD9LQ7sNN34L2iQGkcmBgmw28G1WKkKVrCi7ZVoNWiCZnwOCTniUEV91s1p", "diam.proin@google.couk",   "ACTIVO",   "https://memeguy.com/photos/images/just-some-random-profile-photo-on-okcupid-136668.png", "Ian Horn",      null,        null,     "7556"),
                        ("2345", "g+L0X/jC0XzDOli7UAY1oJytbqSzN/TeouaACEbw/5MYfq7Ltx8pBH8nwC/4tz8C", "nisi@icloud.ca",           "INACTIVO", "https://www.ourteennetwork.com/cacheimagenes/px750-278415131.jpg", "Ivor Randolph", null,        "33322",  "7665"),
                        ("9876", "g+L0X/jC0XzDOli7UAY1oJytbqSzN/TeouaACEbw/5MYfq7Ltx8pBH8nwC/4tz8C", "dignissim@google.edu",     "INACTIVO", "https://i.pinimg.com/originals/d8/88/f8/d888f8242970ff9ac5ab7e8dcc3ab824.jpg", "Kirsten Reese", "1980-02-28", null,    null),
                        ("3344", "Ogj7nzD9LQ7sNN34L2iQGkcmBgmw28G1WKkKVrCi7ZVoNWiCZnwOCTniUEV91s1p", "curabitur@google.couk",    "ACTIVO",   "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500", "Reagan Romero", "1991-06-27", null,    null);

INSERT INTO ciudad VALUES
                       (1, "Quindio", "Armenia", null),
                       (2, "Risaralda", "Pereira", null),
                       (3, "Caldas", "Manizales", null),
                       (4, "Cundinamarca", "Bogota", null),
                       (5, "Antioquia", "Medellin", null),
                       (6, "Valle del Cauca", "Cali", null),
                       (7, "Antioquia", "Armenia", null);

INSERT INTO administrador_teatro VALUES
                        ("2454", "g+L0X/jC0XzDOli7UAY1oJytbqSzN/TeouaACEbw/5MYfq7Ltx8pBH8nwC/4tz8C", "virginiaingram@outlook.couk", "ACTIVO",   "ruta foto 1", "Virginia Ingram", 1),
                        ("3346", "Ogj7nzD9LQ7sNN34L2iQGkcmBgmw28G1WKkKVrCi7ZVoNWiCZnwOCTniUEV91s1p", "lewisgood@outlook.ca",        "ACTIVO",   "ruta foto 2", "Lewis Good",      2),
                        ("9503", "Ogj7nzD9LQ7sNN34L2iQGkcmBgmw28G1WKkKVrCi7ZVoNWiCZnwOCTniUEV91s1p", "daceystanley@hotmail.net",    "INACTIVO", "ruta foto 3", "Dacey Stanley",   3),
                        ("4508", "g+L0X/jC0XzDOli7UAY1oJytbqSzN/TeouaACEbw/5MYfq7Ltx8pBH8nwC/4tz8C", "samu@edmodo.edu.co",          "ACTIVO",   "ruta foto 4", "Samuel García",   4),
                        ("7621", "Ogj7nzD9LQ7sNN34L2iQGkcmBgmw28G1WKkKVrCi7ZVoNWiCZnwOCTniUEV91s1p", "feli@ensq.edu.co",            "INACTIVO", "ruta foto 5", "Felipe Sanchez",  5);

INSERT INTO teatro VALUES
                       (1, "Calle 3 #5",                     "https://www.money.com.bo/wp-content/uploads/2020/09/cine.jpg", "Multiplex Unicentro",   "2454", 1),
                       (2, "Carrera 22 #33-12",              "https://img.ecartelera.com/noticias/42700/42728-m.jpg",        "Cineplaza",             "3346", 2),
                       (3, "Carrera 23 - 12",                "https://www.oregonlive.com/resizer/HTL5G5X0T978-f6X979beLAazbI=/1280x0/smart/advancelocal-adapter-image-uploads.s3.amazonaws.com/image.oregonlive.com/home/olive-media/width2048/img/ent_impact_tvfilm/photo/cinema-21-marquee-61a584c883868fbd.jpg", "Multicines AstorPlaza", "7621", 3),
                       (4, "Al lado del Ara de Las Acacias", "https://img.ecartelera.com/noticias/42700/42728-m.jpg",        "Portal del Circaso",    "9503", 5),
                       (5, "Sidney, Bogotá Cr 15 - 19",      "https://www.money.com.bo/wp-content/uploads/2020/09/cine.jpg", "Arboleda Theaters",     "4508", 4);

INSERT INTO sala VALUES
                    -- id, # sillas, columnas, filas, numero,  tipo,   teatro
                       (1,     70,      10,      7,     2,   "SALA_2D", 1),
                       (2,     80,      10,      8,     3,   "SALA_3D", 1),
                       (3,     150,     15,     10,     1,   "SALA_XD", 2),
                       (4,     60,       6,     10,     1,   "SALA_2D", 2),
                       (5,     90,       9,     10,     3,   "SALA_XD", 1);

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
                         (1, "ESTRENO",           "https://ogre.natalie.mu/media/news/eiga/2022/0608/onepiecefilmred_poster202206.jpg?imwidth=750&imdensity=1",                                                   "One Piece Film: Red",  "Doblaje: Mireya Mendoz, Azul Valadez, Dafnis Fernández, Georgina Sánchez, Alejandro Orozco, Nallely Solís, Raúl Anaya, Kerygma Flores", "Uta, la cantante número uno del mundo, se dispone a dar su primer concierto en directo frente a un público formado por piratas, marines y toda clase de fans. Uta está considerada la cantante más querida de todo el mundo. A pesar de que siempre ha ocultado su identidad, se dice que su voz al cantar es tan maravillosa que parece proceder de “otra dimensión”. Ahora, se celebrará un concierto en directo en el que aparecerá en persona por primera vez frente al público. La tan esperada voz que todo el mundo quiere oír se dispone a resonar mientras multitud de coloridos piratas, marines que no le quitan el ojo de encima, los Piratas de Sombreros de Paja de Luffy, quien se siente atraído por la voz de Uta sin saber nada, y toda clase de fans de Uta llenan el lugar. La historia arranca con la impactante revelación de que Uta es la hija de Shanks.",           "https://www.youtube-nocookie.com/embed/kE8lM3tAeGg"),
                         (2, "PREVENTA",          "http://www.cinepsis.fr/wp-content/uploads/2014/11/fast-furious-poster-big-new-fast-furious-7-poster-brings-the-awesome-previous-6-posters-bring-the-sad.jpeg", "Fast and Furious 10",  "Vin Diesel, Michelle Roddriguez, Tyrese Gibson, Charlize Theron, Jason Momoa, Jason Statham, Brie Larson",                              "Todavía se desconocen los detalles de la trama o la sinopsis de la décima película de la saga y, sinceramente, cuesta adivinarlas. La franquicia ha apostado siempre por el cuanto más mejor y las últimas entregas han sido una espectacular locura, casi tanta como la estupidez que sería intentar adivinar por donde pueden ir los tiros. Podríamos ver a Dom en el espacio saltando con un tanque desde un satélite a otro y no nos extrañaría. Sin embargo, tratándose del final de la saga entendemos que se abordarán viejas heridas, y que los hijos de Dom y Brian tendrá mucha importancia, más que nada por dejar plantadas las semillas para una posible nueva generación.",     "https://www.youtube.com/embed/inW3uM3j5G4"),
                         (3, "ESTRENO",           "https://image.tmdb.org/t/p/original/9l2kF2lTcLb4LtSMh5MU0dZEjQK.jpg",                                                                                          "Taxi Driver",          "Robert De Niro, Jodie Foster, Albert Brooks, Harvey Keitel, Cybill Shepherd",                                                           "La película narra la historia de Travis Bickle (Robert De Niro, 'Luces rojas') un ex militar que había combatido en la guerra de Vietnam. Ese hecho le provoca ansiedad y depresión haciéndole sentir rechazado socialmente. Al no poder conciliar el sueño decide trabajar como taxista por los barrios de Nueva York durante las noches. Brickle se siente atraido por Betsy (Cybill Shepherd, ''The Client List''), una bella mujer que trabaja muy de cerca con el senador Charles Pallantine, aspirante a presidente. Betsy le concede una cita para ir al cine pero al descubrir la aficción que Travis tiene por la pornografía, decide no volver a verle. Aturdido y asqueado por la delincuendia que ve por la noche, decide comprarse un arma y pasar a la acción, limpiando las calles de maleantes. Su primer objetivo es el gobernador al que intentará asesinar en plena campaña de elecciones.",   "https://www.youtube.com/embed/T5IligQP7Fo"),
                         (4, "ULTIMAS_FUNCIONES", "https://wallpapercave.com/wp/wp7245952.jpg",                                                                                                                   "The Batman",           "Robert Pattinson, Zoë Kravitz, Paul Dano, Jeffrey Wright, John Turturro, Colin Farrell, Jayme Lawson, Andy Serkis",                     "Dos años de acechar las calles como Batman (Robert Pattinson), infundiendo miedo en los corazones de los criminales, han llevado a Bruce Wayne a las profundidades de las sombras de Gotham City. Con solo unos pocos aliados de confianza: Alfred Pennyworth (Andy Serkis) y el teniente James Gordon (Jeffrey Wright). Ante la red corrupta de funcionarios y figuras de alto perfil de la ciudad, el vigilante se ha establecido como la única encarnación de la venganza entre sus compañeros ciudadanos.",    "https://www.youtube.com/embed/fWQrd6cwJ0A" ),
                         (5, "PREVENTA",          "https://image.tmdb.org/t/p/original/vuza0WqY239yBXOadKlGwJsZJFE.jpg",                                                                                          "The Truman Show",      "Jim Carrey, Laura Linney, Ed Harris, Paul Giamatti, Natascha McElhone, Noah Emmerich, Tony Todd, Nick Krause, Philip Baker Hall",       "Truman Burbank (Him Carrey), un hombre risueño que cree tener la vida perfecta, es, sin saberlo, el protagonista de un programa de televisión seguido por millones de espectadores. Las cámaras siempre han estado ahí, escondidas, al acecho... Desde sus primeros pasos, pasando por la caída de su primer diente, hasta su primer amor. Los escenarios, las personas que conoce, hasta su esposa Meryl (Laura Linney) y su mejor amigo, Marlon (Noah Emmerich), son productos de la ficción.",                  "https://www.youtube.com/embed/dlnmQbPGuls"),
                         (6, "PREVENTA",          "https://www.sonypictures.com.mx/sites/mexico/files/2022-08/LLC_1400x2100.jpg",                                                                                 "Lilo Lilo Cocodrilo",  "Actores y un cocodrilo",          "Un cocodrilo chistoso quiere cumplir su sueño de ser cantante", "https://www.youtube.com/embed/JZckRZLzmU4");

INSERT INTO pelicula_generos VALUES
                         (1, 5),
                         (2, 3),
                         (3, 3),
                         (4, 3),
                         (5, 8),
                         (6, 6),
                         (6, 8);

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
                    --  id,   tipo,      horario, pelicula,  sala
                        (1, "FUNCION_3D",    2,       1,      2),
                        (2, "FUNCION_2D",    8,       3,      4),
                        (3, "FUNCION_XD",    6,       2,      3),
                        (4, "FUNCION_3D",    7,       5,      5),
                        (5, "FUNCION_XD",    1,       4,      1),
                        (6, "FUNCION_3D",    6,       6,      2),
                        (7, "FUNCION_XD",    8,       2,      5);

INSERT INTO cupon VALUES
                      (1, "BIENVENIDA",     "Cupon de bienvenida",                        "2022-10-05", "Hola Mundo",      15),
                      (2, "CUMPLEANOS",     "Cupon de regalo de cumpleaños",              "2023-01-03", "H-Bday",          15),
                      (3, "PRIMERA_COMPRA", "Cupon de primera compra en Unicine",         "2022-12-06", "Agosto venteado", 10),
                      (4, "HALLOWEEN",      "Cupon de celebracion halloween",             "2023-02-25", "Halo",            20),
                      (5, "NAVIDAD",        "Cupon para la familia uniciner en Navidad",  "2022-12-12", "Mry X-Mas",       25);

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
                    --  id, columna, fila, precio, precio base, compra, sala
                        (1,    3,     4,     0,       8000,        1,     2),
                        (2,    4,     4,     0,       8000,        1,     2),
                        (3,   10,     7,     0,       8000,        2,     1),
                        (4,    9,     7,     0,       8000,        2,     1),
                        (5,    6,     5,     0,       8000,        4,     5),
                        (6,    5,     2,     0,       8000,       null,   3),
                        (7,    4,     9,     0,       8000,        null,   4);

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




