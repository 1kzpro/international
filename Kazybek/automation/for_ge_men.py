import pyautogui

while True:
    currentMouseX, currentMouseY = pyautogui.position()
    print(currentMouseX, currentMouseY)