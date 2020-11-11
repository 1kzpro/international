#include <iostream>

int no_se (int n1, int n2) 
{
    printf("%d %d\n", n1, n2); 
    return n1 + n2; 
}

int se (int *n1, int * n2) 
{
    return *n1 += *n2; 
}

// int main() 
// {
//     int x = 2, y = 2;
//     int result = se(&x,&y) + x + no_se(x,y);
//     printf("%d\n", result); 
// }

// int main() 
// {
//     int x = 2, y = 2;
//     int result = no_se(x,y) + x + se(&x,&y);
//     printf("%d\n", result); 
// }

int main() 
{
    int x = 2, y = 2;
    int result = x + se(&x,&y) + no_se(x,y);
    printf("%d\n", result); 
}