#include<bits/stdc++.h>
using namespace std;
int main(){
    double n;
    cin>>n;
    int a;
    int b;
    int c;
    int d;
    a=(int)n/36;
    c=(int)((n/36-a)*36/3);
    b=1.0*((n/36-int(n/36))*36/3-c)*3;
    if(b==1||b==0)
    b=0;
    else if(b==2)
    b=1;
    d=b+c;
    if(d%12==0){
    a+=d/12;
    d=0;}
    
    cout<<a<<" "<<b<<" "<<" "<<c<<" "<<d;
    

}
