# Chiper Movie App
La estructura del proyecto esta construida bajo los esquemas de trabajo de ViewBinding y el patron MVVM.
# Librerias utilizadas
> Las librerias utilizadas ayudan a mantener la usabilidad y control de la aplicacion de una forma organizada.
 * Dagger: Como controlador de inyeccion de dependencias en el proyecto
 * Retrofit: Consumo de api's y servicios de la nube
 * Facebook Fresco: Mantiene las imagenes en cache y permite seguir mostrandolas en modo offline
 * RxAndroid,RxJava,RxKotlin: Control de procesos asincronos a la API
 * Room Persistence: Manejo de bases de datos tipo SQLite como relacionales
 * Google Paging: Controlador de paginas de modo local y remoto
 * Mapstruct: Mapeo de objetos
 * Groupie: Manejo de multiples listas en un unico RecyclerView
 * StickyScrollView: ScrollView modificado para mantener una cabecera siempre en el top

Seguido a ello se implementa la Clean Architecture.
# Arquitectura
 
![alt text](https://github.com/GeekGianca/chiper_movie_app/blob/main/arch-info/architecture.jpg?raw=true)
 * Utilizando como extension el MVVM de android se implemento la arquitectura limpia de codigo, que nos permitira reutilizar y modificar sin hacer grandes cambios.
 * #CORE: Este folder contiene todos los componentes que son comunes dentro del proyecto
 * #DOMAIN: Maneja toda la capa de interfaces expuestas a la aplicación
 * #DATA: Contiene toda la logica de la app, como repositorios data sources y demas
 * #presentation: Contiene toda la parte grafica de la aplicacion

 ![alt text](https://github.com/GeekGianca/chiper_movie_app/blob/main/arch-info/arch.jpg?raw=true)
 
  * La estructura dentro de cada paquete es acorde a los requerimientos de la aplicacion.
 
### El uso de la libreria paging de modo offline contiene la siguiente estructura, del mismo modo para todo el manejo de los datos locales.
![alt text](https://github.com/GeekGianca/chiper_movie_app/blob/main/arch-info/offline_mode.png?raw=true)

> Se obtiene la referencia de la API, se almacena en la base de datos local, y se empiezan a realizar los llamados localmente.

## Ejecución y resultados de la app
> Pantalla inicial(Cabe aclarar que antes de esta, hay un splash screen que refresca los datos de la app)
![alt text](https://github.com/GeekGianca/chiper_movie_app/blob/main/arch-info/f1_1.png?raw=true)

> Modo de carga de imagenes en cache con Facebook Fresco
![alt text](https://github.com/GeekGianca/chiper_movie_app/blob/main/arch-info/fl_2.png?raw=true)

> Adicional a la prueba quise anexar, una lista de peliculas con mas rating
![alt text](https://github.com/GeekGianca/chiper_movie_app/blob/main/arch-info/fl_3.png?raw=true)

> Detalles de cada pelicula, el cual tiene un ChipView que cambia de color dependiendo el porcentaje de votos que haya tenido, el overview de la pelicula, su titulo, el genero y la cantidad de votos que ha recibido.
![alt text](https://github.com/GeekGianca/chiper_movie_app/blob/main/arch-info/fl_4.png?raw=true)

> Tambien cuenta con una lista local de los actores y trabajadores de la pelicula, asi como tambien un boton que nos llevara a la pagina oficial de la pelicula(si existe).
![alt text](https://github.com/GeekGianca/chiper_movie_app/blob/main/arch-info/fl_5.png?raw=true)

> Evento del boton home page
![alt text](https://github.com/GeekGianca/chiper_movie_app/blob/main/arch-info/fl_6.png?raw=true)

> Por ultimo, queria hacer una muestra del paginador de google pero de manera online, pero tristemete no me alcanzo el tiempo :( 
![alt text](https://github.com/GeekGianca/chiper_movie_app/blob/main/arch-info/fl_7.png?raw=true)

## SALUDOS...