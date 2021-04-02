#include<bits/stdc++.h>
using namespace std;
struct phanso{
	int tu;
	int mau;
	phanso(){
		tu=0;
		mau=1;
	}
	phanso(int a,int b){
		tu=0;
		mau=0;
	}
	void print(){
		cout<<this->tu<<"/"<<this->mau;
	}
};
     istream& operator>>(istream& in,phanso &a){
     	in>>a.tu;
     	in>>a.mau;
     	return in;
	 }
	 ostream& operator<<(ostream& out,phanso &a){
	 	out<<a.tu;
	 	out<<"/";
	 	out<<a.mau;
	 }
    
    
    
    
     phanso operator+(phanso a,phanso b){
     	phanso kq;
     	kq.tu=a.tu*b.mau+b.tu*a.mau;
     	kq.mau=a.mau*b.mau;
     	return kq;
	 }
	 int main(){
	 	phanso a;
	 	phanso b;
	 	cin>>a>>b;
	 	phanso kq=a+b;
	 	cout<<kq;
	 }
   
