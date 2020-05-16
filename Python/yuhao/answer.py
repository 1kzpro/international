import task1

filepath = 'Python/yuhao/data.json'

class Student:
    def __init__(self, filepath):
        data = task1.read_json_file(filepath)        
        self.name = data['name']
        self.age = data['age']
        self.college = data['college']
        self.term = data['term']
        self.year = data['year']
        self.credits_taken = data['credits_taken']
        self.credits_reaining = data['credits_reaining']
        self.advisor = data['advisor']
        self.gpa = data['gpa']

    print(name)
    print(self.age)
    print(self.college)
    print(self.term)
    print(self.year)
    print(self.credits_taken)
    print(self.credits_reaining)
    print(self.advisor)
    print(self.gpa)

    

    