# Torneo_de_Frescas


 

############ MAVEN ############

* Que es maven?

	• Maven es una herramienta que tiene el objetivo de simplificar los procesos de build (compilar y generar ejecutables a partir de código fuente), permitiendo
	compilar cualquier tipo de proyecto de la misma manera, abstrayéndose de las complejidades que hay por detrás. Maven no solo ayuda con la compilación, es una 
	herramienta capaz de gestionar un proyecto de software completo.
	

* POM. que significa y para que nos sirve


	• "Project Object Model". Básicamente es un archivo de configuración para Maven en el cual indicaremos que módulos componen el proyecto, o qué librerias
	utiliza el software que estamos desarrollando. También indicamos cosas como nombre del proyecto, licencias, autores, sitios web, repositorios, etc.	

* Diferencia entre Archetype y ArtifacId
	
	• Archetype es un conjunto de herramientas para la creación de templates de un proyecto Maven. Es un patrón o modelo a partir del cual se hacen todas las 
	demás cosas del mismo tipo. Dentro de un proyecto se usan archetypes para para intentar que los usuarios utilicen el sistema lo más rápido posible al
	proporcionar un proyecto de muestra que enseña muchas características de Maven.

	• ArtifacId es el nombre del .jar sin versión. Si creamos nosotros el jar podemos elegir el nombre, mientras que si es de un tercero tenemos que respetar el
	nombre que viene designado.

* Explique los siguientes goals de maven; [clean, package, install, compile]

	• clean: Es el segundo ciclo de maven, luego del default. Se compone de 3 goals y sirve para limpiar el proyecto y remover todos los archivos generados en 
	compilaciones previas.
	• package: Es parte del ciclo default. Empaqueta el código fuente compilado en un formato distribuible (jar, war, etc).
	• install: También es parte de ciclo default. Instala un paquete en el repositorio local.
	• compile: Parte del ciclo default. Compila el código fuente.
	

* Explique los siguientes Scopes; [compile, provide, runtime, test, system]

	• compile: Es el scope por defecto si no indicamos otro. Las dependencias con compile estarán disponibles en la ruta del proyecto y todas sus dependencias.
	• provided: Este scope indica que la dependencia debe ser usada en tiempo de ejecución por un JDK o contenedor.
	• runtime: Las dependencias con este scope son requeridas en tiempo de ejecución, pero no son necesarias en la compilación del proyecto.
	• test: Se usa para indicar que la dependencia no es requerida en tiempo de ejecución, y será utilizada solo con propositos de testing. Solo están presentes
	en los path de test.
	• system: Similar al provided, pero el system requiere que apuntemos a un jar específico del sistema.



