import task1

class Student:
    def __init__(self, filepath):
        data = task1.read_json_file(filepath)
        self.name = data['name']
        self.age = data['age']
        self.college = data['college']
        self.term = data['term']
        self.year = data['year']
        self.credits_taken = data['credits_taken']
        self.credits_reaining = data['credits_reaining']
        self.advisor = data['advisor']
        self.gpa = data['gpa']

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