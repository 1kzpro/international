#include <cstdlib> 
#include <ctime> 
#include <iostream>
using namespace std;
int main() 
{ 
    srand((unsigned)time(0)); 
    for(int index=0; index<5; index++) 
        cout << (rand()%10)+1<< endl; 
}
