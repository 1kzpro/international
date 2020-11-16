class AST
  # attr_accessor :down
  # attr_accessor :right
  # attr_accessor :token

   def initialize(tok)
      @token = tok
      @down = nil
      @right = nil
   end
   
   def get_token()
      return @token
   end
   
   def set_token(x)
      @token = x
   end

   def addChild(node)
      if (node == nil) then return nil end
      t = @down
      if (t != nil)
         while (t.getNextSibling() != nil)
             t = t.getNextSibling()
         end
         t.setNextSibling(node)
      else
         self.setFirstChild(node) 
      end
   end  

   def addAsFirstChild(node)
      if (node == nil) then return nil end
      t = self.getFirstChild
      if (t != nil)
         n = self.setFirstChild(node)
         n.setNextSibling(t)
      else
         self.setFirstChild(node)
      end
   end

   def reverse
      h = self.getFirstChild
      print("Root: ",h.to_s, "\n")
      if (h != nil)
         while (h.getNextSibling() != nil)
            t = h.getNextSibling().getNextSibling()
            print("Temp: ",t.to_s, "\n")
            h.getNextSibling().setNextSibling(h)
            print("Next Right: ",h.getNextSibling().getNextSibling().to_s, "\n")
            h.setNextSibling(t)
            print("Current Right: ",h.getNextSibling().to_s, "\n")
         end
         print("List",h.toStringList, "\n")
         self.setFirstChild(h)
      end
   end

   def reverse_recursive
      t = self
      r = AST.new("test", test)
      while(t != nil)
         t = t.getFirstChild
         if (t.getFirstChild != nil)
            r
         else
            t = t.getNextSibling
         end
      end
      return r
   end
   
   def getFirstChild
      return @down
   end
   
   def setFirstChild(c)
       @down = c
   end
   
   def getNextSibling
      return @right
   end
   
   def setNextSibling(n)
      @right = n
   end
   
   def to_s
     return @token.to_s    
   end
   
   def my_to_s
      return @token.my_to_s    
   end

   def toStringList
      t = self
      ts = ""
      if (t.getFirstChild() != nil)then ts += " (" end
      ts += " #{self.to_s()}"
      if (t.getFirstChild() != nil)
         ts += t.getFirstChild().toStringList()
      end
      if (t.getFirstChild() != nil)then  ts += " )" end
      if (t.getNextSibling() != nil)
         ts += t.getNextSibling().toStringList()
      end
      return ts
   end

   def myToStringList
      t = self
      ts = ""
      if (t.getFirstChild() != nil)then ts += " (" end
      ts += " #{self.my_to_s()}"
      if (t.getFirstChild() != nil)
         ts += t.getFirstChild().myToStringList()
      end
      if (t.getFirstChild() != nil)then  ts += " )" end
      if (t.getNextSibling() != nil)
         ts += t.getNextSibling().myToStringList()
      end
      return ts
   end
end  

