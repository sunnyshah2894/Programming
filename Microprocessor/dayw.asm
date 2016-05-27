.model small
.stack 100h

.data
arr db 'SUNDAY$$$$','MONDAY$$$$','TUESDAY$$$','WEDNESDAY$','THURSDAY$$','FRIDAY$$$$','SATURDAY$$','$'
msg db 10,13,"Today the Day is : $"

.code
start:
	 
	mov ax,@data
	mov ds,ax

	mov ah, 9
	mov dx, offset msg
	int 21h

	mov ah, 2ah
	int 21h
	
	mov bl, 0ah
	mul bl

	mov dx, 3ch
	mov ah, 9
	
	int 21h

	mov ah, 4ch
	int 21h

end start
