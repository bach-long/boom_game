#include<bits/stdc++.h>
using namespace std;
int main(){
	int n;
	char s[n];
	for(int i=0;i<strlen(s);i++){
		cin>>s[i];
	}
	n++;
	s[n-1]='g';
	for(int i=0;i<strlen(s);i++){
		cout<<s[i];
	}
}
