include macros2.asm
include number.asm

.MODEL  LARGE
.386
.STACK 200h

MAXTEXTSIZE equ 50

.DATA

    _n1         dd  ?
    _n2         dd  ?
    T_Ingrese_un_numero         db  "Ingrese un numero",'$', 33 dup (?)
    T_Ingrese_otro_numero           db  "Ingrese otro numero",'$', 31 dup (?)
    T_El_primer_numero_ingresado_es_mayor_al_segundo            db  "El primer numero ingresado es mayor al segundo",'$', 4 dup (?)
    T_El_primer_numero_ingresado_es_menor_al_segundo            db  "El primer numero ingresado es menor al segundo",'$', 4 dup (?)
    T_Los_numeros_son_iguales           db  "Los numeros son iguales",'$', 27 dup (?)
    _aux            db  MAXTEXTSIZE dup (?),'$'
    _msgPRESIONE            db  0DH,0AH,"Presione una tecla para continuar...",'$'
    _NEWLINE            db  0DH,0AH,'$'

.CODE

START:
    mov AX,@DATA
    mov DS,AX
    mov es,ax
    mov dx,OFFSET T_Ingrese_un_numero
    mov ah,9
    int 21h
    newLine 1
    GetFloat _n1
    mov dx,OFFSET T_Ingrese_otro_numero
    mov ah,9
    int 21h
    newLine 1
    GetFloat _n2
    fld _n1
    fld _n2
    fxch
    fcomp
    fstsw ax
    ffree st(0)
    sahf
    JBE ET_12
    mov dx,OFFSET T_El_primer_numero_ingresado_es_mayor_al_segundo
    mov ah,9
    int 21h
    newLine 1
    JMP ET_18
ET_12:
    fld _n1
    fld _n2
    fxch
    fcomp
    fstsw ax
    ffree st(0)
    sahf
    JAE ET_22
    mov dx,OFFSET T_El_primer_numero_ingresado_es_menor_al_segundo
    mov ah,9
    int 21h
    newLine 1
    JMP ET_28
ET_22:
    mov dx,OFFSET T_Los_numeros_son_iguales
    mov ah,9
    int 21h
    newLine 1
ET_28:
ET_18:
    mov dx,OFFSET _NEWLINE
    mov ah,09
    int 21h
    mov dx,OFFSET _msgPRESIONE
    mov ah,09
    int 21h
    mov ah, 1
    int 21h
    mov ax, 4C00h
    int 21h
END START
