#include<iostream>
#include<vector>
using namespace std;
int main(){
	int a[8];
	for(int n=0;n<8;n++){
		cin >>a[n];
	}
	vector <int> day1;
	vector <int> day2;
	vector <int> day3;
	int dot1;
	int dot2;
	int b,c;
	int dot3;
	for(int i=0;i<8;i++){
		if(a[i]<a[i+1]&&a[i+1]<a[i+2]){
			day1.push_back(a[i]);
		}
		if(a[i]<a[i+1]&&a[i+1]>=a[i+2]){
		int b=(i+2);
		day1.push_back(a[i]);
		day1.push_back(a[i+1]);
		break;
        }
    }
    if(dot1==b){
    for(int j=dot1;j<8;j++){
    	if(a[j]<a[j+1]&&a[j+1]<a[j+2]){
			day2.push_back(a[j]);
		}
		if(a[j]<a[j+1]&&a[j+1]>=a[j+2]){
		int c=(j+2);
		day2.push_back(a[j]);
		day2.push_back(a[j+1]);
		break;
	    }
    }
}
	if(dot2==c){
    for(int k=dot2;k<8;k++){
    	if(a[k]<a[k+1]&&a[k+1]<a[k+2]){
			day3.push_back(a[k]);
		}
		if(a[k]<a[k+1]&&a[k+1]>=a[k+2]){
		dot3=(k+2);
		day3.push_back(a[k]);
		day3.push_back(a[k+1]);
		break;
	    }
	}
}
      int sum1=day1.size();
      int sum2=day2.size();
      int sum3=day3.size();
      //if(sum1>sum2&&sum1>sum3){
      	for(int m=0;m<sum1;m++){
      		cout<<day1.at(m)<<" "<<endl;
		  }
	  //}
	  //else if(sum2>sum3&&sum2>sum1){
	  	for(int m=0;m<sum2;m++){
	  		cout <<day2.at(m)<<" "<<endl;
	  		
		  }
	  //}
	  //else if(sum3>sum1&&sum3>sum2){
	  	for(int m=0;m<sum3;m++){
	  		cout<<day3.at(m)<<" "<<endl;
		  }
	  //}
	  /*else if(sum1==sum2&sum2==sum3){
	  	cout<<"doesn't exist";
	  }*/
	  
}
