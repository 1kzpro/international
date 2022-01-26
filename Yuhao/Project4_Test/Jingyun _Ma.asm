.386
.model flat,stdcall
.stack 4096
ExitProcess proto,dwExitCode:dword

.data
    s1 byte "GARDEN"
    s2 byte "DANGER"
    c1 byte 26 dup(0)
    c2 byte 26 dup(0)
.code
    main proc
        mov eax, 0
        mov ecx, LENGTHOF s1
        mov esi, 0

        CounterLoop:
            movzx edi, s1[esi]
            inc c1[edi - 65]
            movzx edi, s2[esi]
            inc c2[edi - 65]
            inc esi
        loop CounterLoop
            movzx esi, 0
            movzx ebx, LENGTHOF c1
        VerifyLoop:
            movzx bl, c1
            cmp bl, c2
            jmp NoAna            
            inc esi
        loop VerifyLoop
        mov eax, 1
        NoAna:
            invoke ExitProcess, 0
    main endp
end main