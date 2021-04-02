#include<bits/stdc++.h>
using namespace std;
int main(){
	int n;
	cin>>n;
    double a[n];
    for(int i=0;i<n;i++){
    	cin >> a[i];
	}
	sort(a,a+n);
	for(int j=n-1;j>=0;j--){
		if(sqrt(a[j])==(int)sqrt(a[j])){
			cout<<a[j]<<" ";
		}
	}
}
