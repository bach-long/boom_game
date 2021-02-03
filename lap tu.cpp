#include<bits/stdc++.h>
using namespace std;
int main(){
	string s;
	getline(cin,s);
	s=" "+s+" ";
	vector<string> b;
	vector<int> x;
	vector<int> y;
	for(int i=1;i<s.length();i++){
		if(s[i-1]==' '&&s[i]!=' '){
			x.push_back(i);
		}
		else if(s[i-1]!=' '&&s[i]==' '){
			y.push_back(i);
		}
	}
	for(int i=0;i<x.size();i++){
		string a;
		for(int j=x[i];j<y[i];j++){
			a.push_back(s[j]);
		}
		b.push_back(a);
		a.clear();
	}
	vector<string> c;
	int flag;
	c.push_back(b[0]);
	for(int i=0;i<b.size();i++){
		for(int j=0;j<c.size();j++){
			if(b[i]!=c[j])
			flag=1;
			else{
				flag=0;
				break;
			}
		}
		if(flag==1){
			c.push_back(b[i]);
		}
		else
		continue;
	}
	for(int i=0;i<c.size();i++){
		cout<<c[i]<<" ";
	}
}
