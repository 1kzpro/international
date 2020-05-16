import doge.task1 as task1

class Student:
    # name = ""
    # age = 0
    # major = ""
    # college = ""
    # term = ""
    # year = 0
    # credits_taken = ""
    # credits_reaining = ""
    # advisor = ""
    # gpa = 0

    def __init__(self, filepath):
        # filepath = 'assignment/data.json'
        data = task1.read_json_file(filepath)
        self.name = data["name"]
        self.age = data["age"]
        self.major = data["major"]
        self.college = data["college"]
        self.term = data["term"]
        self.year = data["year"]
        self.credits_taken = data["credits_taken"]
        self.credits_reaining = data["credits_remaining"]
        self.advisor = data["advisor"]
        self.gpa = data["gpa"]
        self.levels = ['FRESHMAN', 'SOPHOMORE', 'JUNIOR', 'SENIOR'] # Class constants and so on

    def example(self):
        pass

    def __str__(self):
        return 'Student'