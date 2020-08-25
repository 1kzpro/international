//============================================================================
// Name        : project4_Eganov_vze0008.cpp
// Author      : Eganov Vladimir
// Version     : Summer 2020
// Copyright   : Auburn University
// Description : COMP2710 Software Construction Project 4
// Compile     : g++ project4_Eganov_vze0008.cpp -o project4_Eganov_vze0008.cpp.out
// Run         : ./project4_Eganov_vze0008.out
//============================================================================
#include <string>
#include <iostream>

using namespace std;
//Structure for creating a linked list that holds a trivia question,answer and point\amount.
struct triva_node
{
    string question;
    string answer;
    int   point;
    triva_node *next;
};
//creates a pointer to triva_node.
typedef triva_node * ptr_node;
//Prototypes
void Unit_Test();
void init_question_list(ptr_node&);
void add_question(ptr_node&);
int  ask_question(ptr_node, int);
int get_num_of_ques(ptr_node);
//creates two versions.
#define UNIT_TESTING
//#define triva_quiz
int   main() {
    //Unit_Test();
    ptr_node node_list = new triva_node;
    int num_of_questions = 0;
    string answer;
    //Creates a new trivia game /
    //Sets up three original  questions/
    //Sets up loop for user to input his or her own questions.
    //Quiz questions are stored in linked list.
#ifdef triva_quiz
    init_question_list(node_list);
    num_of_questions = 3;
    cout << "*** Welcome to Eganov's trivia quiz game ***\n";

    add_question(node_list);
    num_of_questions ++;

    cout<< "Continue? (Yes/No): ";
    //cin >> answer;
    getline(cin, answer);
    while (answer.compare("Yes") == 0 ){
        add_question(node_list);
        num_of_questions++;
        cout<< "Continue? (Yes/No): ";
        //cin >> answer;
        getline(cin, answer);
    }
    //This is start of Trivia quiz game.
    ask_question(node_list,num_of_questions);
    cout<< "\n*** Thank you for playing the trivia quiz game. Goodbye! ***";
    return 0;
}
#endif
//Method for testing
#ifdef UNIT_TESTING
    cout << "***This is a debugging version ***\n";
    Unit_Test();
    return 0;
}
#endif

//initializes the quiz to have these three questions inputted into the linked list.
void init_question_list(ptr_node& q_list) {

    ptr_node cur_ptr = new triva_node;
    //q_list = cur_ptr;
    //add first hard coded question
    q_list->question ="How long was the shortest war on record?";
    q_list->answer ="38";
    q_list->point = 100;
    q_list->next = new triva_node;
    cur_ptr = q_list->next;

    //add second hard coded question
    cur_ptr->question ="What was Bank of America’s original name? (Hint: Bank of Italy or Bank of Germany)";
    cur_ptr->answer ="Bank of Italy";
    cur_ptr->point = 50;
    cur_ptr->next = new triva_node;
    cur_ptr = cur_ptr->next;

    //add third hard coded question
    cur_ptr->question ="What is the best-selling video game of all time? (Hint: Call of Duty or Wii Sports)?";
    cur_ptr->answer ="Wii Sports";
    cur_ptr->point = 120;
    cur_ptr->next = NULL;

}
//gives user the option to add a question to the linked list.
//question is added to the front of the linked list.
void add_question(ptr_node& q_list) {
    string points;
    int point;
    ptr_node new_ptr = new triva_node;
    //gets data from user.
    cout<< "Enter a question: ";
    getline(cin, new_ptr->question);
    //cin >>new_ptr->question;
    cout<< "Enter an answer: ";
    getline(cin, new_ptr->answer);
    //cin >> new_ptr->answer;
    cout<< "Enter award points: ";
    getline(cin, points);
    //cin >> point;
    new_ptr->point = stoi(points);
    new_ptr->next = q_list;
    //getline(cin, new_ptr->point);
    q_list = new_ptr;

}
//Checks for null value
//Ask the user trivia questions and starts the game
int   ask_question(ptr_node q_list, int   num_ask) {
    int num_of_questions = get_num_of_ques(q_list);
    int points = 0;
    string user_answer;
    if  (q_list ==   NULL) return 0;
    if (num_ask < 1) {
        cout<< "Warning- the number of trivia to be asked must equal to or be larger than 1.";
    } else if(num_of_questions < num_ask) {
        cout<< "Warning- There is only "<<  num_of_questions<< " trivia in the list.";
    }
    else{
        ptr_node cur_ptr = q_list;
        for  (int   x = 0; x < num_ask; x++) {
            cout<< "Question: "<<  cur_ptr->question<<  endl;
            cout<< "Answer: ";
            getline(cin, user_answer);
            //cin >> user_answer;
            if  (user_answer.compare(cur_ptr->answer) == 0) //correct_answer)
            {
                cout << "Your answer is correct! You receive "<< cur_ptr->point<<  endl;
                points += cur_ptr->point;
            }
            else{
                cout << "Your answer is wrong. The correct answer is "<< cur_ptr->answer<<  endl;
            }
            cur_ptr = cur_ptr->next;

        }
        cout << "Your total points "<< points<<  endl;
    }
    return 0;
}

