#include<iostream>
#include<vector>
using namespace std;
int main(){
	vector<int> arr;
	int check=1;
	int n;
	cin >> n;
	int a[n];
	for(int i=0;i<n;i++){
		cin >> a[i];
		arr.push_back(a[i]);
	}
    while(check==1){
    for(int j=1;j<=arr.size()-2;j++){
    	if(arr[j]<arr[j+1]&&arr[j]<arr[j-1]){
    		arr.erase(arr.begin()+j);
    		check =1;
    		break;
		}	
		else{
			check=0;
			continue;
		}
	}
}
	for(int k=0;k<arr.size();k++){
		cout << arr[k];
}
}
	

