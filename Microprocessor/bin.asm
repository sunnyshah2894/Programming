.model small
.stack 100h
.data
arr DW 1000h,2000h,3000h,4000h,5000h,6000h
len dw ($ - arr)/2
key equ 2000h
pre db "Key is found at "
suc db "  position!$"
failure db "Key is not found!$"
.code
start:	mov ax, @data
		mov ds, ax
		mov bx, 00h
		mov dx, len
		mov cx, key

		again:	cmp bx, dx
				ja fail
				mov ax, bx  ; calculating the middle element!
				add ax, dx
				shr ax, 1
				mov si, ax
				add si, si
				cmp cx, arr[si]
				jae right
				dec ax
				mov dx, ax
				jmp again
				right:	je success
						inc ax
						mov bx, ax
						jmp again
				success: inc ax
						 add al, '0'
						 mov suc, al
						 Lea dx, pre
						 jmp print
				fail: mov ah, 09h
						 mov dx, offset failure
						 int 21h
						 jmp fin
				print: mov ah, 09h
					   int 21h
				fin: mov ah, 4ch
					 int 21h
				end start
end			




