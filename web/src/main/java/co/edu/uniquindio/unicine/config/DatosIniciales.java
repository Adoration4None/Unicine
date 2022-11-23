package co.edu.uniquindio.unicine.config;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatosIniciales implements CommandLineRunner {
    @Autowired
    private AdministradorServicio administradorServicio;

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Autowired
    private ClienteServicio clienteServicio;

    @Override
    public void run(String... args) throws Exception {
        List<Pelicula> peliculas = administradorServicio.listarPeliculas();
        List<Horario> horarios = adminTeatroServicio.listarHorarios();

        if(peliculas.isEmpty()) {
            Pelicula p1 = administradorServicio.crearPelicula( new Pelicula("One Piece Film: Red", "https://www.youtube-nocookie.com/embed/kE8lM3tAeGg", "Uta, la cantante número uno del mundo, se dispone a dar su primer concierto en directo frente a un público formado por piratas, marines y toda clase de fans. Uta está considerada la cantante más querida de todo el mundo. A pesar de que siempre ha ocultado su identidad, se dice que su voz al cantar es tan maravillosa que parece proceder de “otra dimensión”. Ahora, se celebrará un concierto en directo en el que aparecerá en persona por primera vez frente al público. La tan esperada voz que todo el mundo quiere oír se dispone a resonar mientras multitud de coloridos piratas, marines que no le quitan el ojo de encima, los Piratas de Sombreros de Paja de Luffy, quien se siente atraído por la voz de Uta sin saber nada, y toda clase de fans de Uta llenan el lugar. La historia arranca con la impactante revelación de que Uta es la hija de Shanks.", "Doblaje: Mireya Mendoz, Azul Valadez, Dafnis Fernández, Georgina Sánchez, Alejandro Orozco, Nallely Solís, Raúl Anaya, Kerygma Flores", EstadoPelicula.ESTRENO) );
            Pelicula p2 = administradorServicio.crearPelicula( new Pelicula("The Batman", "https://www.youtube.com/embed/fWQrd6cwJ0A", "Dos años de acechar las calles como Batman (Robert Pattinson), infundiendo miedo en los corazones de los criminales, han llevado a Bruce Wayne a las profundidades de las sombras de Gotham City. Con solo unos pocos aliados de confianza: Alfred Pennyworth (Andy Serkis) y el teniente James Gordon (Jeffrey Wright). Ante la red corrupta de funcionarios y figuras de alto perfil de la ciudad, el vigilante se ha establecido como la única encarnación de la venganza entre sus compañeros ciudadanos.", "Robert Pattinson, Zoë Kravitz, Paul Dano, Jeffrey Wright, John Turturro, Colin Farrell, Jayme Lawson, Andy Serkis", EstadoPelicula.ESTRENO) );
            Pelicula p3 = administradorServicio.crearPelicula( new Pelicula("Lilo Lilo Cocodrilo", "https://www.youtube.com/embed/JZckRZLzmU4", "Un cocodrilo chistoso quiere cumplir su sueño de ser cantante", "Actores y un cocodrilo",  EstadoPelicula.PREVENTA) );
            Pelicula p4 = administradorServicio.crearPelicula( new Pelicula("Fast and Furious 10", "https://www.youtube.com/embed/inW3uM3j5G4", "Todavía se desconocen los detalles de la trama o la sinopsis de la décima película de la saga y, sinceramente, cuesta adivinarlas. La franquicia ha apostado siempre por el cuanto más mejor y las últimas entregas han sido una espectacular locura, casi tanta como la estupidez que sería intentar adivinar por donde pueden ir los tiros. Podríamos ver a Dom en el espacio saltando con un tanque desde un satélite a otro y no nos extrañaría. Sin embargo, tratándose del final de la saga entendemos que se abordarán viejas heridas, y que los hijos de Dom y Brian tendrá mucha importancia, más que nada por dejar plantadas las semillas para una posible nueva generación.", "Vin Diesel, Michelle Roddriguez, Tyrese Gibson, Charlize Theron, Jason Momoa, Jason Statham, Brie Larson", EstadoPelicula.PREVENTA) );
            Pelicula p5 = administradorServicio.crearPelicula( new Pelicula("Taxi Driver", "https://www.youtube.com/embed/T5IligQP7Fo", "La película narra la historia de Travis Bickle (Robert De Niro, 'Luces rojas') un ex militar que había combatido en la guerra de Vietnam. Ese hecho le provoca ansiedad y depresión haciéndole sentir rechazado socialmente. Al no poder conciliar el sueño decide trabajar como taxista por los barrios de Nueva York durante las noches. Brickle se siente atraido por Betsy (Cybill Shepherd, ''The Client List''), una bella mujer que trabaja muy de cerca con el senador Charles Pallantine, aspirante a presidente. Betsy le concede una cita para ir al cine pero al descubrir la aficción que Travis tiene por la pornografía, decide no volver a verle. Aturdido y asqueado por la delincuendia que ve por la noche, decide comprarse un arma y pasar a la acción, limpiando las calles de maleantes. Su primer objetivo es el gobernador al que intentará asesinar en plena campaña de elecciones.", "Robert De Niro, Jodie Foster, Albert Brooks, Harvey Keitel, Cybill Shepherd", EstadoPelicula.ESTRENO) );
        }
    }
}
