import task1
filepath = 'Python/data.json'
data = task1.read_json_file(filepath)
name = data["name"]
age = data["age"]
major = data["major"]
college = data["college"]
term = data["term"]
year = data["year"]
credits_taken = data["credits_taken"]
credits_reaining = data["credits_reaining"]
advisor = data["advisor"]
gpa = data["gpa"]
print(name)
print(age)
print(age)
print(major)
print(college)
print(term)
print(year)
print(credits_taken)
print(credits_reaining)
print(advisor)
print(gpa)

class Student:
    def __init__(self, filepath):
        