/*
    Project 1
    @author Kazybek Mizam
    @version 08/25/20

    Documentation for Pointers: https://www.tutorialspoint.com/cprogramming/c_pointers.htm
    Documentation for Prototype: http://www.cplusplus.com/articles/yAqpX9L8/
    Documentation for Formatting: https://thispointer.com/c-convert-double-to-string-and-manage-precision-scientific-notation/
*/

#include <iostream>
#include <string>
#include <iomanip>
#include <sstream>

int input(double *, float *, double *);
int calculate(double *, float *, double *, std::string *, int *, double *);
void output(std::string *, int *, double *);
std::string format_precision_2(double);
std::string format_precision_2(float);

int main()
{
    // Declaring input variables
    double loanAmount;
    float interestRate;
    double monthlyPayment;

    // Declaring private use variables
    std::string calculatedOutput = "";
    int months = 0;
    double totalInterest = 0.0;

    short int status_input = input(&loanAmount, &interestRate, &monthlyPayment);

    if (status_input != 0)
    {
        return 0;
    }

    short int status_calculate = calculate(&loanAmount, &interestRate, &monthlyPayment, &calculatedOutput, &months, &totalInterest);

    if (status_calculate == 0)
    {
        output(&calculatedOutput, &months, &totalInterest);
    }
    else
    {
        std::cout << "Your monthly payment is not enough to pay loan.";
    }

    return 0;
}

int input(double *loanAmount, float *interestRate, double *monthlyPayment)
{
    std::cout << "Loan Amount: ";
    std::cin >> *loanAmount;
    while(*loanAmount <= 0) {
        std::cout << "Loan amount should be positive number: ";
        std::cin >> *loanAmount;
    }

    std::cout << "Interest Rate(% per year): ";
    std::cin >> *interestRate;
    while(*interestRate < 0) {
        std::cout << "Interest rate % per year should be non-negative number: ";
        std::cin >> *interestRate;
    }

    std::cout << "Monthly Payments: ";
    std::cin >> *monthlyPayment;
    while(*monthlyPayment <= 0) {
        std::cout << "Monthly payments should be positive number: ";
        std::cin >> *monthlyPayment;
    }

    return 0;
}

int calculate(double *loanAmount, float *interestRate, double *monthlyPayment, std::string *calculatedOutput, int *months, double *totalInterest)
{
    double balance = *loanAmount;
    float monthlyInterestRate = *interestRate / 12;

    for (int month = 0; balance > 0.00; month++)
    {
        double interest = balance * monthlyInterestRate / 100;
        double principial = *monthlyPayment - interest;
        double payment = interest + principial;

        if (principial < 0)
        {
            return 1;
        }

        if (month == 0)
        {
            *calculatedOutput += std::to_string(month) + "\t$" + format_precision_2(balance) + "\tN/A\tN/A\tN/A\t\tN/A\n";
            continue;
        }

        if (balance < principial)
        {
            principial = balance;
        }

        balance -= principial;

        // Formatting issue solving
        if (balance < 1000) {
            *calculatedOutput += std::to_string(month) + "\t$" + format_precision_2(balance) + "\t\t$" + format_precision_2(payment) + "\t$" + format_precision_2(monthlyInterestRate) + "\t$" + format_precision_2(interest) + "\t\t$" + format_precision_2(principial) + "\n";
        } else {
            *calculatedOutput += std::to_string(month) + "\t$" + format_precision_2(balance) + "\t$" + format_precision_2(payment) + "\t$" + format_precision_2(monthlyInterestRate) + "\t$" + format_precision_2(interest) + "\t\t$" + format_precision_2(principial) + "\n";
        }
        (*months)++;
        *totalInterest += interest;
    }

    return 0;
}

void output(std::string *calculatedOutput, int *months, double *totalInterest)
{
    std::cout << "**********************************************************\n";
    std::cout << "\tAmortization Table\n";
    std::cout << "**********************************************************\n";
    std::cout << "Month\tBalance\t\tPayment\tRate\tInterest\tPrincipal\n";
    std::cout << *calculatedOutput;
    std::cout << "**********************************************************\n";
    std::cout << "\n";
    std::cout << "It takes " << *months << " months to pay off the loan.\n";
    std::cout << "Total interest paid is: $" << format_precision_2(*totalInterest);
}

std::string format_precision_2(double number)
{
    // Create an output string stream
    std::ostringstream streamObj;
    // Set Fixed -Point Notation
    streamObj << std::fixed;
    // Set precision to 2 digits
    streamObj << std::setprecision(2);
    //Add double to stream
    streamObj << number;
    // Get string from output string stream
    return streamObj.str();
}

std::string format_precision_2(float number)
{
    // Create an output string stream
    std::ostringstream streamObj;
    // Set Fixed -Point Notation
    streamObj << std::fixed;
    // Set precision to 2 digits
    streamObj << std::setprecision(2);
    //Add double to stream
    streamObj << number;
    // Get string from output string stream
    return streamObj.str();
}