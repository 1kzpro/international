#include <iostream>
using namespace std;

struct Node
{
    int data;
    struct Node *left;
    struct Node *right;

    Node(int data)
    {
        this->data = data;
        left = NULL;
        right = NULL;
    }
};

void printInorder(struct Node* node)   
{   
    if (!node)   
        return;
    
    /* first recur on left child */
    printInorder(node->left);
    
    /* then print the data of node */
    cout << node->data << " ";
    
    /* now recur on right child */
    printInorder(node->right);
}

int main() {
    return 0;
}