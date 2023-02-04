PATH=C:\TASM;

tasm numbers.asm
tasm ejemplo.asm
tlink ejemplo.obj numbers.obj
ejemplo.exe
del ejemplo.obj 
del numbers.obj 
del ejemplo.exe
del ejemplo.map