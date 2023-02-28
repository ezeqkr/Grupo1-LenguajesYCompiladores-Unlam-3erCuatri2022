echo "Compiling run.txt"
java "-jar" "target\lyc-compiler-2.0.0.jar" "target\input\test.txt"
COPY  "target\output\final.asm" "target\asm\final.asm"