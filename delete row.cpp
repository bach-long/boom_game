#include<bits/stdc++.h>
using namespace std;
int main(){
	int n;
	cin>>n;
	int m;
	m=n;
	int a[n][m];
	for(int i=0;i<n;i++){
		for(int j=0;j<m;j++){
			cin>>a[i][j];
		}
	}
	int x=2;
	for(int i=2;i<n-1;i++){
		for(int j=0;j<m;j++){
			a[i][j]=a[i+1][j];
		}
	}
	n--;
	for(int i=0;i<n;i++){
		for(int j=0;j<m;j++){
			cout<<a[i][j]<<" ";
		}
		cout<<endl;
	}
}
