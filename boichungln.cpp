#include<iostream>
#include<math.h>
using namespace std;
int main(){
	long int a,b;
	cin >>a>>b;
	long int d=a*b;
	while(abs(a)!=0){
		if(abs(a)<abs(b)){
		  swap(a,b);
		}
		else{b=abs(b);}
		a=abs(abs(a)-abs(b));
	}
	long int ucln=a+abs(b);
	cout<<ucln<<endl
	    <<d/ucln;
}