###################################################################################

	• Torneo de frescas by Santiago Carrizo.

	En este caso el torneo consiste en dos equipos de cantidad variable de participantes, de los cuales saldrá solo un ganador. Para empezar se presentan todos
	los equipos (Vikingos y Espartanos), y se irán enfrentando uno vs uno el primero de cada lista, mostrando los resultados de cada enfrentamiento individual.
	Se implementa el patrón Strategy para realizarlo. Cada participante que ya participó, es eliminado de la lista.
	
	• Sistema de enfrentamiento y puntajes:

		Cada participante beberá todo lo que pueda hasta que se orine. No hay un límite en la cantidad de bebida que pueda incorporar (expresada en mililitros),
		sino que dependerá de la suerte que tenga, ya que el sistema arroja números al azar que determinaran si se orina o sigue bebiendo.
		En cada ronda (iteración), el participante beberá una cantidad X de cerveza, y se irá acumulando en una variable para saber cuánto tomó al final.
		Dependiendo de la suerte que tenga, puede seguir bebiendo u orinarse, ¿De qué depende? de si el número de mililitros ingerido al final de la ronda, es 
		múltiplo de un número Y  ( cantidadBebida % Y = 0), en cuál caso, el participante orinara una cantidad Z de bebida, que se descontará de su variable 
		bebida, pudiendo quedar en 0 puntos/ml si orinó más de lo que tomó (bebió agua antes de participar lo cuál no es recomendable).

		Los Vikingos son bebedores profesionales y en cada ronda podrán incorporar una mayor cantidad de bebida que un Espartano.
		Un Vikingo normalmente toma entre 400 y 700 ml. de cerveza por ronda (iteración) (número random entre esos valores), pero a su vez tiene el plus de
		bebedor profesional que se sumara a este número (+80 si tiene mas de 40 años, o +40 si tiene 40 años o menos), pudiendo incorporar un máximo de 780 por
		ronda los mas experimentados, y un mínimo de 440 ml.

		Los Espartanos por su parte no son bebedores profesionales, por lo que beben entre 300 y 600 ml. (random) sin 'plus'.
		
		Pero no todo es bueno para los Vikingos, a la hora de orinar, suelen al baño en rondas más tempranas que los espartanos, permitiendo que estos últimos
		puedan alcanzar en cantidad de alcohol bebido a los Vikingos. Un Vikingo va al baño si la cantidad de alcohol bebida al terminar la ronda
		(bebido previamente + num random + bebedor profesional), es múltiplo de 8, en cual caso le tocará orinarse. 
		El Vikingo orina entre 400 y 800 ml. de bebida, que se descontaran de lo que haya bebido, pudiendo quedar en 0 si por ejemplo, en la primer ronda debe ir.
		
		En cambio por suerte para los Espartanos, solo orinan entre 200 y 500 ml. Además ellos tienen una tolerancia extra que los ayuda a aguantar mas tiempo sin ir al 
		baño, en principio es de 10, pero se ve aumentada por su peso (+8 si pesa más de 90, o +5 si pesa menos), por lo que un Espartano irá al baño si la cantidad 
		de bebida ingerida al finalizar la ronda es múltiplo de 18, o 15 dependiendo el caso. Esto hace que, aunque beba menos por ronda, puede aguantar en general más 
		rondas bebiendo y poder hacerle frente a los Vikingos. 
		Ejemplo: en 10 litros de cerveza: 10.000 ml. un Vikingo tiene 1250 números que son múltiplos de 8 en los cuales puede caer, sin embargo un Espartano tiene
		entre 550 y 660 números múltiplos en los que puede caer para orinarse. Asi que prácticamente tienen la mitad de posibilidades de orinarse que un Vikingo!
		Esto hace que los duelos se emparejen mucho y todos tengan posibilidades. Es muy ámplia la posibilidad, cualquiera puede quedarse en 0, cualquiera puede 
		beber 40.000 con mucha suerte de su lado, dependerá de cada ronda y la experiencia de cada participante.

		En conclusión, ¿Quien gana? Quien más cantidad de bebida tenga en su cuerpo luego de orinar, indiferentemente si uno orina antes que otro.

		De todos los enfrentamientos solo persiste 1. El que quedará guardado en el cuaderno de honor (base de datos) será quien de todos los participantes el
		más puntos haya obtenido. Todos sus datos son guardados incluyendo a qué clase pertenece.

		Como bonus, el ganador del Torneo de Frescas anterior, se enfrentará al tabernero por diversión, un adicto al alcohol muy acostumbrado a este tipo de
		enfrentamientos.
		El tabernero, dueño del lugar y del torneo, bebe como Vikingo y Orina como Espartano (bebe entre 400 y 700, orina entre 200 y 500). Pero al ser un bebedor
		profesional (incluso mejor que los vikingos mas experimentados) sumará 200ml. por ronda a su random bebido. También tendrá una tolerancia extra de +17, 
		sumados a los 10 de base. Por lo que solo irá al baño si su cantidad bebida es múltiplo de 27 (solo 370 posibilidades cada 10.000).
		
		Sin embargo, como el Tabernero ha perdido en reiteradas ocaciones (bebe mucho durante la noche incluso antes de sus enfrentamientos), por disposición 
		suya, este enfrentamiento entre el ganador del Torneo y el Tabernero, no persistira en el cuaderno de honor. Por lo que futuras generaciones no podrán saber
		si el tabernero efectivamente ha perdido en alguna oportunidad o es solo un rumor que se corrió en el bar.
		




