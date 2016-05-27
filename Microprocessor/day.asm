.model small
.stack 100h
.data
arr db 'SUNDAY$$$$','MONDAY$$$$','TUESDAY$$$','WEDNESDAY$','THURSDAY$$','FRIDAY$$$$','SATURDAY$$','$'
msg db 10,13,"Today the Day is : $"
.code
start:
     mov ax,@data
     mov ds,ax
     
	 mov ah,2ah
	 int 21h
	
	 mov si,00h
	 mov cl,al
	
	 cmp cl,00h
	 je disp	  ;if(the day is sunday then jump to disp)
	
	l1:			  ;This is to access a day string.
	   add si,0Ah ;10 is added as the day string is made of equal length.
	   dec cl
	   jnz l1
	   
disp: mov ah,09h
	lea dx,msg
	int 21h
	lea dx,arr[si]
	int 21h
	
	mov ah,4ch
	int 21h
end start	
 
	
