    #include<iostream>
    #include<cstdio>
    #include<algorithm>
    #include<vector>
    #include<set>
    #include<map>
    #include<cmath>
    #include<cstring>
    #include<ctime>
    using namespace std;
     
    //end of header files
    #define inf 1000000000
    #define M 1000000007
     
    #define maxn 1000000
    #define maxt 100000
     
    #define FOR(i,a,b) for(int i=(a);i<(b);i++)
    #define MIN(a,b) ((a) < (b) ? (a) : (b))
     
    typedef long long int LL;
     
    //fast input
     
    int scan_d() {int ip=getc(stdin),ret=0;for(;ip<'0'||ip>'9';ip=getc(stdin));for(;ip>='0'&&ip<='9';ip=getc(stdin))ret=ret*10+ip-'0';return ret;}
    long scan_ld() {long ip=getc(stdin),ret=0;for(;ip<'0'||ip>'9';ip=getc(stdin));for(;ip>='0'&&ip<='9';ip=getc(stdin))ret=ret*10+ip-'0';return ret;}
    long long scan_lld() {long long ip=getc(stdin),ret=0;for(;ip<'0'||ip>'9';ip=getc(stdin));for(;ip>='0'&&ip<='9';ip=getc(stdin))ret=ret*10+ip-'0';return ret;}
    long long unsigned scan_llu() {long long unsigned ip=getc(stdin),ret=0;for(;ip<'0'||ip>'9';ip=getc(stdin));for(;ip>='0'&&ip<='9';ip=getc(stdin))ret=ret*10+ip-'0';return ret;}
     
    bool IsPrime(int number) { // just change the comdition here that's It 
     
        if(number<2)return false;
        for (int i = 2; i < number; i++) {
            if (number % i == 0 && i != number)
                return false;

        }
     
        return true;
    }
     
    int findbet(int start, int end, int ar[]){	//this functn finds nos of no betn start
        
        int l = start, h = end+1;	// and end(including) which are prime(general use any)
        return (ar[h] - ar[l]);
     
    }
     
    int main(){

        int ar[10001];
        ar[0] = 0;

        FOR(i,1,500){ //use this to calculate the array 
         
            ar[i+1] = ar[i];

            if(IsPrime(i)){
             
                ar[i+1]++;
         
            }
         
        }
        //find between any two locations!!!
        cout<<"BETWEEN 10 AND 30 Primes Are: "<<findbet(10,30,ar)<<endl;   
        
        FOR(i,0,500)
            cout<<ar[i]<<", ";

        return 0;

    }