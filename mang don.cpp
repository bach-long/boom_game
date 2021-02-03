#include<bits/stdc++.h>
using namespace std;
int main(){
    int n;
    vector<int> a;
  while(n!=-1){
      cin>>n;
      a.push_back(n);
  }
 vector<int> b;
 for(int i=0;i<a.size();i++){
         b.push_back(a[i]);
 }
 for(int i=0;i<a.size();i++){
 	int flag=0;
     for(int j=0;j<b.size();j++){
     	if(i==j)
     	continue;
     	else if(a[i]==b[j])
     	flag++;
	 }
	 if(flag>0)
	 continue;
	 else 
	 cout<<a[i]<<" ";
 }
}
