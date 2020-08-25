//============================================================================
// Name        : project3_Eganov_vze0008.cpp
// Author      : Eganov Vladimir
// Version     : Summer 2020
// Copyright   : Auburn University
// Description : COMP2710 Software Construction Project 3
// Compile     : g++ project2_Eganov_vze0008.cpp -o project3_Eganov_vze0008.cpp.out
// Run         : ./project3_Eganov_vze0008.out
//============================================================================
//Sample code for Project 3
#include <fstream>
#include <iostream>
#include <vector>
using namespace std;
//int readfile( int inputArray[], ifstream& instream);
void write_file(string, vector <int>); // writes data in file
vector <int> merge( vector<int>, vector<int>); // returns vector of sorted data
bool check_file(string); // checks file for readability
vector<int> read_file(string); // reads data into vector

bool check_file(string file) {
/* Input file stream. (ifstream) */
    ifstream stream;
/* Check whether file exists. */
    stream.open(file.c_str());
    if  (stream.fail()) {
        cout<<"error occured while opening file ";//print that error occured
        return false;
    }
    stream.close();
    return true;
}
vector<int> read_file(string file) {
    /* Input file stream. (ifstream) */
    ifstream stream;
    /* Vector containing numbers from file. (vector<int>) */
    vector <int> v;
    /* Integer read from file. (int) */
    int i;
    /* Add each number in the file to a vector. */
    stream.open(file);
    stream >> i;
    while (!stream.eof()) {
        v.push_back(i);
        stream >> i;
    }
    return v;
}
void write_file(string file,  vector<int> v) {
    /* Output file stream. (ofstream) */
    ofstream stream;
    stream.open(file);
    for(int i; i < v.size();i++){
        stream << v[i]<<"\n";
    }
}
vector<int> merge(vector<int> v1, vector<int>  v2 ) {
    /*   Compare both vectors. */
    vector<int> v3;
    int i1, i2;
    i1=0;
    i2=0;

    while( (i1 < v1.size()) && (i2 < v2.size()) ) {
        if  (v1[i1] > v2[i2]) {
            v3.push_back(v2[i2]);
            i2++;
        }  else
        {
            v3.push_back(v1[i1]);
            i1++;
        }
    }
    /* Add any remaining numbers from vector one. */
    if  (i1 < v1.size()) {
        for(;i1 < v1.size();i1++ ){
            v3.push_back(v1[i1]);
        }
    }
    /* Add any remaining numbers from vector two. */
    if  (i2 < v2.size()) {
        for(;i2 < v2.size();i1++ ){
            v3.push_back(v2[i2]);
        }
    }
    return v3;
}
int main(){
    ifstream inStream1;
    ifstream inStream2;
    vector <int>  iArray1;
    int iArray1_size;
    vector <int> iArray2;
    int iArray2_size;
    vector <int> output;
    string file1;
    string file2;
    string file3;
    cout<<"*** Welcome to Eganov's sorting program	***\n";
    do  {
        cout<<"Enter the first input file name: ";
        cin>>file1;
        /* user friendly interfaces */
    }  while (cin.fail() || !check_file(file1));
    cout<<"list of numbers in the file\n";
    inStream1.open(file1);
    iArray1 = read_file(file1);
    inStream1.close();
    for(int i = 0;i < iArray1.size();i++ ){
        cout<<iArray1[i]<<"\n";
    }
    do  {
        cout<<"Enter the second input file name: ";
        cin>>file2;
        /*   user friendly interfaces */
    }  while (cin.fail() || !check_file(file2));
    inStream2.open( file2);

    iArray2 = read_file(file2);
    cout<<"list of numbers in the file\n";
    for(int i = 0;i < iArray2.size();i++ ){
        cout<<iArray2[i]<<"\n";
    }

    inStream2.close();
    output = merge(iArray1,iArray2);
    //cout<<"processing\n";
    cout<<"The sorted list of numbers is:";
    for(int i = 0;i < output.size();i++ ){
        cout<<output[i]<<" ";
    }
    /*   Get name of output file. */
    do  {
        cout<<"\ntype in output file ";
        cin>>file3;
        //to_string(file3,output);
        //cout<<output;
        /* user friendly interfaces */
    }  while (cin.fail());
    //cout<<"processing\n";
    /* Write combined vector to output file. */
    write_file(file3, output);
    cout<<"\n*** Please check the new file - "<<file3<< "***\n*** Goodbye. ***";
    //write_file(out, output);
    return 0;
}
