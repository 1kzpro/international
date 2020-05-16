import task1

class Student:
    def __init__(self, filepath):
        data = task1.read_json_file(filepath)
        self.name = data['name']
        self.age = data['age']
        self.major = data['major']
        self.college = data['college']
        self.term = data['term']
        self.year = data['year']
        self.credits_taken = data['credits_taken']
        self.credits_remaining = data['credits_reaining']
        self.advisor = data['advisor']
        self.gpa = data['gpa']

    def __str__(self):
        text = 'Student\n'
        text += '1. Name: ' + self.name
        text += '\n2. Age: ' + str(self.age)
        text += '\n3. Major: ' + self.major
        text += '\n4. College: ' + self.college
        text += '\n5. Term: ' + self.term
        text += '\n5. Year: ' + str(self.year)
        text += '\n6. Credits Taken: ' + str(self.credits_taken)
        text += '\n7. Credits Remaining: ' + str(self.credits_remaining)
        text += '\n8. Advisor: ' + self.advisor
        text += '\n9. GPA: ' + str(self.gpa)
        return text

filepath = 'Python/kaza/data.json'
student = Student(filepath)

print('Student')
print('1. Name:', student.name)
print('2. Age:', student.age)
print('3. Major:', student.major)
print('4. College:', student.college)
print('5. Term:', student.term)
print('5. Year:', student.year)
print('6. Credits Taken:', student.credits_taken)
print('7. Credits Remaining:', student.credits_remaining)
print('8. Advisor:', student.advisor)
print('9. GPA:', student.gpa)

print(student)