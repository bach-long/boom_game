#include<iostream>
#include<string>
#include<algorithm>
#include<math.h>
using namespace std;
int main(){
	string sum;
	string a,b;
	cin >>a>>b;
	int c=a.length();
	int d=b.length();
	reverse(a.begin(),a.end());
	reverse(b.begin(),b.end());
    if(c<d){
    	for(int i=c;i<d;i++){
    		a.push_back('0');
		}
	}
	else if(c>d){
		for(int i=d;i<c;i++){
			b.push_back('0');
		}
	}
	//cout<<c<<" "<<d;}
	  int tong[a.length()];
	  int du[a.length()];
	for(int i=0;i<a.length();i++){
		if(i==0){
			tong[i]=a[i]+b[i]-'0'-'0';
		}
		else{
			tong[i]=a[i]+b[i]-'0'-'0'+du[i-1];
		}
		du[i]=(int)(tong[i]/10);
	}
	for(int j=0;j<a.length();j++){
	sum.push_back(char(tong[j]%10+'0'));
	}
	if(tong[a.length()-1]>=10){
		sum.push_back('1');
	}
	reverse(sum.begin(),sum.end());
	for(int k=0;k<sum.length();k++){
		cout<<sum[k];
	}
}
