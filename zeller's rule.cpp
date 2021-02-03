#include<iostream>
using namespace std;
int main(){
	int d,m,y;
	cin >> d>>m>>y;
	int M;
	int Y;
	int C;
	if(m==1|m==2){
		M=10+m;
		Y=(y-1)%100;
		C=(int)((y-1)/100);
	}
	else{
		M=m-2;
		Y=y%100;
		C=(int)(y/100);
	}

	int x=(d+((13*M-1)/5)+Y+(int)(Y/4)+(int)(C/4)-2*C)%7;
	switch(x){
case 0:
    cout<<"SUNDAY";
    break;
case 1:
    cout<<"MONDAY";
    break;
case 2:
    cout<<"TUESDAY";
    break;
case 3:
    cout<<"WEDNESDAY";
    break;
case 4:
    cout<<"THURSDAY";
    break;
case 5:
    cout<<"FRIDAY";
    break;
case 6:
    cout<<"SATURDAY";
    break;

	}


}
