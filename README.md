# Unicine
### Proyecto Final - Programacion Avanzada
Proyecto que busca establecer una plataforma para gestionar todas las operaciones llevadas a cabo en un cine.

El proyecto esta escrito en Java y construido utilizando Gradle. Consta de dos modulos: Uno de negocio y otro de presentacion.

**A continuacion se presenta el enunciado con los requisitos (cumplidos) del proyecto final**.
_ _ _

**UniCine** es una cadena de cines con operaciones en Colombia y tiene la necesidad de construir una plataforma donde sus clientes puedan comprar las entradas a cine y la confitería para cualquier película. Para esto, el cliente debe registrarse, para el registro es necesario una cédula, nombre completo, números de teléfono, email, imagen de perfil y contraseña. Una vez registrado puede ingresar, elegir la ciudad, buscar la función de la película que desee y comprar las entradas. Al momento de comprar las entradas, el cliente debe indicar cuántas va a comprar y en qué sillas quiere ubicarse, luego, puede elegir la confitería y por último debe pagar el valor total de la compra.

El cine cuenta con un sistema de cupones que ofrecen descuentos al cliente en el valor de la compra, estos cupones son redimibles al momento de pagar. Los cupones se distribuyen de la siguiente manera:
- Cuando un cliente se registra por primera vez se le envía un cupón vía correo electrónico. Al redimir este cupón obtiene un 15% de descuento.
- Cuando un cliente hace una compra por primera vez por medio de la plataforma se le envía un cupón vía correo electrónico. Al redimir este cupón obtiene un 10% de descuento.
- El administrador puede crear más cupones, cada cupón tiene un valor de descuento, una fecha de vencimiento, una descripción y un criterio.

El administrador de la plataforma tiene la responsabilidad de crear la confitería, los administradores por cada ciudad y las películas. El administrador de cada ciudad puede crear Teatros, cada teatro tiene un nombre, una dirección y una lista de salas. Cada sala tiene su propia distribución de sillas, esta distribución se crea por medio de una matriz. Cada película tiene un nombre, una imagen, un tráiler (url youtube), géneros, sinopsis, reparto y estado (cartelera o preventa). Para la confitería cada elemento tiene una imagen y un precio.

Por otro lado, cada administrador de teatro tiene la tarea de definir los horarios de cada película en cada sala y los precios. 

**NOTA:** En adelante la acción gestionar hará referencia a crear, modificar, buscar (ver información), listar y si es posible eliminar o invalidar información.

En UniCine se desea contar con una aplicación que maneje tres tipos de persona.

**1. Administrador:**
- Loguearse
- Gestionar Administradores de teatros
- Gestionar Películas
- Gestionar Confitería
- Gestionar Cupones

**2. Administrador de Teatro:**
- Loguearse
- Gestionar Teatros
- Gestionar Funciones
- Gestionar Salas

**3. Cliente:**
- Registrarse y loguearse
- Buscar películas
- Realizar compras
- Redimir cupones
- Listar sus propias compras
- Cambiar contraseña usando correo electrónico por medio de un enlace. Esta funcionalidad es para cuando el usuario olvida su contraseña

### Para tener en cuenta:
- Cuando el usuario se registra no puede iniciar sesión inmediatamente. Una vez el usuario se registra se le envía un correo electrónico con un enlace. Al darle click a dicho enlace se debe activar su cuenta y ahora sí podrá iniciar sesión.
- La página de inicio debe mostrar la cartelera de películas en general (estrenadas y en preventa) y debe poder filtrar por ciudad y por teatro.
- Se debe validar automáticamente que las sillas elegidas están disponibles.
- En la compra se debe guardar, el cliente, el teatro, las sillas, el horario, la película, la fecha de compra, la confitería y método de pago.
- Cada vez que se realice una compra se debe generar un código QR y se debe enviar un correo electrónico que muestre los detalles de la misma, incluyendo el QR. Se debe mostrar el subtotal y el total.
- Para el manejo de imágenes se debe hacer uso de un servicio de terceros, puede ser Cloudinary, Firebase, Flickr, etc.
_ _ _

## Tecnologias utilizadas:
- Java 11
- Spring Boot
- Gradle
- JPA
- Hibernate
- MySQL
- Lombok
- JSF
- PrimeFaces
- Bootstrap 5
- Cloudinary

