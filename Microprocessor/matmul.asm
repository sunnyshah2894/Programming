.MODEL SMALL
.STACK 100H
.DATA
	R1 EQU 3
	C1 EQU 3
	R2 EQU 3
	C2 EQU 3
    M1 DB 02H,2H,2H,2H,2H,2H,2h,02H,02H
	M2 DB 02H,1H,01H,01H,01H,02H,01H,02H,01H				
	M3 DB 09H DUP(0h),'$'
	space db ' $'
	newline db 10,13,'$'
	
.code
start:
	
	mov ax, @data
	mov ds, ax
	
	;FOR(i,0,R1)
	;   FOR(j,0,C2)
	;       For(k,0,C1)
	;           M3[i,j] += M1[i,k]*M2[k,j]

	mov si, 00h
	FOR1:
			mov di, 00h
			FOR2: 
					mov cl, 00h
					FOR3:   
					        ; actual is M3[i,j] += M1[i,k]*M2[k,j]
					        ; hr it'l b M3[si,di] += M1[si,cl]*M2[cl,di]
					        ; hence to find M1[si,cl] , we do
					        ; M1[ si*R1( ie noofrows in M1 ) + cl ]
					         
							mov ax, si  ; putting si in ax
							mov dl, R1  ; putting R1 in dl
							mul dl      ; ax <- si*dl
							add al, cl  ; al <- al + cl 
							mov bp,offset M1 ; bp points to base of M1
							add bp, ax       ; make bp point to M1[si,di] by adding al to bp
							; here Ds: is important as Bp is pointer to stack					
							mov bl,Ds:[bp]   ; now bl <- M1[si,cl]
                          
                            ; So till now M1[si,cl] is in bl
                            
                            ;similarly finding M2[cl,di]
							mov al, cl
							mov dl, r2
							mul dl
							add ax, di         
							mov bp, offset M2
							add bp, ax
							mov al, Ds:[bp] ; Here now M2[cl,di] is in al  
							
							; so now we have to multiply M1[si,cl]*M2[cl,di]
							; ie bl*al
							mul bl
                            
                            ; now we need to add this product to M3[si,di]
                            ; first we store the result of above prod in bx
							mov bx, ax
                                
                            ; now making bp point to M3[si,di] and adding bx to it   
							mov ax, si
							mov dl, R1
							mul dl
							add ax, di
							mov bp, offset M3 
							add bp, ax
							mov al, Ds:[bp] ; now bp is pointing to M3[si,di] so temporarily move it to al
							
							; adding it to al with our result above in bx
							add bl,al                                    
							
							; again put this al into ds:[bp] ie M3[si,di]
							mov Ds:[bp], bl

							inc cl
							cmp cl, C1
							jne FOR3

					inc di
					cmp di, C2
					jne FOR2

			inc si
			cmp si, R1
			jne FOR1


	mov cl, R1
	MOV SI,OFFSET M3


	ROW1:

			mov ch, C2
			COL1:
				MOV dl,BYTE PTR[SI]	

				; We are printing character here that is taking digit and adding 30h('0') to get its ASCII value and printing so 
				; the result M3 should have all the ans as a single digit. ie if M3[i,j] > 10 then it will be A something so
				; on adding 30H will produce some wierd ascii value character hence output will be wierd
				
				ADD dl,30H       
				MOV AH,02H ;DISP. A CHAR.
				INT 21H

				mov ah, 9
				mov dx, offset space
				int 21h

				INC SI
				DEC ch
				JNZ COL1
			

			mov ah, 9
			mov dx, offset newline
			int 21h

			DEC cl
			JNZ ROW1


	mov ah, 4ch
	int 21h

end start

