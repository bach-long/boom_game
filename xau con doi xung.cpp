#include<bits/stdc++.h>
using namespace std;
int main(){
	string s;
	getline(cin,s);
	int n;
	cin>>n;
	int check=0;
	vector<char> b;
	int a[n];
	for(int i=0;i<n;i++){
		cin>>a[i];
	}
	for(int i=0;i<n;i++){
		if(a[i]>s.length()){
			cout<<"invalid"<<endl;
			continue;
		}
		
			for(int j=0;j<=s.length()-a[i];j++){
				for(int k=j;k<a[i]+j;k++){
					b.push_back(s[k]);
				}
				for(int k=0;k<a[i];k++){
					if(b[k]!=b[a[i]-k-1]){
						check=0;
						continue;
					}
					else 
					check++;
					
					b.clear();
				}
				if(check==a[i]||check==a[i]-1){
					cout<<"1"<<endl;
					break;
				}
			}
			if(check==0)
		     cout<<"0"<<endl;
		}
}

