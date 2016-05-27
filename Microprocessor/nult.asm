.model small
.stack 100h
.data 
mul1 dw 2521h, 3206h
mul2 dw 0000h, 0001h
ans dw 0000h, 0000h,0000h, 0000h
.code
start:	mov ax, @data
		mov ds, ax

		mov ax, mul2+2
		mul mul1+2
		add [ans], ax
		add [ans+2], dx

		mov ax, mul2+2
		mul mul1
		add [ans+2], ax
		adc [ans+4], dx
		adc [ans+6], 00h

		mov ax, mul2
		mul mul1+2
		add [ans+2], ax
		adc [ans+4], dx
		adc [ans+6], 00h

		mov ax, mul2 
		mul mul1
		add [ans+4], ax
		adc [ans+6], dx
		adc [ans+8], 00h
		
		mov bx, [ans]
		mov cx, [ans+2]
		lea dx, ans
		mov ah, 09h
		int 21h
		mov ah, 4ch
		int 21h
		end start
		end