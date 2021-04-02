#include<iostream>
using namespace std;
int main(){
    int n;
    cin>>n;
    int a;
    int b;
    int c;
    if(n==0){
        cout<<"1";
    }
    else if(n==1){
        cout<<1;
    }
    else if(n>=2){
        for(int i=2;i<=n;i++){
        if(i==2){
            b=1;
            c=1;
           a=3*b-c;
        }
        else{
            c=b;
            b=a;
            a=3*b-c;}
    }
    cout<<a<<" ";
    }
}
