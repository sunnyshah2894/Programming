.model small
.stack 100h
.data
message db "Hello Sunny Shah u rock man!$"
.code
start:  mov ax, @data
		mov ds, ax
		mov ah, 09h
		mov dx, offset message
		int 21h
		mov ah, 4ch
		int 21h
end start