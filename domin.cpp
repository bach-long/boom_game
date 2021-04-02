#include<bits/stdc++.h>
using namespace std;
int main(){
	int m,n;
	cin>>m>>n;
	char c[m+2][n+2];
	for(int i=0;i<m+2;i++){
		for(int j=0;j<n+2;j++){
			if(i==0||j==0||i==m+1||j==n+1)
			c[i][j]='#';
			else
			cin>>c[i][j];
		}
	}
	for(int i=1;i<m+1;i++){
		for(int j=1;j<n+1;j++){
			if(c[i][j]!='*')
			
			{ int sum=0;
			  for(int k=i-1;k<=i+1;k++){
			  	for(int l=j-1;l<=j+1;l++){
			  		if(c[k][l]=='*')
			  		sum++;
				  }
			  }
			  c[i][j]=(char)(sum+'0');
			}
			
		}
	}
	for(int i=1;i<m+1;i++){
		for(int j=1;j<n+1;j++){
			cout<<c[i][j]<<" ";
		}
		cout<<endl;
	}
}
