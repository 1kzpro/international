#include <iostream>
#include "single_node.h"
using namespace std;
int main() {
    SinglyLinkedList *myListS = new SinglyLinkedList;
    myListS->insertNode(0, 1);
    myListS->insertNode(0, 2);
    myListS->insertNode(0, 3);
    myListS->insertNode(0, 4);
    myListS->insertNode(0, 5);

    cout << myListS->deleteNode(5) << endl;

    myListS->display();

    return 0;
}