#include <iostream>
#include "double_node.h"
using namespace std;
int main() {

    DoublyLinkedList *myListD = new DoublyLinkedList;
    myListD->insertNode(0, 1);
    myListD->insertNode(0, 2);
    myListD->insertNode(0, 3);
    myListD->insertNode(0, 4);
    myListD->insertNode(0, 5);


    // printf("%d \n", myListD->findNode(7));

    // Node *the_node = myListD->getNode(myListD->findNode(3));

    // printf("Prev Value: %d \n", the_node->prev->value);
    // printf("Current Value: %d \n", the_node->value);
    // printf("Next Value: %d \n", the_node->next->value);

    // myListD->deleteNode(1);

    // the_node = myListD->getNode(myListD->findNode(2));

    // if (the_node->prev)
    //     printf("Prev Value: %d \n", the_node->prev->value);

    // printf("Current Value: %d \n", the_node->value);

    // if (the_node->next)
    // printf("Next Value: %d \n", the_node->next->value);

    myListD->display();
    myListD->reverse(myListD->head);
    myListD->recursive_display(myListD->head);

    return 0;
}