load "Parser.rb"
load "Lexer.rb"
load "Token.rb"
load "AST.rb"

parse = Parser.new("test/test.tiny")
mytree = parse.program()
puts mytree.myToStringList()
