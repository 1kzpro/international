/**
 * Project 4
 * @author Kazybek Mizam
 * @version 11/02/20
 * 
 * Code hints used from:
 * I used insert to end from: https://stackoverflow.com/questions/20125477/linked-list-insert-at-the-end-c
 * I used input with space: https://stackoverflow.com/questions/5838711/stdcin-input-with-spaces
 * I solved cin and getline issue with: https://stackoverflow.com/questions/10553597/cin-and-getline-skipping-input
 * I used this function to delete entire list: https://www.geeksforgeeks.org/write-a-function-to-delete-a-linked-list/
 * 
 * Compile: g++ ./project4_Mizam_kzm0099.cpp -o project4_Mizam_kzm0099
 * Run: ./project4_Mizam_kzm0099.out
*/

// #define UNIT_TESTING

#include <iostream>
#include <string>
#include <time.h>
using namespace std;

struct TriviaNode
{
    string question;
    string answer;
    int points;
    TriviaNode * next;
};

// Total number of questions in trivia nodes
int number_of_questions = 3;

/* Function to insert node at the end of list*/
void insert(TriviaNode * &head, string question, string answer, int points) {
    TriviaNode * newNode = new TriviaNode;
    newNode->question = question;
    newNode->answer = answer;
    newNode->points = points;
    newNode->next = NULL;

    if(!head) {
        head = newNode;
        return;
    } else {
        TriviaNode * lastNode = head;
        while (lastNode->next)  {
            lastNode=lastNode->next;
        }

        lastNode->next = newNode;
    }
}

/* Function to delete the entire linked list */
void delete_list(TriviaNode** head)  
{
    /* deref head_ref to get the real head */
    TriviaNode* current = *head;
    TriviaNode* next;
    
    while (current != NULL)  
    {  
        next = current->next;  
        free(current);  
        current = next;  
    }  
        
    /* deref head_ref to affect the real head back  
        in the caller. */
    *head = NULL;  
}

/* Function that creates default questions */
void create_default_questions(TriviaNode * &head) {
    insert(head, "How long was the shortest war on record? (Hint: how many minutes)", "38", 100);
    insert(head, "What was Bank of America’s original name? (Hint: Bank of Italy or Bank of Germany)", "Bank of Italy", 50);
    insert(head, "What is the best-selling video game of all time? (Hint: Call of Duty or Wii Sports)?", "Wii Sports", 20);
}

/* Service function that outputs question */
void display_question(string * question) {
    cout << "Question: " << *question << endl << "Answer: ";
}

/* Service function that outputs correct answer version */
void display_correct(int * points) {
    cout << "Your answer is correct. You receive " << *points << " points." << endl;
}

/* Service function that outputs wrong answer version */
void display_wrong(string * answer) {
    cout << "Your answer is wrong. The correct answer is: " << *answer << endl;
}

/* Service function that outputs total award points version */
void display_total_award(int * total_award) {
    cout << "Your total points: " << *total_award << endl;
}

/* Service function that inputs question */
void input_answer(string * answer) {
    getline(cin, *answer);
}

/* Function that asks user defined questions*/
void ask_user_questions(TriviaNode * &head) {
    string continue_answer;

    do {
        string user_question, user_answer;
        int user_points;

        cout << "Enter a question: ";
        input_answer(&user_question);

        cout << "Enter an answer: ";
        input_answer(&user_answer);

        cout << "Enter award points: ";
        cin >> user_points;

        insert(head, user_question, user_answer, user_points);
        number_of_questions++;

        cout << "Continue? (Yes/No): ";
        cin >> continue_answer;

        cin.ignore();
    } while (continue_answer == "Yes");

    cout << endl;
}

// This is exact reprensentation of quiz() function but without debug version
void quiz_no_unit_test(TriviaNode * head, int num_of_questions, int &total_award) {
    if (head == NULL) {
        return;
    }  
    else if (num_of_questions < 1) {
        printf("Warning - the number of trivia to be asked must equal to or be larger than 1.\n");
        return;
    } else if (num_of_questions > number_of_questions) {
        printf("Warning - There is only %d trivia in the list.", number_of_questions);
        return;
    }

    int currentIndex = 1;
    TriviaNode * currentNode = head;

    total_award = 0;

    while (currentNode != NULL && num_of_questions >= currentIndex)
    {
        string user_answer;
        display_question(&currentNode->question);
        input_answer(&user_answer);
        if (currentNode->answer == user_answer) {
            display_correct(&currentNode->points);
            total_award += currentNode->points;
        } else {
            display_wrong(&currentNode->answer);
        }
        display_total_award(&total_award);

        cout << endl;

        currentNode = currentNode->next;
        currentIndex++;
    }
}

