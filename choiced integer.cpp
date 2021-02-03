#include<bits/stdc++.h>
using namespace std;
int main(){
	int flag=1;
	int n;
	cin>>n;
	int a[n][n];
	for(int i=0;i<n;i++){
		for(int j=0;j<n;j++){
			cin>>a[i][j];
		}
	}
	/*if(n==2){
		cout<<max(max(a[0][0],a[0][1]),max(a[1][0],a[1][1]));
	}
	if(n==3){
	int max=a[0][0];
	for(int i=0;i<n;i++){
		for(int j=0;j<n;j++){
			if(a[i][j]>max)
			max=a[i][j];
		}
	}
	if(max==a[0][0]||max==a[0][2]||max==a[1][1]||max==a[2][0]||max==a[2][2])
	cout<<max;
	else
	flag=0;
	}*/
	//if(n>3){
		int save1;
		int save2;
		int max=a[0][0];
		for(int i=0;i<n;i++){
			if(a[i][i]>max){
				max=a[i][i];
				save1=save2=i;
			}
		}
		for(int i=0;i<n;i++){
			if(a[save1][i]>max)
			{
			flag=0;
			break;}
			if(a[i][save1]>max){
				flag=0;
				break;
			}
		}
		while(save1>=0&&save2<n){
			if(a[save1][save2]>max){
				flag=0;
				break;
			}
			save1--;
			save2++;
		}
		while(save1<n&&save2>=0){
			if(a[save1][save2]>max){
				flag=0;
				break;
			}
			save1++;
			save2--;
		}
		if(flag==1)
		cout<<max;
		else if(flag==0)
		cout<<"invalid";
		
		
		
	//}
}

