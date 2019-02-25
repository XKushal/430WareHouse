 #include <iostream>
#include <string>
#include "math.h"

using namespace std;

int main()
{
   int n;
   int x= 2;
   cout<<"Enter a number: ";
   cin>>n;
   
   while (!(n % x==0 || x>=floor(sqrt(n))))
   {
      x =x+1;
   }
   if (n % x==0)
   {
      cout << "n is not a prime number" << endl;   
   }
   else
   {
      cout << "n is a prime number" << endl;   
   }

   return 0;
}
