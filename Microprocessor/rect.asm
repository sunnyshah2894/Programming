.model small
.stack 100h
.code
start:	mov ah, 00h
		mov al, 12h
		int 10h

		mov cx, 0170
		mov dx, 0140

		lab1:	mov ax, 0c02h
				int 10h
				inc cx
				cmp cx, 0470
				jb lab1

		mov cx, 0170
		mov dx, 0440

		lab2:	mov ax, 0c01h
				int 10h
				inc cx
				cmp cx, 0470
				jb lab2

		mov cx, 0170
		mov dx, 0140

		lab3:	mov ax, 0c04h
				int 10h
				inc dx
				cmp dx, 0440
				jb lab3

		mov cx, 0470
		mov dx, 0140

		lab4:	mov ax, 0c03h
				int 10h
				inc dx
				cmp dx, 0440
				jb lab4

		end start
		end
