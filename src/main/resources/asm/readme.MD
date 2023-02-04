# Turbo Assembler y DOSBox.

## Prerequisitos

1. Instalar [DOSBox](https://www.dosbox.com/)
2. Abrir DSBOX y ejecutar el comando:

``
mount c <<tu directorio>>
``

Nota: reemplazar "tu directorio" con la ruta absoluta de este directorio.
Este comando creará una unidad C que tendrá como contenido los archivos de esta carpeta.

3. Acceder al directorio montado ejecutando:

``
C:
``

4. Verificar que se listen los archivos correspondientes con el comando:

``
dir
``

5. Ejecutar el script run.bat, el cual asemblará, linkeará y ejecutará el programa de ejemplo:

``
run-example.bat
``