/* Function that asks a question and checks a player’s answer. */
void quiz(TriviaNode * head, int num_of_questions, int &total_award) {
    if (head == NULL) {
        return;
    }  
    else if (num_of_questions < 1) {
        printf("Warning - the number of trivia to be asked must equal to or be larger than 1.\n");
        return;
    } else if (num_of_questions > number_of_questions) {
        printf("Warning - There is only %d trivia in the list.\n", number_of_questions);
        return;
    }

    int currentIndex = 1;
    TriviaNode * currentNode = head;

    total_award = 0;

    #ifdef UNIT_TESTING
        assert(num_of_questions >= 1);
        assert(num_of_questions <= number_of_questions);
    #endif

    while (currentNode != NULL && num_of_questions >= currentIndex)
    {
        #ifdef UNIT_TESTING
            srand(time(NULL));
            int rand_gen = -1;
            if (number_of_questions == num_of_questions) {
                rand_gen = rand() % 100;
                if (rand_gen < 50) {
                    printf("Unit Test Case 3.%d: Ask question number %d in the linked list. The tester enters a incorrect answer.\n", currentIndex, currentIndex);
                } else {
                    printf("Unit Test Case 3.%d: Ask question number %d in the linked list. The tester enters a correct answer.\n", currentIndex, currentIndex);
                }
            }
        #endif

        string user_answer;
        display_question(&currentNode->question);
        input_answer(&user_answer);
        if (currentNode->answer == user_answer) {
            display_correct(&currentNode->points);
            total_award += currentNode->points;

            #ifdef UNIT_TESTING
                if (number_of_questions == num_of_questions && rand_gen < 50) {
                    printf("Unit Test Case 3.%d: Failed.\nTester did not follow instructions or it is logic error\n", currentIndex);
                    assert(false);
                }
            #endif

        } else {
            display_wrong(&currentNode->answer);

            #ifdef UNIT_TESTING
                if (number_of_questions == num_of_questions && rand_gen >= 50) {
                    printf("Unit Test Case 3.%d: Failed.\nTester did not follow instructions or it is logic error\n", currentIndex);
                    assert(false);
                }
            #endif
        }
        display_total_award(&total_award);

        cout << endl;

        #ifdef UNIT_TESTING
            if (number_of_questions == num_of_questions) {
                printf("Case 3.%d passed\n\n", currentIndex);
            }
        #endif

        currentNode = currentNode->next;
        currentIndex++;
    }
}

/* This for local debugging used to test insert() function to display all nodes*/
// Not for assignment
void display(TriviaNode * head) {
    printf("Values of list: ");
    
    TriviaNode * currentNode = head;

    while (currentNode)
    {
        printf("%s \n", currentNode->question.c_str());
        printf("%s \n", currentNode->answer.c_str());
        printf("%d \n\n", currentNode->points);
        currentNode = currentNode->next;
    }
}

void unit_test() {
    TriviaNode * head = new TriviaNode;
    int total_award = 0;

    printf("Unit Test Case 1: Ask no question. The program should give a warning message.\n");
    create_default_questions(head);
    quiz(head, 0, total_award); printf("Case 1 Passed\n\n");
    delete_list(&head);
    
    
    printf("Unit Test Case 2.1: Ask 1 question in the linked list. The tester enters an incorrect answer.\n");
    create_default_questions(head);
    quiz(head, 1, total_award);
    if (total_award != 0) {
        printf("Unit Test Case 2.1: Failed.\nTester did not follow instructions or it is logic error\n");
        assert(false);
    }
    printf("Case 2.1 passed\n\n");
    delete_list(&head);

    
    printf("Unit Test Case 2.2: Ask 1 question in the linked list. The tester enters a correct answer.\n");
    create_default_questions(head);
    quiz(head, 1, total_award);
    if (total_award != 100) {
        printf("Unit Test Case 2.2: Failed.\nTester did not follow instructions or it is logic error\n");
        assert(false);
    }
    printf("Case 2.2 passed\n\n");
    delete_list(&head);


    printf("Unit Test Case 3: Ask all the questions of the last trivia in the linked list.\n");
    create_default_questions(head);
    ask_user_questions(head);
    quiz(head, number_of_questions, total_award);
    delete_list(&head);

    
    printf("Unit Test Case 4: Ask 5 questions in the linked list.\n");
    create_default_questions(head);
    quiz(head, 5, total_award); cout << "Case 4 passed\n\n";
    delete_list(&head);
    printf("*** End of the Debugging Version ***\n");
} 

int main() {
    /**
     * create_default_questions(TriviaNode * head) -> Creates or insertes default questions
     *                                                with given head.
     * 
     * ask_user_questions(TriviaNode * head) -> asks user question from keyboard
     * 
     * quiz(TriviaNode *head, int number_of_questions, int &total_award) -> A function that asks 
     *                                                      a question and checks a player’s answer.
     * 
     * delete_list(TriviaNode ** head) -> empties memory after usage
    */
    TriviaNode * head = NULL;

    #ifdef UNIT_TESTING
        unit_test();
    #else
        int total_award = 0;
        printf("*** Welcome to Kazybek’s trivia quiz game ***\n");
        create_default_questions(head);
        ask_user_questions(head);
        quiz(head, number_of_questions, total_award);
        delete_list(&head);

        // Step 7 Last requirement
        printf("Your total award points is %d\n\n", total_award);

        printf("*** Thank you for playing the trivia quiz game. Goodbye! ***\n");
    #endif
}