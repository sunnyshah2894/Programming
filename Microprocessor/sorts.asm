.model small
.stack 100h

.data
msg1 db 10,13,"Enter Your string : $","$"
msg2 db 10,13,"The reverse String is : $","$"
String db 40 dup("$"),"$"
Sort_s db 40 dup('$'),'$'


.code 
start:

	mov ax, @data
	mov ds, ax

	mov dx, offset msg1
	mov ah, 9
	int 21h

	;take the string input
	mov ah, 0ah
	mov dx, offset String
	int 21h

	mov cx, 00h
	mov cl, String[01h]

	mov si, 02h
	mov di, 00h
		
	strcpy:        
	        mov al, String[si]
			mov Sort_s[di], al      
			inc si
			inc di
			loop strcpy

	mov cx, 00h
	mov cl, String[01h]

	mov si, 00h
	FOR1:
			mov di, si
			FOR2:     
			        mov al, Sort_s[si]
					mov bl, Sort_s[di]
					cmp al,bl
					jl else2
					mov Sort_s[si], bl	
					mov Sort_s[di], al

					else2:
						inc di 
						cmp di, cx
						jne FOR2

			inc si 
			cmp si, cx
			jne FOR1


                     
    mov ah, 9
    mov dx, offset msg2
    int 21h
    
	mov ah, 9
	mov dx, offset Sort_s
	int 21h

	mov ah, 4ch
	int 21h

end start