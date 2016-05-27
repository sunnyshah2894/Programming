.model small
.stack 100
.data 
msg db "TEXT"
.code
start:	mov ax, @data
		mov ds, ax

		mov ah, 00h   ;text mode and graphics mode
		mov al, 03h
		int 10h

		mov di, 0004h
		mov bh, 00h
		mov dx, 000fh
		mov si, offset msg

		mov ah, 02h
		int 10h

		loop1:	mov ah, 02h
				int 10h

				mov ah, 09h
				mov al, [si]
				mov bh, 00h
				mov bl, 11000001B
				inc si
				mov cx, 0001h
				inc dl
				int 10h

				dec di
				jnz loop1
		mov ah, 4ch
		int 21h
		end start
		end
