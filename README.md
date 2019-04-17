# Torneo_de_Frescas


 

############ MAVEN ############

* Que es maven?

	� Maven es una herramienta que tiene el objetivo de simplificar los procesos de build (compilar y generar ejecutables a partir de c�digo fuente), permitiendo
	compilar cualquier tipo de proyecto de la misma manera, abstray�ndose de las complejidades que hay por detr�s. Maven no solo ayuda con la compilaci�n, es una 
	herramienta capaz de gestionar un proyecto de software completo.
	

* POM. que significa y para que nos sirve


	� "Project Object Model". B�sicamente es un archivo de configuraci�n para Maven en el cual indicaremos que m�dulos componen el proyecto, o qu� librerias
	utiliza el software que estamos desarrollando. Tambi�n indicamos cosas como nombre del proyecto, licencias, autores, sitios web, repositorios, etc.	

* Diferencia entre Archetype y ArtifacId
	
	� Archetype es un conjunto de herramientas para la creaci�n de templates de un proyecto Maven. Es un patr�n o modelo a partir del cual se hacen todas las 
	dem�s cosas del mismo tipo. Dentro de un proyecto se usan archetypes para para intentar que los usuarios utilicen el sistema lo m�s r�pido posible al
	proporcionar un proyecto de muestra que ense�a muchas caracter�sticas de Maven.

	� ArtifacId es el nombre del .jar sin versi�n. Si creamos nosotros el jar podemos elegir el nombre, mientras que si es de un tercero tenemos que respetar el
	nombre que viene designado.

* Explique los siguientes goals de maven; [clean, package, install, compile]

	� clean: Es el segundo ciclo de maven, luego del default. Se compone de 3 goals y sirve para limpiar el proyecto y remover todos los archivos generados en 
	compilaciones previas.
	� package: Es parte del ciclo default. Empaqueta el c�digo fuente compilado en un formato distribuible (jar, war, etc).
	� install: Tambi�n es parte de ciclo default. Instala un paquete en el repositorio local.
	� compile: Parte del ciclo default. Compila el c�digo fuente.
	

* Explique los siguientes Scopes; [compile, provide, runtime, test, system]

	� compile: Es el scope por defecto si no indicamos otro. Las dependencias con compile estar�n disponibles en la ruta del proyecto y todas sus dependencias.
	� provided: Este scope indica que la dependencia debe ser usada en tiempo de ejecuci�n por un JDK o contenedor.
	� runtime: Las dependencias con este scope son requeridas en tiempo de ejecuci�n, pero no son necesarias en la compilaci�n del proyecto.
	� test: Se usa para indicar que la dependencia no es requerida en tiempo de ejecuci�n, y ser� utilizada solo con propositos de testing. Solo est�n presentes
	en los path de test.
	� system: Similar al provided, pero el system requiere que apuntemos a un jar espec�fico del sistema.



