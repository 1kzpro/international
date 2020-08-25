/*
    Project 1
    @author Kazybek Mizam
    @version 08/25/20

    Documentation for Pointers: https://www.tutorialspoint.com/cprogramming/c_pointers.htm
    Documentation for Prototype: http://www.cplusplus.com/articles/yAqpX9L8/
*/

#include <iostream>
#include <string>

void input(double*, float*, double*);
int calculate(double*, float*, double*, std::string*, int*, double*);
void output(std::string*, int*, double*);

int main() {
    // Declaring input variables
    double loanAmount;
    float interestRate;
    double monthlyPayment;

    // Declaring private use variables
    std::string calculatedOutput = "";
    int months = 0;
    double totalInterest = 0.0;

    input(&loanAmount, &interestRate, &monthlyPayment);

    short int status = calculate(&loanAmount, &interestRate, &monthlyPayment, &calculatedOutput, &months, &totalInterest);

    if (status == 0) {
        output(&calculatedOutput, &months, &totalInterest);
    }
    else {
        std::cout << "I am sorry but you are poor(";
    }

    return 0;

}

void input(double *loanAmount, float *interestRate, double *monthlyPayment) {
    std::cout << "Loan Amount: ";
    std::cin >> *loanAmount;
    std::cout << "Interest Rate(% per year): " ;
    std::cin >> *interestRate;
    std::cout << "Monthly Payments: ";
    std::cin >> *monthlyPayment;
}

int calculate(double *loanAmount, float *interestRate, double *monthlyPayment, std::string *calculatedOutput, int* months, double* totalInterest) {
    double balance = *loanAmount;
    float monthlyInterestRate = *interestRate / 12;

    for (int month = 0; balance > 0.00; month++) {
        double interest = balance * monthlyInterestRate / 100;
        double principial = *monthlyPayment - interest;
        double payment = interest + principial;

        if (principial < 0) {
            return 1;
        }

        if (month == 0) {
            *calculatedOutput += std::to_string(month) + "\t$" + std::to_string(balance) + "\tN/A\tN/A\tN/A\tN/A\n";
            continue;
        }

        if (balance < principial) {
            principial = balance;
        }

        balance -= principial;

        *calculatedOutput += std::to_string(month) + "\t$" + std::to_string(balance) + "\t$" + std::to_string(payment) 
            + "\t$" + std::to_string(monthlyInterestRate) + "\t$" + std::to_string(interest) + "\t$" + std::to_string(principial) + "\n";

        (*months)++;
        *totalInterest += interest;
    }

    return 0;
}

void output(std::string *calculatedOutput, int* months, double* totalInterest) {
    std::cout << "**********************************************************\n";
    std::cout << "\tAmortization Table\n";
    std::cout << "Month\tBalance\tPayment\tRate\tInterest\tPrincipal\n";
    std::cout << "**********************************************************\n";
    std::cout << *calculatedOutput;
    std::cout << "**********************************************************\n";
    std::cout << "\n";
    std::cout << "It takes " << *months << " months to pay off the loan.\n";
    std::cout << "Total interest paid is: $" << *totalInterest;
}