#include<bits/stdc++.h>
using namespace std;
char letterGetIn(){
    char c;
    cin>>c;
    return c;
}
string randomString(){
    string s[10]={"accept","wonderful","understandable","limitless","handsome","beautiful","crocodile","bravery","impossible","reckless"};
     return s[rand()%9+1];
}
void printAnswer(char& s,string &model,string &show,int &chance){
    if (model.find(s)==-1){
        chance++;
        cout<<"fault: "<<chance<<" "<<endl<<"string: "<<show<<endl;
    }
    else{
        for(int i=0;i<model.length();i++){
            if(model[i]==s)
                show[i]=s;
        }
        cout<<"string: "<<show<<endl;
    }
}


int main(){
 char ans;
 cout<<"Here we go!"<<endl;
 srand(time(0));
 int chance=0;
 char s;
 string model=randomString();
 string show;
 for(int i=0;i<model.length();i++){
    show.push_back('_');
 }
 do{
    s=letterGetIn();
    printAnswer(s,model,show,chance);

 }
 while(chance<7&&show.find('_')!=-1);
    if(show.find('_')==-1)
    cout<<endl<<"Congratulations"<<endl<<"You wanna do it again?"<<endl<<cout<<"press Y or N"<<endl;
    else if(show.find('_')!=1&&chance==7)
    cout<<"Answer: "<<model<<endl<<"You Lose"<<endl<<"You wanna do it again?"<<endl<<cout<<"press Y or N"<<endl;
    cin>>ans;
    if(ans=='Y'){
    	cout<<endl<<endl;
    	main();
	}
	else if(ans=='N'){
		cout<<"Bye Bye";
	}
    
}

