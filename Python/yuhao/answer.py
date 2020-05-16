import task1

filepath = 'Python/yuhao/data.json'

class Student:
    def __init__(self, filepath):
        self.data1 = filepath

    def print_all(self, name, age, college, term, year, credits_taken, credits_reaining, advisor, gpa):
        self.name = name
        self.age = age
        self.college = college
        self.term = term
        self.year = year
        self.credits_taken = credits_taken
        self.credits_reaining = credits_reaining
        self.advisor = advisor
        self.gpa = gpa
        print(self.data1, self.name, self.age, self.college, self.term, self.year, self.credits_taken, self.credits_reaining, self.advisor, self.gpa)


student = Student(filepath)

print(student.term)