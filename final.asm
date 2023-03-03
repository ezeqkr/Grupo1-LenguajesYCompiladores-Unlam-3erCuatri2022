.MODEL LARGE
.386
.STACK 200h

.DATA

b                    dd    ?                             
z                    dd    ?                             
a                    dd    ?                             
d                    dd    ?                             
x                    dd    ?                             
e                    dd    ?                             
c                    dd    ?                             
_1                   dd    1                             
_0                   dd    0                             
_3                   dd    3                             
_100                 dd    100                           
_2                   dd    2                             
@retorno             dd    ?                             
@E                   dd    ?                             
@indice              dd    ?                             

.CODE

MOV AX, @DATA
MOV DS, AX
MOV ES, AX

FLD _1
FSTP x

FLD _0
FSTP e

FLD a
FLD d
FXCH
FCOM
FSTSW AX
SAHF
BLE etiqueta1

FLD _3
FLD _100
FADD
FSTP @aux1

FLD @aux1
FSTP a

etiqueta1:
FLD _2
FSTP a

FLD _2
FSTP d

FLD x
FLD _1
FADD
FSTP @aux2

FLD @E
FLD a
FXCH
FCOM
FSTSW AX
SAHF
BNE etiqueta2

etiqueta2:
FLD @indice
FLD @indice
FADD
FSTP @aux3

FLD @E
FLD e
FXCH
FCOM
FSTSW AX
SAHF
BNE etiqueta3

etiqueta3:
FLD @indice
FLD @indice
FADD
FSTP @aux4

FLD @E
FLD _3
FXCH
FCOM
FSTSW AX
SAHF
BNE etiqueta4

etiqueta4:
FLD @indice
FLD @indice
FADD
FSTP @aux5

FLD @E
FLD d
FXCH
FCOM
FSTSW AX
SAHF
BNE etiqueta5

etiqueta5:
FLD @indice
FLD @indice
FADD
FSTP @aux6

FLD @retorno
FSTP x


MOV AX, 4C00h
INT 21h
END
