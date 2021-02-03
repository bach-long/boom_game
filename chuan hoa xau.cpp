#include<iostream>
#include<fstream>
#include<vector>
#include<string>
using namespace std;
int main(){
    string path;
    getline(cin,path);
    ifstream file(path);
    int n;
    file >> n;
    string tempt;
    getline(file,tempt);
    string s;
    for(int i=1;i<=n;i++){
        getline(file,s);
        for(int j=0;j<s.length();j++){
            if(j==0){
            if(s[j]>='a'&&s[j]<='z'){
                s[j]=(char)(s[j]-32);
            }
            }
            else{
                if(s[j]<='z'&&s[j-1]==' '&&s[j]>='a'){
                    s[j]=(char)(s[j]-32);
                }
                else if(s[j]>='A'&&s[j]<='Z'&&s[j-1]==' '){
                    continue;
                }
                else if(s[j]<='Z'&&s[j]>='A'){
                    s[j]=(char)(s[j]+32);
                }
            }
        }
        cout << s<<endl;
        }
        
    }
   
   
    
