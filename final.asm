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

MOV AX, 4C00h
INT 21h
END
