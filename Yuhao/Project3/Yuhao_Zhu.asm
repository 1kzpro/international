; Author: Yuhao Zhu
; Date: 10/15/2021
; Description: read the values from the array input and place in output,the location should be shifted

.386
.model flat,stdcall
.stack 4096
ExitProcess proto,dwExitCode:dword

.data 
    shift dword 3 
    input byte 5,0ah,3,6,0ch 
    output byte lengthof input dup(?)
.code
    main proc
            mov eax, 0
            mov ebx, lengthof input
            sub ebx, shift
            mov ecx, shift
            mov esi,0
            
        l1: 
            mov al, input[ebx] ;Move the value from input into al 
            mov output[esi], al ;Move the value from al into output                            
            inc esi ;Increment the input index        
            inc ebx ;Increment the output index
        loop l1
            mov ebx, shift ;This loop will iterate shift times
            mov esi, 0 ;Start writing at index 0
            mov ecx, lengthof input ;Start reading at index length - shift.  
            
        l2: 
            mov al, input[esi] ;Move the value from input into al 
            mov output[ebx], al ;Move the value from al into output                    
            inc esi ;Increment the input index         
            inc ebx ;Increment the output index
        loop l2
               
        invoke ExitProcess, 0
    main endp
end main
