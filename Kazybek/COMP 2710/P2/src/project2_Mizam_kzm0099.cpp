/*
    Project 2
    @author Kazybek Mizam
    @version 09/18/20

    My friend Vladimir Eganov explained me how to implement functions

    Compile: g++ ./project2_Mizam_kzm0099.cpp -o project2_Mizam_kzm0099
    Run: ./project2_Mizam_kzm0099.out
*/

#include <iostream>
#include <stdlib.h>
#include <assert.h>
#include <ctime>
using namespace std;

const int TOTAL_RUNS = 10000;
const bool second_constant = true;
const short third_constant = 1;


//prototypes
bool at_least_two_alive(bool A_alive, bool B_alive, bool C_alive);
void Aaron_shoots1(bool& B_alive, bool& C_alive);
void Bob_shoots(bool& A_alive, bool& C_alive);
void Charlie_shoots(bool& A_alive,bool& B_alive);
void Aaron_shoots2(bool& B_alive, bool& C_alive);

void test_at_least_two_alive(void);
void test_Aaron_shoots1(void);
void test_Bob_shoots(void);
void test_Charlie_shoots(void);
void test_Aaron_shoots2(void);
void press_any_key(void);

int main() {
   /*
    * This is the main function *...
    */
   srand(time(0));

    test_at_least_two_alive();
    press_any_key();

    test_Aaron_shoots1();
    press_any_key();

    test_Bob_shoots();
    press_any_key();

    test_Charlie_shoots();
    press_any_key();

    test_Aaron_shoots2();
    press_any_key();

    //Strategy 1 loop
    cout << "Ready to test strategy 1 (run 10000 times):\n";
    press_any_key();

    bool A_alive = true, B_alive = true, C_alive = true;
    int A_wins1 = 0, B_wins = 0, C_wins = 0;
    for (int i = 0; i < TOTAL_RUNS; i++ ){
        A_alive = true;
        B_alive = true;
        C_alive = true;
        while (at_least_two_alive(A_alive, B_alive, C_alive))
        {
            if (A_alive) {
                Aaron_shoots1(B_alive, C_alive);
            }
            if (B_alive) {
                Bob_shoots(A_alive, C_alive);
            }
            if (C_alive) {
                Charlie_shoots(A_alive, B_alive);
            }

        }
        A_alive ? A_wins1++ : A_wins1;
        B_alive ? B_wins++ : B_wins;
        C_alive ? C_wins++ : C_wins;
    }

    cout << "Aaron won " << A_wins1 << "/10000 duels or " << (double)A_wins1 / TOTAL_RUNS * 100 << "%\n"
         << "Bob won " << B_wins << "/10000 duels or " << (double)B_wins / TOTAL_RUNS * 100 << "%\n"
         << "Charlie won " << C_wins << "/10000 duels or " << (double)C_wins / TOTAL_RUNS * 100 << "%\n"
         << endl;

    //Strategy 2 loop
    cout << "Ready to test strategy 2 (run 10000 times):\n";
    press_any_key();

    int A_wins2 = 0;
    B_wins = 0, C_wins = 0;
    for (int i = 0; i < TOTAL_RUNS; i++ ){
        A_alive = true;
        B_alive = true;
        C_alive = true;
        while (at_least_two_alive(A_alive, B_alive, C_alive)) {
            if (A_alive) {
                Aaron_shoots2(B_alive, C_alive);
            }
            if (B_alive) {
                Bob_shoots(A_alive, C_alive);
            }
            if (C_alive) {
                Charlie_shoots(A_alive, B_alive);
            }
        }
        if (A_alive) A_wins2++;
        if (B_alive) B_wins++;
        if (C_alive) C_wins++;


    }

    cout << "Aaron won " << A_wins2 << "/10000 duels or " << (double)A_wins2 / TOTAL_RUNS * 100 << "%\n"
         << "Bob won " << B_wins << "/10000 duels or " << (double)B_wins / TOTAL_RUNS * 100 << "%\n"
         << "Charlie won " << C_wins << "/10000 duels or " << (double)C_wins / TOTAL_RUNS * 100 << "%\n"
         << endl;
    
    if ((double)(A_wins2 / TOTAL_RUNS) * 100 > (double)(A_wins1 / TOTAL_RUNS) * 100) {
        cout << "Strategy 1 is better than strategy 2.\n";
    } else {
        cout << "Strategy 2 is better than strategy 1.\n";
    }

    return 0;
}

