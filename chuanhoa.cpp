#include<bits/stdc++.h>
using namespace std;

string lineEncoding(string s)
{   
     string s1="";
         int count=1;
         for(int i=0;i<s.length();i++) {
                    if(s[i]==s[i+1]) count++;
                    if(s[i]!=s[i+1]){
                 if (count!=1)
                           s1.push_back(count+'0');
                 s1.push_back(s[i]);
                 count=1;
                    }
         }
         return s1;
}
int main(){
	string s;
	cin>>s;
	cout<<lineEncoding(s);
}
