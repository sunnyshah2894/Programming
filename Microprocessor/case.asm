.model small
.stack 100h
.data
max_len db 100
act_len db 0
inps db 100 dup('$')
mess1 db "Enter yout String: $",10,13
mess2 db 10,13,"Case inverted String is: $"
.code
start:	mov ax, @data
		mov ds, ax
		mov dx, offset mess1
		mov ah, 09h
		int 21h
		mov ah, 0ah
		mov dx, offset max_len
		int 21h
		mov si, offset inps
		mov cl, act_len
		again:	cmp byte ptr[si],5ah
				ja cllow
				cmp byte ptr[si],41h
				jb next
				add byte ptr[si],20h
				jmp next
		cllow:	cmp byte ptr[si],7ah
				ja next
				cmp byte ptr[si],61h
				jb next
				sub byte ptr[si],20h
				jmp next
		next:	inc si
				dec cx
				jnz again
		mov ah, 09h
		lea dx, mess2
		int 21h
		mov ah, 09h
		lea dx, inps
		int 21h
		mov ah, 4ch
		int 21h
		end start
		end