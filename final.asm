.MODEL LARGE
.386
.STACK 200h

.DATA

z                    dd    ?                             
a                    dd    ?                             
d                    dd    ?                             
x                    dd    ?                             
e                    dd    ?                             
b                    dd    ?                             
c                    dd    ?                             
_3                   dd    3                             
_4                   dd    4                             
_1                   dd    1                             
_0                   dd    0                             
_100                 dd    100                           
_2                   dd    2                             
@retorno             dd    ?                             
@E                   dd    ?                             
@indice              dd    ?                             

.CODE

MOV AX, @DATA
MOV DS, AX
MOV ES, AX

FLD _3
FSTP a

FLD _4
FSTP d

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
FSTP d

FLD _0
FSTP @retorno

FLD _1
FSTP @indice

FLD x
FLD _1
FADD
FSTP @aux2

FLD @aux2
FSTP @E

FLD @E
FLD a
FXCH
FCOM
FSTSW AX
SAHF
BNE etiqueta2

FLD @indice
FSTP @retorno

etiqueta2:
FLD @indice
FLD _1
FADD
FSTP @aux3

FLD @aux3
FSTP @indice

FLD @E
FLD b
FXCH
FCOM
FSTSW AX
SAHF
BNE etiqueta3

FLD @indice
FSTP @retorno

etiqueta3:
FLD @indice
FLD _1
FADD
FSTP @aux4

FLD @aux4
FSTP @indice

FLD @E
FLD _3
FXCH
FCOM
FSTSW AX
SAHF
BNE etiqueta4

FLD @indice
FSTP @retorno

etiqueta4:
FLD @indice
FLD _1
FADD
FSTP @aux5

FLD @aux5
FSTP @indice

FLD @E
FLD d
FXCH
FCOM
FSTSW AX
SAHF
BNE etiqueta5

FLD @indice
FSTP @retorno

etiqueta5:
FLD @indice
FLD _1
FADD
FSTP @aux6

FLD @aux6
FSTP @indice

FLD @retorno
FSTP x


MOV AX, 4C00h
INT 21h
END
