#include<iostream>
#include<vector>
using namespace std;
int main(){
	int n;
	vector<int> a;
	a.push_back(1);
	a.push_back(1);
	vector<int> b;
	cin >>n;
	cout<<endl;
	
	if(n==1){
		cout<<"1"<<endl
		<<"1 1";
	}
	
	else if(n>1){
		cout<<"1"<<endl<<"1 1"<<endl;
	for(int i=2;i<=n;i++){
		b=a;
		a.clear();
		for(int j=0;j<=i;j++){
			if(j==0|j==i){
				a.push_back(1);
			}
			else{
				a.push_back(b[j-1]+b[j]);
			}
		}
		for(int k=0;k<=i;k++){
			cout <<a[k]<<" ";
		}
		cout <<endl;
	}
    }
    return 0;
}