/* Implementation of at_least_two_alive() */
bool at_least_two_alive(bool A_alive, bool B_alive, bool C_alive) {
    /* add the implementation below */
    return (A_alive & B_alive) | (B_alive & C_alive) | (A_alive & C_alive);
}

/* Implementation of the test driver for at_least_two_alive() */
void test_at_least_two_alive(void) {
    cout << "Unit Testing 1: Function -at_least_two_alive()\n";
    cout << "\tCase 1: Aaron alive, Bob alive, Charlie alive\n";
    assert(true == at_least_two_alive(true, true, true));
    cout << "\tCase passed ...\n";
    cout << "\tCase 2: Aaron dead, Bob alive, Charlie alive\n";
    assert(true == at_least_two_alive(false, true, true));
    cout << "\tCase passed ...\n";
    /* add test cases 4-6 below */
    cout << "\tCase 3: Aaron alive, Bob dead, Charlie alive\n";
    assert(true == at_least_two_alive(true, false, true));
    cout << "\tCase passed ...\n";
    cout << "\tCase 4: Aaron alive, Bob alive, Charlie dead\n";
    assert(true == at_least_two_alive(true, true, false));
    cout << "\tCase passed ...\n";
    cout << "\tCase 5: Aaron dead, Bob dead, Charlie alive\n";
    assert(false == at_least_two_alive(false, false, true));
    cout << "\tCase passed ...\n";
    cout << "\tCase 6: Aaron dead, Bob alive, Charlie dead\n";
    assert(false == at_least_two_alive(false, true, false));
    cout << "\tCase passed ...\n";
    cout << "\tCase 7: Aaron alive, Bob dead, Charlie dead\n";
    assert(false == at_least_two_alive(true, false, false));
    cout << "\tCase passed ...\n";
    cout << "\tCase 8: Aaron dead, Bob dead, Charlie dead\n";
    assert(false == at_least_two_alive(false, false, false));
    cout << "\tCase passed ...\n";
}

void Aaron_shoots1(bool& B_alive, bool& C_alive) {
    /* Strategy 1: Use call by reference
    * Input: B_alive indicates whether Bob alive or dead 
    *        C_alive indicates whether Charlie is alive or dead
    * Return: Change B_alive into false if Bob is killed.  
    *         Change C_alive into false if Charlie is killed. 
    */

   /* Assume that the probabilty of hit a target is 33 percent */

    int shoot_target_result = rand() % 100;
    if (shoot_target_result <= 33) {
        if(C_alive) {
            C_alive = false;
        } else if (B_alive) {
            B_alive = false;
        }
    }
}

void test_Aaron_shoots1(){
    cout << "Unit Testing 2: Function Aaron_shoots1(Bob_alive, Charlie_alive)\n";

    bool B_alive = true, C_alive = true;
    cout << "\tCase 1: Bob alive, Charlie alive\n"
         << "\t\tAaron is shooting at Charlie\n";
    Aaron_shoots1(B_alive, C_alive);
    assert(true == B_alive | C_alive);

    B_alive = false; C_alive = true;
    cout << "\tCase 2: Bob dead, Charlie alive\n"
         << "\t\tAaron is shooting at Charlie\n";
    Aaron_shoots1(B_alive, C_alive);
    assert(false == B_alive);

    B_alive = true; C_alive = false;
    cout << "\tCase 3: Bob alive, Charlie dead\n"
         << "\t\tAaron is shooting at Bob\n";
    Aaron_shoots1(B_alive, C_alive);
    assert(false == C_alive);
}

void Bob_shoots(bool& A_alive, bool& C_alive) {
    /* Call by reference
    * Input: A_alive indicates if Aaron is alive or dead 
    *        C_alive indicates whether Charlie is alive or dead 
    * Return: Change A_alive into false if Aaron is killed.  
    *         Change C_alive into false if Charlie is killed. 
    */
    int shoot_target_result = rand() % 100;
    if (shoot_target_result <= 50) {
        if (C_alive) {
            C_alive = false;
        } else if(A_alive){
            A_alive = false;
        }
    }
}

