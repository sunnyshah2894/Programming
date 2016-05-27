.model small
.stack 100h

.data
s db 'Sunny$'
s_size EQU $ - s
msg1 db 10,13,"This is palindrome!$"
msg2 db 10,13,"This is not a palindrome!$"


.code
start:


mov ax, @data
mov ds, ax       
 
; first let's print it: 
mov ah, 9
mov dx, offset s
int 21h


lea di, s
mov si, di
add si, s_size
dec si  ; point to last char! but it is $ so again Decrement!
dec si  ; now pointing to last a

mov cx, s_size
cmp cx, 1
je is_palindrome  ; single char is always palindrome!

shr cx, 1     ; divide by 2!

next_char:
    mov al, [di]
    mov bl, [si]
    cmp al, bl
    jne not_palindrome
    inc di
    dec si
loop next_char


is_palindrome:  
   ;  the string is "palindrome!"
   mov ah, 9
   mov dx, offset msg1
   int 21h
jmp stop

not_palindrome:
   ;  the string is "not palindrome!"
   mov ah, 9
   mov dx, offset msg2
   int 21h

stop:
  
  mov ah, 4ch
  int 21h

end start

