/*
 * Midterm Exam 2
 * Software Construction COMP2710
 * Instructor: Xuechao Li @ Auburn University
 * Student: Kazybek Mizam
 * To run this code: g++ TowerOfHanoi_plain.cpp -o TowerOfHanoi_plain.out
 */

#include <iostream>
#include <time.h>
using namespace std;

int counter = 0; // Used to count seconds

void moveOneDisk(int disk, char Original, char Dest) {
	counter++;
	cout << "Move Disk " << disk << " from " << Original << " to " << Dest << endl;
}

// tower of HANOI function implementation
void moveDisk(int n, char Original, char Aux, char Dest)
{
	// printf("%c %c %c \n", Original, Aux, Dest);
	if (n == 1) {
		moveOneDisk(n, Original, Dest);
	} else {
		moveDisk(n-1, Original, Dest, Aux);
		moveOneDisk(n, Original, Dest);
		moveDisk(n-1, Aux, Original, Dest);
	}
}

// main program
int main()
{
	int n;
	time_t start, end;

	cout << "Enter the number of disks:";
	cin >> n;

	// calling the moveDisk
	time(&start);
	moveDisk(n, 'A', 'B', 'C');
	time(&end);

	double elapse = difftime(end, start);
	cout << "The elapsed time is " << elapse << " seconds"
		 << " for moving " << n << " disks " << endl;

	// printf("%d \n", counter);

	return 0;
}
