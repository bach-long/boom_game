#include<bits/stdc++.h>
using namespace std;
int main(){
	int n;
	cin>>n;
	int m=n;
	int x=2;
	int a[n][m];
	for(int i=0;i<n;i++){
		for(int j=0;j<m;j++){
			cin>>a[i][j];
		}
	}
	n++;
	for(int i=n-1;i>=2;i--){
		for(int j=0;j<m;j++){
			a[i][j]=a[i-1][j];
		}
	}
	for(int j=0;j<m;j++){
		cin>>a[1][j];
	}
	for(int i=0;i<n;i++){
		for(int j=0;j<m;j++){
			cout<<a[i][j]<<" ";
		}
		cout<<endl;
	}
	
}
