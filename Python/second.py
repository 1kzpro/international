import task1

filepath = 'Python/data.json'

# Example how to get data: data = task1.read_json_file(filepath)

# print(data['data'])

# Create class that takes filepath as an argument then creates all instance variables.

# Example how to make instance varibale 

self.name = data['name']

print(name)

# How to make class with multiple inheritance

class Engine:
    def __init__(self, engine_type):
        self.engine = engine_type

class Wheels:
    def __init__(self, count):
        self.count = count

class Car(Engine, Wheels):
    def __init__(self, name, engine_type, count):
        Engine.__init__(engine_type)
        Wheels.__init__(count)
        sefl.name = name