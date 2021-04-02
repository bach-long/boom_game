#include<iostream>
#include<vector>
using namespace std;
int main(){
    long int n;
	cin>>n;
	vector<int> a;
	if(n<0){
		cout << "doesn't exist";
	}
	else if(n==0){
		cout<<"0";
	}
	else if(n>0){
		while(n/2!=0){
		a.push_back(n%2);
		n/=2;
		}
		a.push_back(1);
		int b=a.size()-1;
		for(int i=b;i>=0;i--){
			cout<<a[i];
		}
	}
	}