###################################################################################

	� Torneo de frescas by Santiago Carrizo.

	En este caso el torneo consiste en dos equipos de cantidad variable de participantes, de los cuales saldr� solo un ganador. Para empezar se presentan todos
	los equipos (Vikingos y Espartanos), y se ir�n enfrentando uno vs uno el primero de cada lista, mostrando los resultados de cada enfrentamiento individual.
	Se implementa el patr�n Strategy para realizarlo. Cada participante que ya particip�, es eliminado de la lista.
	
	� Sistema de enfrentamiento y puntajes:

		Cada participante beber� todo lo que pueda hasta que se orine. No hay un l�mite en la cantidad de bebida que pueda incorporar (expresada en mililitros),
		sino que depender� de la suerte que tenga, ya que el sistema arroja n�meros al azar que determinaran si se orina o sigue bebiendo.
		En cada ronda (iteraci�n), el participante beber� una cantidad X de cerveza, y se ir� acumulando en una variable para saber cu�nto tom� al final.
		Dependiendo de la suerte que tenga, puede seguir bebiendo u orinarse, �De qu� depende? de si el n�mero de mililitros ingerido al final de la ronda, es 
		m�ltiplo de un n�mero Y  ( cantidadBebida % Y = 0), en cu�l caso, el participante orinara una cantidad Z de bebida, que se descontar� de su variable 
		bebida, pudiendo quedar en 0 puntos/ml si orin� m�s de lo que tom� (bebi� agua antes de participar lo cu�l no es recomendable).

		Los Vikingos son bebedores profesionales y en cada ronda podr�n incorporar una mayor cantidad de bebida que un Espartano.
		Un Vikingo normalmente toma entre 400 y 700 ml. de cerveza por ronda (iteraci�n) (n�mero random entre esos valores), pero a su vez tiene el plus de
		bebedor profesional que se sumara a este n�mero (+80 si tiene mas de 40 a�os, o +40 si tiene 40 a�os o menos), pudiendo incorporar un m�ximo de 780 por
		ronda los mas experimentados, y un m�nimo de 440 ml.

		Los Espartanos por su parte no son bebedores profesionales, por lo que beben entre 300 y 600 ml. (random) sin 'plus'.
		
		Pero no todo es bueno para los Vikingos, a la hora de orinar, suelen al ba�o en rondas m�s tempranas que los espartanos, permitiendo que estos �ltimos
		puedan alcanzar en cantidad de alcohol bebido a los Vikingos. Un Vikingo va al ba�o si la cantidad de alcohol bebida al terminar la ronda
		(bebido previamente + num random + bebedor profesional), es m�ltiplo de 8, en cual caso le tocar� orinarse. 
		El Vikingo orina entre 400 y 800 ml. de bebida, que se descontaran de lo que haya bebido, pudiendo quedar en 0 si por ejemplo, en la primer ronda debe ir.
		
		En cambio por suerte para los Espartanos, solo orinan entre 200 y 500 ml. Adem�s ellos tienen una tolerancia extra que los ayuda a aguantar mas tiempo sin ir al 
		ba�o, en principio es de 10, pero se ve aumentada por su peso (+8 si pesa m�s de 90, o +5 si pesa menos), por lo que un Espartano ir� al ba�o si la cantidad 
		de bebida ingerida al finalizar la ronda es m�ltiplo de 18, o 15 dependiendo el caso. Esto hace que, aunque beba menos por ronda, puede aguantar en general m�s 
		rondas bebiendo y poder hacerle frente a los Vikingos. 
		Ejemplo: en 10 litros de cerveza: 10.000 ml. un Vikingo tiene 1250 n�meros que son m�ltiplos de 8 en los cuales puede caer, sin embargo un Espartano tiene
		entre 550 y 660 n�meros m�ltiplos en los que puede caer para orinarse. Asi que pr�cticamente tienen la mitad de posibilidades de orinarse que un Vikingo!
		Esto hace que los duelos se emparejen mucho y todos tengan posibilidades. Es muy �mplia la posibilidad, cualquiera puede quedarse en 0, cualquiera puede 
		beber 40.000 con mucha suerte de su lado, depender� de cada ronda y la experiencia de cada participante.

		En conclusi�n, �Quien gana? Quien m�s cantidad de bebida tenga en su cuerpo luego de orinar, indiferentemente si uno orina antes que otro.

		De todos los enfrentamientos solo persiste 1. El que quedar� guardado en el cuaderno de honor (base de datos) ser� quien de todos los participantes el
		m�s puntos haya obtenido. Todos sus datos son guardados incluyendo a qu� clase pertenece.

		Como bonus, el ganador del Torneo de Frescas anterior, se enfrentar� al tabernero por diversi�n, un adicto al alcohol muy acostumbrado a este tipo de
		enfrentamientos.
		El tabernero, due�o del lugar y del torneo, bebe como Vikingo y Orina como Espartano (bebe entre 400 y 700, orina entre 200 y 500). Pero al ser un bebedor
		profesional (incluso mejor que los vikingos mas experimentados) sumar� 200ml. por ronda a su random bebido. Tambi�n tendr� una tolerancia extra de +17, 
		sumados a los 10 de base. Por lo que solo ir� al ba�o si su cantidad bebida es m�ltiplo de 27 (solo 370 posibilidades cada 10.000).
		
		Sin embargo, como el Tabernero ha perdido en reiteradas ocaciones (bebe mucho durante la noche incluso antes de sus enfrentamientos), por disposici�n 
		suya, este enfrentamiento entre el ganador del Torneo y el Tabernero, no persistira en el cuaderno de honor. Por lo que futuras generaciones no podr�n saber
		si el tabernero efectivamente ha perdido en alguna oportunidad o es solo un rumor que se corri� en el bar.
		




