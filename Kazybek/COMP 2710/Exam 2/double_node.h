#include <iostream>

struct Node {
    int value;
    Node * prev;
    Node * next;
};

class DoublyLinkedList {
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
            if (head) {
                head->prev = newNode;
            }
            head = newNode;
        }
        else {
            newNode->prev = currentNode->prev;
            newNode->next = currentNode;
            currentNode->prev->next = newNode;
            currentNode->prev = newNode;
        }
        return newNode;
    }

    int deleteNode(int value) {
        Node * currentNode = head;
        int currentIndex = 1;
        while (currentNode && currentNode->value != value)
        {
            currentNode = currentNode->next;
            currentIndex++;
        }
        
        if(currentNode) {
            if(currentNode->prev) {
                currentNode->prev->next = currentNode->next;
                if (currentNode->next) {
                    currentNode->next->prev = currentNode->prev;
                }
            }
            else {
                head = currentNode->next;
                if (currentNode->next) {
                    currentNode->next->prev = currentNode->prev;
                }
            }

            delete currentNode;
            return currentIndex;
        }

        return 0;
    }

    Node * getNode(int index) {
        if (index < 0) return nullptr;

        int currentIndex = 1;
        Node * currentNode = head;

        while (currentNode && index > currentIndex)
        {
            currentNode = currentNode->next;
            currentIndex++;
        }
        
        return currentNode;
    }

    void display() {
        printf("Values of list: ");

        Node * currentNode = head;

        while (currentNode)
        {
            printf("%d ", currentNode->value);
            currentNode = currentNode->next;
        }

        printf("\n");
    }
};