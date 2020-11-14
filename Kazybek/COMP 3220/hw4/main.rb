load "Parser.rb"
load "Lexer.rb"
load "Token.rb"
load "AST.rb"

parse = Parser.new("test/input1.tiny")
mytree = parse.program()
puts mytree.myToStringList()
