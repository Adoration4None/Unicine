INSERT INTO cliente VALUES
                        ("1223", "a123", "dictum.phasellus@aol.org", "INACTIVO", "ruta foto 1", "Oren Ingram"),
                        ("1235", "b123", "diam.proin@google.couk",   "ACTIVO",   "ruta foto 2", "Ian Horn"),
                        ("2345", "c123", "nisi@icloud.ca",           "INACTIVO", "ruta foto 3", "Ivor Randolph"),
                        ("9876", "d123", "dignissim@google.edu",     "INACTIVO", "ruta foto 4", "Kirsten Reese"),
                        ("3344", "e123", "curabitur@google.couk",    "ACTIVO",   "ruta foto 5", "Reagan Romero");

INSERT INTO cliente_telefonos VALUES
                        ("1223", "3223344", "Celular"),
                        ("1223", "78898",   "Fijo"),
                        ("2345", "3245677", "Celular"),
                        ("2345", "78423",   "Fijo"),
                        ("7564", "313337", "Celular");

INSERT INTO ciudad VALUES
                       (1, "Armenia"),
                       (2, "Pereira"),
                       (3, "Manizales"),
                       (4, "Bogota"),
                       (5, "Medellin"),
                       (6, "Cali");

INSERT INTO administrador_teatro VALUES
                        ("2454", "QWL22XGI4SX", "virginiaingram@outlook.couk", "ACTIVO",   "ruta foto 1", "Virginia Ingram"),
                        ("3346", "JFV33PHQ0HH", "lewisgood@outlook.ca",        "ACTIVO",   "ruta foto 2", "Lewis Good"),
                        ("9503", "BRQ58RPZ6FC", "daceystanley@hotmail.net",    "INACTIVO", "ruta foto 3", "Dacey Stanley"),
                        ("4508", "GFHTR5YU9IJ", "samu@edmodo.edu.co", "ACTIVO", "ruta foto 4", "Samuel García"),
                        ("7621", "DTR8UY6IS32", "feli@ensq.edu.co", "INACTIVO", "ruta foto 5", "Felipe Sanchez");


INSERT INTO teatro VALUES
                       (1, "Calle 3 #5",        "Multiplex Unicentro", "2454", 1),
                       (2, "Carrera 22 #33-12", "Cineplaza",           "3346", 2),
                       (3, "Carrera 23 - 12", "Multicines AstorPlaza", "7621", 3),
                       (4, "Al lado del Ara de Las Acacias", "Portal del Circaso", "9503", 5),
                       (5, "Sidney, Bogotá Cr 15 - 19", "Arboleda Theaters", "4508", 4);


INSERT INTO sala VALUES
                       (1, 70, "SALA_2D", 1),
                       (2, 80, "SALA_3D", 1),
                       (3, 65, "SALA_XD", 2),
                       (4, 60, "SALA_2D", 2),
                       (5, 90, "SALA_XD", 1);

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
                         ("One Piece Film: Red", "ESTRENO", "ruta imagen 1", "Actores de voz", "Piratas", "ruta trailer"),
                         ("Fast & Furious 10", "PROXIMA", "ruta imagen 2", "Actores", "Carros y bala", "ruta trailer"),
                         ("Taxi Driver", "NO_DISPONIBLE", "ruta imagen 3", "Actores", "Taxi, accion", "ruta trailer"),
                         ("The Batman", "ULTIMAS_FUNCIONES", "ruta imagen 4", "Actores", "Otra de Batman", "ruta trailer" ),
                         ("The Truman Show", "PROXIMA", "ruta imagen 5", "actores de voice", "Comedia Dramatica", "ruta trailer");


INSERT INTO pelicula_generos VALUES
                         ("One Piece Film: Red", 5),
                         ("Fast & Furious 10", 3),
                         ("Taxi Driver", 3),
                         ("The Batman", 3),
                         ("The Truman Show", 8);

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
                        (1, "DISPONIBLE", 15000, "FUNCION_3D", 2, "One Piece Film: Red", 2),
                        (2, "DISPONIBLE", 17000, "FUNCION_2D", 8, "Taxi Driver", 4),
                        (3, "COMPLETA",   20000, "FUNCION_XD", 6, "Fast & Furious 10", 3),
                        (4, "COMPLETA", 12500, "FUNCION_3D", 7, "The Truman Show", 5),
                        (5, "DISPONIBLE", 6000, "FUNCION_XD", 1, "The Batman", 1);

INSERT INTO cupon VALUES
                      (1, "BIENVENIDA", "Cupon de bienvenida", "USADO", "2022-10-05", "Hola Mundo", 5000),
                      (2, "CUMPLEANOS", "Cupon de regalo de cumpleaños", "DISPONIBLE", "2023-01-03", "H-Bday", 10000),
                      (3, "BIENVENIDA", "Cupon de bienvenida al mes de agosto", "USADO", "2022-12-06", "Agosto venteado", 50000),
                      (4, "HALLOWEEN", "Cupon de celebracion halloween", "DISPONIBLE", "2023-02-25", "Halo", 20000),
                      (5, "NAVIDAD", "Cupon para la familia uniciner en Navidad", "USADO", "2022-12-12", "Mry X-Mas", 1000);


INSERT INTO cupon_clientes VALUES
                       (1, "1235"),
                       (1, "2345"),
                       (2, "9876"),
                       (3, "3344"),
                       (4, "1223");

INSERT INTO compra VALUES
                       (1, "2022-10-05 14:37:12", "NEQUI",           0.0, "1235", 1,    2),
                       (2, "2022-10-05 18:54:30", "TARJETA_CREDITO", 0.0, "2345", null, 1),
                       (3, "2022-10-03 09:18:06", "TARJETA_DEBITO",  0.0, "9876", 2,    2),
                       (4, "2022-10-04 16:20:06", "DAVIPLATA",       0.0, "1235", null, 3),
                       (5, "2022-10-05 11:05:17", "EFECTY",          0.0, "2345", 1,    1);

INSERT INTO entrada VALUES
                        (1, 3, 4, 1, 2),
                        (2, 4, 4, 1, 2),
                        (3, 10, 7, 2, 1),
                        (4, 9, 7, 2, 1),
                        (5, 6, 5, 4, 5);

INSERT INTO confiteria VALUES
                        (1, "", "", "", "", 50000, 3),
                        (2, "", "", "", "", 6000, 6),
                        (3, "", "", "", "", 5000, 47),
                        (4, "", "", "", "", 30000, 12),
                        (5, "", "", "", "Hamburguesa", 10000, 15);

INSERT INTO compra_confiteria VALUES
                        (1, 5000F, 4, 3, 1),
                        (2, 7500F, 12, 3, 5),
                        (3, 10000F, 7, 2, 3),
                        (4, 3000F, 6, 4, 4),
                        (5, 21000F, 2, 5, 1);

INSERT INTO administrador_teatro_telefonos VALUES
                        ("2454", "3223344", "Celular"),
                        ("3346", "78898",   "Fijo"),
                        ("9503", "3245677", "Celular"),
                        ("4508", "78423",   "Fijo"),
                        ("7621", "313337", "Celular"));




