#include<bits/stdc++.h>
using namespace std;
int main(){
string inputString;
cin>>inputString;
vector<string> a,b;
    int n=inputString.length();
    for(int i=0;i<n;i++){
        for(int j=i;j<n;j++){
            string s;
            for(int k=i;k<=j;k++){
                s.push_back(inputString[k]);
            }
            a.push_back(s);
            s.clear();
        }
    }
    b.push_back(" ");
   
    for(int i=0;i<a.size();i++){
    	 int flag=0;
    	for(int j=0;j<b.size();j++){
    		if(a[i]!=b[j])
    		flag=1;
    		else{
    			flag=0;
    			break;
			}
		}
		if(flag==1)
		b.push_back(a[i]);
	}
	b.erase(b.begin());
	cout<<b.size()<<endl;
	for(int i=0;i<b.size();i++){
		cout<<b[i]<<" ";
	}
}
