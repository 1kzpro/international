#include <iostream>

struct Node
{
    int value;
    Node *prev;
    Node *next;
};

class DoublyLinkedList
{
public:
    Node *head;

    int findNode(int value)
    {
        int currentIndex = 1;
        Node *currentNode = head;
        while (currentNode && currentNode->value != value)
        {
            currentNode = currentNode->next;
            currentIndex++;
        }

        if (currentNode)
        {
            return currentIndex;
        }
        return -1;
    }

    Node *insertNode(int index, int value)
    {
        if (index < 0)
            return nullptr;

        int currentIndex = 1;
        Node *currentNode = head;
        while (currentNode && index > currentIndex)
        {
            currentNode = currentNode->next;
            currentIndex++;
        }
        if (index > 0 && currentNode == nullptr)
            return nullptr;

        Node *newNode = new Node;
        newNode->value = value;
        if (index == 0)
        {
            newNode->next = head;
            if (head)
            {
                head->prev = newNode;
            }
            head = newNode;
        }
        else
        {
            newNode->prev = currentNode->prev;
            newNode->next = currentNode;
            currentNode->prev->next = newNode;
            currentNode->prev = newNode;
        }
        return newNode;
    }

    int deleteNode(int value)
    {
        Node *currentNode = head;
        int currentIndex = 1;
        while (currentNode && currentNode->value != value)
        {
            currentNode = currentNode->next;
            currentIndex++;
        }

        if (currentNode)
        {
            if (currentNode->prev)
            {
                currentNode->prev->next = currentNode->next;
                if (currentNode->next)
                {
                    currentNode->next->prev = currentNode->prev;
                }
            }
            else
            {
                head = currentNode->next;
                if (currentNode->next)
                {
                    currentNode->next->prev = currentNode->prev;
                }
            }

            delete currentNode;
            return currentIndex;
        }

        return 0;
    }

    Node *getNode(int index)
    {
        if (index < 0)
            return nullptr;

        int currentIndex = 1;
        Node *currentNode = head;

        while (currentNode && index > currentIndex)
        {
            currentNode = currentNode->next;
            currentIndex++;
        }

        return currentNode;
    }

    void display()
    {
        printf("Values of list: ");

        Node *currentNode = head;

        while (currentNode)
        {
            printf("%d ", currentNode->value);
            currentNode = currentNode->next;
        }

        printf("\n");
    }

    void display(Node ** head)
    {
        printf("Values of list: ");

        Node *currentNode = *head;

        while (currentNode)
        {
            printf("%d ", currentNode->value);
            currentNode = currentNode->next;
        }

        printf("\n");
    }

    void recursive_display(Node *currentNode)
    {

        if (!currentNode)
        {
            return;
        }
        printf("%d ", currentNode->value);
        recursive_display(currentNode->next);
    }

    void reverse(Node *node)
    {
        // If empty list, return
        if (!node)
            return;

        // swap
        Node *temp = node->next;
        node->next = node->prev;
        node->prev = temp;

        // If the prev is now NULL, the list
        // has been fully reversed
        if (!node->prev)
            head = node;
            return;

        // Otherwise, keep going
        return reverse(node->prev);
    }

    void reverse_iteration(Node **head)
    {
        Node *temp = NULL;
        Node *current = *head;

        /* swap next and prev for all nodes of  doubly linked list */
        while (current != NULL)
        {
            temp = current->prev;  
            current->prev = current->next;  
            current->next = temp;              
            current = current->prev;
        }
        /* Before changing the head, check for the cases like empty  list and list with only one node */
        if (temp != NULL) {
            *head = temp->prev;
        }
    }
};