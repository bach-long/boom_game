#include<iostream>
#include<math.h>
#include<vector>
using namespace std;
int main(){
	vector<int> a;
	int ex=1;
	int check=1;
	int n;
	cin >>n;
	if(n%2!=0){
		cout<<"-1";
	}
	else{
		for(int i=2;i<=n;i++){
			for(int j=2;j<=sqrt(i);j++){
				if(i%j==0){
					check=0;
					break;
				}
				else if(i%j!=0){
					check=1;
				}
			}
			if(check==1){
				a.push_back(i);
			}		
	}
	    for(int k=0;k<a.size();k++){
	        for(int l=k;l<a.size();l++){
	        	if(a[k]+a[l]==n){
	        		cout<<a[k]<<" "<<a[l];
	        		ex=0;
	        		break;
				}
			}
			if(ex==0){
					break;
				}
		}
}
}
