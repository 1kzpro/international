/**
 * Project 2
 * @author Kazybek Mizam
 * @version 09/18/20
 * 
 * Compile: g++ ./project3_Mizam_kzm0099.cpp -o project2_Mizam_kzm0099
 * Run: ./project3_Mizam_kzm0099.out
*/

#include <fstream>
#include <iostream>
#include <cstdlib>
using namespace std;

const int MAX_SIZE = 100;

int read_file(int inputArray[], ifstream& inStream);
int sort(int * inputArray1, int &inputArray1_size, int * inputArray2, int &inputArray2_size, int * outputArray);
void write_file(int * outputArray, int &outputArray_size, string output_filepath);
void print_array(int * array, int &size);

int main() {
    ifstream instream1;
    int input_array1[MAX_SIZE];
    int input_array1_size;

    string filepath1;

    cout << "*** Welcome to Kaza's sorting program ***" << endl;
    instream1.open("input1.txt");
    input_array1_size = read_file(input_array1, instream1);
    cout << "entered array num is:" << input_array1_size << endl;
    instream1.close();


    cout << "The list of " << input_array1_size << " numbers in file " << filepath1 << " is:" << endl;
    print_array(input_array1, input_array1_size);
    cout << endl;

    return 0;
}

int read_file(int inputArray[], ifstream& inStream) {
    cout << "entered func" << endl;
    int index = 0;

    cout << "bf" << endl;
    inStream >> inputArray[index];
    
    cout << "before loop" << endl;
    while(! inStream.eof()) {
        cout << "entered loop" << endl;
        cout << inputArray[index] << endl;
        index++;
        inStream >> inputArray[index];
    }
    return index;
}

// Function to print an array of integers
void print_array(int * array, int &size)
{
    for (int i = 0; i < size; i++)
    {
        cout << *(array + i) << endl;
    }
}
