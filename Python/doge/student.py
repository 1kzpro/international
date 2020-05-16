
import task1
class student:
      name = ""
     age = 0
     major = ""
     college = ""
     term = ""
     year = 0
     credits_taken = ""
     credits_reaining = ""
     advisor = ""
     gpa = 0

     def __init__(self, filepath):
           filepath = 'assignment/data.json'
          data = task1.read_json_file(filepath)
         self.name = data["name"]
         self.age = data["age"]
         self.major = data["major"]
          self.college = data["college"]
         self.term = data["term"]
          self.year = data["year"]
         self.credits_taken = data["credits_taken"]
         self.credits_reaining = data["credits_reaining"]
         self.advisor = data["advisor"]
          self.gpa = data["gpa"]
