#include<bits/stdc++.h>
using namespace std;
int main(){
    int n;
    cin>>n;
    int a[n];
    for(int i=0;i<n;i++){
        cin >>a[i];
    }
    int* b=new int[1000];
    for(int j=0;j<100;j++){
        b[j]=0;
    }
    for(int i=0;i<n;i++){
        for(int j=0;j<1000;j++){
            if(j==a[i])
            b[j]++;
        }
    }
    for(int j=0;j<1000;j++){
        if(b[j]!=0)
        cout<<j<<" - "<<b[j]<<"; ";
    }
    delete [] b;


}