void test_Bob_shoots(void) {
    cout << "Unit Testing 3: Function Bob_shoots(Aaron_alive, Charlie_alive)\n";

    bool A_alive = true, C_alive = true;
    cout << "\tCase 1: Aaron alive, Charlie alive\n"
         << "\t\tBob is shooting at Charlie\n";
    Bob_shoots(A_alive, C_alive);
    assert(true == A_alive | C_alive);

    A_alive = false; C_alive = true;
    cout << "\tCase 2: Aaron dead, Charlie alive\n"
         << "\t\tBob is shooting at Charlie\n";
    Bob_shoots(A_alive, C_alive);
    assert(false == A_alive);

    A_alive = true; C_alive = false;
    cout << "\tCase 3: Aaron alive, Charlie dead\n"
         << "\t\tBob is shooting at Aaron\n";
    Bob_shoots(A_alive, C_alive);
    assert(false == C_alive);
}

void Charlie_shoots(bool& A_alive, bool& B_alive) {
    /* Call by reference
    * Input: A_alive indicates if Aaron is alive or dead 
    *        B_alive indicates whether Bobis alive or dead 
    * Return: Change A_alive into false if Aaronis killed.  
    *         Change B_alive into false if Bobis killed. 
    */
    if (B_alive){
        B_alive = false;
    } else if(A_alive){
        A_alive = false;
    }
}

void test_Charlie_shoots() {
    cout << "Unit Testing 4: Function Charlie_shoots(Aaron_alive, Bob_alive)\n";

    bool A_alive = true, B_alive = true;
    cout << "\tCase 1: Aaron alive, Bob alive\n"
         << "\t\tCharlie is shooting at Bob\n";
    Charlie_shoots(A_alive, B_alive);
    assert(false == B_alive);
    assert(true == A_alive);

    A_alive = false; B_alive = true;
    cout << "\tCase 2: Aaron dead, Bob alive\n"
         << "\t\tCharlie is shooting at Bob\n";
    Charlie_shoots(A_alive, B_alive);
    assert(false == B_alive);
    assert(false == A_alive);

    A_alive = true; B_alive = false;
    cout << "\tCase 3: Aaron alive, Bob dead\n"
         << "\t\tCharlie is shooting at Aaron\n";
    Charlie_shoots(A_alive, B_alive);
    assert(false == B_alive);
    assert(false == A_alive);
}

void Aaron_shoots2(bool& B_alive, bool& C_alive) {
    /* Strategy 2: Use call by reference
    * Input: B_alive indicates whether Bob alive or dead 
    *        C_alive indicates whether Charlie is alive or dead 
    * Return: Change B_alive into false if Bob is killed.  
    *         Change C_alive into false if Charlie is killed.
    */
    int shoot_target_result = rand() % 100;
    if (!(B_alive && C_alive) && shoot_target_result <= 33) {
        if (C_alive){
            C_alive = false;
        }else if(B_alive){
            B_alive = false;
        }
    }
}

void test_Aaron_shoots2() {
    cout << "Unit Testing 5: Function Aaron_shoots2(Bob_alive, Charlie_alive)\n";

    bool B_alive = true, C_alive = true;
    cout << "\tCase 1: Bob alive, Charlie alive\n"
         << "\t\tAaron intentionally misses his first shot\n";
    Aaron_shoots2(B_alive, C_alive);
    assert(true == B_alive);
    assert(true == C_alive);

    B_alive = false; C_alive = true;
    cout << "\tCase 2: Bob dead, Charlie alive\n"
         << "\t\tAaron is shooting at Charlie\n";
    Aaron_shoots2(B_alive, C_alive);
    assert(false == B_alive);

    B_alive = true; C_alive = false;
    cout << "\tCase 3: Bob alive, Charlie dead\n"
         << "\t\tAaron is shooting at Bob\n";
    Aaron_shoots2(B_alive, C_alive);
    assert(false == C_alive);
}

void press_any_key() {
    cout << "Press Enter to continue...";
    cin.ignore().get(); //Pause Command for Linux Terminal
}