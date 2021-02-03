#include<bits/stdc++.h>
using namespace std;
int main(){
	int n;
	cin>>n;
	vector<int> x;
	int classes[n][2];
	for(int i=0;i<n;i++){
		for(int j=0;j<2;j++){
			cin>>classes[i][j];
		}
	}
	for(int i=1;i<=24;i++){
		int t=0;
		for(int j=0;j<n;j++){
			if(classes[j][0]<=i&&i<classes[j][1])
			++t;
			}
		x.push_back(t);
	}
	int max=x[0];
	int y;
	for(int i=0;i<x.size();i++){
	    if(max<x[i]){
	    max=x[i];
	    y=i+1;
	   }
	}
	for(int i=0;i<n;i++){
		if(classes[i][0]<=y&&y<classes[i][1])
		cout<<i+1<<" ";
	}
	cout<<endl<<y;
}
