#include<bits/stdc++.h>
using namespace std;
int main(){
	srand(time(0));
	int a[5]={1,2,3,4,5};
	for(int i=0;i<5;i++){
		swap(a[i],a[rand()%4+1]);
	}
	for(int i=0;i<5;i++){
		cout<<a[i];
	}
}
