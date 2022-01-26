#include<stdio.h>
#include <math.h>
#define N 10
int main() {
    int a[N];
    int i, s = 0;
    double avg = 0.0;
    printf("input 10 numbers:\n", N);
    for (i=0; i<N; i++) {
        scanf("%d", &a[i]);
        s += sqrt(a[i]);
    }
    avg = s/N;
    printf("The average is %f\n", avg);
    return avg;
}