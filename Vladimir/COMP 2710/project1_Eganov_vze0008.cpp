/* FILE NAME:project1_Eganov_vze0008.cpp
* AUTHOR: Vladimir Eganov
I looked at project hint for this project
*/
#include <iostream>
using namespace std;
int main(){
    std::cout.setf(ios::fixed); // from project hint
    std::cout.setf(ios::showpoint); // from project hint
    std::cout.precision(2); // from project hint
    float loan;
    std::cout<<"Loan Amount: ";
    std::cin>> loan;
    while (loan <= 0) {
        std::cout<<"Loan can't be negative\n";
        std::cin>> loan;
    }
    float interestRate;
    std::cout<<"Interest Rate (% per year): ";
    std::cin>> interestRate;
    while (interestRate < 0) {
        std::cout<<"Interest Rate can't be negative\n";
        std::cin>> interestRate;
    }
    float payment;
    std::cout<<"Payments: ";
    std::cin>> payment;
    while (payment <= 0) {
        std::cout<<"Payment can't be negative\n";
        std::cin>> payment;
    }
    if (payment <= (loan - payment) * (interestRate / 100 / 12)) {
        std::cout<<"bad situation";
    }
    else {
        std::cout<<"*************************************************************************\n";
        std::cout<<"\tAmortization Table\n";
        std::cout<<"*************************************************************************\n";
        std::cout<<"Month\tBalance\t\tPayment\t\tRate\tInterest\tPrincipal\n";
        std::cout<<"0\t";
        float balance = loan;// = (loan)*(interestRate/100/12 + 1) - payment;
        std::cout<<"$"<<float(loan);
        if (loan < 1000) cout << "\t"; // from handout
        std::cout<<"\tN/A\t\tN/A\tN/A\t\tN/A\n";
        int month = 0;
        float interMon = interestRate / 12 / 100; //variable for month interest rate

        float interest = balance * interMon; // variable for interest

        float principal = payment - (balance*interMon); // variable for principal
        balance = balance*(interMon + 1) - payment;
        float totalPaid = 0;
        while (balance > 0.0){
            totalPaid += interest; // summing up interest
            month++;
            //balance = balance*(interMon + 1) - payment;//formula for next month
            //loan = balance;
            std::cout<<month<<"\t"; // current month
            std::cout<<"$"<<balance; //current balance
            if (balance < 1000) cout << "\t"; //indentation correction
            std::cout<<"\t$"<<payment<<"\t\t"; //payment
            std::cout<<interestRate/12 <<"\t"; //interest rate per month
            std::cout<<"$"<<interest <<"\t"; //interest

            std::cout<<"\t$"<< principal <<"\t"; //principal
            std::cout<<"\n"; //changing line

            interest = balance * interMon; // formula for interest
            principal = (balance*(interMon + 1) > payment? payment : balance*(interMon + 1)) - (balance*interMon); // formula for principal
            balance = balance*(interMon + 1) - payment; // formula for next ballance
        }
        totalPaid += interest;
        std::cout<<++month<<"\t"; // current month
        std::cout<<"$"<<max(balance,float(0.0))<<"\t"; //current balance
        std::cout<<"\t$"<<principal+interest <<"\t\t"; //payment
        std::cout<<interestRate/12 <<"\t"; //interest rate per month
        std::cout<<"$"<<interest <<"\t"; //interest
        std::cout<<"\t$"<< principal <<"\t"; //principal
        std::cout<<"\n";
        std::cout<<"*************************************************************************\n\n";
        std::cout<<"It takes "<<month<<" months to pay off the loan.\n";
        std::cout<<"Total interest paid is "<< totalPaid<<"\n\n";
    }
}
