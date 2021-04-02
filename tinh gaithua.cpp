#include<bits/stdc++.h>
using namespace std;
int giai_thua(int n){
	if(n==1){
	cout<<"X="<<n<<" at "<<&n<<endl;
	return 1;
}
	else{
		cout<<"X="<<n<<" at "<<&n<<endl;
		return(n*giai_thua(n-1));
	}
}
int main(){
	int n;
	cin>>n;
	int result=giai_thua(n);
	cout<<result<<endl;
}
