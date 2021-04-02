#include<bits/stdc++.h>
using namespace std;
int main(){
   string s;
   unsigned long int n;
   cin>>s;
   cin>>n;
   vector<int> a;
   vector<int> sum;
   vector<int> du;
   
   reverse(s.begin(),s.end());
   for(int i=0;i<s.length();i++){
       a.push_back(s[i]-'0');
   }
   for(int j=0;j<a.size();j++){
       
       if(j==0){
           sum.push_back(n*a[j]%10);
           du.push_back((int)(n*a[j]/10));
       }
       else if(j==a.size()-1){
       	if(n*a[j]+du[j-1]>=10){
           sum.push_back((n*a[j]+du[j-1])%10);
           sum.push_back((int)((n*a[j]+du[j-1])/10));}
        else{
           sum.push_back((n*a[j]+du[j-1])%10);
           du.push_back((int)((n*a[j]+du[j-1])/10));
       }
        
    
   }
       else{
           sum.push_back((n*a[j]+du[j-1])%10);
           du.push_back((int)((n*a[j]+du[j-1])/10));
       }
       
       
   }
   for(int k=sum.size()-1;k>=0;k--){
           cout<<sum[k];
       }
   
    
}


