# Plantilla para TP Integrador usando JFlex y JCup (Java)

## Prerequisitos.

Para poder usar esta plantilla deberá instalar:
1.  [JDK 18](https://www.oracle.com/java/technologies/javase/jdk18-archive-downloads.html)

### Instalación en Windows
1. Bajarse [Windows x64 Installer](https://www.oracle.com/java/technologies/javase/jdk18-archive-downloads.html)
2. Setear JAVA_HOME como variable de entorno en variables del sistema ([Instructivo](https://docs.oracle.com/cd/E19182-01/821-0917/inst_jdk_javahome_t/index.html#:~:text=To%20set%20JAVA_HOME%2C%20do%20the,Program%20Files%5CJava%5Cjdk1.))
3. Corroborar que JAVA_HOME fue seteado correctamente abriendo la terminal de símbolos del sistema (command prompt) ejecuntando el siguiente comando:
```
echo %JAVA_HOME%
```

## Herramientas:

Este proyecto usa [JFlex](https://www.jflex.de/) como Analizador Léxico y [Cup](http://www2.cs.tum.edu/projects/cup/) como Analizador Sintáctico.
Este compilador generará código listo para correr en el procesador [DOSBox](https://www.dosbox.com/).
Toda la documentación referente a ambas herramientas la encontrará en las páginas adjuntas.

## Compilación.

Abrir una terminal en el directorio raíz del proyecto y correr el siguiente comando:

Para Linux/Mac:
```
./mvnw clean install
```


Para Windows:
```
./mvnw.cmd clean install
```

El mismo generará los ejecutables y correrá los tests.

## Ejecución.

Abrir una terminal en el directorio raíz del proyecto y correr el siguiente comando:

Para Linux/Mac:
```
./mvnw clean install -Drun-compiler
```


Para Windows:
```
./mvnw.cmd clean install -Drun-compiler
```

Dicho comando compilará el proyecto y luego correrá el script run.sh o run.bat (Para Unix o Windows respectivamente) presente en el directorio raíz.
Dichos scripts se pueden correr directamente desde la terminal siempre y cuando el proyecto se haya compilado primero.

## Archivo de prueba:

El mismo se encuentra en target/input/test.txt y es copiado del código fuente presente en src/main/resources/input/test.txt.
Agregar en este archivo todos los casos de prueba detallados en la consigna.

## Archivos Generados

En la carpeta target/output se generarán automáticamente los siguientes archivos:

- intermediate.code.txt
- symbol-table.txt
- final.asm

En la carpeta target/asm se copiarán todos los archivos necesarios para correr el programa fuente final, incluyendo el final.asm generado.
La carpeta target/asm contiene la herramienta DOSBox, junto con el ensamblador y el linker.
En ella encontrará un archivo assembler de ejemplo y un [README](src/main/resources/asm/readme.MD) con instrucciones de cómo correrlo.
La misma también contiene el script run.bat que ejecutará el programa generado por su compilador.

## Tests:

En la plantilla ya están incluídos dos casos de prueba automatizados y listos para ser corridos:

- LexerTest (Analizador Léxico)
- ParserTest (Analizador Sintáctico)

A tener en cuenta:
1. Los Tests vienen deshabilitados por defecto ya que las funcionalidades que son probadas no están implementadas aún.
2. Al tope de la clase está presente la annotation [@Disabled](https://howtodoinjava.com/junit5/junit-5-disabled-test-example/) para dicho propósito
3. Cada grupo podrá ir habilitando los tests a medida que vayan implementado las funcionalidades.
4. Vea los criterios de aprobación de cada entrega para ver qué tests deben funcionar en cada punto de control.
5. Cada grupo podrá agregar tests que considere apropiados a los tests ya provistos, sin remover ninguno de los test base.
6. Cada grupo deberá agregar al menos un test para cada uno de sus temas especiales.
