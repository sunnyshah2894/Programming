.MODEL SMALL
.STACK 100H
.DATA
	MSG1 DB 0DH,0AH,"ENTER THE STRING : ","$"
	MSG2 DB 10,13,"ENTER THE STRING TO COPY  : ","$"
	MSG3 DB 10,13,"RESULT : ","$"
	STRING1 DB 20 DUP("$"),"$"		;INPUT STRING.
	STRING2 DB 20 DUP("$"),"$"	
.CODE
START:
	MOV AX,@DATA
	MOV DS,AX
	
	MOV AH,09H
	LEA DX,MSG1
	INT 21H
	
	MOV AH,0AH
	LEA DX,STRING1
	INT 21H
	
	MOV AH,09H
	LEA DX,MSG2
	INT 21H
	
	MOV AH,0AH
	LEA DX,STRING2
	INT 21H
	
	MOV CL,STRING1[01H]
	MOV CH,00H
	
	MOV SI,00H
	MOV DI,02H
	
	ADD SI,CX
	INC SI
	INC SI
	
	MOV CH,STRING2[01H]
	
LOOP1: 
	MOV AL,STRING2[DI]
	MOV STRING1[SI],AL	
	INC DI
	INC SI
	DEC CH
	JNZ LOOP1
	
	MOV AH,09H
	LEA DX,MSG3 
	INT 21H
	LEA DX,STRING1[02H]
	INT 21H
	    	
Exit :
    MOV AH,4CH
    INT 21H
END START 