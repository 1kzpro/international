/**
 * Project 2
 * @author Kazybek Mizam
 * @version 09/18/20
 * 
 * I used quicksort method from this link https://medium.com/human-in-a-machine-world/quicksort-the-best-sorting-algorithm-6ab461b5a9d0
 * 
 * Compile: g++ ./project3_Mizam_kzm0099.cpp -o project3_Mizam_kzm0099
 * Run: ./project3_Mizam_kzm0099.out
*/

#include <fstream>
#include <iostream>
#include <cstdlib>
using namespace std;

const int MAX_SIZE = 100;

int read_file(int inputArray[], ifstream& instream);
int sort(int * inputArray1, int &inputArray1_size, int * inputArray2, int &inputArray2_size, int * outputArray);
void write_file(int * outputArray, int &outputArray_size, string output_filepath);
void print_array(int * array, int &size);
void print_array_inline(int * array, int &size);

int main() {
    ifstream instream;
    int input_array1[MAX_SIZE];
    int input_array1_size;
    int input_array2[MAX_SIZE];
    int input_array2_size;
    int output_array[MAX_SIZE];
    int output_array_size;

    string filepath1;
    string filepath2;
    string output_filepath;

    cout << "*** Welcome to Kaza's sorting program ***" << endl;
    cout << "Enter the first input file name: ";
    cin >> filepath1;
    instream.open(filepath1);
    if (instream.fail()) {cout << "Input file opening failed." << endl;exit(1);}
    input_array1_size = read_file(input_array1, instream);
    instream.close();
    cout << "entered filename is:" << filepath1 << endl;
    cout << "The list of " << input_array1_size << " numbers in file " << filepath1 << " is:" << endl;
    print_array(input_array1, input_array1_size);
    cout << endl;

    cout << "Enter the second input file name: ";
    cin >> filepath2;
    instream.open(filepath2);
    input_array2_size = read_file(input_array2, instream);
    instream.close();
    cout << "The list of " << input_array2_size << "numbers in file " + filepath2 + " is:" << endl;
    print_array(input_array2, input_array2_size);
    cout << endl;

    output_array_size = sort(input_array1, input_array1_size, input_array2, input_array2_size, output_array);
    cout << "The sorted list of " << output_array_size << " numbers is: ";
    print_array_inline(output_array, output_array_size);

    cout << "Enter the output file name: ";
    cin >> output_filepath;
    write_file(output_array, output_array_size, output_filepath);
    cout << "*** Please check the new file - " << output_filepath  << " ***" << endl;
    cout << "*** Goodbye. ***" << endl;

    return 0;
}

int read_file(int inputArray[], ifstream& inStream) {
    int index = 0;
    inStream >> inputArray[index];
    
    while(! inStream.eof()) {
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

// Function to print an array of integers
void print_array_inline(int * array, int &size)
{
    for (int i = 0; i < size; i++)
    {
        cout << *(array + i) << " ";
    }
    cout << endl;
}

// Function to swap two pointers
void swap(int *a, int *b)
{
    int temp = *a;
    *a = *b;
    *b = temp;
}

// Function to run quicksort on an array of integers
// l is the leftmost starting index, which begins at 0
// r is the rightmost starting index, which begins at array length - 1
void quicksort(int arr[], int l, int r)
{
    // Base case: No need to sort arrays of length <= 1
    if (l >= r)
    {
        return;
    }
    
    // Choose pivot to be the last element in the subarray
    int pivot = arr[r];

    // Index indicating the "split" between elements smaller than pivot and 
    // elements greater than pivot
    int cnt = l;

    // Traverse through array from l to r
    for (int i = l; i <= r; i++)
    {
        // If an element less than or equal to the pivot is found...
        if (arr[i] <= pivot)
        {
            // Then swap arr[cnt] and arr[i] so that the smaller element arr[i] 
            // is to the left of all elements greater than pivot
            swap(&arr[cnt], &arr[i]);

            // Make sure to increment cnt so we can keep track of what to swap
            // arr[i] with
            cnt++;
        }
    }
    
    // NOTE: cnt is currently at one plus the pivot's index 
    // (Hence, the cnt-2 when recursively sorting the left side of pivot)
    quicksort(arr, l, cnt-2); // Recursively sort the left side of pivot
    quicksort(arr, cnt, r);   // Recursively sort the right side of pivot
}

int merge(int * inputArray1, int &inputArray1_size, int * inputArray2, int &inputArray2_size, int * outputArray) {
    for(int i = 0; i < inputArray1_size; i++) {
        *(outputArray + i) = *(inputArray1 + i);
    }


    for(int i = 0; i < inputArray2_size; i++) {
        *(outputArray + inputArray1_size + i) = *(inputArray2 + i);
    }

    return inputArray1_size + inputArray2_size;
}

int sort(int * inputArray1, int& inputArray1_size, int * inputArray2, int& inputArray2_size, int * outputArray) {
    int outputArray_size = merge(inputArray1, inputArray1_size, inputArray2, inputArray2_size, outputArray);

    quicksort(outputArray, 0, outputArray_size - 1);
    return outputArray_size;
}

void write_file(int * outputArray, int &outputArray_size, string output_filepath) {
    ofstream out_file;

    out_file.open(output_filepath);
    for (int i = 0; i < outputArray_size; i++)
    {
        out_file << *(outputArray + i) << "\n";
    }
    out_file.close();
}
