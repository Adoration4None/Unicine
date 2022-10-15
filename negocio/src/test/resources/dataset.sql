INSERT INTO cliente VALUES
                        ("1223", "a123", "dictum.phasellus@aol.org", "INACTIVO", "ruta foto 1", "Oren Ingram"),
                        ("1235", "b123", "diam.proin@google.couk",   "ACTIVO",   "ruta foto 2", "Ian Horn"),
                        ("2345", "c123", "nisi@icloud.ca",           "INACTIVO", "ruta foto 3", "Ivor Randolph"),
                        ("9876", "d123", "dignissim@google.edu",     "INACTIVO", "ruta foto 4", "Kirsten Reese"),
                        ("3344", "e123", "curabitur@google.couk",    "ACTIVO",   "ruta foto 5", "Reagan Romero");

INSERT INTO cliente_telefonos VALUES
                        ("1223", "3223344", "Celular"),
                        ("1223", "78898", "Fijo"),
                        ("2345", "3245677", "Celular"),
                        ("2345", "78423", "Fijo");

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
                        ("9503", "BRQ58RPZ6FC", "daceystanley@hotmail.net",    "INACTIVO", "ruta foto 3", "Dacey Stanley");

INSERT INTO teatro VALUES
                       (1, "Calle 3 #5", "Multiplex Unicentro", "2454", 1),
                       (2, "Carrera 22 #33-12", "Cineplaza", "3346", 2);

INSERT INTO sala VALUES
                       (1, 70, "DISPONIBLE", "2D", 1),
                       (2, 80, "DISPONIBLE", "3D", 1),
                       (3, 65, "DISPONIBLE", "XD", 2),
                       (4, 60, "NO_DISPONIBLE", "2D", 2),
                       (5, 90, "NO_DISPONIBLE", "XD", 1);

/**
  PENDIENTE:
  INSERT INTO silla VALUES
 */

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
                         ("One Piece Film: Red", "ESTRENO", "ruta imagen", "Actores de voz", "Piratas", "ruta trailer"),
                         ("Fast & Furious 10", "PROXIMA", "ruta imagen", "Actores", "Carros y bala", "ruta trailer"),
                         ("Taxi Driver", "NO_DISPONIBLE", "ruta imagen", "Actores", "Taxi, accion", "ruta trailer"),
                         ("The Batman", "ULTIMAS_FUNCIONES", "ruta imagen", "Actores", "Otra de Batman", "ruta trailer" );

INSERT INTO pelicula_generos VALUES
                         ("One Piece Film: Red", 5),
                         ("Fast & Furious 10", 3),
                         ("Taxi Driver", 3),
                         ("The Batman", 3);

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
                        (1, "DISPONIBLE", 15000, "3D", 2, "One Piece Film: Red", 2),
                        (2, "DISPONIBLE", 17000, "2D", 8, "Taxi Driver", 4),
                        (3, "NO_DISPONIBLE", 20000, "XD", 6, "Fast & Furious 10", 3);

INSERT INTO cupon VALUES
                      (1, "BIENVENIDA", "Cupon de bienvenida", "USADO", "2022-10-05", "Hola Mundo", 5000, "1223"),
                      (2, "CUMPLEANOS", "Cupon de regalo de cumpleaños", "DISPONIBLE", "2023-01-03", "H-Bday", 10000, "1223");



