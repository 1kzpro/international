#include<stdio.h>
#include <math.h>
#define N 10
int main() {
    int a[N] = {-1,2,-3,4,-5,6,7,-8,9,-10};
    int i, j = 0;
    int max = 0;
    for (i=0; i<N; i++) {
        int sum = 0;
        for (j=i; j<N; j++) {
            sum = sum + a[j];
            if (sum > max) {
                max = sum;
            }
        }
        
        printf("The max is %d\n", max);
        return max;
    }
}