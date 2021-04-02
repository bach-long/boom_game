#include<bits/stdc++.h>
using namespace std;
int main(){
     int x,y,check=1;
     cin >>x>>y;
     int a[x],b[y];
     vector<int> a1;
     vector<int> b1;
     vector<int> c;
     for(int i=0;i<x;i++){
     	cin>>a[i];
     	a1.push_back(a[i]);
	 }
	 for(int j=0;j<y;j++){
	 	cin>>b[j];
	 	b1.push_back(b[j]);
	 }
	 for(int k=0;k<x;k++){
	 	for(int l=k;l<k+y;l++){
	 		c.push_back(a[l]);
		 }
		 if(c==b1){
	 			check=1;
	 			break;
			 }
		else{
			 	check=0;
			 	c.clear();
			 }
	 }
	 if(check==1)
	 	cout<<"Yes";
	 else if(check==0);
	 cout<<"No";
	 
	 
}
