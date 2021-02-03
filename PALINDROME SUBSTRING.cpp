#include<bits/stdc++.h>
using namespace std;
int main(){
	int flag;
	string ex;
	int L=0;
	int R=0;
	string s;
	getline(cin,s);
	int n=s.length();
	for(int i=0;i<n;i++){
		for(int j=i;j<n;j++){
			
			if(s[i]==s[j]){
				for(int k=i;k<=j;k++){
					ex.push_back(s[k]);
				}
				for(int k=0;k<ex.length();k++){
					if(ex[k]==ex[ex.length()-k-1])
					flag=1;
					else{
					flag=0;
					break;}
				}
				if(flag==1){
					if((j-i)>=(R-L)){
						L=i;
						R=j;
						ex.clear();
					}
					else{
						ex.clear();
					}
				}
				else if(flag==0){
					ex.clear();
				}	
				
			}
			
			
		}
	}
for(int l=L;l<=R;l++)
cout<<s[l];
}
