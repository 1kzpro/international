ExitProcess PROTO, dwExitCode:DWORD
.DATA
  array SBYTE -19, -3, -1, 0, 1, 3, 8, 24, 53, 100
  v SBYTE 3
.CODE
  main PROC
  CLD
  MOV EBX, 1
  MOV AL, v
  MOV ECX, LENGTHOF array
  MOV EDI, OFFSET array
  REPNE SCASB
  CMP ECX, 0
  JNZ D
  MOV EBX, 0
  D:
  INVOKE ExitProcess, 0
main ENDP
END main