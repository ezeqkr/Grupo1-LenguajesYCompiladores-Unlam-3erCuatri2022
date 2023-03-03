echo "Compiling run.txt"
java "-jar" "target\lyc-compiler-3.0.0.jar" "target\input\test.txt"
COPY  "target\output\final.asm"
tasm final.asm
tlink final.obj
final.exe
del final.obj
del final.exe
del final.map