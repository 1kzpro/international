#include <iostream>
#include "double_node.h"
using namespace std;
int main() {

    DoublyLinkedList *myListD = new DoublyLinkedList;
    myListD->insertNode(0, 1);
    myListD->insertNode(0, 2);
    myListD->insertNode(0, 3);

    myListD->display();
    Node **m_head = NULL;
    myListD->reverse_iteration(m_head);
    // myListD->display(m_head);
    // myListD->display();

    return 0;
}