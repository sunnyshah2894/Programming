.model small
.stack 100h

.data
msg1 db 10,13,"Enter Your string : $","$"
msg2 db 10,13,"The reverse String is : $","$"
String db 40 dup("$"),"$"
Rev_string db 40 dup('$'),'$'
pri db "# $"

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
	mov si, cx
	inc si

	mov di, offset Rev_string

	loop1:    
	        mov al, String[si] 
			mov [di], al 
			dec si
			inc di
			loop loop1
                     
    mov ah, 9
    mov dx, offset msg2
    int 21h
    
	mov ah, 9
	mov dx, offset Rev_string
	int 21h

	mov ah, 4ch
	int 21h

end start