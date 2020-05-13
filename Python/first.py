# -> One line comment https://www.w3schools.com/python/default.asp

# ''
# ""
# both same

# a = 'Yuhao'
# b = "Yuhao"

# a == b

'''
    This is your long comment
'''

"""
    This is also long comment
    /*
        This is Java
    */
"""

# a = 'Yuhao' # variable a
# b = "Kazybek" # variable b

# if a == b: # same as a.equals(b) in java
#     #here
#     print('They are same') # same as System.out.println("They are same")

# : same as {} in java
# [] -> list

# if a == b: 
#     c = 'Kazybek'
#     print('They are same')
# elif a != b:
#     print('They are not same')
# else:
#     print('nothing')

# else if -> elif

#ifKeyPressed -> if_key_pressed
# ifKeyPressed = True # it will work fine
# if_key_pressed = True # just little python standard

a = 'Yuhao' # variable a
b = "Kazybek" # variable b
# c = 'Doge'
# a, b, c = 'Yuhao' + ' Zhu', 7, 'Doge'

# number = 5.7

# number = 'Yuhao'

# number = True

# print(type(number))

# #the variable can be anything and it defines automatically with this language
# c = 5.7 # int
# # str means string

# d = int(c)

# print(a + ' ' + b + ' ' + str(c)) # casting float->int String(5)
# print(c + 3)
# , -> + ' ' +

# Python does not have CHAR
# a = 'A' -> string

# [] -> list #kinda ArrayBag
# () -> tuple
#range() -> range same as length

# int[] a = {1, 2, 3}

# for j in range(5): #i < 5 i++
#     print(type(j))
#     print(j)

# people = ['Yuhao', 'Kazybek', 'Doge']

# __str__
#len() -> people.length

# for i in range(len(people)):
#     print(people[i])

# people.append('Shanghai') # add Arraybag a; a.add('shaghai')
# people.remove('Doge')
# people.pop() #removes by index

# print(people)

# people = ('Yuhao', 'Kazybek', 'Doge')
# a = people.index('Doge')
# print(a)

# print(people[0])


# dictionary, map, struct
# person = {
#     'name': 'Kazybek'
# }

# book = {
#     'name': 'Yuhao',
#     'price': 1000000000000,
#     'genre': 'Hentai',
#     'owner': person
# }

# car = {
#     'model': 'Benz',
#     'engine': 'v8',
#     'color': 'Space Gray',
#     'range': 1000,
#     'owners': ['Yuhao', 'Doge'],
#     'book': book
# }

# # car['range'] += 1000

# # car['engine'] = 'v10'

# print(car['book'])


# a = False # boolean
# print(a)

# dictionary, map, struct
# car = {
#     'model': 'Benz',
#     'engine': 'v8',
#     'color': 'Space Gray',
#     'range': 1000
# }

# # for key in car:
# #     print(key)

# # print(car.items())

# data = car.items()

# for key, value, price in [('model', 'Benz', 1234), ('engine', 'v8', 1234), ('color', 'Space Gray', 1234), ('range', 1000, 1234)]:
#     print(value)

# print(car.get('model'))

# if 'k' in ['Benz', 'v8', 'Space Gray', 1000]:
#     print('Yea')
# else:
#     print('Oh no')

# a = 5
# print(a)

# a = 'Yuhao'
# print(a)

# a = 'Yuhao'

# b = 'Kazybek'

# a = b

# print(a)
# print(b)

# b = 'Yuhao'

# print(a)
# print(b)

# Methods function


# def greeting(a, b):
#     print('Hello', a, b)

name = 'Doge'
name2 = 'Yuhao'
# greeting(name, name2)


# class Person:
#     name = 'Kazybek'
#     def __init__(self, a, b):
#         self.name1 = a
#         self.name2 = b

#     def greeting(self, func):
#         return 'Have ' + func() + ' Day'

#     def print_all(self, day):
#         print(self.name1, self.name2, self.name, day)

#     @staticmethod
#     def method():
#         print('LOL')

# person = Person(name, name2)

# def good():
#     return 'Good'

# return_value = person.greeting(good)

# print(return_value)

# person.method()

def greeting(func):
    def wrapper():
        print("Hello", func(), 'my ge men')
    return wrapper

@greeting
def yuhao():
    return 'Yuhao'

yuhao()

#@greeting -> greeting(yuhao)
