
#include<bits/stdc++.h>
using namespace std;
int primeSum(int n)
{   long sum=0;
    bool check[n+1];
    for(int i=0;i<n+1;i++){
        check[i]=0;
    }


    for(int i=2;i<=n;i++){
       if(check[i]==1)
       continue;
       if(i*i>n)
       break;
       else if(check[i]==0&&i*i<=n)
       {
           int j=i;
           while(i*j<=n){
               check[i*j]=1;
               j++;
           }
       }
       
    }
    
    for(int i=2;i<=n;i++){
        if(check[i]==0)
        sum+=i;
    }
    for(int i=0;i<=n;i++){
    	cout<<check[i]<<" ";
	}
	cout<<endl;
    return sum%22082018;

}
int main(){
	int n;
	cin>>n;
	int a=primeSum(n);
	cout<<a;
	
}
