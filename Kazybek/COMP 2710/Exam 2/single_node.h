#include <iostream>

struct Node {
    int value;
    Node * next;
};

class SinglyLinkedList {
    public:
    Node * head;

    int findNode(int value) {
        int currentIndex = 1;
        Node * currentNode = head;
        while (currentNode && currentNode->value != value)
        {
            currentNode = currentNode->next;
            currentIndex++;
        }
        
        if(currentNode) {
            return currentIndex;
        }
        return -1;
    }

    Node * insertNode(int index, int value) {
        if (index < 0) return nullptr;

        int currentIndex = 1;
        Node * currentNode = head;
        while (currentNode && index > currentIndex)
        {
            currentNode = currentNode->next;
            currentIndex++;
        }
        if (index > 0 && currentNode == nullptr) return nullptr;

        Node * newNode = new Node;
        newNode->value = value;
        if (index == 0) {
            newNode->next = head;
            head = newNode;
        }
        else {
            newNode->next = currentNode->next;
            currentNode->next = newNode;
        }
        return newNode;
    }

    int deleteNode(int value) {
        Node * previousNode = nullptr;
        Node * currentNode = head;
        int currentIndex = 1;
        while (currentNode && currentNode->value != value)
        {
            previousNode = currentNode;
            currentNode = currentNode->next;
            currentIndex++;
        }
        
        if(currentNode) {
            if(previousNode) {
                previousNode->next = currentNode->next;
            }
            else {
                head = currentNode->next;
                delete currentNode;
            }
            return currentIndex;
        }

        return 0;
    }

    void display() {
        printf("Values of list: ");

        int currentIndex = 1;
        Node * currentNode = head;

        while (currentNode)
        {
            printf("%d ", currentNode->value);
            currentNode = currentNode->next;
            currentIndex++;
        }

        printf("\n");
    }
};