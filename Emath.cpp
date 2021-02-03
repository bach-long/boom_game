#include<bits/stdc++.h>
using namespace std;
int main(){
	string s;
	int x,y;
	getline(cin,s);
	vector<char> a;
	vector<char> b;
	s=" "+s+" ";
	for(int i=0;i<s.length();i++){
		if(s[i-1]==' '&&s[i]!='+')
			a.push_back(s[i]);
	}
	for(int j=0;j<s.length();j++){
		if(s[j+1]==' '&&s[j]!='+')
		    b.push_back(s[j]);
	}
	if(a[0]=='z')
	x=0;
    else if(a[0]=='o')
	x=1;
	else if(a[0]=='t'&&b[0]=='o')
	x=2;
	else if(a[0]=='t'&&b[0]=='e')
	x=3;
	else if(a[0]=='f'&&b[0]=='r')
	x=4;
    else if(a[0]=='f'&&b[0]=='e')
    x=5;
    
    if(a[1]=='z')
	y=0;
	else if(a[1]=='o')
	y=1;
	else if(a[1]=='t'&&b[1]=='o')
	y=2;
	else if(a[1]=='t'&&b[1]=='e')
	y=3;
	else if(a[1]=='f'&&b[1]=='r')
	y=4;
    else if(a[1]=='f'&&b[1]=='e')
    y=5;
    cout<<x+y;

	
}
