#include<stdio.h>
int main(){
	int n;
	scanf("%d", &n);
	while(n>0){
		int last= n%10;
		n/=10;
		if(last==0){
			continue;
		}
		if(last!=0){
			printf("%d", last);
		}
	}
}
