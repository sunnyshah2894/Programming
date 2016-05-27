.model small
;.stack 100H
.data
arr db 'ASHAU$'
msg db 10,13,"Iterations : $"
len equ $-arr	
.code
start:
	;This uses Bubble Sort algorithm.
	MOV AX,@DATA
	MOV DS,AX
	
	MOV CX,05H	;n
	DEC CX		
	DEC CX
	;this is because we have n-2 for. 
	MOV BX,CX
	
	MOV SI,00H         ;for(int si=0;
	OUTER:	CMP SI,CX  ;  is si<=n  (condition)
		JA BAHAR	   ;JA is used as we need to go till (limit+1) to exit loop. 
		MOV DI,00H	   ;for(int di=0;
		sub BX,SI	   ;  is di<=n-si
	INNER: CMP DI,CX
				JA OUTIN
				MOV AL,ARR[DI+1] 
				CMP AL,BYTE PTR ARR[DI]  ;if(a[di+1]>a[di]) only then swap done.
					JA IFs      ;as if result is false then don't swap.
					;Displays each step of algo.
					MOV AH,09H
					LEA DX,ARR
					INT 21H
					
					LEA DX,msg
					INT 21H
					;swap
					MOV AH,ARR[DI]
					MOV ARR[DI+1],AH
					MOV ARR[DI],AL
			    IFs:INC DI  ;it is di++)
		JMP INNER			;loop for inner di loop.
		OUTIN:	INC SI		;it is si++)
	JMP OUTER				;loop for outer si loop.
	
	BAHAR:
	MOV AH,09H
	LEA DX,ARR
	INT 21H
	
	MOV AH,4CH
	INT 21H
	END START