int get_num_of_ques(ptr_node q_list){
    int num_of_questions = 0;
    while (q_list != NULL) {
        num_of_questions++;
        q_list = q_list->next;
   }
    return num_of_questions;
}


//Test cases to check whether the methods work.
void Unit_Test() {
    ptr_node node_list = new triva_node;
    init_question_list(node_list);
    cout<< "Unit Test Case 1: Ask no question. The program should give a warning message."<<  endl;
    ask_question(node_list, 0);
    cout<< "\nCase 1 Passed\n\n";

    cout<< "Unit Test Case 2.1: Ask 1 question in the linked list.  The tester enters an incorrect answer."<< endl;
    ask_question(node_list, 1);
    cout<< "\nCase 2.1 Passed\n\n";
    cout<< "Unit Test Case 2: Ask 1 question in the linked list.  The tester enters correct answer."<< endl;
    ask_question(node_list, 1);
    cout<< "\nCase 2.2 Passed\n\n";


    //I perform full testing, so there are 8 variants of choosing questions.
    cout<< "Unit Test Case 3: Ask 3 questions in the linked list.  The tester enters all correct answers."<< endl;
    ask_question(node_list, 3);
    cout<< "\nCase 3.1 Passed\n\n";
    cout<< "Unit Test Case 3: Ask 3 questions in the linked list.  The tester enters all incorrect answers."<< endl;
    ask_question(node_list, 3);
    cout<< "\nCase 3.2 Passed\n\n";
    cout<< "Unit Test Case 3: Ask 3 questions in the linked list.  The tester enters 2 correct and 1 incorrect answers."<< endl;
    ask_question(node_list, 3);
    cout<< "\nCase 3.3 Passed\n\n";
    cout<< "Unit Test Case 3: Ask 3 questions in the linked list.  The tester enters 1 correct and 2 incorrect answers."<< endl;
    ask_question(node_list, 3);
    cout<< "\nCase 3.4 Passed\n\n";
    cout<< "Unit Test Case 3: Ask 3 questions in the linked list.  The tester enters 2 incorrect and 1 correct answers."<< endl;
    ask_question(node_list, 3);
    cout<< "\nCase 3.5 Passed\n\n";
    cout<< "Unit Test Case 3: Ask 3 questions in the linked list.  The tester enters correct, incorrect and correct answers."<< endl;
    ask_question(node_list, 3);
    cout<< "\nCase 3.6 Passed\n\n";
    cout<< "Unit Test Case 3: Ask 3 questions in the linked list.  The tester enters incorrect, correct and correct answers."<< endl;
    ask_question(node_list, 3);
    cout<< "\nCase 3.7 Passed\n\n";
    cout<< "Unit Test Case 3: Ask 3 questions in the linked list.  The tester enters 1 incorrect and 2 correct answers."<< endl;
    ask_question(node_list, 3);
    cout<< "\nCase 3.8 Passed\n\n";

    cout<< "Unit Test Case 4: Ask 5 questions in the linked list."<< endl;
    ask_question(node_list, 5);
    cout<< "\nCase 4 Passed\n\n";

    cout<< "\n\n*** End of the Debugging Version ***";
}
