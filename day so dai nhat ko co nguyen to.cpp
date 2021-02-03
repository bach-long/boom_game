#include<iostream>
#include<math.h>
#include<vector>
using namespace std;
int main(){
     int n;
     int c,d;
     cin >> n;
     vector<int> a;
     int exam=1;
     for(int i=2;i<=n;i++){
        for(int j=2;j<=sqrt(i);j++){
        if(i%j!=0){
            exam=1;
        }
        else{
           exam=0;
           break;
        }
        }
        if(exam==1){
            a.push_back(i);
        }
     }
     int b=a[1]-a[0];
     for(int k=1;k<a.size();k++){
         if((a[k]-a[k-1])>b){
             b=a[k]-a[k-1];
             c=a[k];
             d=a[k-1];
         }
     }
            if(n-a[a.size()-1]-1<c-1-d-1){
                for(int l=d+1;l<=c-1;l++){
                    cout<<l<<" ";
                }
            }
            else if(c-1-d-1<n-a[a.size()-1]-1){
                for(int l=a[a.size()-1]+1;l<=n;l++){
                    cout<<l<<" ";
                }
            }

}
